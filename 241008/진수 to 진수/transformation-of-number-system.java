import java.util.*;

public class Main {
    static final Scanner sc = new Scanner(System.in);
    static final Deque<Integer> stk = new ArrayDeque<>();

    static int a, b, a10;
    static String n;

    public static void main(String[] args) {
        a = sc.nextInt();
        b = sc.nextInt();
        n = sc.next();

        if (n.equals('0')) {
            System.out.print(0);
            return;
        }

        int curPointValue = 1;
        for (int i = n.length() - 1; i >= 0; i--) {
            a10 += curPointValue * (n.charAt(i) - 48);
            curPointValue *= a;
        }

        while (a10 > 0) {
            stk.addFirst(a10 % b);
            a10 /= b;
        }

        while (!stk.isEmpty())
            System.out.print(stk.pollFirst());
    }
}