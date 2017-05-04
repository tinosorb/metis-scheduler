package Optimization;

public class Test_zeroOneProgram extends Object {
  
  public static void main(String args[]) {
  
    int n = 4;
    int m = 3;
    int a[][] = {{0,  0,    0,   0,   0},
                 {0, -10, -13, -11, -23},
                 {0,  -4,  -6, -11, -16}, 
                 {0, -12, -10,  -5,  -9}};
    int b[] = {0, -10, -12, -8};
    int c[] = {0,  12,  14,  23, 36};
    int sol[] = new int[n + 1];

    Optimize.zeroOneIntegerProgramming(true, n, m, a, b, c, sol);
    if (a[0][0] > 0)
      System.out.println("No feasible solution.");
    else {
      System.out.print("Optimal solution found.\n Solution vector: ");
      for (int i=1; i<=n; i++)
        System.out.print("  " + sol[i]);
      System.out.println("\n Optimal value of the objective function = " +
                         sol[0]);
    }
  }
}

