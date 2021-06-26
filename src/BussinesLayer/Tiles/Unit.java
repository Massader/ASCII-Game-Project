package BussinesLayer.Tiles;

import BussinesLayer.Health;
import BussinesLayer.Position;
import BussinesLayer.Util.MessageCallback;
import BussinesLayer.Util.DeathCallback;

public abstract class Unit extends Tile {

    public String name;
    public Health health;
    public int attackPoints;
    public int defensePoints;
    public DeathCallback deathCallback;
    public MessageCallback messageCallback;

    public Unit() {}

    public Unit(char tile, String name, int health, int attackPoints, int defensePoints) {
        super(tile);
        this.name = name;
        this.health = new Health(health);
        this.attackPoints = attackPoints;
        this.defensePoints = defensePoints;
    }

    public void setDeathCallback(DeathCallback deathCallback) {
        this.deathCallback = deathCallback;
    }

    public void setMessageCallback(MessageCallback messageCallback) {
        this.messageCallback = messageCallback;
    }

    public Health getHealth() { return health;}

    public int getAttack() { return attackPoints;}

    public int getDefense() { return defensePoints;}

    public String getName() {
        return name;
    }

    public Unit initialize(Position position) {
        this.position = position;
        return this;
    }

    public String describe() {
        return String.format("%s\t\tHealth: %s\t\tAttack: %d\t\tdefense: %d", getName(), getHealth(), getAttack(), getDefense());
    }

    public void attack(Unit u){
        int attackRoll = (int)(Math.random() * (attackPoints + 1));
        messageCallback.send(String.format("%s engaged in combat with %s.", name, u.name));
        messageCallback.send(describe());
        messageCallback.send(u.describe());
        messageCallback.send(String.format("%s rolled %d attack points.", name, attackRoll));
        u.defend(attackRoll, this);
    }

    public void defend(int attackRoll, Unit u) {
        int defenseRoll = (int)(Math.random() * (defensePoints + 1));
        messageCallback.send(String.format("%s rolled %d defense points.", name, defenseRoll));
        messageCallback.send(String.format("%s dealt %d damage to %s.", u.name, Math.max(attackRoll - defenseRoll,0), name));
        if (defenseRoll < attackRoll)
            takeDamage(Math.max(attackRoll - defenseRoll, 0));
    }

    public void takeDamage(int damage){
        if(this.health.takeDamage(damage)){
            deathCallback.notifyObservers();
        }
    }
}
