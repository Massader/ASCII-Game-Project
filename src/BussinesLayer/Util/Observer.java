package BussinesLayer.Util;
import BussinesLayer.Tiles.Enemies.Enemy;
import BussinesLayer.Tiles.Player.Player;

public interface Observer {

    void update(Player p);

    void update(Enemy e);

}
