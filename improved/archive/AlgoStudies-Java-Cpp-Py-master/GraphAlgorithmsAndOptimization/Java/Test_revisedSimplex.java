package Optimization;

public class Test_revisedSimplex extends Object {
  
  public static void main(String args[]) {
  
    int n = 7;
    int m = 4;
    double eps = 1.0e-5;
    double a[][] = {{0,  5,  5,  3,  0,  0,  0, 0},
                    {3,  1,  3,  1,  1,  0,  0, 0},
                    {2, -1,  0,  3,  0,  1,  0, 0}, 
                    {4,  2, -1,  2,  0,  0,  1, 0},
                    {2,  2,  3, -1,  0,  0,  0, 1}};
    int basicvar[] = new int[m + 3];

    Optimize.revisedSimplex(true, n, m, a, eps, basicvar);
    if (basicvar[m+1] > 0)
      System.out.println("No feasible solution.");
    else {
      if (basicvar[m+2] > 0)
        System.out.println("Objective function is unbound.");
      else {
        System.out.println("Optimal solution found." + 
                           "\n\n Basic variable   Value");
        for (int i=1; i<=m; i++)
          System.out.printf(" %6d %17.5f\n", basicvar[i], a[i][0]);
        System.out.println("\nOptimal value of the objective function = " +
                           a[0][0]);
      }
    }
  }
}

