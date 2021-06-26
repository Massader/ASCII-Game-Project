package BussinesLayer.Tiles.Player;

import BussinesLayer.Health;
import BussinesLayer.HeroicUnit;
import BussinesLayer.Util.Observer;
import BussinesLayer.Util.PlayerDeathCallback;
import BussinesLayer.Util.Visited;
import BussinesLayer.Util.Visitor;
import BussinesLayer.Tiles.Enemies.Enemy;
import BussinesLayer.Tiles.Tile;
import BussinesLayer.Tiles.Unit;
import BussinesLayer.Util.Tuple;

import java.util.List;

public abstract class Player extends Unit implements HeroicUnit, Observer, Visitor, Visited {

    public final char PLAYER_TILE = '@';
    private final int REQ_EXP = 50;
    private final int ATTACK_BONUS = 4;
    private final int DEFENSE_BONUS = 1;
    private final int HEALTH_BONUS = 10;


    protected int experience;
    protected int playerLevel;
    protected List<Tuple<Enemy, Double>> enemiesInRange;

    protected Player() {}
    protected Player(String name, int healthPool, int attackPoints, int defensePoints) {
        this.name = name;
        this.tile = PLAYER_TILE;
        this.health = new Health(healthPool);
        this.attackPoints = attackPoints;
        this.defensePoints = defensePoints;
        this.experience = 0;
        this.playerLevel = 1;
    }

    public void setDeathCallback(PlayerDeathCallback playerDeathCallback) {
        this.deathCallback = playerDeathCallback;
    }

    public void addExperience(int experienceGained) {
        this.experience += experienceGained;
        int nextLevelReq = levelUpRequirement();
        while(experience >= nextLevelReq){
            experience -= nextLevelReq;
            levelUp();
            nextLevelReq = levelUpRequirement();
        }
    }

    public void levelUp() {
        playerLevel++;
        health.setPool(health.getPool() + (HEALTH_BONUS * playerLevel));
        health.setAmount(health.getPool());
        attackPoints += ATTACK_BONUS * playerLevel;
        defensePoints += DEFENSE_BONUS * playerLevel;
    }

    public int getLevel(){
        return playerLevel;
    }

    public int getExperience(){
        return experience;
    }

    private int levelUpRequirement() {
        return REQ_EXP * playerLevel;
    }

    public String describe() {
        return String.format("%s\t\tLevel: %d\t\tExperience: %d/%d", super.describe(), getLevel(), getExperience(), levelUpRequirement());
    }

    @Override
    public void update(Enemy e) {
        messageCallback.send(String.format("%s gained %d experience.", name, e.experienceValue));
        addExperience(e.experienceValue);
    }

    public void update(Player p) {}

    public void visit(Enemy e) {
        e.attack(this);
    }

    public void visit(Player p) { }

    public void visit(Tile tile) { }

    public void acceptAttack(Visitor v) {
        v.visit(this);
    }

    public void getEnemyRange(List<Tuple<Enemy, Double>> enemies){
        this.enemiesInRange = enemies;
    }

    public abstract void castAbility();

    public abstract void onTurn();
}
