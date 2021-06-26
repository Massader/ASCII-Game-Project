package BussinesLayer.Util;

import BussinesLayer.Tiles.Enemies.Enemy;
import BussinesLayer.Tiles.Player.Player;
import BussinesLayer.Tiles.Tile;

public interface Visitor {

    public void visit(Enemy enemy);

    public void visit(Player player);

    public void visit(Tile tile);

}
