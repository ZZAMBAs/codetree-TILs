import java.util.*;
import java.util.stream.*;

public class Main {
    static Node[] nodes = new Node[100001];
    static List<Integer> roots = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);
    static int Q, mid, pid, color, maxDepth, nowTime;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) {
        Q = sc.nextInt();
        IntStream.range(0, Q).forEach(i -> {
            input();
        });

        System.out.print(sb.toString());
    }

    static void input() {
        int cmd = sc.nextInt();
        switch(cmd) {
            case 100:
                addNode(sc.nextInt(), sc.nextInt(), sc.nextInt(), sc.nextInt());
                break;
            case 200:
                changeColor(sc.nextInt(), sc.nextInt());
                break;
            case 300:
                viewColor(sc.nextInt());
                break;
            case 400:
                calScore();
        }
    }

    static void addNode(int id, int pid, int color, int maxDepth) {
        if (pid == -1) {
            Node newNode = new Node(id, maxDepth, color, pid);
            nodes[id] = newNode;
            roots.add(id);

            return;
        }

        // 부모들 최대 depth 체크
        if (nodes[pid].maxDepth - 1 <= 0)
            return;

        Node newNode = new Node(id, Math.min(maxDepth, nodes[pid].maxDepth - 1), color, pid);
        nodes[id] = newNode;
        nodes[pid].children.add(id);
    }

    static void changeColor(int id, int color) {
        nodes[id].color = color;
        nodes[id].colorUpdatedAt = nowTime++;
    }

    static void viewColor(int id) {
        Node realColorNode = nodes[id];
        int pid = realColorNode.pid;
        while(pid != -1) {
            Node par = nodes[pid];
            if (realColorNode.colorUpdatedAt < par.colorUpdatedAt)
                realColorNode = par;
            pid = par.pid;
        }

        nodes[id].color = realColorNode.color;
        nodes[id].colorUpdatedAt = realColorNode.colorUpdatedAt;
        sb.append(nodes[id].color);
        sb.append('\n');
    }

    static void calScore() {
        int res = 0;
        boolean[] visited = new boolean[100001];
        for(int i = 1; i < 100001; i++) {
            if (nodes[i] != null && !visited[i])
                res += cal(i, new int[]{0, 0, 0, 0, 0, 0});
        }

        sb.append(res);
        sb.append('\n');
    }

    private static int cal(int id, int[] arr) {
        if (nodes[id].pid != -1 && nodes[nodes[id].pid].colorUpdatedAt > nodes[id].colorUpdatedAt) {
            nodes[id].colorUpdatedAt = nodes[nodes[id].pid].colorUpdatedAt;
            nodes[id].color = nodes[nodes[id].pid].color;
        }

        for (int nextId : nodes[id].children) {
            int[] arrCopy = Arrays.copyOf(arr, 6);
            cal(nextId, arrCopy);
            IntStream.range(1, 6).forEach(i -> arr[i] |= arrCopy[i]);
        }

        arr[nodes[id].color] = 1;
        int cnt = 0;
        for (int i = 1; i < 6; i++)
            cnt = arr[i] == 1 ? cnt + 1 : cnt;
        return cnt * cnt;
    }

    static class Node {
        int id;
        int maxDepth;
        int color;
        int pid;
        int colorUpdatedAt = nowTime++;
        List<Integer> children = new ArrayList<>();

        Node(int id, int maxDepth, int color, int pid) {
            this.id = id;
            this.maxDepth = maxDepth;
            this.color = color;
            this.pid = pid;
        }
    }
}