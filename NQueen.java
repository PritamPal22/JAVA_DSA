import java.util.ArrayList;
import java.util.Scanner;

public class NQueen {

    // Function to solve N-Queen problem
    public static ArrayList<ArrayList<String>> solveNQueen(int n) {

        ArrayList<ArrayList<String>> ans = new ArrayList<>();

        // Hashing arrays
        int[] leftRow = new int[n];
        int[] upperDiagonal = new int[2 * n - 1];
        int[] lowerDiagonal = new int[2 * n - 1];

        // Create empty board
        char[][] board = new char[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = '.';
            }
        }

        solve(0, board, ans, leftRow, upperDiagonal, lowerDiagonal, n);

        return ans;
    }

    // Backtracking function
    public static void solve(int col, char[][] board, ArrayList<ArrayList<String>> ans, int[] leftRow, int[] upperDiagonal, int[] lowerDiagonal, int n) {

        // Base Case
        if (col == n) {

            ArrayList<String> temp = new ArrayList<>();

            for (int i = 0; i < n; i++) {
                temp.add(new String(board[i]));
            }

            ans.add(temp);
            return;
        }

        // Try placing queen in every row
        for (int row = 0; row < n; row++) {

            // Check if safe using hashing
            if (leftRow[row] == 0 &&
                lowerDiagonal[row + col] == 0 &&
                upperDiagonal[n - 1 + col - row] == 0) {

                // Place Queen
                board[row][col] = 'Q';

                leftRow[row] = 1;
                lowerDiagonal[row + col] = 1;
                upperDiagonal[n - 1 + col - row] = 1;

                // Recursive call
                solve(col + 1, board, ans,
                        leftRow, upperDiagonal, lowerDiagonal, n);

                // Backtracking
                board[row][col] = '.';

                leftRow[row] = 0;
                lowerDiagonal[row + col] = 0;
                upperDiagonal[n - 1 + col - row] = 0;
            }
        }
    }

    public static void printSolutions(ArrayList<ArrayList<String>> ans) {

        int count = 1;

        for (ArrayList<String> solution : ans) {

            System.out.println("Solution " + count + ":");

            for (String row : solution) {

                for (char ch : row.toCharArray()) {

                    System.out.print(ch + " ");
                }

                System.out.println();
            }

            System.out.println();
            count++;
        }
    }

    public static void main(String[] args) {

        int n;
        try (Scanner sc = new Scanner(System.in)) {
            System.out.print("Enter value of N: ");
            n = sc.nextInt();
        }

        ArrayList<ArrayList<String>> ans = solveNQueen(n);

        if (ans.isEmpty()) {
            System.out.println("No Solution Exists");
        } else {
            System.out.println("\nTotal Solutions: " + ans.size());
            System.out.println();

            printSolutions(ans);
        }
    }
}