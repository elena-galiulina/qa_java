import com.example.*;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class LionParametrizedTest {
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
}