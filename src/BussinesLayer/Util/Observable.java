package BussinesLayer.Util;

public interface Observable {

    void addObserver(Observer o);

    void notifyObservers();

}
