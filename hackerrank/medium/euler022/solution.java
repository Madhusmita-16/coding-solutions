import java.io.*;
import java.util.*;

public class Solution {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Number of names
        int n = sc.nextInt();

        String[] names = new String[n];

        // Read names
        for (int i = 0; i < n; i++) {
            names[i] = sc.next();
        }

        // Sort names alphabetically
        Arrays.sort(names);

        // Store name -> score
        HashMap<String, Long> scores = new HashMap<>();

        for (int i = 0; i < n; i++) {
            String name = names[i];

            long alphabeticalValue = 0;

            // Calculate alphabetical value
            for (char c : name.toCharArray()) {
                alphabeticalValue += c - 'A' + 1;
            }

            // Position is i + 1 because indexing starts from 0
            long score = alphabeticalValue * (i + 1L);

            scores.put(name, score);
        }

        // Number of queries
        int q = sc.nextInt();

        // Answer each query
        for (int i = 0; i < q; i++) {
            String query = sc.next();
            System.out.println(scores.get(query));
        }

        sc.close();
    }
}
