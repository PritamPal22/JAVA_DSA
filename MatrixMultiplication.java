
import java.util.Scanner;

public class MatrixMultiplication {
    
    static int r1, c1, r2, c2;
    static float [][] M1, M2, result;

    public static void EnterElement(Scanner sc) {
        System.out.println("Enter Matrix1 Elements: ");
        for(int i=0;i<r1;i++) {
            for(int j=0;j<c1;j++) {
                System.out.print("Element[" + (i+1) + "][" + (j+1) + "]:" );
                M1[i][j] = sc.nextFloat();
            }
        }
        System.out.println("Enter Matrix2 Elements: ");
        for(int i=0;i<r2;i++) {
            for(int j=0;j<c2;j++) {
                System.out.print("Element[" + (i+1) + "][" + (j+1) + "]:" );
                M2[i][j] = sc.nextFloat();
            }
        }
    }

    public static void Multiplication() {
        result = new float[r1][c2];
        for(int i=0;i<r1;i++) {
            for(int j=0;j<c2;j++) {
                float sum = 0;
                for(int k=0;k<c1;k++) {
                    sum += M1[i][k] * M2[k][j];
                }
                result[i][j] = sum;
            }
        }
    }

    public static void printMatrix() {
        System.out.println("==========Matrix1==========");
        for(int i=0;i<r1;i++) {
            for(int j=0;j<c1;j++) {
                System.out.print(M1[i][j] +"\t");
            }
            System.out.println("");
        }
        System.out.println("==========Matrix2==========");
        for(int i=0;i<r2;i++) {
            for(int j=0;j<c2;j++) {
                System.out.print(M2[i][j] +"\t");
            }
            System.out.println("");
        }
        System.out.println("==========Multiplication Result==========");
        for(int i=0;i<r1;i++) {
            for(int j=0;j<c2;j++) {
                System.out.print(result[i][j] +"\t");
            }
            System.out.println("");
        }
    }
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        while(true) { 

            System.out.print("Enter Matrix1 Row: ");
            r1 = sc.nextInt();

            System.out.print("Enter Matrix1 Column: ");
            c1 = sc.nextInt();

            System.out.print("Enter Matrix2 Row: ");
            r2 = sc.nextInt();

            System.out.print("Enter Matrix2 Column: ");
            c2 = sc.nextInt();

            if(c1 == r2) {
                M1 = new float[r1][c1];
                M2 = new float[r2][c2];
                break;
            }
            System.out.println("Invalid Choice!");
            System.out.println("Column of First Matrix must be same of Row of second matrix");
        }

        

        while(true) { 
            System.out.println("Enter Choice c/C to continue or q/Q to Quit: ");
            String choice = sc.next();
            switch(choice.toLowerCase()) {
                case "c" -> {
                    EnterElement(sc);
                    Multiplication();
                    printMatrix();
                }
                case "q" -> {
                    System.out.println("Programme Ended......");
                    sc.close();
                    return;
                }
                default -> {
                    System.out.println("Invalid Choice.");
                    System.out.println("Enter only c/C or q/Q:");
                }
            }
        }
    }
}
