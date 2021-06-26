package Tests;

import PresentationLayer.GamePresentation;
import BussinesLayer.GameInitializer;
import BussinesLayer.GameManager;
import BussinesLayer.Position;
import BussinesLayer.Tiles.Enemies.Enemy;
import BussinesLayer.Tiles.Enemies.Monster;
import BussinesLayer.Tiles.Player.Mage;
import BussinesLayer.Util.MessageCallback;
import BussinesLayer.Util.ProgressCallback;
import BussinesLayer.Util.Tuple;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MageTest {

    Mage mage;

    @BeforeEach
    void setUp() {
        mage = new Mage("Melisandre", 100, 5, 1, 300, 30, 15, 5, 6);
    }

    @Test
    void castAbility() {
        Monster m1 = new Monster('s', "Lannister's Nitzan", 80, 8, 3,25, 3);
        m1.initialize(new Position(2,2));
        Monster m2 = new Monster('s', "Lannister's Nitzan", 80, 8, 3,25, 3);
        m2.initialize(new Position(2,7));
        mage.initialize(new Position(0,0));
        List<Tuple<Enemy, Double>> l = new ArrayList<Tuple<Enemy, Double>>();
        l.add(new Tuple<Enemy, Double>(m1, 5.0));
        l.add(new Tuple<Enemy, Double>(m2, 7.0));
        GameInitializer gi = new GameInitializer("");
        GamePresentation gp = new GamePresentation();
        ProgressCallback pc = new ProgressCallback(gi);
        GameManager gm = new GameManager(mage,pc, gp);
        MessageCallback msg = new MessageCallback(gp);
        mage.setMessageCallback(msg);
        m1.setMessageCallback(msg);
        m2.setMessageCallback(msg);
        mage.getEnemyRange(l);
        mage.castAbility();
        assertTrue(m1.health.getAmount() < m1.health.getPool());
        assertTrue(m2.health.getAmount() == m2.health.getPool());
    }

    @Test
    void onTurn() {
        assertEquals(75, mage.currentMana);
        mage.onTurn();
        assertEquals(76, mage.currentMana);
        mage.onTurn();
        assertEquals(77, mage.currentMana);
        mage.onTurn();
        assertEquals(78, mage.currentMana);
    }

}