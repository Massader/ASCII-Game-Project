package Tests;

import PresentationLayer.GamePresentation;
import BussinesLayer.GameInitializer;
import BussinesLayer.GameManager;
import BussinesLayer.Position;
import BussinesLayer.Tiles.Player.Player;
import BussinesLayer.Tiles.Player.Warrior;
import BussinesLayer.Util.MessageCallback;
import BussinesLayer.Util.ProgressCallback;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import BussinesLayer.Tiles.Enemies.Boss;

import static org.junit.jupiter.api.Assertions.*;

class BossTest {

    Boss boss;
    Player p;

    @BeforeEach
    void setUp() {
        boss = new Boss('K', "Night's Nitzan", 500, 30, 150, 5, 8, 3);
        Position pos1 = new Position(0,0);
        boss.initialize(pos1);
        GameInitializer gi = new GameInitializer("");
        ProgressCallback pc = new ProgressCallback(gi);
        GamePresentation gp = new GamePresentation();
        GameManager gm = new GameManager(p,pc, gp);
        MessageCallback msg = new MessageCallback(gp);
        p = new Warrior("Roei Snow", 3300, 3330, 433, 3);
        p.setMessageCallback(msg);
        boss.setMessageCallback(msg);
        Position pos2 = new Position(3,0);
        p.initialize(pos2);
    }


    @Test
    void makeTurn() {
        boss.combatTicks = boss.abilityFrequency;
        assertEquals(101, boss.makeTurn(p));
        assertEquals(100, boss.makeTurn(p));
    }
}