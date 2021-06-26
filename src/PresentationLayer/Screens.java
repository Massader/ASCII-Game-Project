package PresentationLayer;

public class Screens {

    enum Color {
        //Color end string, color reset
        RESET("\033[0m"),

        // Regular Colors. Normal color, no bold, background color etc.
        BLACK("\033[0;30m"),    // BLACK
        RED("\033[0;31m"),      // RED
        GREEN("\033[0;32m"),    // GREEN
        YELLOW("\033[0;33m"),   // YELLOW
        BLUE("\033[0;34m"),     // BLUE
        MAGENTA("\033[0;35m"),  // MAGENTA
        CYAN("\033[0;36m"),     // CYAN
        WHITE("\033[0;37m"),    // WHITE

        // Bold
        BLACK_BOLD("\033[1;30m"),   // BLACK
        RED_BOLD("\033[1;31m"),     // RED
        GREEN_BOLD("\033[1;32m"),   // GREEN
        YELLOW_BOLD("\033[1;33m"),  // YELLOW
        BLUE_BOLD("\033[1;34m"),    // BLUE
        MAGENTA_BOLD("\033[1;35m"), // MAGENTA
        CYAN_BOLD("\033[1;36m"),    // CYAN
        WHITE_BOLD("\033[1;37m"),   // WHITE

        // Underline
        BLACK_UNDERLINED("\033[4;30m"),     // BLACK
        RED_UNDERLINED("\033[4;31m"),       // RED
        GREEN_UNDERLINED("\033[4;32m"),     // GREEN
        YELLOW_UNDERLINED("\033[4;33m"),    // YELLOW
        BLUE_UNDERLINED("\033[4;34m"),      // BLUE
        MAGENTA_UNDERLINED("\033[4;35m"),   // MAGENTA
        CYAN_UNDERLINED("\033[4;36m"),      // CYAN
        WHITE_UNDERLINED("\033[4;37m"),     // WHITE

        // Background
        BLACK_BACKGROUND("\033[40m"),   // BLACK
        RED_BACKGROUND("\033[41m"),     // RED
        GREEN_BACKGROUND("\033[42m"),   // GREEN
        YELLOW_BACKGROUND("\033[43m"),  // YELLOW
        BLUE_BACKGROUND("\033[44m"),    // BLUE
        MAGENTA_BACKGROUND("\033[45m"), // MAGENTA
        CYAN_BACKGROUND("\033[46m"),    // CYAN
        WHITE_BACKGROUND("\033[47m"),   // WHITE

        // High Intensity
        BLACK_BRIGHT("\033[0;90m"),     // BLACK
        RED_BRIGHT("\033[0;91m"),       // RED
        GREEN_BRIGHT("\033[0;92m"),     // GREEN
        YELLOW_BRIGHT("\033[0;93m"),    // YELLOW
        BLUE_BRIGHT("\033[0;94m"),      // BLUE
        MAGENTA_BRIGHT("\033[0;95m"),   // MAGENTA
        CYAN_BRIGHT("\033[0;96m"),      // CYAN
        WHITE_BRIGHT("\033[0;97m"),     // WHITE

        // Bold High Intensity
        BLACK_BOLD_BRIGHT("\033[1;90m"),    // BLACK
        RED_BOLD_BRIGHT("\033[1;91m"),      // RED
        GREEN_BOLD_BRIGHT("\033[1;92m"),    // GREEN
        YELLOW_BOLD_BRIGHT("\033[1;93m"),   // YELLOW
        BLUE_BOLD_BRIGHT("\033[1;94m"),     // BLUE
        MAGENTA_BOLD_BRIGHT("\033[1;95m"),  // MAGENTA
        CYAN_BOLD_BRIGHT("\033[1;96m"),     // CYAN
        WHITE_BOLD_BRIGHT("\033[1;97m"),    // WHITE

        // High Intensity backgrounds
        BLACK_BACKGROUND_BRIGHT("\033[0;100m"),     // BLACK
        RED_BACKGROUND_BRIGHT("\033[0;101m"),       // RED
        GREEN_BACKGROUND_BRIGHT("\033[0;102m"),     // GREEN
        YELLOW_BACKGROUND_BRIGHT("\033[0;103m"),    // YELLOW
        BLUE_BACKGROUND_BRIGHT("\033[0;104m"),      // BLUE
        MAGENTA_BACKGROUND_BRIGHT("\033[0;105m"),   // MAGENTA
        CYAN_BACKGROUND_BRIGHT("\033[0;106m"),      // CYAN
        WHITE_BACKGROUND_BRIGHT("\033[0;107m");     // WHITE

        private final String code;

        Color(String code) {
            this.code = code;
        }

        @Override
        public String toString() {
            return code;
        }
    }

    public String printScreen(int l){
        if(l == 0)
            return printGameOver();
        else if(l==1)
            return level1();
        else if(l==2)
            return level2();
        else if(l==3)
            return level3();
        else if(l==4)
            return level4();
        else if(l==-1)
            return youWon();
        return "";
    }

    private String printGameOver() {
        String output = "";
        String gameover =
                "############################################################\n" +
                "#..........................................................#\n" +
                "#.....GGGGGG.......AAAAAAAAA.....MMMMMMMMM.....EEEEEEEEEE..#\n" +
                "#...GGG....GGG....AAA.....AAA...MMM.MMM.MMM...EEE..........#\n" +
                "#..GGG......GGG...AAA.....AAA...MMM.MMM.MMM...EEE..........#\n" +
                "#..GGG............AAA.....AAA...MMM.MMM.MMM...EEEEEEEEEEE..#\n" +
                "#..GGG..GGGGGGG...AAAAAAAAAAA...MMM.MMM.MMM...EEE..........#\n" +
                "#..GGG......GGG...AAA.....AAA...MMM.MMM.MMM...EEE..........#\n" +
                "#...GGG....GGG....AAA.....AAA...MMM.MMM.MMM...EEE..........#\n" +
                "#.....GGGGGG......AAA.....AAA...MMM.MMM.MMM....EEEEEEEEEE..#\n" +
                "#..........................................................#\n" +
                "#.....OOOOOO......VVV.....VVV....EEEEEEEEEE...RRRRRRRRR....#\n" +
                "#...OOO....OOO....VVV.....VVV...EEE...........RRR....RRR...#\n" +
                "#..OOO......OOO...VVV.....VVV...EEE...........RRR.....RRR..#\n" +
                "#..OOO......OOO...VVV.....VVV...EEEEEEEEEEE...RRR....RRR...#\n" +
                "#..OOO......OOO...VVV.....VVV...EEE...........RRRRRRRRR....#\n" +
                "#..OOO......OOO....VVV...VVV....EEE...........RRR....RRR...#\n" +
                "#...OOO....OOO......VVV.VVV.....EEE...........RRR.....RRR..#\n" +
                "#.....OOOOOO..........VVV........EEEEEEEEEE...RRR.....RRR..#\n" +
                "#..........................................................#\n" +
                "#############################################################";

        for(int i = 0; i<gameover.length()-1; i++) {
            if (gameover.charAt(i) == '#' || gameover.charAt(i) == 'G' || gameover.charAt(i) == 'A'
                    || gameover.charAt(i) == 'M' || gameover.charAt(i) == 'E' || gameover.charAt(i) == 'O' ||
                    gameover.charAt(i) == 'V' || gameover.charAt(i) == 'R') {
                output += Color.RED_BACKGROUND;
                output += gameover.charAt(i);
                output += Color.RESET;
            }
            else
                output += gameover.charAt(i);
        }
        return output +"\n";
    }

    private String level1() {
        String output = "";
        String level1 =
                "#######################################################\n" +
                "#.....................................................#\n" +
                "#..ll........eeeeeeee...vv....vv..eeeeeeee..ll........#\n" +
                "#..ll........ee....ee...vv....vv..ee....ee..ll........#\n" +
                "#..ll........eeeeeeee....vv..vv...eeeeeeee..ll........#\n" +
                "#..ll........ee...........vvvv....ee........ll........#\n" +
                "#..llllllll..eeeeeeee......vv.....eeeeeeee..llllllll..#\n" +
                "#.....................................................#\n" +
                "#........................11111........................#\n" +
                "#...........................11........................#\n" +
                "#...........................11........................#\n" +
                "#...........................11........................#\n" +
                "#........................1111111......................#\n" +
                "#.....................................................#\n" +
                "########################################################";
        for(int i = 0; i<level1.length()-1; i++) {
            if (level1.charAt(i) == '#' || level1.charAt(i) == 'l' || level1.charAt(i) == 'e' || level1.charAt(i) == 'v' || level1.charAt(i) == '1') {
                output += Color.GREEN_BACKGROUND;
                output += level1.charAt(i);
                output += Color.RESET;
            }
            else if(level1.charAt(i) == '.'){
                output += Color.BLUE_BACKGROUND;
                output += level1.charAt(i);
                output += Color.RESET;
            }
            else
                output += level1.charAt(i);
        }
        return output +"\n";
    }

    private String level2() {
        String output = "";
        String level2 =
                "#######################################################\n" +
                "#.....................................................#\n" +
                "#..ll........eeeeeeee...vv....vv..eeeeeeee..ll........#\n" +
                "#..ll........ee....ee...vv....vv..ee....ee..ll........#\n" +
                "#..ll........eeeeeeee....vv..vv...eeeeeeee..ll........#\n" +
                "#..ll........ee...........vvvv....ee........ll........#\n" +
                "#..llllllll..eeeeeeee......vv.....eeeeeeee..llllllll..#\n" +
                "#.....................................................#\n" +
                "#.......................2222222.......................#\n" +
                "#............................22.......................#\n" +
                "#.......................2222222.......................#\n" +
                "#.......................22............................#\n" +
                "#.......................2222222.......................#\n" +
                "#.....................................................#\n" +
                "########################################################";
        for(int i = 0; i<level2.length()-1; i++) {
            if (level2.charAt(i) == '#' || level2.charAt(i) == 'l' || level2.charAt(i) == 'e' || level2.charAt(i) == 'v' || level2.charAt(i) == '2') {
                output += Color.BLUE_BACKGROUND;
                output += level2.charAt(i);
                output += Color.RESET;
            }
            else if(level2.charAt(i) == '.'){
                output += Color.YELLOW_BACKGROUND;
                output += level2.charAt(i);
                output += Color.RESET;
            }
            else
                output += level2.charAt(i);
        }
        return output +"\n";
    }

    private String level3() {
        String output = "";
        String level3 =
                "#######################################################\n" +
                "#.....................................................#\n" +
                "#..ll........eeeeeeee...vv....vv..eeeeeeee..ll........#\n" +
                "#..ll........ee....ee...vv....vv..ee....ee..ll........#\n" +
                "#..ll........eeeeeeee....vv..vv...eeeeeeee..ll........#\n" +
                "#..ll........ee...........vvvv....ee........ll........#\n" +
                "#..llllllll..eeeeeeee......vv.....eeeeeeee..llllllll..#\n" +
                "#.....................................................#\n" +
                "#.......................33333333......................#\n" +
                "#.............................33......................#\n" +
                "#.......................33333333......................#\n" +
                "#.............................33......................#\n" +
                "#.......................33333333......................#\n" +
                "#.....................................................#\n" +
                "########################################################";
        for(int i = 0; i<level3.length()-1; i++) {
            if (level3.charAt(i) == '#' || level3.charAt(i) == 'l' || level3.charAt(i) == 'e' || level3.charAt(i) == 'v' || level3.charAt(i) == '3') {
                output += Color.YELLOW_BACKGROUND;
                output += level3.charAt(i);
                output += Color.RESET;
            }
            else if(level3.charAt(i) == '.'){
                output += Color.BLACK_BACKGROUND;
                output += level3.charAt(i);
                output += Color.RESET;
            }
            else
                output += level3.charAt(i);
        }
        return output +"\n";
    }

    private String level4() {
        String output = "";
        String level4 =
                "#######################################################\n" +
                "#.....................................................#\n" +
                "#..ll........eeeeeeee...vv....vv..eeeeeeee..ll........#\n" +
                "#..ll........ee....ee...vv....vv..ee....ee..ll........#\n" +
                "#..ll........eeeeeeee....vv..vv...eeeeeeee..ll........#\n" +
                "#..ll........ee...........vvvv....ee........ll........#\n" +
                "#..llllllll..eeeeeeee......vv.....eeeeeeee..llllllll..#\n" +
                "#.....................................................#\n" +
                "#.......................44....44......................#\n" +
                "#.......................44....44......................#\n" +
                "#.......................44444444......................#\n" +
                "#.............................44......................#\n" +
                "#.............................44......................#\n" +
                "#.....................................................#\n" +
                "########################################################";
        for(int i = 0; i<level4.length()-1; i++) {
            if (level4.charAt(i) == '#' || level4.charAt(i) == 'l' || level4.charAt(i) == 'e' || level4.charAt(i) == 'v' || level4.charAt(i) == '4') {
                output += Color.CYAN_BACKGROUND;
                output += level4.charAt(i);
                output += Color.RESET;
            }
            else if(level4.charAt(i) == '.'){
                output += Color.WHITE_BACKGROUND;
                output += level4.charAt(i);
                output += Color.RESET;
            }
            else
                output += level4.charAt(i);
        }
        return output +"\n";
    }

    private String youWon() {
        String output = "";
        String youWon =
                        "###################################################\n" +
                        "#.................................................#\n" +
                        "#...YYY.......YYY.....OOOOOO.......UUU......UUU...#\n" +
                        "#....YYY.....YYY....OOO....OOO.....UUU......UUU...#\n" +
                        "#.....YYY...YYY....OOO......OOO....UUU......UUU...#\n" +
                        "#......YYY.YYY.....OOO......OOO....UUU......UUU...#\n" +
                        "#.......YYYYY......OOO......OOO....UUU......UUU...#\n" +
                        "#........YYY.......OOO......OOO....UUU......UUU...#\n" +
                        "#........YYY........OOO....OOO......UUU....UUU....#\n" +
                        "#........YYY..........OOOOOO..........UUUUUU......#\n" +
                        "#.................................................#\n" +
                        "#...WWW..WWW..WWW.....OOOOOO......NNN.......NNN...#\n" +
                        "#...WWW..WWW..WWW...OOO....OOO....NNNN......NNN...#\n" +
                        "#...WWW..WWW..WWW..OOO......OOO...NNNNNN....NNN...#\n" +
                        "#...WWW..WWW..WWW..OOO......OOO...NNN..NNN..NNN...#\n" +
                        "#...WWW..WWW..WWW..OOO......OOO...NNN...NNN.NNN...#\n" +
                        "#...WWW..WWW..WWW..OOO......OOO...NNN.....NNNNN...#\n" +
                        "#...WWW..WWW..WWW...OOO....OOO....NNN......NNNN...#\n" +
                        "#.....WWWW.WWWW.......OOOOOO......NNN.......NNN...#\n" +
                        "#.................................................#\n" +
                        "####################################################";
        for(int i = 0; i<youWon.length()-1; i++) {
            if (youWon.charAt(i) == '#' || youWon.charAt(i) == 'Y' || youWon.charAt(i) == 'O'
                    || youWon.charAt(i) == 'U' || youWon.charAt(i) == 'W' || youWon.charAt(i) == 'N') {
                output += Color.YELLOW_BACKGROUND;
                output += youWon.charAt(i);
                output += Color.RESET;
            }
            else
                output += youWon.charAt(i);
        }
        return output +"\n";
    }
    /*



     */
}
