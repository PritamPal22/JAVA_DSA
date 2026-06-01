import java.util.HashMap;
import java.util.Scanner;

public class LCSMenuDriven {

    static HashMap<String, Integer> memo = new HashMap<>();

    public static int lcsRecursive(String s1, String s2, int n, int m) {
        if (n == 0 || m == 0) {
            return 0;
        }

        if (s1.charAt(n - 1) == s2.charAt(m - 1)) {
            return 1 + lcsRecursive(s1, s2, n - 1, m - 1);
        } else {
            return Math.max(lcsRecursive(s1, s2, n - 1, m), lcsRecursive(s1, s2, n, m - 1));
        }
    }

    public static int lcsMemo(String s1, String s2, int n, int m) {
        if (n == 0 || m == 0) {
            return 0;
        }
        
        String key = n + "," + m;

        if (memo.containsKey(key)) {
            return memo.get(key);
        }

        int result;
        if (s1.charAt(n - 1) == s2.charAt(m - 1)) {
            result = 1 + lcsMemo(s1, s2, n - 1, m - 1);
        } else {
            result = Math.max(lcsMemo(s1, s2, n - 1, m), lcsMemo(s1, s2, n, m - 1));
        }
        
        memo.put(key, result);
        return result;
    }

    public static int lcsDpTable(String s1, String s2, int n, int m) {
        if (n == 0 || m == 0) {
            return 0;
        }

        char[] c1 = s1.toCharArray();
        char[] c2 = s2.toCharArray();

        int[][] dp = new int[n + 1][m + 1];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (c1[i - 1] == c2[j - 1]) dp[i][j] = 1 + dp[i - 1][j - 1];
                else dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
            }
        }
        return dp[n][m];
    }

    public static void main(String[] args) {

        try (Scanner sc = new Scanner(System.in)) {
            while (true) {
                
                System.out.println("\n=== LCS Approaches ===");
                System.out.println("1. Pure Recursion");
                System.out.println("2. Memoization (Top-Down)");
                System.out.println("3. DP Table (Bottom-Up)");
                System.out.println("4. Exit");
                System.out.print("Select an option: ");
                
                int choice = sc.nextInt();
                sc.nextLine(); // Consume newline

                if (choice == 4) {
                    System.out.println("Exiting...");
                    break;
                }

                if (choice < 1 || choice > 4) {
                    System.out.println("Invalid choice.");
                    continue;
                }

                System.out.print("Enter first string: ");
                String s1 = sc.nextLine();

                System.out.print("Enter second string: ");
                String s2 = sc.nextLine();

                int ans = 0;

                switch (choice) {
                    case 1:
                        ans = lcsRecursive(s1, s2, s1.length(), s2.length());
                        break;
                    case 2:
                        // CRITICAL: Purge the static cache before executing
                        memo.clear(); 
                        ans = lcsMemo(s1, s2, s1.length(), s2.length());
                        break;
                    case 3:
                        ans = lcsDpTable(s1, s2, s1.length(), s2.length());
                        break;
                }

                System.out.println("Length of LCS = " + ans);
            }
        }
    }
}