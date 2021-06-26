package Tests;

import BussinesLayer.Util.EnemyDeathCallback;
import BussinesLayer.Util.MessageCallback;
import BussinesLayer.Util.ProgressCallback;
import BussinesLayer.Util.Tuple;
import PresentationLayer.GamePresentation;
import BussinesLayer.GameInitializer;
import BussinesLayer.GameManager;
import BussinesLayer.Position;
import BussinesLayer.Tiles.Enemies.Enemy;
import BussinesLayer.Tiles.Enemies.Monster;
import BussinesLayer.Tiles.Player.Warrior;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class WarriorTest {

    Warrior warrior;

    @BeforeEach
    void setUp() {
        warrior = new Warrior("Jon Snow", 300, 30, 4, 3);
    }

    @Test
    void castAbility() {
        Monster m1 = new Monster('s', "Lannister's Nitzan", 900, 8, 3,25, 3);
        m1.initialize(new Position(2,2));

        warrior.initialize(new Position(0,0));
        List<Tuple<Enemy, Double>> l = new ArrayList<Tuple<Enemy, Double>>();
        l.add(new Tuple<Enemy, Double>(m1, 2.0));

        GameInitializer gi = new GameInitializer("");
        ProgressCallback pc = new ProgressCallback(gi);
        GamePresentation gp = new GamePresentation();
        GameManager gm = new GameManager(warrior, pc, gp);
        MessageCallback msg = new MessageCallback(gp);
        warrior.setMessageCallback(msg);
        m1.setMessageCallback(msg);
        warrior.getEnemyRange(l);
        warrior.castAbility();

        assertTrue(m1.health.getAmount() < m1.health.getPool());
        warrior.remainingCooldown = 0;
        Monster m2 = new Monster('s', "Lannister's Nitzan", 800, 8, 3,25, 3);
        m2.initialize(new Position(2,7));
        l.add(new Tuple<Enemy, Double>(m2, 7.0));
        l.remove(new Tuple<Enemy, Double>(m1, 2.0));
        m2.setMessageCallback(msg);
        m2.setDeathCallback(new EnemyDeathCallback(m2));
        warrior.getEnemyRange(l);
        warrior.castAbility();
        assertTrue(m2.health.getAmount() == m2.health.getPool());
        assertEquals(warrior.abilityCooldown+1, warrior.remainingCooldown);
        l.remove(new Tuple<Enemy, Double>(m2, 7.0));
        warrior.getEnemyRange(l);
        warrior.takeDamage(1);
        warrior.remainingCooldown = 0;

        warrior.castAbility();
        assertTrue(warrior.health.getAmount() == warrior.health.getPool());
    }

    @Test
    void onTurn() {
        assertTrue(warrior.remainingCooldown == 0);
        warrior.onTurn();
        assertTrue(warrior.remainingCooldown == 0);
        warrior.initialize(new Position(0,0));
        warrior.setMessageCallback(new MessageCallback(new GamePresentation()));
        List<Tuple<Enemy, Double>> l = new ArrayList<Tuple<Enemy, Double>>();
        warrior.getEnemyRange(l);
        warrior.castAbility();
        warrior.onTurn();
        assertEquals(warrior.abilityCooldown, warrior.remainingCooldown);
    }
}