package PresentationLayer;

import java.util.Scanner;

public class InputReader {

    public int getUserInput(){
        Scanner s = new Scanner(System.in);
        char r = s.next().charAt(0);
        return r;
    }

}
