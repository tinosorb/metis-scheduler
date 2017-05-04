package Optimization;

public class Test_dualSimplex extends Object {
  
  public static void main(String args[]) {
  
    int n = 7;
    int m = 3;
    double eps = 1.0e-5;
    double a[][] = {{  0, -5, -3, -3, -6,  0,  0,  0},
                    { 14, -6,  1,  2,  4,  0,  0,  0},
                    {-25,  3, -2, -1, -5,  0,  0,  0}, 
                    { 14, -2,  1,  0,  2,  0,  0,  0}};
    int basicvar[] = new int[n + m + 1];

    Optimize.dualSimplex(true, n, m, a, eps, basicvar);
    if (basicvar[m+1] > 0)
      System.out.println("No feasible solution.");
    else {
      if (basicvar[m+2] > 0)
        System.out.println("Objective function is unbound.");
      else {
        System.out.println("Optimal solution found." + 
                           "\n\n Basic variable   Value");
        for (int i=1; i<=m; i++)
          System.out.printf(" %6d %17.4f\n", basicvar[i], a[i][0]);
        System.out.println("\nOptimal value of the objective function = " +
                           a[0][0]);
      }
    }
  }
}

