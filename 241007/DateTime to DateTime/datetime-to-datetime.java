import java.util.*;
import java.util.stream.*;

public class Main {
    static final Scanner sc = new Scanner(System.in);
    static final int FIXED_NUM = 11;

    static int a, b, c, res;
    public static void main(String[] args) {
        a = sc.nextInt();
        b = sc.nextInt();
        c = sc.nextInt();

        if (a < FIXED_NUM || (a == FIXED_NUM && b < FIXED_NUM) || (a == FIXED_NUM && b == FIXED_NUM && c < FIXED_NUM)) {
            res = -1;
        } else {
            res = (24 * 60) * (a - FIXED_NUM) + 60 * (b - FIXED_NUM) + (c - FIXED_NUM);
        }

        System.out.print(res);
    }
}