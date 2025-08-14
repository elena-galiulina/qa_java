import com.example.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CatTest {

    @Mock
    private Feline feline; // Используем Feline вместо Predator

    @Test
    void getSoundReturnsMeow() {
        Cat cat = new Cat(feline);
        assertEquals("Мяу", cat.getSound());
    }

    @Test
    void getFoodReturnsFromPredator() throws Exception {
        List<String> expectedFood = List.of("Животные", "Птицы", "Рыба");
        when(feline.eatMeat()).thenReturn(expectedFood);
        Cat cat = new Cat(feline);
        assertEquals(expectedFood, cat.getFood());
    }

    @Test
    void getFoodThrowsException() throws Exception {
        when(feline.eatMeat()).thenThrow(new Exception("Test exception"));
        Cat cat = new Cat(feline);
        Exception exception = assertThrows(Exception.class, cat::getFood);
        assertEquals("Test exception", exception.getMessage());
    }

    @Test
    void constructorInitializesPredator() {
        Feline testFeline = mock(Feline.class);
        Cat cat = new Cat(testFeline);
        try {
            java.lang.reflect.Field predatorField = Cat.class.getDeclaredField("predator");
            predatorField.setAccessible(true);
            assertEquals(testFeline, predatorField.get(cat));
        } catch (Exception e) {
            fail("Failed to access private field: " + e.getMessage());
        }
    }
}