package BussinesLayer.Util;
import BussinesLayer.GameInitializer;

public class ProgressCallback{

    GameInitializer gi;

    public ProgressCallback(GameInitializer gi) {
        this.gi = gi;
    }

    public void progress(int level) {
        gi.progressLevel(level);
    }
}
