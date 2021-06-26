package BussinesLayer;
import PresentationLayer.GamePresentation;
import PresentationLayer.InputReader;
import BussinesLayer.Util.Observer;
import BussinesLayer.Tiles.Player.Player;
import BussinesLayer.Tiles.Tile;
import BussinesLayer.Tiles.Enemies.Enemy;
import BussinesLayer.Util.ProgressCallback;
import PresentationLayer.Screens;
import BussinesLayer.Util.Tuple;

import java.util.ArrayList;
import java.util.List;

public class GameManager implements Observer {

    Player p;
    Board board;
    InputReader reader = new InputReader();
    List<Enemy> enemies;
    ProgressCallback progressCallback;
    Screens screens;
    GamePresentation gamePresentation;

    public GameManager(Player p, ProgressCallback progressCallback, GamePresentation gamePresentation) {
        this.p = p;
        enemies = new ArrayList<Enemy>();
        this.progressCallback = progressCallback;
        this.screens = new Screens();
        this.gamePresentation = gamePresentation;
    }

    public void initializeBoard(Board board) {
        this.board = board;
    }

    public void addEnemy(Enemy e) {
        enemies.add(e);
    }

    public void playGame(int level) {
        while (!enemies.isEmpty()) {
            gamePresentation.printBoard(board);
            p.messageCallback.send(p.describe());
            boolean validKey = false;
            while (!validKey) {
                validKey = makeAction(reader.getUserInput());
            }
            enemyTurn();
        }
        progressCallback.progress(level+1);
    }

    private boolean makeAction(int i) {
        switch (i) {
            case 119, 115, 97, 100 -> { // move
                Tile t = board.checkMove(p, i);
                if (t.tile == '.') board.swapPositions(p, t);
                else if (t.tile == '#') {
                } else {
                    int currPosX = p.position.x;
                    int currPosY = p.position.y;
                    p.acceptAttack(t);
                    if (p.position.x != currPosX || p.position.y != currPosY) {
                        int nextPosX = p.position.x;
                        int nextPosY = p.position.y;
                        p.initialize(new Position(currPosX,currPosY));
                        board.swapPositions(board.tiles[p.position.y][p.position.x],board.tiles[nextPosY][nextPosX]);
                    }
                }
                p.onTurn();
                return true;
            }
            case 101 -> { //cast special ability ('e')
                List<Tuple<Enemy, Double>> enemiesInRange = new ArrayList<Tuple<Enemy, Double>>();
                for (Enemy e : enemies) {
                    double range = e.position.range(p);
                    if (range <= 6) enemiesInRange.add(new Tuple<Enemy, Double>(e, range));
                }
                p.getEnemyRange(enemiesInRange);
                p.castAbility();
                p.onTurn();
                return true;
            } //Do nothing ('q')
            case 113 -> {
                p.onTurn();
                return true;
            }
            default -> {
                return false;
            }
        }

    }

    private void enemyTurn() {
        int i;
        for (Enemy e : enemies) {
            i = e.makeTurn(p);
            switch (i){
                case 119, 115, 97, 100 -> { // move
                    Tile t = board.checkMove(e, i);
                    if (t.tile == '.') board.swapPositions(e, t);
                    else if (t.tile == '#') {
                    } else {
                        e.acceptAttack(t);
                    }
                }
                case 16 -> {
                    e.attack(p);
                }
            }
        }
    }
    public void printMessage(String message){
        System.out.println(message);
    }

    @Override
    public void update(Enemy e) {
        enemies.remove(e);
        printMessage(String.format("%s died.", e.name));
    }

    @Override
    public void update(Player p) {
        System.out.println("You died.");;
        System.out.println(board.toString());
        System.out.println(screens.printScreen(0));
        System.exit(0);
    }

}