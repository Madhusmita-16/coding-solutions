import java.util.*;
import java.io.*;

class Solution {
    public static void main(String[] argh) {

        Scanner in = new Scanner(System.in);

        int n = in.nextInt();
        in.nextLine();

        HashMap<String, String> phoneBook = new HashMap<>();

        for (int i = 0; i < n; i++) {
            String name = in.nextLine();
            String phone = in.nextLine();

            phoneBook.put(name, phone);
        }

        while (in.hasNextLine()) {
            String name = in.nextLine();

            if (phoneBook.containsKey(name)) {
                System.out.println(name + "=" + phoneBook.get(name));
            } else {
                System.out.println("Not found");
            }
        }

        in.close();
    }
}



