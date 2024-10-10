import java.util.*;
import java.util.stream.*;

public class Main {
    static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int n = sc.nextInt();
        int curLoc = 0;
        PriorityQueue<Pair> pq = new PriorityQueue<>(Comparator.comparing(Pair::getX).thenComparing(Pair::getY));

        while (n-- > 0) {
            int x = sc.nextInt();
            String cmd = sc.next();

            int next = curLoc + (cmd.equals("R") ? x : -x);

            pq.add(new Pair(Math.min(curLoc, next), true));
            pq.add(new Pair(Math.max(curLoc, next), false));
            curLoc = next;
        }

        int res = 0, curIdx = pq.peek().x, preIdx = pq.peek().x, curPass = 0;
        while (!pq.isEmpty()) {
            Pair curP = pq.poll();
            
            if (curP.x != curIdx) {
                preIdx = curIdx;
                curIdx = curP.x;
                res += curPass >= 2 ? curIdx - preIdx : 0;
            }

            curPass += curP.y ? 1 : -1;
        }

        System.out.print(res);
    }

    static class Pair {
        int x;
        boolean y;
        Pair(int x, boolean y) {
            this.x = x;
            this.y = y;
        }

        int getX() {
            return x;
        }

        boolean getY() {
            return y;
        }
    }
}