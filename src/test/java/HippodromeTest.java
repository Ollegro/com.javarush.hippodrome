import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
public class HippodromeTest {

    @Test
    public void nullException() {
        assertThrows(IllegalArgumentException.class, () -> new Hippodrome(null));
    }

    @Test
    public void nullMessage() {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(null));
        assertEquals("Horses cannot be null.", e.getMessage());
    }

    @Test
    public void emptyException() {
        assertThrows(IllegalArgumentException.class, () -> new Hippodrome(new ArrayList<>()));
    }

    @Test
    public void emptyMessage() {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(new ArrayList<>()));
        assertEquals("Horses cannot be empty.", e.getMessage());
    }

    @Test
    public void getHorses() {
        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            horses.add(new Horse(String.valueOf("name_" + i), i, i));
        }
        Hippodrome hippodrome = new Hippodrome(horses);
        List<Horse> result = hippodrome.getHorses();
        assertEquals(30, result.size());
    }
    @Test
    public void move() {
        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            horses.add(mock(Horse.class));
        }
        new Hippodrome(horses).move();

        for (Horse e : horses) {
            Mockito.verify(e).move();
        }
    }
    @Test
    public void getWinner() {
        Horse horse1 = new Horse("name1",1,5.99);
        Horse horse2 = new Horse("name2",1,5);
        Horse horse3 = new Horse("name3",1,4.99);
        Horse horse4 = new Horse("name4",1,3);
        Horse horse5 = new Horse("name5",1,1.99);
        Hippodrome hippodrome = new Hippodrome(List.of(horse1,horse2,horse3,horse4,horse5));
        assertSame(horse1,hippodrome.getWinner());
    }
}

