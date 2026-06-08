import java.util.Scanner;

public class TowerOfHanoiMemoization {
    
    // static HashMap<Integer, Long> memo = new HashMap<>();

    // static long TOH(int disk) {
    //     if(disk == 1) return 1;

    //     if(memo.containsKey(disk)) return memo.get(disk);
    //     long moves = 2 * TOH(disk - 1) + 1;

    //     memo.put(disk, moves);
    //     return moves;
    // }

    static void solveTOH(int disk, char Source, char Auxillary, char Destination) {
        if(disk == 1) {
            System.out.println("Move Disk 1 from " + Source + " to " + Destination);
            return;
        }
        solveTOH(disk - 1, Source, Destination, Auxillary);
        System.out.println("Move Disk" + disk + " from " + Source + " to " + Destination);
        solveTOH(disk - 1, Auxillary, Source, Destination);
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of disk: ");
        int disk = sc.nextInt();
        
        // long totalmoves = TOH(disk);
        // System.out.println("Minimum number of moves: " + totalmoves);
        System.out.println("\nSteps to solve Tower of Hanoi: ");
        solveTOH(disk, 'S', 'A', 'D');

        sc.close();
    }
}
