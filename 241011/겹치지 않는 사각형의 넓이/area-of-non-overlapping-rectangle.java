import java.util.*;
import java.util.stream.*;

public class Main {
    static final Scanner sc = new Scanner(System.in);

    static int res;
    static int[][] rectInfo = new int[3][4]; // []{x1, y1, x2, y2}

    public static void main(String[] args) {
        IntStream.range(0, rectInfo.length).forEach(i -> {
            for (int j = 0; j < 4; j++)
                rectInfo[i][j] = sc.nextInt();
        });

        res += spaceDiff(rectInfo[0], rectInfo[2]);
        res += spaceDiff(rectInfo[1], rectInfo[2]);

        System.out.print(res);
    }

    static int spaceDiff(int[] r1, int[] r2) {
        int diffX1 = Math.max(r1[0], Math.min(r1[2], r2[0]));
        int diffY1 = Math.max(r1[1], Math.min(r1[3], r2[1]));
        int diffX2 = Math.max(r1[0], Math.min(r1[2], r2[2]));
        int diffY2 = Math.max(r1[1], Math.min(r1[3], r2[3]));

        int rectSpace = (r1[2] - r1[0]) * (r1[3] - r1[1]);
        int diffSpace = (diffX2 - diffX1) * (diffY2 - diffY1);

        return rectSpace - diffSpace;
    }

    static int calSpace(int x1, int y1, int x2, int y2) {
        return (x2 - x1) * (y2 - y1);
    }
}