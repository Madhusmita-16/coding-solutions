import java.util.*;

class Student {
    private int id;
    private String fname;
    private double cgpa;

    public Student(int id, String fname, double cgpa) {
        super();
        this.id = id;
        this.fname = fname;
        this.cgpa = cgpa;
    }

    public int getId() {
        return id;
    }

    public String getFname() {
        return fname;
    }

    public double getCgpa() {
        return cgpa;
    }
}

// Comparator for sorting students
class StudentComparator implements Comparator<Student> {

    @Override
    public int compare(Student s1, Student s2) {

        // 1. CGPA: decreasing order
        if (s1.getCgpa() != s2.getCgpa()) {
            return Double.compare(s2.getCgpa(), s1.getCgpa());
        }

        // 2. First name: alphabetical order
        if (!s1.getFname().equals(s2.getFname())) {
            return s1.getFname().compareTo(s2.getFname());
        }

        // 3. ID: increasing order
        return Integer.compare(s1.getId(), s2.getId());
    }
}

public class Solution {

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        int testCases = Integer.parseInt(in.nextLine());

        List<Student> studentList = new ArrayList<Student>();

        while (testCases > 0) {

            int id = in.nextInt();
            String fname = in.next();
            double cgpa = in.nextDouble();

            Student st = new Student(id, fname, cgpa);
            studentList.add(st);

            testCases--;
        }

        // Sort using comparator
        Collections.sort(studentList, new StudentComparator());

        // Print names
        for (Student st : studentList) {
            System.out.println(st.getFname());
        }

        in.close();
    }
}


