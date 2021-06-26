package Tests;

import BussinesLayer.Position;
import BussinesLayer.Tiles.Enemies.Monster;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PositionTest {

    Position pos1;
    Position pos2;
    Position pos3;

    @BeforeEach
    void setUp() {
        pos1 = new Position(0,0);
        pos2 = new Position(1,1);
        pos3 = new Position(2,0);
    }

    @Test
    void range() {
        Monster m = new Monster('n', "Nitzan Lannister", 80, 8, 3,25, 5);
        m.initialize(pos2);
        assertTrue(pos1.range(m) == Math.sqrt(2));
        m.initialize(pos3);
        assertTrue(pos1.range(m) == 2);
        assertTrue(pos2.range(m) == Math.sqrt(2));
    }
}