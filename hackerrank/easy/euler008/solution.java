import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;



public class Solution {

    static long largestProduct(String num, int k) {
        long maxProduct = 0;
        int n = num.length();
        for (int i = 0; i + k <= n; i++) {
            long product = 1;
            for (int j = i; j < i + k; j++) {
                product *= (num.charAt(j) - '0');
            }
            if (product > maxProduct) {
                maxProduct = product;
            }
        }
        return maxProduct;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(bufferedReader.readLine().trim());

        IntStream.range(0, t).forEach(tItr -> {
            try {
                String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

                int n = Integer.parseInt(firstMultipleInput[0]);

                int k = Integer.parseInt(firstMultipleInput[1]);

                String num = bufferedReader.readLine();

                System.out.println(largestProduct(num, k));
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        bufferedReader.close();
    }
}
