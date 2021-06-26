package BussinesLayer.Tiles.Enemies;

import BussinesLayer.Tiles.Player.Player;
import BussinesLayer.Util.Visited;
import BussinesLayer.Util.Visitor;
import BussinesLayer.Tiles.Tile;
import BussinesLayer.Tiles.Unit;

public abstract class Enemy extends Unit implements Visitor, Visited {
    public int experienceValue;

    public Enemy() {}

    public Enemy(char tile, String name, int health, int attackPoints, int defensePoints, int experienceValue) {
        super(tile, name, health, attackPoints, defensePoints);
        this.experienceValue = experienceValue;
    }

    public abstract int makeTurn(Player p);

    @Override
    public void visit(Tile tile) {
    }

    public void visit(Enemy e) {
        return;
    }

    public void visit(Player p) {
        p.attack(this);
        if (health.getAmount() <= 0) {
            p.initialize(position);
        }
    }
    public void acceptAttack(Visitor v) {
        v.visit(this);
    }
}