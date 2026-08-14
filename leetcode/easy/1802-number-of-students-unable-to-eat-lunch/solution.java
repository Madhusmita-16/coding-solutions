class Solution {
    public int countStudents(int[] students, int[] sandwiches) {

        int[] count = new int[2];

        // Count student preferences
        for (int student : students) {
            count[student]++;
        }

        // Process sandwiches from top to bottom
        for (int sandwich : sandwiches) {

            // Nobody wants this sandwich
            if (count[sandwich] == 0) {
                break;
            }

            // One student takes it
            count[sandwich]--;
        }

        // Remaining students cannot eat
        return count[0] + count[1];
    }
}