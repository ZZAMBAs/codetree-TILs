import java.util.*;
import java.util.stream.*;

public class Main {
    static final Scanner sc = new Scanner(System.in);
    static final int MAX_ROW = 201;
    static final int MAX_COL = 201;
    static final int OFFSET = 100;

    static int N, res;
    static int[][] map = new int[MAX_ROW][MAX_COL];
    public static void main(String[] args) {
        N = sc.nextInt();
        IntStream.range(0, N).forEach(i -> {
            int sideLen = 8;
            int x = sc.nextInt() + OFFSET, y = sc.nextInt() + OFFSET;

            for (int j = 0; j < sideLen; j++)
                for (int k = 0; k < sideLen; k++)
                    map[x + j][y + k] = 1;
        });
    
        for (int i = 0; i < MAX_ROW; i++)
            for (int j = 0; j < MAX_COL; j++)
                res += map[i][j];
        
        System.out.print(res);
    }
}