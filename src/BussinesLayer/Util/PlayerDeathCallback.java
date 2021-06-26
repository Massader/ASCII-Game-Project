package BussinesLayer.Util;
import java.util.ArrayList;
import java.util.List;
import BussinesLayer.Tiles.Player.Player;

public class PlayerDeathCallback implements DeathCallback{

    private Player p;
    private List<Observer> observers = new ArrayList<>();

    public PlayerDeathCallback(Player p) {
        this.p = p;
    }

    @Override
    public void addObserver(Observer o) {
        observers.add(o);
    }

    @Override
    public void notifyObservers() {
        for (Observer o : observers) o.update(p);
    }
}
