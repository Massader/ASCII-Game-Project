package BussinesLayer.Tiles.Enemies;

import BussinesLayer.Tiles.Player.Player;


public class Monster extends Enemy{

    public int visionRange;

    public Monster(char tile, String name, int health, int attackPoints, int defensePoints, int experienceValue, int visionRange) {
        super(tile, name, health, attackPoints, defensePoints, experienceValue);
        this.visionRange = visionRange;
    }

    @Override
    public int makeTurn(Player p) {
        if (position.range(p) < visionRange) {
            int dx = this.position.x - p.position.x;
            int dy = this.position.y - p.position.y;
            if (Math.abs(dx) > Math.abs((dy))) {
                if (dx > 0)
                    return 97;
                else
                    return 100;
            } else {
                if (dy > 0)
                    return 119;
                else
                    return 115;
            }
        }
        else {
            int i = (int) (Math.random()*100);
            if (i > 80) return 97;
            else if (i > 60) return 100;
            else if (i > 40) return 119;
            else if (i > 20) return 115;
            else return 113;
        }
    }
}
