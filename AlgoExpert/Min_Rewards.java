// Noah Park
/*

Problem: Imagine that you're a teacher who's just graded the final exam in a class. You have a
list of student scores on the final exam in a particular order (not necessarily sorted), and you
want to reward your students. You decide to do so fairly by giving them arbitrary rewards following
two rules:

    1) All students must receive at least one reward.
    2) Any given student must receive strictly more rewards than an adjacent student (a student immediately
    to the left or to the right) with a lower score and must receive strictly fewer rewards than an adjacent
    student with a higher score.

Write a function that takes in a list of scores and returns the minimum number of rewards that you must give out
to students to satisfy the two rules.

You can assume that all students have different scores; in other words, the scores are all unique

*/

import java.util.Arrays;

public class Min_Rewards {
    // Optimal Solution
    // Time: O(n) | Space: O(n)
    public static int minRewards(int[] scores) {
        // Simulate finding local mins and maxes by simply applying the algorithm once each way
        int[] rewards = new int[scores.length];
        Arrays.fill(rewards, 1); // Initialize all rewards to 1

        // Go from left to right and increment values that should be larger
        for(int i = 1; i < scores.length; i++){
            if(scores[i] > scores[i - 1]) rewards[i] = rewards[i - 1] + 1;
        }

        // Go from right to left and increment values that should be larger but
        // only applying the maximum of the reward that is currently there and the reward
        // that is in front of it plus 1.
        for(int i = scores.length - 2; i >= 0; i--){
            if(scores[i] > scores[i + 1]) rewards[i] = Math.max(rewards[i], rewards[i + 1] + 1);
        }

        // Get the sum of the rewards and return it
        int sum = 0;
        for(int i = 0; i < rewards.length; i++){
            sum += rewards[i];
        }
        return sum;
    }

    // Naive solution
    // Time: O(n^2) | Space: O(n)
    public static int minRewards2(int[] scores) {
        // Initialize a rewards array with the first value as 1
        int[] rewards = new int[scores.length];
        rewards[0] = 1;

        // Iterate through the rest of the array
        for(int i = 1; i < scores.length; i++){
            // If the scores are increasing, increment it
            if(scores[i] > scores[i - 1]) rewards[i] = rewards[i - 1] + 1;
            else{
                // If the scores are decreasing, mark the current one as 1 and update the
                // prior values
                // Note we use the math.max technique to maintain the property of the problem.
                // We must either keep the value there, as it is already good, or increment the value
                // in front of it to maintain the property of the problem.
                rewards[i] = 1;
                for(int j = i - 1; j >= 0; j--){
                    if(rewards[j] < rewards[j + 1]) break;
                    rewards[j] = Math.max(rewards[j], rewards[j + 1] + 1);
                }
            }
        }

        // Get the sum of the rewards and return it.
        int sum = 0;
        for(int i = 0; i < rewards.length; i++){
            System.out.println(rewards[i]);
            sum += rewards[i];
        }
        return sum;
    }
}
