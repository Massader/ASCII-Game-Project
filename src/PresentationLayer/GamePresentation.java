package PresentationLayer;

import BussinesLayer.Board;

public class GamePresentation {

    public void printBoard(Board board){
        System.out.println(board);
    }

    public void printMessage(String msg){
        System.out.println(msg);
    }

    public void printDescription(String description) {
        System.out.println(description);
    }
}
