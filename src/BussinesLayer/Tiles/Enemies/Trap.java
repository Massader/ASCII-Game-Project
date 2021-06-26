package BussinesLayer.Tiles.Enemies;

import BussinesLayer.Tiles.Player.Player;

public class Trap extends Enemy {

    public int visibilityTime;
    public int invisibilityTime;
    public int ticksCount;
    public boolean visible;

    public Trap(char tile, String name, int health, int attackPoints, int defensePoints, int experienceValue, int visibilityTime, int invisibilityTime) {
        super(tile, name, health, attackPoints, defensePoints, experienceValue);
        this.visibilityTime = visibilityTime;
        this.invisibilityTime = invisibilityTime;
        ticksCount = 0;
        visible = false;
    }

    @Override
    public int makeTurn(Player p) {
        visible = ticksCount < visibilityTime;
        if (ticksCount == (visibilityTime + invisibilityTime)) ticksCount = 0;
        else ticksCount++;
        if (position.range(p) < 2) return 16;
        else return 113;
    }


}
