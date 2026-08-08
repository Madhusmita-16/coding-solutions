import java.io.*;
import java.util.*;

public class Solution {

    static class Pair {
        String first;
        String second;

        Pair(String first, String second) {
            this.first = first;
            this.second = second;
        }

        public int hashCode() {
            return Objects.hash(first, second);
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof Pair)) {
                return false;
            }

            Pair p = (Pair) obj;
            return first.equals(p.first) && second.equals(p.second);
        }
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int t = scan.nextInt();
        HashSet<Pair> set = new HashSet<>();

        for (int i = 0; i < t; i++) {
            String a = scan.next();
            String b = scan.next();

            set.add(new Pair(a, b));

            System.out.println(set.size());
        }

        scan.close();
    }
}
