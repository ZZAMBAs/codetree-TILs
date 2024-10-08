import java.util.*;

public class Main {
    static final int MUL_VAL = 17;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String N = sc.next();

        if (N.equals("0")) {
            System.out.print(0);
            return;
        }

        int num10 = 0, cur2 = 1;
        Deque stk = new ArrayDeque<>();

        for (int i = N.length() - 1; i >= 0; i--) {
            num10 += cur2 * (N.charAt(i) - 48);
            cur2 *= 2;
        }
        num10 *= MUL_VAL;

        while (num10 > 0) {
            stk.addFirst(num10 & 1);
            num10 /= 2;
        }
        
        while (!stk.isEmpty())
            System.out.print(stk.pollFirst());
    }
}