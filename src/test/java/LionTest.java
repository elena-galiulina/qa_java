import com.example.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class LionTest {
    @Mock
    Family felidae;
    @Mock
    Predator predator;
    private Lion lion;

    @ParameterizedTest
    @CsvSource({
            "Самец, true",
            "Самка, false"
    })
    void constructorValidSexSetsManeCorrectly(String sex, boolean expectedMane) throws Exception {
        Lion lion = new Lion(sex, felidae, predator);
        boolean actual = lion.doesHaveMane();
        assertEquals(expectedMane, actual);
    }
    @ParameterizedTest
    @ValueSource(booleans = {true, false})
    void doesHaveManeReturnsCorrectValue(boolean maneValue) throws Exception {
        Lion lion = mock(Lion.class);
        when(lion.doesHaveMane()).thenReturn(maneValue);
        assertEquals(maneValue, lion.doesHaveMane());
    }

    @Test
    void constructorInvalidSexThrowsException() {
        Exception exception = assertThrows(Exception.class, () ->
                new Lion("НеверныйПол", felidae, predator));
        assertEquals("Используйте допустимые значения пола животного - самец или самка",
                exception.getMessage());
    }

    @Test
    void getKittensReturnsFromFelidae() throws Exception {
        when(felidae.getKittens()).thenReturn(3);
        Lion lion = new Lion("Самец", felidae, predator);
        assertEquals(3, lion.getKittens());
    }

    @Test
    void getFoodReturnsFromPredator() throws Exception {
        List<String> expectedFood = List.of("Животные", "Птицы", "Рыба");
        when(predator.eatMeat()).thenReturn(expectedFood);
        Lion lion = new Lion("Самец", felidae, predator);
        assertEquals(expectedFood, lion.getFood());
    }

    @Test
    void getFoodThrowsException() throws Exception {
        when(predator.eatMeat()).thenThrow(new Exception("Test exception"));
        Lion lion = new Lion("Самец", felidae, predator);
        Exception exception = assertThrows(Exception.class, lion::getFood);
        assertEquals("Test exception", exception.getMessage());
    }

}
