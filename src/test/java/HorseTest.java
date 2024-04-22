import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mockStatic;

class HorseTest {

    @Test
    void nullNameException() {
        assertThrows(IllegalArgumentException.class, () -> new Horse(null, 1, 1));
    }

    @Test
    void nullNameMessage() {
        try {
            new Horse(null, 1, 1);
            fail("expected");
        } catch (IllegalArgumentException e) {
            assertEquals("Name cannot be null.", e.getMessage());
        }
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "  ", "\t\t", "\n\n\n\n\n\n\n"})
    void blankNameMessage(String name) {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> new Horse(name, 1, 1));
        assertEquals("Name cannot be blank.", e.getMessage());
    }

    @Test
    void negativeSpeedException() {
        assertThrows(IllegalArgumentException.class, () -> new Horse("name", -1, 1));
    }

    @Test
    void negativeSpeedExceptionName() {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> new Horse("name", -1, 1));
        assertEquals("Speed cannot be negative.", e.getMessage());
    }

    @Test
    void negativeDistanceException() {
        assertThrows(IllegalArgumentException.class, () -> new Horse("name", 1, -1));
    }

    @Test
    void negativeDistanceExceptionName() {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> new Horse("name", 1, -1));
        assertEquals("Distance cannot be negative.", e.getMessage());
    }

    @Test
    void messageFromNameField() {            // проверяем поле которое принял конструктор = переданнному в конструктор
        Horse horse = new Horse("testname", 1, 1);
        try {
            Field name = Horse.class.getDeclaredField("name");
            name.setAccessible(true);
            String nameValue = (String) name.get(horse);
            assertEquals("testname", nameValue);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void messageFromGetName() {
        Horse horse = new Horse("testname", 1, 1);
        String field = horse.getName();
        assertEquals("testname", field);
    }

    @Test
    void getSpeed() {
        double expectedSpeed = 11.5;
        Horse horse = new Horse("testname", expectedSpeed, 1);
        double actualSpeed = horse.getSpeed();
        assertEquals(expectedSpeed, actualSpeed);
    }

    @Test
    void getDistance() {
        double expectedDistance = 11.5;
        Horse horse = new Horse("testname", 1, expectedDistance);
        double actualDistance = horse.getDistance();
        assertEquals(expectedDistance, actualDistance);
    }

    @Test
    void getDistanceWithTwoParameters() {
        Horse horse = new Horse("testname", 1);
        double actualDistance = horse.getDistance();
        assertEquals(0, actualDistance);
    }
    @Test
    void moveUsedGetRandom   () {
        try {
            MockedStatic<Horse> mock = mockStatic(Horse.class);
            new Horse("testname",1,1).move();
            mock.verify(() -> Horse.getRandomDouble(0.2, 0.9));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}



