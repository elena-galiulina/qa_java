import com.example.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AlexTest {

    @Mock
    private Feline feline;

    @Test
    void constructorCreatesMaleLion() throws Exception {
        Alex alex = new Alex(feline);
        assertTrue(alex.doesHaveMane(), "Alex должен быть самцом и иметь гриву");
    }

    @Test
    void getFriendsReturnsCorrectList() throws Exception {
        Alex alex = new Alex(feline);
        List<String> friends = alex.getFriends();
        assertEquals(3, friends.size(), "У Алекса должно быть 3 друга");
    }

    @Test
    void getPlaceOfLivingReturnsNewYorkZoo() throws Exception {
        Alex alex = new Alex(feline);
        assertEquals("Нью-Йоркский зоопарк", alex.getPlaceOfLiving(),
                "Местом проживания должен быть Нью-Йоркский зоопарк");
    }

    @Test
    void getKittensReturnsZero() throws Exception {
        Alex alex = new Alex(feline);
        assertEquals(0, alex.getKittens(), "У Алекса нет львят");
    }

    @Test
    void getFoodReturnsFromPredator() throws Exception {
        List<String> expectedFood = List.of("Животные", "Птицы", "Рыба");
        when(feline.eatMeat()).thenReturn(expectedFood);
        Alex alex = new Alex(feline);
        assertEquals(expectedFood, alex.getFood(), "Еда должна совпадать с тем, что возвращает Predator");
    }

    @Test
    void doesHaveManeReturnsTrue() throws Exception {
        Alex alex = new Alex(feline);
        assertTrue(alex.doesHaveMane(), "Alex должен иметь гриву, так как он самец");
    }

    @Test
    void getFriendsListContainsOnlyExpectedFriends() throws Exception {
        Alex alex = new Alex(feline);
        List<String> friends = alex.getFriends();
        assertEquals(Arrays.asList("Марти", "Глория", "Мелман"), friends);
    }
}