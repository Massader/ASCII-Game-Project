package Tests;

import PresentationLayer.GamePresentation;
import BussinesLayer.GameInitializer;
import BussinesLayer.GameManager;
import BussinesLayer.Position;
import BussinesLayer.Tiles.Enemies.Trap;
import BussinesLayer.Tiles.Player.Player;
import BussinesLayer.Tiles.Player.Warrior;
import BussinesLayer.Util.MessageCallback;
import BussinesLayer.Util.ProgressCallback;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TrapTest {

    Trap t;
    Player p;

    @BeforeEach
    void setUp() {
        t = new Trap('B', "Bonus Trap", 1, 1, 1, 250,  1, 5);
        GameInitializer gi = new GameInitializer("");
        p = new Warrior("Roei Stark", 330330, 33340, 4323, 3);
        ProgressCallback pc = new ProgressCallback(gi);
        GamePresentation gp = new GamePresentation();
        GameManager gm = new GameManager(p,pc, gp);
        MessageCallback msg = new MessageCallback(gp);
        p.setMessageCallback(msg);
        t.setMessageCallback(msg);
        Position pos1 = new Position(3,0);
        Position pos2 = new Position(2,0);
        p.initialize(pos2);
        t.initialize(pos1);
    }

    @Test
    void makeTurn() {
        assertEquals(16, t.makeTurn(p));
        p.initialize(new Position(15,15));
        assertEquals(113, t.makeTurn(p));
    }
}