package BussinesLayer;

import BussinesLayer.Tiles.Empty;
import BussinesLayer.Tiles.PlayerDeathTile;
import BussinesLayer.Util.Observer;
import BussinesLayer.Tiles.Unit;
import BussinesLayer.Tiles.Enemies.Enemy;
import BussinesLayer.Tiles.Player.Player;
import BussinesLayer.Tiles.Tile;

public class Board implements Observer {

    public Tile[][] tiles;

    final String ANSI_GREEN = "\033[1;32m";
    final String ANSI_RESET = "\u001B[0m";
    final String ANSI_BLUE = "\u001B[34m";
    final String YELLOW_BACKGROUND_BRIGHT = ("\033[0;103m");
    final String RESET = ("\033[0m");
    final String ANSI_RED = "\u001B[31m";

    public Board() {}
    public Board initialize(Tile[][] tiles){
        this.tiles = tiles;
        return this;
    }

    public void swapPositions(Tile a, Tile b) {
        Tile temp = tiles[a.position.y][a.position.x];
        tiles[a.position.y][a.position.x] = tiles[b.position.y][b.position.x];
        tiles[b.position.y][b.position.x] = temp;
        Position p = new Position(a.getPosition().x, a.getPosition().y);
        a.position = new Position(b.getPosition().x, b.getPosition().y);
        b.position = p;
    }

    public Tile checkMove(Unit u, int move) {
        if(move==119){
            return tiles[u.position.y-1][u.position.x];
        }
        else if(move==115){
            return tiles[u.position.y+1][u.position.x];
        }
        else if(move==97){
            return tiles[u.position.y][u.position.x-1];
        }
        else if(move==100){
            return tiles[u.position.y][u.position.x+1];
        }
        return null;
    }

    @Override
    public String toString(){
        String print = "";
        for (int i = 0; i < tiles.length; i++){
            for (int j = 0; j < tiles[i].length; j++){
                if(tiles[i][j].tile==('#'))
                    print += (YELLOW_BACKGROUND_BRIGHT + "#" + RESET);
                else if(tiles[i][j].tile==('@'))
                    print += (ANSI_GREEN + "@" + ANSI_RESET);
                else if(tiles[i][j].tile==('K') || tiles[i][j].tile==('w'))
                    print += (ANSI_BLUE + tiles[i][j].toString() + ANSI_RESET);
                else if(tiles[i][j].tile!=('@') & tiles[i][j].tile!=('.') & tiles[i][j].tile!=('#')
                        & tiles[i][j].tile!=('w') & tiles[i][j].tile!=('K'))
                    print += (ANSI_RED + tiles[i][j].toString() + ANSI_RESET);
                else
                    print += tiles[i][j].toString();
            }
            print += "\n";
        }
        return print;
    }

    public void update(Enemy e) {
         tiles[e.position.y][e.position.x] = new Empty(e.position);
    }

    public void update(Player p) {
        tiles[p.position.y][p.position.x] = new PlayerDeathTile(p.position);
    }
}



