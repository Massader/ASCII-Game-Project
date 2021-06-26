package BussinesLayer.Tiles.Player;

import BussinesLayer.Tiles.Enemies.Enemy;
import BussinesLayer.Util.Tuple;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Warrior extends Player {

    private final int WARRIOR_ABILITY_RANGE = 3;
    private final int WARRIOR_ATTACK_BONUS = 2;
    private final int WARRIOR_DEFENSE_BONUS = 1;
    private final int WARRIOR_HEALTH_BONUS = 5;

    public int abilityCooldown;
    public int remainingCooldown;

    public Warrior(String name, int health, int attackPoints, int defensePoints, int abilityCooldown) {
        super(name, health, attackPoints, defensePoints);
        this.abilityCooldown = abilityCooldown;
        remainingCooldown = 0;
    }

    @Override
    public String toString() {
        return "" + tile;
    }

    @Override
    public void castAbility() { //Avenger's Shield
        if (remainingCooldown != 0) return;
        List<Enemy> randomAttack = new ArrayList<>();
        for(Tuple<Enemy, Double> t : enemiesInRange){ //Search the enemies in ability range
            if (t.y < WARRIOR_ABILITY_RANGE)
                randomAttack.add(t.x);
        }
        if(!randomAttack.isEmpty()){
            Random rand = new Random();
            //attack one of the monster randomly
            Enemy e = randomAttack.get(rand.nextInt(randomAttack.size()));
            e.takeDamage(this.health.getPool()/10);
            messageCallback.send(String.format("%s engaged in combat with %s.", name, e.name));
            messageCallback.send(describe());
            messageCallback.send(e.describe());
            messageCallback.send(String.format("%s dealt %d damage to %s.", name, this.health.getPool()/10, e.name));
        }
        messageCallback.send(String.format("%s healed %d amount of damage.", name, Math.min(10 * defensePoints, health.getPool() - health.getAmount())));
        this.health.heal((10 * defensePoints));
        remainingCooldown = abilityCooldown + 1;
    }

    @Override
    public void onTurn() { //on the end of each turn the remaining cooldown decrements by 1.
        remainingCooldown = Math.max(0, remainingCooldown-1);
    }

    @Override
    public String describe() {
        return String.format("%s\t\t Ability Cooldown: %d\t\t Remaining Cooldown: %d" , super.describe(), abilityCooldown, remainingCooldown);
    }

    @Override
    public void levelUp() {
        super.levelUp();
        health.setPool(health.getPool() + (WARRIOR_HEALTH_BONUS * playerLevel));
        attackPoints += WARRIOR_ATTACK_BONUS * playerLevel;
        defensePoints += WARRIOR_DEFENSE_BONUS * playerLevel;
    }
}
