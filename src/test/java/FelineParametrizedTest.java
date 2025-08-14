import com.example.*;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assertions.*;

class FelineParametrizedTest {

    private final Feline feline = new Feline();

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 5, 10})
    void getKittensReturnsCorrectValue(int kittensCount) {
        assertEquals(kittensCount, feline.getKittens(kittensCount));
    }

    @ParameterizedTest
    @CsvSource({
            "Самец, true",
            "Самка, false"
    })
    void lionSexTest(String sex, boolean expectedMane) throws Exception {
        Lion lion = new Lion(sex, feline);
        assertEquals(expectedMane, lion.doesHaveMane());
    }
}