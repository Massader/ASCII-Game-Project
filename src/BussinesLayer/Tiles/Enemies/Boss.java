package BussinesLayer.Tiles.Enemies;
import BussinesLayer.HeroicUnit;
import BussinesLayer.Tiles.Player.Player;


public class Boss extends Monster implements HeroicUnit {

    public int combatTicks = 0;
    public int abilityFrequency;

    public Boss(char tile, String name, int health, int attackPoints, int defensePoints, int experienceValue, int visionRange, int abilityFrequency) {
        super(tile, name, health, attackPoints, defensePoints, visionRange, experienceValue);
        this.abilityFrequency = abilityFrequency;
    }

    @Override
    public void castAbility() {
        combatTicks = 0;
    }

    @Override
    public int makeTurn(Player p) {
        if (position.range(p) < visionRange) {
            if (combatTicks == abilityFrequency){
                castAbility();
                attack(p);
                return 101;
            }
            else {
                combatTicks++;
                int dx = this.position.x - p.position.x;
                int dy = this.position.y - p.position.y;
                if (Math.abs(dx) > Math.abs((dy))) {
                    if (dx > 0)
                        return 97;
                    else
                        return 100;
                }
                else {
                    if (dy > 0)
                        return 119;
                    else
                        return 115;
                }
            }
        }
        else {
            combatTicks = 0;
            int i = (int) (Math.random() * 100);
            if (i > 80) return 97;
            else if (i > 60) return 100;
            else if (i > 40) return 119;
            else if (i > 20) return 115;
            else return 113;
        }
    }
}
