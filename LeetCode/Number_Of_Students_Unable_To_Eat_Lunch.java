/* Noah Park

The school cafeteria offers circular and square sandwiches at lunch break, referred to by numbers 0 and 1 respectively. All students stand in a queue. Each student either prefers square or circular sandwiches.

The number of sandwiches in the cafeteria is equal to the number of students. The sandwiches are placed in a stack. At each step:

If the student at the front of the queue prefers the sandwich on the top of the stack, they will take it and leave the queue.
Otherwise, they will leave it and go to the queue's end.
This continues until none of the queue students want to take the top sandwich and are thus unable to eat.

You are given two integer arrays students and sandwiches where sandwiches[i] is the type of the ith sandwich in the stack (i = 0 is the top of the stack) and students[j] is the preference of the jth student in the initial queue (j = 0 is the front of the queue). Return the number of students that are unable to eat.

*/

class Solution {
    public int countStudents(int[] students, int[] sandwiches) {
        int[] freq = new int[2]; // use counting sort
        for (int student : students) freq[student]++;
        
        // iterate through the sandwiches
        for (int i = 0; i < sandwiches.length; i++) {
            if (freq[sandwiches[i]] > 0) freq[sandwiches[i]]--; // keep decrementing a valid sandwich
            else return sandwiches.length - i; // no more students want this sandwich, we can't go any further
        }
        
        return 0;
    }
    
    // suboptimal solution
    public int countStudentsInitial(int[] students, int[] sandwiches) {
        Queue<Integer> q = new LinkedList<>(); // student queue
        for (int student : students) q.offer(student); // put all students into a queue
        
        int ptr = 0, count = 0; // ptr into sandwiches and count of consecutive students that had to go back in line
        
        while (!q.isEmpty()) {
            int student = q.poll(); // get the current student
            
            if (student == sandwiches[ptr]) { // we have a match
                ptr++;
                count = 0;
            } else { // they went back in line
                count++;
                q.offer(student);
            }
            
            if (count == q.size()) return sandwiches.length - ptr; // no more students can eat
        }
        
        return 0;
    }
}
