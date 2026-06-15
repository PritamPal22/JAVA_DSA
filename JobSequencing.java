
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

class Job {
    int id;
    int deadline;
    int profit;

    Job(int id, int deadline, int profit) {
        this.id = id;
        this.deadline = deadline;
        this.profit = profit;
    } 
}

public class JobSequencing {

    public static Job[] inputJobs(Scanner sc, int n) {
        Job[] jobs = new Job[n];

        for(int i=0;i<n;i++) {
            System.out.println("\nJob" + (i+1));

            System.out.print("Enter Deadline (Must be >= 1): ");
            int deadline = sc.nextInt();
            if(deadline < 1) deadline = 1;

            System.out.print("Enter Profit: ");
            int profit = sc.nextInt();

            jobs[i] = new Job(i+1, deadline, profit);
        }
        return jobs;
    }

    public static void sortJobs(Job[] jobs) {
        Arrays.sort(jobs, Comparator.comparingInt((Job j) -> j.profit).reversed());
    }

    public static int findMaxDeadline(Job[] jobs) {
        int maxDeadline = 0;

        for(Job job : jobs) {
            if(job.deadline > maxDeadline) maxDeadline = job.deadline;
        }
        return maxDeadline;
    }

    public static Job[] scheduleJobs(Job[] jobs, int maxDeadline) {
        Job[] slots = new Job[maxDeadline];
        for(Job job : jobs) {
            for(int j=job.deadline-1;j>=0;j--) {
                if(slots[j] == null) {
                    slots[j] = job;
                    break;
                }
            }
        }
        return slots;
    }

    public static void displayResult(Job[] slots) {
        int totalprofit = 0;
        System.out.print("\nSelected Jobs: ");
        for(Job job : slots) {
            if(job != null) {
                System.out.print("Job" + job.id + " ");
                totalprofit += job.profit;
            }
        }
        System.out.println("\nTotal Profit = " + totalprofit);
    }

    public static void main(String[] args) {
        int n;
        Job[] jobs;
        try(Scanner sc = new Scanner(System.in)) {
            System.out.print("Enter number of Jobs: ");
            n = sc.nextInt();
            if(n <= 0) {
                System.out.println("Number of Jobs must be greater than 0");
                return;
            }
            jobs = inputJobs(sc, n);
        }
        
        sortJobs(jobs);
        int maxDeadline = findMaxDeadline(jobs);
        Job[] slots = scheduleJobs(jobs, maxDeadline);
        displayResult(slots);
    }
}
