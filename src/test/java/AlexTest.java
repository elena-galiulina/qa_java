import com.example.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AlexTest {

    @Mock
    private Family felidae;

    @Mock
    private Predator predator;

    @Test
    void constructorCreatesMaleLion() throws Exception {
        Alex alex = new Alex(felidae, predator);
        assertTrue(alex.doesHaveMane(), "Alex должен быть самцом и иметь гриву");
    }

    @Test
    void getFriendsReturnsCorrectList() throws Exception {
        Alex alex = new Alex(felidae, predator);
        List<String> friends = alex.getFriends();
        assertEquals(3, friends.size(), "У Алекса должно быть 3 друга");
        assertTrue(friends.contains("Марти"), "Должен содержать Марти");
        assertTrue(friends.contains("Глория"), "Должен содержать Глорию");
        assertTrue(friends.contains("Мелман"), "Должен содержать Мелмана");
    }

    @Test
    void getPlaceOfLivingReturnsNewYorkZoo() throws Exception {
        Alex alex = new Alex(felidae, predator);
        assertEquals("Нью-Йоркский зоопарк", alex.getPlaceOfLiving(),
                "Местом проживания должен быть Нью-Йоркский зоопарк");
    }

    @Test
    void getKittensReturnsZero() throws Exception {
        Alex alex = new Alex(felidae, predator);
        assertEquals(0, alex.getKittens(), "У Алекса нет львят");
    }

    @Test
    void getFoodReturnsFromPredator() throws Exception {
        List<String> expectedFood = List.of("Животные", "Птицы", "Рыба");
        when(predator.eatMeat()).thenReturn(expectedFood);
        Alex alex = new Alex(felidae, predator);
        assertEquals(expectedFood, alex.getFood(), "Еда должна совпадать с тем, что возвращает Predator");
    }

    @Test
    void doesHaveManeReturnsTrue() throws Exception {
        Alex alex = new Alex(felidae, predator);
        assertTrue(alex.doesHaveMane(), "Alex должен иметь гриву, так как он самец");
    }

    @Test
    void getFriendsListContainsOnlyExpectedFriends() throws Exception {
        Alex alex = new Alex(felidae, predator);
        List<String> friends = alex.getFriends();
        assertEquals(3, friends.size());
        assertAll("Проверка списка друзей",
                () -> assertTrue(friends.contains("Марти")),
                () -> assertTrue(friends.contains("Глория")),
                () -> assertTrue(friends.contains("Мелман")),
                () -> assertFalse(friends.contains("Алекс")),
                () -> assertFalse(friends.contains("Кинг-Джулиан"))
        );
    }
}