/* Noah Park

We have jobs: difficulty[i] is the difficulty of the ith job, and profit[i] is the profit of the ith job. 

Now we have some workers. worker[i] is the ability of the ith worker, which means that this worker can only complete a job with difficulty at most worker[i]. 

Every worker can be assigned at most one job, but one job can be completed multiple times.

For example, if 3 people attempt the same job that pays $1, then the total profit will be $3.  If a worker cannot complete any job, his profit is $0.

What is the most profit we can make?

*/

class Job {
    int difficulty;
    int profit;
    
    public Job(int difficulty, int profit){
        this.difficulty = difficulty;
        this.profit = profit;
    }
}

class Solution {
    public int maxProfitAssignment(int[] difficulty, int[] profit, int[] workers) {
        
        int totalProfit = 0;
        int p = 0;
        
        Arrays.sort(workers); // sort the workers
        Job[] jobs = new Job[difficulty.length];
        for(int i = 0; i < difficulty.length; i++)
            jobs[i] = new Job(difficulty[i], profit[i]);
        Arrays.sort(jobs, (a, b) -> Integer.compare(a.difficulty, b.difficulty)); // sort the jobs
        
        for(int worker : workers){
            if(worker < jobs[0].difficulty) continue; // No jobs available for the given worker
            for(int i = 0; i < difficulty.length && jobs[i].difficulty <= worker; i++)
                p = Math.max(p, jobs[i].profit);
            totalProfit += p;       
        }
        
        return totalProfit;
    }
}
