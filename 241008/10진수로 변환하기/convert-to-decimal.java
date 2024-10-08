import java.util.*;

public class Main {
    static final Scanner sc = new Scanner(System.in);

    static String inp;
    static int res;

    public static void main(String[] args) {
        inp = sc.next();
        int cur2 = 1;

        for (int i = inp.length() - 1; i >= 0; i--) {
            res += cur2 * (inp.charAt(i) - 48);
            cur2 *= 2;
        }

        System.out.print(res);
    }
}