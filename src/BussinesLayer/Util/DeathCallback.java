package BussinesLayer.Util;

public interface DeathCallback extends Observable {
    @Override
    public void addObserver(Observer a);
    @Override
    public void notifyObservers();
}
