import java.util.*;
import java.util.stream.*;

public class Main {
    static final Scanner sc = new Scanner(System.in);
    static final int START_POINT = 1000000;
    static final int BLACK = 2;
    static final int WHITE = 1;

    static int[][] tiles = new int[3][2000001]; // [0]: 현재 색, [1]: 흰색 횟수, [2]: 검은색 횟수

    public static void main(String[] args) {
        int n = sc.nextInt();
        int curLoc = START_POINT;
        int resW = 0, resB = 0, resG = 0;
        while (n-- > 0) {
            int x = sc.nextInt() - 1;
            String cmd = sc.next();
            int color = cmd.equals("R") ? BLACK : WHITE;
            int next = curLoc + (cmd.equals("R") ? x : -x);

            for (int i = Math.min(curLoc, next); i <= Math.max(curLoc, next); i++) {
                tiles[0][i] = color;
                tiles[color][i]++;
            }
            curLoc = next;
        }

        for (int i = 0; i < tiles[0].length; i++) {
            if (tiles[BLACK][i] >= 2 && tiles[WHITE][i] >= 2)
                resG++;
            else {
                switch(tiles[0][i]) {
                    case BLACK:
                        resB++;
                        break;
                    case WHITE:
                        resW++;
                    default:
                        break;
                }
            }
        }

        System.out.print(resW + " " + resB + " " + resG);
    }
}