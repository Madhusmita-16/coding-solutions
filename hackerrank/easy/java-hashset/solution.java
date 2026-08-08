import java.io.*;
import java.util.*;

public class Solution {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        int t = s.nextInt();

        HashSet<String> set = new HashSet<>();

        for (int i = 0; i < t; i++) {
            String left = s.next();
            String right = s.next();

            set.add(left + right);

            System.out.println(set.size());
        }

        s.close();
    }
}
