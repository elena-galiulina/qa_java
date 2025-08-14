import com.example.*;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class FelineTest {

    private final Feline feline = new Feline();

    @Test
    void getKittensReturnsOne() {
        assertEquals(1, feline.getKittens());
    }

    @Test
    void eatMeatReturnsCorrectFood() throws Exception {
        List<String> food = feline.eatMeat();
        assertEquals(3, food.size());
    }

    @Test
    void eatMeatThrowsException() {
        Feline felineWithException = new Feline() {
            @Override
            public List<String> getFood(String animalKind) throws Exception {
                throw new Exception("Test exception");
            }
        };

        Exception exception = assertThrows(Exception.class, felineWithException::eatMeat);
        assertEquals("Test exception", exception.getMessage());
    }

    @Test
    void getFamilyReturnsCorrectFamily() {
        assertEquals("Кошачьи", feline.getFamily());
    }

    @Test
    void getFoodWithValidPredatorReturnsCorrectList() throws Exception {
        List<String> food = feline.getFood("Хищник");
        assertEquals(3, food.size());
    }

    @Test
    void getFoodWithInvalidKindThrowsException() {
        Feline feline = new Feline();
        Exception exception = assertThrows(Exception.class, () ->
                feline.getFood("НеверныйВид"));
        assertEquals("Неизвестный вид животного, используйте значение Травоядное или Хищник",
                exception.getMessage());
    }
}