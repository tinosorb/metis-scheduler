package Optimization;

public class Test_setPartitioning extends Object {

  public static void main(String args[]) {
  
    int n = 6;
    int m = 4;
    int a[][] = new int[m+1][n+1];
    int c[] = {0, 4, 2, 1, 6, 5, 2};
    int sol[] = new int[n + 1];

    a[1][1] = a[1][3] = a[1][6] = 1;
    a[2][2] = a[2][3] = a[2][5] = a[2][6] = 1;
    a[3][3] = a[3][4] = a[3][5] = 1;
    a[4][1] = a[4][2] = a[4][4] = 1;
    Optimize.setPartitioning(n, m, a, c, sol);
    if (a[0][0] > 0)
      System.out.println("No feasible solution.");
    else {
      System.out.print("Optimal solution found\n\n Solution vector: ");
      for (int i=1; i<=n; i++)
        System.out.print("  " + sol[i]);
      System.out.println("\n\nOptimal objective function value = " + sol[0]);
    }
  }
}

