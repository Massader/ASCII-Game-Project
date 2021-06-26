package BussinesLayer.Tiles.Player;

import BussinesLayer.Tiles.Enemies.Enemy;
import BussinesLayer.Util.Tuple;

public class Hunter extends Player {

    private final int HUNTER_ATTACK_BONUS = 2;
    private final int HUNTER_DEFENSE_BONUS = 1;
    private final int ARROWS_BONUS = 10;

    public int abilityRange;
    public int arrowsCount;
    public int ticksCount;

    public Hunter(String name, int health, int attackPoints, int defensePoints, int abilityRange) {
        super(name, health, attackPoints, defensePoints);
        this.abilityRange = abilityRange;
        ticksCount = 0;
        arrowsCount = 10;
    }

    @Override
    public void levelUp() {
        super.levelUp();
        arrowsCount += ARROWS_BONUS * playerLevel;
        attackPoints += HUNTER_ATTACK_BONUS * playerLevel;
        defensePoints += HUNTER_DEFENSE_BONUS * playerLevel;
    }


    @Override
    public void castAbility() { //Shoot
        if (enemiesInRange.size() == 0) {
            arrowsCount = Math.max(0, arrowsCount - 1);
            return;
        }
        double closestRange = enemiesInRange.get(0).y;
        Enemy e = enemiesInRange.get(0).x;
        for(Tuple<Enemy, Double> t : enemiesInRange) { //Search the closest enemy
            if (t.y < closestRange) {
                closestRange = t.y;
                e = t.x;
            }
        }
        attack(e);
        arrowsCount = Math.max(0, arrowsCount - 1);
    }

    @Override
    public void onTurn() {
        ticksCount++;
        if (ticksCount == 10) {
            ticksCount = 0;
            arrowsCount += playerLevel;
        }
    }

    @Override
    public String toString() {
        return "" + tile;
    }
    public int getArrowsCount() { return arrowsCount;}
    public int getAbilityRange() { return abilityRange;}
    public int getTicksCount() { return ticksCount;}

    @Override
    public String describe() {
        return String.format("%s\t\tArrows: %d\t\tRange: %d\t\tTicksCount: %d", super.describe(), getArrowsCount(), getAbilityRange(), getTicksCount());
    }


}
