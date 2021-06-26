package BussinesLayer.Tiles;

import BussinesLayer.Position;
import BussinesLayer.Util.Visited;
import BussinesLayer.Util.Visitor;

public abstract class Tile implements Visitor, Visited {

    public char tile;
    public Position position;

    protected Tile() {}

    protected Tile(char tile, Position position){
        this.tile = tile;
        this.position = position;
    }

    protected Tile(char tile) {
        this.tile = tile;
    }

    public char getTile(){
        return tile;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }


    @Override
    public String toString() {
        return String.valueOf(tile);
    }

    public void acceptAttack(Visitor v) {
        v.visit(this);
    }
}