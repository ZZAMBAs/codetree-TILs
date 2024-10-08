import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt(), B = sc.nextInt();
        Deque stk = new ArrayDeque<>();

        while (N > 0) {
            stk.addFirst(N % B);
            N /= B;
        }

        while (!stk.isEmpty())
            System.out.print(stk.pollFirst());
    }
}