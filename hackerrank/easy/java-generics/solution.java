import java.io.*;
import java.util.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
        int t = Integer.parseInt(br.readLine().trim());
        Set<String> pairs = new HashSet<>();
        for (int i = 0; i < t; i++) {
            String[] parts = br.readLine().trim().split("\\s+");
            String key;
            if (parts[0].compareTo(parts[1]) <= 0) {
                key = parts[0] + " " + parts[1];
            } else {
                key = parts[1] + " " + parts[0];
            }
            pairs.add(key);
            out.println(pairs.size());
        }
        out.flush();
    }
}
