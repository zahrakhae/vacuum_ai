import java.util.*;

public class Vacuum_cleaner {
    private static String[][] board;
    private static final int ROWS = 4;
    private static final int COLUMNS = 4;
    int moves = 0;
    List<String> orientation = Arrays.asList(new String[]{"N", "E", "W", "S"});
    List<String> choices = new ArrayList<String>(3);
//

    public Vacuum_cleaner() {
        //String state = "d";
        board = new String[ROWS][COLUMNS];
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                board[i][j] = " ";

            }
        }
//        board[4][4] = "d";
        board[0][2] = "d";
        board[1][3] = "d";
        board[3][0] = "d";
//        board[4][7] = "d";
//        board[1][5] = "d";
//        board[6][6] = "d";
//        board[4][0] = "d";
    }

    public String toString() {
        String r = "";
        for (int i = 0; i < ROWS; i++) {

            for (int j = 0; j < COLUMNS; j++) {
                r += "[" + board[i][j] + "]";
            }
            r += "\n";
        }
        return r;
    }

    public int[] gostright(int x, int y) {
        if (choices.contains("N")) {
            x--;
            if (x == -1) {
                x = 0;
            }

        } else if (choices.contains("W")) {
            y--;
            if (y == -1) {
                y = 0;
            }

        } else if (choices.contains("S")) {
            x++;
            if (x == 4) {
                x = 3;
            }
        } else if (choices.contains("E")) {
            y++;
            if (y == 4) {
                y = 3;
            }
        }
        System.out.println("choise taste equal" + x + ":" + y);
        return new int[]{x, y};
    }

    public boolean Wall(int x, int y) {

        if (choices.contains("N") && y == 3) {
            return false;
        } else if (choices.contains("S") && y == 0) {
            return false;
        } else if (choices.contains("W") && x == 0) {
            return false;
        } else if (choices.contains("E") && x == 3) {
            return false;
        } else {
            return true;
        }
    }

    public int CountDirt() {
        int count = 0;
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                if (board[i][j] == "d") {
                    count++;
                }
            }
        }
        // System.out.println("the found dirt " + count+" dirts");
        return count;
    }

    public void Move() {
        Collections.shuffle(orientation);
        int nowX = 0, nowY = 0;
        int counter = 0;
        int strightCounter = 1;
        int wallx = 0;
        int wally = 0;
        while (CountDirt() > 0) {
            for (int i = 0; i < ROWS; i++) {
                choices.add(orientation.get(i));
                for (int j =0; j < COLUMNS; j++) {
                    for (int x = 0; x < strightCounter; x++) {
                        for (int y = 0; y < strightCounter; y++) {

                            Wall(x, y);
                            System.out.println("Wall" + x + ":" + y);
                            board[nowX][nowY] = "1";
                            int[] pos = gostright(nowX, nowY);
                            nowX = pos[0];
                            nowY = pos[1];
                            System.out.println("" + nowX + ":" + nowY);
                            System.out.println("nowX and nowY" + board[nowX][nowY]);
                            board[nowX][nowY] = "#";
                            moves++;
                            System.out.println(toString());
                            System.out.println(orientation.get(i));
                            System.out.println("Choices " + choices);
                            System.out.println("# move" + moves);
                        }
                    }
                }
                counter++;
                System.out.println("CountDirt()==" + CountDirt());


            }
            choices.clear();

        }

    }

    public static void main(String[] args) {
        Vacuum_cleaner nnn = new Vacuum_cleaner();
        System.out.println("The Vacuum Cleaner starting to clean the room");
        System.out.println(nnn.toString());
        nnn.Move();
        System.out.println("Mission is done, the room is clean.");
    }
}
