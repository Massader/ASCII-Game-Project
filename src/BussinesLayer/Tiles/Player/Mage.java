package BussinesLayer.Tiles.Player;

import BussinesLayer.Tiles.Enemies.Enemy;
import BussinesLayer.Util.Tuple;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Mage extends Player{

    public int manaPool;
    public int currentMana;
    public int manaCost;
    public int spellPower;
    public int hitsCount;
    public int abilityRange;

    private final int MAGE_MANA_POOL_BONUS = 25;
    private final int MAGE_SPELL_POWER_BONUS = 10;

    public Mage(String name, int health, int attackPoints, int defensePoints, int manaPool, int manaCost, int spellPower, int hitsCount, int abilityRange) {
        super(name, health, attackPoints, defensePoints);
        this.manaPool = manaPool;
        this.manaCost = manaCost;
        this.currentMana = manaPool/4;
        this.spellPower = spellPower;
        this.hitsCount = hitsCount;
        this.abilityRange = abilityRange;
    }

    @Override
    public void castAbility() { //Blizzard
        if (currentMana < manaCost) return;
        List<Enemy> randomAttack = new ArrayList<>();
        for(Tuple<Enemy, Double> t : enemiesInRange){ //Search the enemies in range
            if (t.y < abilityRange)
                randomAttack.add(t.x);
        }
        if(!randomAttack.isEmpty()){
            Random rand = new Random();
            //attack monsters randomly up to (hitsCount) enemies
            for (int hits = 0; hits < hitsCount && randomAttack.size() != 0; hits++) {
                int j = rand.nextInt(randomAttack.size());
                randomAttack.get(j).defend(spellPower, this);
                randomAttack.remove(j);
            }
        }
        currentMana -= manaCost;
    }

    @Override
    public void levelUp(){
        super.levelUp();
        manaPool += MAGE_MANA_POOL_BONUS * playerLevel;
        spellPower += MAGE_SPELL_POWER_BONUS * playerLevel;
        currentMana = Math.min(currentMana + (manaPool/4), manaPool);
    }

    @Override
    public void onTurn() {
        currentMana = Math.min(manaPool, currentMana + playerLevel);
    }

    @Override
    public String toString() {
        return "" + tile;
    }

    @Override
    public String describe() {
        return String.format("%s\t\t Mana: %d / %d\t\t Mana Cost: %d\nSpell Power: %d\t\tHits Count: %d\t\tAbility Range: %d", super.describe(), currentMana, manaPool, manaCost, spellPower, hitsCount, abilityRange);
    }


}