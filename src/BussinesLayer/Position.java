package BussinesLayer;

import BussinesLayer.Tiles.Tile;

public class Position {

    public int x;
    public int y;

    public Position(int x, int y){
        this.x=x;
        this.y=y;
    }

    public double range(Tile p){
        return Math.sqrt(Math.pow(p.position.x - this.x,2)+Math.pow(p.position.y - this.y,2));
    }
}
