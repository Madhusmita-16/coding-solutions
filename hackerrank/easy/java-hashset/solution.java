import java.io.*;
import java.util.*;

public class Solution {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine().trim());

        Set<String> pairs = new HashSet<>();
        StringBuilder output = new StringBuilder();

        for (int i = 0; i < t; i++) {
            String line = br.readLine().trim();  // trim kills \r and stray spaces
            String[] parts = line.split(" ");
            String key = parts[0] + " " + parts[1];
            pairs.add(key);
            output.append(pairs.size()).append("\n");
        }

        System.out.print(output);
    }
}
