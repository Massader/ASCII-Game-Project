package BussinesLayer.Tiles.Player;

import BussinesLayer.Tiles.Enemies.Enemy;
import BussinesLayer.Util.Tuple;

public class Rogue extends Player{

    private final int ROUGE_ATTACK_BONUS = 3;

    public int cost;
    public int currentEnergy;

    public Rogue(String name, int health, int attackPoints, int defensePoints, int cost) {
        super(name, health, attackPoints, defensePoints);
        this.cost = cost;
        currentEnergy = 100;
    }

    @Override
    public String toString() {
        return "" + tile;
    }

    @Override
    public void castAbility() { //Fan of Knives
        if (currentEnergy < cost) return;
        for(Tuple<Enemy, Double> t : enemiesInRange){
            if (t.y < 2) //attack all enemies around the rouge
                attack(t.x);
        }
        currentEnergy -= cost;
    }

    @Override
    public void levelUp(){
        super.levelUp();
        attackPoints += ROUGE_ATTACK_BONUS * playerLevel;
        currentEnergy = 100;
    }

    @Override
    public void onTurn() {
        currentEnergy = Math.min(currentEnergy + 10, 100);
    }

    @Override
    public String describe() {
        return String.format("%s\t\t Energy: %d / 100\t\t Ability Cost: %d", super.describe(), currentEnergy, cost);
    }
}
