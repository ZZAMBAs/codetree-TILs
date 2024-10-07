import java.util.*;

public class Main {
    static final Scanner sc = new Scanner(System.in);

    static int a, b, c, d;
    public static void main(String[] args) {
        a = sc.nextInt();
        b = sc.nextInt();
        c = sc.nextInt();
        d = sc.nextInt();

        System.out.println((c - a) * 60 + d - b);
    }
}