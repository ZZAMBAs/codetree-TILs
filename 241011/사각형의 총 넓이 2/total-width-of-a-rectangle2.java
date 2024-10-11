import java.util.*;

public class Main {
    static final Scanner sc = new Scanner(System.in);
    static final int MAX_ROW = 201;
    static final int MAX_COL = 201;
    static final int OFFSET = 100;

    static int N, res;
    static int[][] map = new int[MAX_ROW][MAX_COL];

    public static void main(String[] args) {
        N = sc.nextInt();
        while (N-- > 0) {
            int x1 = sc.nextInt() + OFFSET, y1 = sc.nextInt() + OFFSET, x2 = sc.nextInt() + OFFSET, y2 = sc.nextInt() + OFFSET;

            for (int i = x1; i < x2; i++)
                for (int j = y1; j < y2; j++)
                    map[i][j] = 1;
        }

        for (int i = 0; i < MAX_ROW; i++)
            for (int j = 0; j < MAX_COL; j++)
                res += map[i][j];
        
        System.out.println(res);
    }
}