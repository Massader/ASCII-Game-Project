package Tests;

import PresentationLayer.GamePresentation;
import BussinesLayer.GameInitializer;
import BussinesLayer.GameManager;
import BussinesLayer.Position;
import BussinesLayer.Tiles.Enemies.Monster;
import BussinesLayer.Tiles.Player.Player;
import BussinesLayer.Tiles.Player.Warrior;
import BussinesLayer.Util.MessageCallback;
import BussinesLayer.Util.ProgressCallback;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MonsterTest {

    Monster m;
    Player p;

    @BeforeEach
    void setUp() {
        m = new Monster('n', "Nitzan Lannister", 80, 8, 3,25, 5);
        GameInitializer gi = new GameInitializer("");
        ProgressCallback pc = new ProgressCallback(gi);
        GamePresentation gp = new GamePresentation();
        GameManager gm = new GameManager(p, pc, gp);
        MessageCallback msg = new MessageCallback(gp);
        p = new Warrior("Roei Stark", 330330, 33340, 4323, 3);
        p.setMessageCallback(msg);
        m.setMessageCallback(msg);
        Position pos2 = new Position(3,0);
        p.initialize(pos2);
    }

    @Test
    void makeTurn() {
        m.initialize(new Position(0,1));
        assertEquals(100, m.makeTurn(p));
        m.initialize(new Position(1,1));
        assertEquals(100, m.makeTurn(p));
        m.initialize(new Position(2,1));
        assertEquals(119, m.makeTurn(p));
        m.initialize(new Position(2,0));
        assertEquals(100, m.makeTurn(p));
    }
}