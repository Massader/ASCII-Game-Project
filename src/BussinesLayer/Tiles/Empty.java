package BussinesLayer.Tiles;

import BussinesLayer.Position;
import BussinesLayer.Tiles.Enemies.Enemy;
import BussinesLayer.Tiles.Player.Player;

public class Empty extends Tile {

    private static final char EMPTY_TILE = '.';

    public Empty(Position position) {
        super(EMPTY_TILE, position);
    }

    @Override
    public void visit(Enemy enemy) {

    }

    @Override
    public void visit(Player player) {

    }

    @Override
    public void visit(Tile tile) {

    }
}
