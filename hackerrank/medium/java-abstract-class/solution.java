import java.io.*;
import java.util.*;

abstract class Book {
    String title;
    abstract void setTitle(String s);
    String getTitle() {
        return title;
    }
}

class MyBook extends Book {
    MyBook(String title) {
        setTitle(title);
    }

    void setTitle(String s) {
        title = s;
    }
}

public class Solution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String title = sc.nextLine();
        Book new_novel = new MyBook(title);
        System.out.print("The title is: " + new_novel.getTitle());
    }
}
