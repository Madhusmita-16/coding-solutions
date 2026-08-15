import java.util.*;

class Solution {
    public int minMutation(String startGene, String endGene, String[] bank) {

        Set<String> bankSet = new HashSet<>(Arrays.asList(bank));

        // End gene must be present in the bank
        if (!bankSet.contains(endGene)) {
            return -1;
        }

        Queue<String> queue = new LinkedList<>();
        queue.offer(startGene);

        Set<String> visited = new HashSet<>();
        visited.add(startGene);

        char[] genes = {'A', 'C', 'G', 'T'};

        int mutations = 0;

        while (!queue.isEmpty()) {

            int size = queue.size();

            for (int i = 0; i < size; i++) {

                String current = queue.poll();

                if (current.equals(endGene)) {
                    return mutations;
                }

                char[] chars = current.toCharArray();

                // Change one character at a time
                for (int j = 0; j < 8; j++) {

                    char original = chars[j];

                    for (char gene : genes) {

                        if (gene == original) {
                            continue;
                        }

                        chars[j] = gene;
                        String next = new String(chars);

                        if (bankSet.contains(next) && !visited.contains(next)) {
                            visited.add(next);
                            queue.offer(next);
                        }
                    }

                    // Restore original character
                    chars[j] = original;
                }
            }

            mutations++;
        }

        return -1;
    }
}