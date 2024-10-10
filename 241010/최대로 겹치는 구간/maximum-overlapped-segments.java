import java.util.*;
import java.util.stream.*;

public class Main {
    static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int n = sc.nextInt();
        PriorityQueue<Pair> pq = new PriorityQueue<>(Comparator.comparing(Pair::getX));
        while (n-- > 0) {
            pq.add(new Pair(sc.nextInt(), true));
            pq.add(new Pair(sc.nextInt(), false));
        }

        int res = 0, cur = 0;
        while (!pq.isEmpty()) {
            cur += pq.poll().y ? 1 : -1;
            res = Math.max(res, cur);
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
    }
}