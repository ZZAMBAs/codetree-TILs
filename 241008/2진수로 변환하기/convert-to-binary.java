import java.util.*;

public class Main {
    static final Scanner sc = new Scanner(System.in);

    static int n = sc.nextInt();

    public static void main(String[] args) {
        Deque stk = new ArrayDeque<>();

        while (n > 0) {
            stk.addFirst(n & 1);
            n = n >> 1;
        }

        while (!stk.isEmpty())
            System.out.print(stk.pollFirst());
    }
}