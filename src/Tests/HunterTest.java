package Tests;

import PresentationLayer.GamePresentation;
import BussinesLayer.GameInitializer;
import BussinesLayer.GameManager;
import BussinesLayer.Position;
import BussinesLayer.Tiles.Enemies.Enemy;
import BussinesLayer.Tiles.Enemies.Monster;
import BussinesLayer.Tiles.Player.Hunter;
import BussinesLayer.Util.MessageCallback;
import BussinesLayer.Util.ProgressCallback;
import BussinesLayer.Util.Tuple;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HunterTest {

    Hunter hunter;

    @BeforeEach
    void setUp() {
        hunter = new Hunter("Roeitte", 220, 30, 2, 6);
    }

    @Test
    void levelUp() {
        hunter.arrowsCount = 0;
        hunter.health.takeDamage(10);
        hunter.levelUp();
        assertEquals(240, hunter.health.getPool());
        assertEquals(240, hunter.health.getAmount());
        assertEquals(20, hunter.arrowsCount);
        assertEquals(42, hunter.attackPoints);
        assertEquals(6, hunter.defensePoints);
    }

    @Test
    void addExperience() {
        hunter.addExperience(100);
        assertEquals(2, hunter.getLevel());
        assertEquals(240, hunter.health.getPool());
        assertEquals(240, hunter.health.getAmount());
        assertEquals(30, hunter.arrowsCount);
        assertEquals(42, hunter.attackPoints);
        assertEquals(6, hunter.defensePoints);
    }

    @Test
    void castAbility() {
        Monster m = new Monster('s', "Lannister's Nitzan", 80, 8, 3,25, 3);
        m.initialize(new Position(2,2));
        hunter.initialize(new Position(0,0));
        List<Tuple<Enemy, Double>> l = new ArrayList<Tuple<Enemy, Double>>();
        l.add(new Tuple<Enemy, Double>(m, 5.0));
        GameInitializer gi = new GameInitializer("");
        ProgressCallback pc = new ProgressCallback(gi);
        GamePresentation gp = new GamePresentation();
        GameManager gm = new GameManager(hunter,pc, gp);
        MessageCallback msg = new MessageCallback(gp);
        hunter.setMessageCallback(msg);
        m.setMessageCallback(msg);
        hunter.getEnemyRange(l);
        hunter.castAbility();
        assertTrue(m.health.getAmount() < m.health.getPool());
        assertEquals(9, hunter.arrowsCount);
    }

    @Test
    void onTurn() {
        assertEquals(0, hunter.getTicksCount());
        assertEquals(10, hunter.getArrowsCount());
        hunter.onTurn();
        assertEquals(1, hunter.getTicksCount());
        hunter.ticksCount = 9;
        assertEquals(10, hunter.getArrowsCount());
        hunter.onTurn();
        assertEquals(11, hunter.getArrowsCount());
        assertEquals(0, hunter.getTicksCount());
    }
}