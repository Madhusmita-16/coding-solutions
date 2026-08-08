import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        BitSet[] sets = new BitSet[3];
        sets[1] = new BitSet(n);
        sets[2] = new BitSet(n);

        StringBuilder out = new StringBuilder();

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            String op = st.nextToken();
            int a = Integer.parseInt(st.nextToken());

            if (op.equals("AND") || op.equals("OR") || op.equals("XOR")) {
                int b = Integer.parseInt(st.nextToken());
                if (op.equals("AND")) {
                    sets[a].and(sets[b]);
                } else if (op.equals("OR")) {
                    sets[a].or(sets[b]);
                } else {
                    sets[a].xor(sets[b]);
                }
            } else {
                int index = Integer.parseInt(st.nextToken());
                if (op.equals("FLIP")) {
                    sets[a].flip(index);
                } else {
                    sets[a].set(index);
                }
            }

            out.append(sets[1].cardinality()).append(" ").append(sets[2].cardinality()).append("\n");
        }

        System.out.print(out);
    }
}
