import java.util.*;
import java.util.stream.*;

public class Main {
    static final Scanner sc = new Scanner(System.in);

    static int minV, maxV;
    public static void main(String[] args) {
        int n = sc.nextInt();
        int curLoc = 0;
        String cmd = "";
        while (n-- > 0) {
            int x = sc.nextInt() - 1;
            cmd = sc.next();
            int next = curLoc + (cmd.equals("R") ? x : -x);

            minV = Math.min(minV, next);
            maxV = Math.max(maxV, next);

            curLoc = next;
        }

        if (cmd == "R")
            System.out.print((maxV - curLoc) + " " + (curLoc - minV + 1));
        else
            System.out.print((maxV - curLoc + 1) + " " + (curLoc - minV));
    }
}