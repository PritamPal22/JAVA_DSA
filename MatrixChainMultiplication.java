
import java.util.Scanner;

public class MatrixChainMultiplication {
    static int[][] dpTable;
    static int[][] kTable;

    public static void initializeTable(int n) {
        for(int i=1;i<=n;i++) {
            dpTable[i][i] = 0;
        }
    }

    public static void matrixChainOrder(int p[], int n) {
        for(int L=2;L<=n;L++) {
            for(int i=1;i<=n-L+1;i++) {
                int j = i+L-1;
                dpTable[i][j] = Integer.MAX_VALUE;

                for(int k=i;k<j;k++) {
                    int cost = dpTable[i][k] + dpTable[k+1][j] + p[i-1]*p[k]*p[j];
                    if(cost < dpTable[i][j]) {
                        dpTable[i][j] = cost;

                        // store k Value
                        kTable[i][j] = k;
                    }
                }
            }
        }
    }

    static void printParenthesis(int i, int j) {
        if(i==j) {
            System.out.print("A" + i);
        } else {
            System.out.print("(");
            printParenthesis(i, kTable[i][j]);
            printParenthesis(kTable[i][j] + 1, j);
            System.out.print(")");
        }
    }

    static void printDPTable(int n) {
        System.out.println("\nDP Table (Minimum Cost Table):");
        for(int i=1;i<=n;i++) {
            for(int j=1;j<=n;j++) {
                System.out.print(dpTable[i][j] + "\t");
            }
            System.out.println("");
        }
    }

    static void printKTable(int n) {
        System.out.println("\nK Table (Partition Table):");
        for(int i=1;i<=n;i++) {
            for(int j=1;j<=n;j++) {
                System.out.print(kTable[i][j] + "\t");
            }
            System.out.println("");
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of Matrices: ");
        int n = sc.nextInt();

        int p[] = new int[n+1];

        System.out.println("Enter Dimensios Arrray: ");

        for(int i=0;i<=n;i++) {
            p[i] = sc.nextInt();
        }

        dpTable = new int[n+1][n+1];
        kTable = new int[n+1][n+1];

        // Initialize Diagonal
        initializeTable(n);

        // Calculate DP
        matrixChainOrder(p, n);

        // Final answer
        System.out.println("\nMinimal Multiplication cost = " + dpTable[1][n]);

        System.out.print("Optimal Parenthesis : ");
        printParenthesis(1, n);
        System.out.println("");

        // Print Tables
        printDPTable(n);
        printKTable(n);

        sc.close();
    }
}