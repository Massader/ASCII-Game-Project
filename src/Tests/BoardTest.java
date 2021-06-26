package Tests;

import BussinesLayer.Board;
import BussinesLayer.Position;
import BussinesLayer.Tiles.Enemies.Trap;
import BussinesLayer.Tiles.Player.Warrior;
import BussinesLayer.Tiles.Tile;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class BoardTest {
     Trap nitzan;
     Warrior roei;
     Board board;
     Position p1;
     Position p2;

    @BeforeEach
    void setUp() {
        board = new Board();
        p1 = new Position(0,0);
        p2 = new Position(1,0);
        nitzan = new Trap('N', "Nitzan Trap", 5, 1, 1, 0, 10, 1);
        roei = new Warrior("Roei Sheleg", 30054, 3330, 4241, 1);
        nitzan.initialize(p1);
        roei.initialize(p2);
        Tile[][] tiles = new Tile[1][2];
        tiles[0][0] = nitzan;
        tiles[0][1] = roei;
        board.initialize(tiles);
    }

    @Test
    void swapPositions1() {
        board.swapPositions(nitzan, roei);
        assertEquals(1, nitzan.position.x);
        assertEquals(0, nitzan.position.y);
        assertEquals(0, roei.position.x);
        assertEquals(0, roei.position.y);
    }

}