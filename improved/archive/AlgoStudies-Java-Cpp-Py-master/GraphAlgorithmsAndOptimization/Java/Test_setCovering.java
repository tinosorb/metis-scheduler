package Optimization;

public class Test_setCovering extends Object {
  
  public static void main(String args[]) {
  
    int n = 5;
    int m = 4;
    int a[][] = new int[m+1][n+1];
    int c[] = {0, 7, 2, 5, 8, 6};
    int sol[] = new int[n + 1];

    a[1][1] = a[1][3] = a[1][4] = 1;
    a[2][2] = a[2][4] = 1;
    a[3][1] = a[3][5] = 1;
    a[4][3] = a[4][5] = 1;
    Optimize.setCovering(n, m, a, c, sol);
    if (a[0][0] > 0)
      System.out.println("No feasible solution.");
    else {
      System.out.print("Optimal solution found.\n\n Solution vector: ");
      for (int i=1; i<=n; i++)
        System.out.print("  " + sol[i]);
      System.out.println("\n\nOptimal objective function value = " + sol[0]);
    }
  }
}

