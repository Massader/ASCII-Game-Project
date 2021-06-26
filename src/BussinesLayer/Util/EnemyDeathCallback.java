package BussinesLayer.Util;

import BussinesLayer.Tiles.Enemies.Enemy;

import java.util.ArrayList;
import java.util.List;

public class EnemyDeathCallback implements DeathCallback {

    private List<Observer> observers;
    Enemy e;

    public EnemyDeathCallback(Enemy e) {
        observers = new ArrayList<Observer>();
        this.e = e;
    }

    @Override
    public void addObserver(Observer o) {
        observers.add(o);
    }

    @Override
    public void notifyObservers() {
        for (Observer o : observers) o.update(e);
    }
}
