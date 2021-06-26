package Tests;

import PresentationLayer.GamePresentation;
import BussinesLayer.GameInitializer;
import BussinesLayer.GameManager;
import BussinesLayer.Position;
import BussinesLayer.Tiles.Enemies.Enemy;
import BussinesLayer.Tiles.Enemies.Monster;
import BussinesLayer.Tiles.Player.Rogue;
import BussinesLayer.Util.MessageCallback;
import BussinesLayer.Util.ProgressCallback;
import BussinesLayer.Util.Tuple;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RogueTest {

    Rogue rogue;

    @BeforeEach
    void setUp() {
        rogue = new Rogue("Arya Stark", 150, 40, 2, 20);
    }

    @Test
    void castAbility() {
        Monster m1 = new Monster('s', "Lannister's Nitzan", 80, 8, 3,25, 3);
        Monster m2 = new Monster('s', "Lannister's Nitzan", 80, 8, 3,25, 3);
        Monster m3 = new Monster('s', "Lannister's Nitzan", 80, 8, 3,25, 3);
        m1.initialize(new Position(1,1));
        m2.initialize(new Position(2,7));
        m3.initialize(new Position(1,0));

        rogue.initialize(new Position(0,0));
        List<Tuple<Enemy, Double>> l = new ArrayList<Tuple<Enemy, Double>>();

        l.add(new Tuple<Enemy, Double>(m1, 1.0)); //in range
        l.add(new Tuple<Enemy, Double>(m2, 3.0)); //outside range
        l.add(new Tuple<Enemy, Double>(m3, 1.5)); //in range

        GameInitializer gi = new GameInitializer("");
        ProgressCallback pc = new ProgressCallback(gi);
        GamePresentation gp = new GamePresentation();
        GameManager gm = new GameManager(rogue, pc, gp);
        MessageCallback msg = new MessageCallback(gp);
        rogue.setMessageCallback(msg);
        m1.setMessageCallback(msg);
        m2.setMessageCallback(msg);
        m3.setMessageCallback(msg);

        rogue.getEnemyRange(l);
        rogue.castAbility();

        assertTrue(m1.health.getAmount() < m1.health.getPool());
        assertTrue(m2.health.getAmount() == m2.health.getPool());
        assertTrue(m3.health.getAmount() < m3.health.getPool());
        assertEquals(80,rogue.currentEnergy);
    }

    @Test
    void onTurn() {
        rogue.currentEnergy = 50;
        assertEquals(50, rogue.currentEnergy);
        rogue.onTurn();
        assertEquals(60, rogue.currentEnergy);
        rogue.levelUp();
        rogue.onTurn();
        assertEquals(100, rogue.currentEnergy);
    }

}