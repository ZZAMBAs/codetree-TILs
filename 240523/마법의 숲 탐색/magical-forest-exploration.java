import java.util.*;
import java.util.stream.*;

public class Main {
    static Golem[] golems = new Golem[1002];
    static Scanner sc = new Scanner(System.in);
    static int R, C, K, ci, di, res, curNum = 1;
    static int[][] board, dd = { {-1, 0}, {0, 1}, {1, 0}, {0, -1} };

    public static void main(String[] args) {
        R = sc.nextInt(); C = sc.nextInt(); K = sc.nextInt();
        board = new int[R + 3][C];
        IntStream.range(0, K).forEach(i -> {
            ci = sc.nextInt(); di = sc.nextInt();
            res += Golem.run(ci - 1, di);
        });

        System.out.print(res);
    }

    static class Golem {
        static final int DOWN = 0, EAST = 1, WEST = 2;
        static int[][][] checkBoard = {  { {1, -1}, {2, 0}, {1, 1} },
                                    { {-1, 1}, {0, 2}, {1, 1} },
                                    { {-1, -1}, {0, -2}, {1, -1} }
                                };
        static int[][] dl = { {-1, 0}, {0, 1}, {1, 0}, {0, -1} };

        int golemNum = curNum++;
        int r;
        int c;
        int d;

        Golem(int r, int c, int d) {
            this.r = r;
            this.c = c;
            this.d = d;
        }

        static int run(int c, int d) {
            Golem g = new Golem(1, c, d);
            golems[g.golemNum] = g;

            g.move();

            if (g.r <= 2) {
                board = new int[R + 3][C];
                return 0;
            }
            g.print();

            return g.bfs();
        }

        int bfs() {
            int ret = r + 1;
            Queue<Integer> q = new ArrayDeque<>();
            HashSet<Integer> visited = new HashSet<>();
            visited.add(this.golemNum);
            q.add(this.golemNum);

            while(!q.isEmpty()) {
                Golem curG = golems[q.poll()];

                ret = Math.max(ret, curG.r + 1);

                int curGExitR = curG.r + dl[curG.d][0], curGExitC = curG.c + dl[curG.d][1];

                IntStream.range(0, 4).forEach(i -> {
                    int nextR = curGExitR + dl[i][0], nextC = curGExitC + dl[i][1];
                    
                    if (inRange(nextR, nextC) && board[nextR][nextC] != 0 && !visited.contains(Math.abs(board[nextR][nextC]))) {
                        int nextGNum = Math.abs(board[nextR][nextC]);
                        visited.add(nextGNum);
                        q.add(nextGNum);
                    }});
            }

            return ret - 2; // 추가된 행 2 제거
        }

        void move() {
            boolean sw = true;
            while(sw)
                sw = down() ? true : east() ? true : west();
        }

        boolean down() {
            if (!checkNext(DOWN))
                return false;

            this.r += 1;

            return true;
        }

        boolean east() {
            if (!checkNext(EAST))
                return false;

            this.c += 1;

            if (!down()) {
                this.c -= 1;
                return false;
            }
            this.d = (this.d + 1) % 4;
            
            return true;
        }

        boolean west() {
            if (!checkNext(WEST))
                return false;

            this.c -= 1;

            if (!down()) {
                this.c += 1;
                return false;
            }
            this.d = (this.d + 3) % 4;

            return true;
        }

        void print() {
            IntStream.range(0, 4).forEach(i -> board[r + dl[i][0]][c + dl[i][1]] = golemNum);
            board[r][c] = golemNum;
            board[r + dl[d][0]][c + dl[d][1]] *= -1;
        }

        boolean inRange(int r, int c) {
            return r >= 0 && r < R + 3 && c >= 0 && c < C;
        }

        boolean checkNext(int direction) {
            for (int i = 0; i < 3; i++) {
                int nextR = r + checkBoard[direction][i][0];
                int nextC = c + checkBoard[direction][i][1];
                if (!inRange(nextR, nextC) || board[nextR][nextC] != 0)
                    return false;
            }

            return true;
        }
    }
}