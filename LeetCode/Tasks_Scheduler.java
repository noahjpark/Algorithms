/* Noah Park

Given a characters array tasks, representing the tasks a CPU needs to do, where each letter represents a different task. Tasks could be done in any order. Each task is done in one unit of time. For each unit of time, the CPU could complete either one task or just be idle.

However, there is a non-negative integer n that represents the cooldown period between two same tasks (the same letter in the array), that is that there must be at least n units of time between any two same tasks.

Return the least number of units of times that the CPU will take to finish all the given tasks.

*/

class Solution {
    public int leastInterval(char[] taskslist, int n) {
        if (taskslist == null || taskslist.length == 0) return 0; // edge cases
        
        Map<Character, Integer> map = new HashMap<>();
        for (Character c : taskslist) map.put(c, map.getOrDefault(c, 0) + 1); // put frequencies of all tasks together
        
        // store in a max heap
        PriorityQueue<Map.Entry<Character, Integer>> maxHeap = new PriorityQueue<>((a, b) -> Integer.compare(b.getValue(), a.getValue()));
        maxHeap.addAll(map.entrySet());
        
        int units = 0; // total time units
        
        // iterate until we complete all tasks
        while (!maxHeap.isEmpty()) {
            List<Map.Entry<Character, Integer>> todo = new ArrayList<>(); // this will hold tasks we need to still complete
            int tasks = n + 1; // there needs to be n units before completing a task again so we buffer by 1
            for (; tasks > 0 && !maxHeap.isEmpty(); tasks--) { // since we want to do the task with the maximum frequency as soon as possible, we either go until the heap is empty or we can repeat a task again
                Map.Entry<Character, Integer> task = maxHeap.poll();
                units++;
                if (task.getValue() > 1) { // decrement task frequency and add to todo list
                    task.setValue(task.getValue() - 1);
                    todo.add(task);
                }
            }
            maxHeap.addAll(todo); // add all we still need to complete back to the list
            if (!maxHeap.isEmpty()) units += tasks; // if the heap is not empty, we have to wait 'tasks' time until we can repeat tasks
        }
        
        return units;
    }
}
