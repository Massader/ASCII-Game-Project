package BussinesLayer.Tiles;

import BussinesLayer.Position;
import BussinesLayer.Tiles.Enemies.Enemy;
import BussinesLayer.Tiles.Player.Player;

public class PlayerDeathTile extends Tile {

    private static final char DEATH_TILE = 'X';

    public PlayerDeathTile(Position position){
        super(DEATH_TILE, position);
    }

    @Override
    public String toString() {
        return String.valueOf('X');
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