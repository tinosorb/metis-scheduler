package Optimization;

public class Test_quadraticProgramming extends Object {
  
  public static void main(String args[]) {

    int k;  
    int n = 3;
    int m = 2;
    int itmax = 10;
    double a[][] = {{0,  0, 0, 0},
                    {0, 13, 7, 5},
                    {0,  1, 2, 1}};
    double b[] = {0, 67, 24};
    double c[][] = {{0,  0,  0,  0},
                    {0,  3, -3, -5},
                    {0, -3,  4,  8},
                    {0, -5,  8,  7}};
    double d[] = {0, -5, -3, -2};
    double sol[] = new double[n+1];
    
    k = Optimize.quadraticProgramming(n, m, a, b, c, d, itmax, sol);
    if (k != 0)
      System.out.println("No solution is found, error code = " + k);
    else {
      System.out.printf("Objective function value = %7.2f\n",sol[0]);
      for (int i=1; i<=n; i++)
        System.out.printf("  x(%d) = %6.3f\n",i,sol[i]);
    }
  }
}

