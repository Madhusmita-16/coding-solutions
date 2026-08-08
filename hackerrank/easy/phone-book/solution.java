import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        ArrayList<Integer> list = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            list.add(sc.nextInt());
        }

        int q = sc.nextInt();

        for (int i = 0; i < q; i++) {

            String operation = sc.next();

            if (operation.equals("Insert")) {
                int index = sc.nextInt();
                int value = sc.nextInt();

                list.add(index, value);

            } else if (operation.equals("Delete")) {
                int index = sc.nextInt();

                list.remove(index);
            }
        }

        for (int value : list) {
            System.out.print(value + " ");
        }

        sc.close();
    }
}
