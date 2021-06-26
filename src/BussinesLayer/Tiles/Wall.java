package BussinesLayer.Tiles;

import BussinesLayer.Position;
import BussinesLayer.Tiles.Enemies.Enemy;
import BussinesLayer.Tiles.Player.Player;

public class Wall extends Tile {

    private final static char WALL_TILE = '#';

    public Wall(Position position) {
        super(WALL_TILE, position);
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
