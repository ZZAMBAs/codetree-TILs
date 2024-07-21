import java.util.*;
import java.util.stream.*;

public class Main {
    static Scanner sc = new Scanner(System.in);
    static int[][] map = new int[5][5], tempMap = new int[5][5], dl = { {1, 0}, {0, 1}, {0, -1}, {-1, 0} };
    static int[] additionalBlocks;
    static List<Integer> results = new ArrayList<>();
    static int K, M, addBlocksIdx;

    public static void main(String[] args) {
        K = sc.nextInt();
        M = sc.nextInt();
        for (int i = 0; i < 5; i++)
            for (int j = 0; j < 5; j++)
                map[i][j] = sc.nextInt();
        additionalBlocks = IntStream.range(0, M).map(i -> sc.nextInt()).toArray();

        while (K-- > 0) {
            List<Info> infos = new ArrayList<>();

            for (int i = 1; i <= 3; i++)
                for (int j = 1; j <= 3; j++)
                    for (int rotateNum = 1; rotateNum <= 3; rotateNum++) {
                        rotate(i, j, rotateNum);
                        Info info = findVal(i, j, rotateNum);

                        if (info.getVal() <= 0)
                            continue;

                        infos.add(info);
                    }

            if (infos.isEmpty())
                break;

            infos.sort(Comparator.comparing(Info::getVal).reversed().thenComparing(Info::getDegree).thenComparing(Info::getMidCol).thenComparing(Info::getMidRow));

            Info maxInfo = infos.get(0);

            continueValSum(maxInfo);
        }

        System.out.print(results.stream().map(String::valueOf).collect(Collectors.joining(" ")));
    }

    static void continueValSum(Info maxInfo) {
        fill(maxInfo.map);
        int addVal = getValByBfs(maxInfo.map);
        while (addVal > 0) {
            maxInfo.val += addVal;

            fill(maxInfo.map);
            addVal = getValByBfs(maxInfo.map);
        }

        mapCopy(maxInfo.map, map);
        results.add(maxInfo.getVal());
    }

    static Info findVal(int midRow, int midCol, int rotateNum) {
        int valSum = getValByBfs(tempMap);

        Info info = new Info(valSum, rotateNum, midRow, midCol);
        mapCopy(tempMap, info.map);

        return info;
    }

    static int getValByBfs(int[][] map) {
        int valSum = 0;

        boolean[][] visited = new boolean[5][5];

        for (int i = 0; i < 5; i++)
            for (int j = 0; j < 5; j++)
                if (!visited[i][j])
                    valSum += bfs(i, j, visited, map);
        return valSum;
    }

    static void rotate(int midRow, int midCol, int rotateNum) {
        mapCopy(map, tempMap);
        int[][] temp3x3 = new int[3][3];

        while (rotateNum-- > 0) {
            for (int i = 0; i < 3; i++)
                for (int j = 0; j < 3; j++) {
                    int curR = i + midRow - 1;
                    int curC = j + midCol - 1;

                    temp3x3[curC - midCol + 1][-curR + midRow + 1] = tempMap[curR][curC];
                }

            for (int i = 0; i < 3; i++)
                for (int j = 0; j < 3; j++)
                    tempMap[i + midRow - 1][j + midCol - 1] = temp3x3[i][j];

        }

        /*
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++)
                System.out.print(temp3x3[i][j] + " ");
            System.out.println();
        }

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++)
                System.out.print(tempMap[i][j] + " ");
            System.out.println();
        }*/
    }

    static int bfs(int r, int c, boolean[][] visited, int[][] map) {
        Queue<Pair> q = new ArrayDeque<>();
        q.add(new Pair(r, c));
        visited[r][c] = true;
        List<Pair> coor = new ArrayList<>();

        while (!q.isEmpty()) {
            int qSize = q.size();
            for (int i = 0; i < qSize; i++) {
                Pair curP = q.poll();
                coor.add(curP);

                for (int[] d : dl) {
                    int nextR = curP.r + d[0];
                    int nextC = curP.c + d[1];

                    if (inRange(nextR, nextC) && !visited[nextR][nextC] && map[nextR][nextC] == map[r][c]) {
                        visited[nextR][nextC] = true;
                        q.add(new Pair(nextR, nextC));
                    }
                }
            }
        }

        /*if (r == 0 && c == 1) {
            coor.forEach(coord -> System.out.print(coord.r + ", " + coord.c + " "));
            System.out.println();
        }*/

        if (coor.size() >= 3)
            coor.forEach(p -> map[p.r][p.c] = 0);

        return coor.size() >= 3 ? coor.size() : 0;
    }

    static boolean inRange(int r, int c) {
        return r >= 0 && r < 5 && c >= 0 && c < 5;
    }

    static void fill(int[][] map) {
        for (int j = 0; j < 5; j++)
            for (int i = 4; i >= 0; i--)
                if (map[i][j] == 0)
                    map[i][j] = additionalBlocks[addBlocksIdx++];
    }

    static void mapCopy(int[][] original, int[][] forCopy) {
        for (int i = 0; i < 5; i++)
            for (int j = 0; j < 5; j++)
                forCopy[i][j] = original[i][j];
    }

    static class Pair {
        int r;
        int c;

        Pair (int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    static class Info {
        int val;
        int degree;
        int midRow;
        int midCol;
        int[][] map = new int[5][5];

        public Info(int val, int degree, int midRow, int midCol) {
            this.val = val;
            this.degree = degree;
            this.midRow = midRow;
            this.midCol = midCol;
        }

        public int getVal() {
            return val;
        }

        public int getDegree() {
            return degree;
        }

        public int getMidRow() {
            return midRow;
        }

        public int getMidCol() {
            return midCol;
        }
    }
}