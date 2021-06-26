package Tests;

import BussinesLayer.Health;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HealthTest {

    Health health;

    @BeforeEach
    void setUp() {
        health = new Health(100);
    }

    @Test
    void heal() {
        health.heal(10);
        assertEquals(100, health.getAmount()); //expected not to changed
        health.setAmount(50);
        health.heal(25);
        assertEquals(75, health.getAmount());
    }

    @Test
    void takeDamage() {
        assertFalse(health.takeDamage(10));
        assertEquals(90, health.getAmount());
        assertTrue(health.takeDamage(100));
        assertEquals(0, health.getAmount());
    }
}