import java.util.*;
import java.util.stream.*;

public class Main {
    static int Q, cmd, a, b, c, start;
    static Scanner sc = new Scanner(System.in);
    static int[] dist = new int[2001];
    static PriorityQueue<Pair<Integer, Tour>> q;
    static List<List<Pair<Integer, Integer>>> adj = new ArrayList<>();
    static Tour[] tours = new Tour[30001];
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) {
        Q = sc.nextInt();
        while (Q-- > 0) {
            cmd = sc.nextInt();
            switch (cmd) {
                case 100:
                    a = sc.nextInt(); b = sc.nextInt();
                    IntStream.range(0, a).forEach(i -> adj.add(new ArrayList<>()));
                    IntStream.range(0, b).forEach(i -> { 
                        int u = sc.nextInt(), v = sc.nextInt(), w = sc.nextInt();
                        adj.get(u).add(new Pair<>(v, w));
                        adj.get(v).add(new Pair<>(u, w));
                    });
                    changeStartingPoint(0);
                    break;
                case 200:
                    add(sc.nextInt(), sc.nextInt(), sc.nextInt());
                    break;
                case 300:
                    delete(sc.nextInt());
                    break;
                case 400:
                    sb.append(sell());
                    sb.append('\n');
                    break;
                case 500:
                    changeStartingPoint(sc.nextInt());
                    break;
                default:
            }
        }

        System.out.print(sb);
    }

    static void add(int id, int revenue, int dest) {
        tours[id] = new Tour(id, revenue, dest);
        if (tours[id].revenue - dist[tours[id].dest] >= 0) 
            q.add(new Pair<>(tours[id].revenue - dist[tours[id].dest], tours[id]));
    }

    static void delete(int id) {
        if (tours[id] != null)
            tours[id].isDeleted = true;
    }

    static int sell() {
        while (!q.isEmpty() && q.peek().r.isDeleted)
            q.poll();

        if (q.isEmpty())
            return -1;

        Pair<Integer, Tour> polled = q.poll();

        Tour curT = polled.r;
        curT.isDeleted = true;

        return curT.id;
    }

    static void changeStartingPoint(int s) {
        start = s;
        dist = new int[2001];
        Arrays.fill(dist, Integer.MAX_VALUE);
        q = new PriorityQueue<>((p1, p2) -> { 
                if ((int)p1.t != (int)p2.t) 
                    return (int)p2.t - (int)p1.t; 
                return (int)p1.r.id - (int)p2.r.id; 
            });

        PriorityQueue<Pair<Integer, Integer>> dijkstraQ = new PriorityQueue<>((p1, p2) -> p1.t - p2.t); // {거리, id}
        dijkstraQ.add(new Pair<>(0, start));
        while (!dijkstraQ.isEmpty()) {
            Pair<Integer, Integer> curP = dijkstraQ.poll();

            if (dist[curP.r] <= curP.t)
                continue;
            
            dist[curP.r] = curP.t;

            for (Pair nextP : adj.get(curP.r)) {
                int nextDist = curP.t + (int)nextP.r;
                int nextId = (int)nextP.t;

                if (dist[nextId] <= nextDist)
                    continue;
                
                dijkstraQ.add(new Pair<>(nextDist, nextId));
            }
        }

        Arrays.stream(tours).forEach(t -> { 
                if (t != null && t.revenue - dist[t.dest] >= 0) 
                    q.add(new Pair<>(t.revenue - dist[t.dest], t));
            });
    }

    static class Pair<T, R> { // {목적지, 가중치}
        T t;
        R r;

        Pair(T t, R r) {
            this.t = t;
            this.r = r;
        }
    }

    static class Tour {
        int id;
        int revenue;
        int dest;
        boolean isDeleted = false;

        Tour(int id, int revenue, int dest) {
            this.id = id;
            this.revenue = revenue;
            this.dest = dest;
        }
    }
}