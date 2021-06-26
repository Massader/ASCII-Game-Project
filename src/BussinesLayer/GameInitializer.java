package BussinesLayer;
import BussinesLayer.Tiles.Player.Player;
import BussinesLayer.Tiles.TileFactory;
import BussinesLayer.Util.EnemyDeathCallback;
import BussinesLayer.Util.MessageCallback;
import BussinesLayer.Util.PlayerDeathCallback;
import BussinesLayer.Util.ProgressCallback;
import PresentationLayer.GamePresentation;
import PresentationLayer.Screens;
import BussinesLayer.Util.*;
import BussinesLayer.Tiles.Enemies.Enemy;
import BussinesLayer.Tiles.Player.*;
import BussinesLayer.Tiles.Tile;
import PresentationLayer.levels.TextFileReader;

import java.util.List;
import java.util.Scanner;

public class GameInitializer {

    final int NUM_OF_CHARACTERS = 7;

    Player p;
    GameManager gm;
    Board board;
    TileFactory factory;
    String path;
    final int NUMBER_OF_LEVELS = 4;

    public GameInitializer(String path) {
        factory = new TileFactory();
        this.path = path;
    }

    public void initializeGame() {
        System.out.println("Choose your character:");
        for (int i = 0; i < NUM_OF_CHARACTERS; i++) {
            System.out.println(i + ". " + factory.producePlayer(i).describe());
        }
        Scanner scanner = new Scanner(System.in);
        p = factory.producePlayer(scanner.nextInt());
        GamePresentation gp = new GamePresentation();
        gm = new GameManager(p, new ProgressCallback(this), gp);
        board = new Board();
        initializeLevel(1);
        gm.initializeBoard(board);
        PlayerDeathCallback pdc = new PlayerDeathCallback(p);
        pdc.addObserver(board);
        pdc.addObserver(gm);
        p.setDeathCallback(pdc);
        p.setMessageCallback(new MessageCallback(gp));

        gm.playGame(1);
    }

    private void initializeLevel(int level) {
        if (level <= NUMBER_OF_LEVELS) {
            TextFileReader reader = new TextFileReader();
            List<String> text = reader.readAllLines(path + "/level" + level + ".txt");
            Tile[][] tiles = new Tile[text.size()][text.get(0).length()];

            for (int i = 0; i < text.size(); i++) {
                for (int j = 0; j < text.get(0).length(); j++) {
                    Position pos = new Position(j, i);
                    if (text.get(i).charAt(j) == '#')
                        tiles[i][j] = factory.produceWall(pos);
                    else if (text.get(i).charAt(j) == '.')
                        tiles[i][j] = factory.produceEmpty(pos);
                    else if (text.get(i).charAt(j) == '@') {
                        tiles[i][j] = p.initialize(pos);
                    } else {
                        Enemy e = factory.produceEnemy(text.get(i).charAt(j));
                        tiles[i][j] = e;
                        EnemyDeathCallback edc = new EnemyDeathCallback(e);
                        edc.addObserver(board);
                        edc.addObserver(p);
                        edc.addObserver(gm);
                        e.initialize(pos);
                        e.setDeathCallback(edc);
                        e.setMessageCallback(new MessageCallback(gm.gamePresentation));
                        gm.addEnemy(e);
                    }
                }
            }
            board.initialize(tiles);
        }
        Screens screens = new Screens();
        if (level > NUMBER_OF_LEVELS){
            System.out.println(screens.printScreen(-1));
            System.exit(0);
        }
        else
            System.out.println(screens.printScreen(level));
    }

    public void progressLevel(int level) {
        gm = new GameManager(p, gm.progressCallback, gm.gamePresentation);
        initializeLevel(level);
        gm.initializeBoard(board);
        gm.playGame(level);
    }

}
