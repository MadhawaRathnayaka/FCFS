import java.util.Scanner;

public class FirstComeFirstServe {
    Scanner console = new Scanner(System.in);
    int processes;
    String[] pids;
    int[] burstTimes;
    int[] completionTimes;
    int[] waitingTimes;
    int[] turnAroundTimes;
    double totalTurnArroundTime = 0;
    double totalWaitingTime = 0;

    public FirstComeFirstServe() {
        System.out.print("Enter number of processes : ");
        this.processes = console.nextInt();

        pids = new String[processes];
        burstTimes = new int[processes];
        completionTimes = new int[processes];
        waitingTimes = new int[processes];
        turnAroundTimes = new int[processes];
    }

    void run() {
        for (int i = 0; i < processes; i++) {
            System.out.print("Enter PID of process " + (i + 1) + " : ");
            pids[i] = console.next();
        }
        System.out.println();

        for (int i = 0; i < processes; i++) {
            System.out.print("Enter burst time of process " + pids[i] + " : ");
            burstTimes[i] = console.nextInt();
        }
        System.out.println();

        for (int i = 0; i < processes; i++) {
            // calculate times;
            int completionTime = i == 0 ? burstTimes[i] : completionTimes[i - 1] + burstTimes[i];
            int turnAroundTime = completionTime;
            int waitingTime = turnAroundTime - burstTimes[i];

            completionTimes[i] = completionTime;
            turnAroundTimes[i] = turnAroundTime;
            waitingTimes[i] = waitingTime;

            totalWaitingTime += waitingTime;
            totalTurnArroundTime += turnAroundTime;
        }

        // print
        System.out.println("PID" + "\t" + "BT" + "\t" + "CT" + "\t" + "TAT" + "\t" + "WT");
        for (int i = 0; i < processes; i++) {
            System.out.println(pids[i] + "\t" + burstTimes[i] + "\t" + completionTimes[i] + "\t"
                    + turnAroundTimes[i] + "\t" + waitingTimes[i]);
        }
        System.out.println();

        System.out.println("Avaerage waiting time " + totalWaitingTime / processes);
        System.out.println("Avaerage turn arround time " + totalTurnArroundTime / processes);
    }
}
