package Optimization;

public class Test_integerProgram extends Object {
  
  public static void main(String args[]) {
  
    int n = 3;
    int m = 2;
    int a[][] = {{0,  3,  3,  4,   0},
                 {0, -2, -2, -3, -12},
                 {0, -4, -1, -1, -10}, 
                 {0,  0,  0,  0,   0},
                 {0,  0,  0,  0,   0},
                 {0,  0,  0,  0,   0}};

    Optimize.integerProgramming(n, m, a);
    if (a[0][n+1] > 0)
      System.out.println("No feasible solution.");
    else {
      System.out.print("Optimal solution found.\n Solution: ");
      for (int i=1; i<=n; i++)
        System.out.print("  " + a[m+i][0]);
      System.out.println("\n Optimal value of the objective function = " +
                         a[0][0]);
    }
  }
}

