package Optimization;

public class Test_mixedIntegerProgram extends Object {
  
  public static void main(String args[]) {
 
    int numvar = 6;
    int numcontraints = 6;
    int numintvar = 3;
    int constraintype[] = {0, 0, 1, 1, 1, -1, -1, -1};
    double upbound[] = {0.,1.,1.,1.,0.,0.,0.};
    double tableau[][] = {{0.,  0.,  0.,  0.,  0., 0., 0., 0.},
                          {0.,  0., 24., 12., 16., 4., 2., 3.},
                          {0., 15.,  0.,  0.,  0., 1., 3., 0.},
                          {0., 10.,  0.,  0.,  0., 1., 0., 2.},
                          {0., 20.,  0.,  0.,  0., 2., 1., 0.},
                          {0.,  0.,-15.,  0.,  0., 1., 0., 0.},
                          {0.,  0.,  0.,-20.,  0., 0., 1., 0.},
                          {0.,  0.,  0.,  0., -5., 0., 0., 1.},
                          {0.,  0.,  0.,  0.,  0., 0., 0., 0.}};

    Optimize.mixedIntegerProgram(numvar, numcontraints, numintvar, 
                                 upbound, constraintype, tableau);
    if (constraintype[0] > 0)
      System.out.println("No solution found.\nReturn error code = " + 
                         constraintype[0]);
    else {
      System.out.println("Optimal solution found." + "\nSolution vector: ");
      for (int i=1; i<=numvar; i++)
        System.out.printf("%7.2f", tableau[0][i]);
      System.out.printf("\nOptimal objective function value = %6.2f\n",
                        tableau[0][0]);
    }
  }
}

