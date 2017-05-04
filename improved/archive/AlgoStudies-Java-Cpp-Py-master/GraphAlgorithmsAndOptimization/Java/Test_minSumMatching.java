package GraphAlgorithms;

public class Test_minSumMatching extends Object {

  public static void main(String args[]) {
  
    int n = 8;
    double weight[][] = {{0., 0., 0., 0., 0., 0., 0., 0., 0.},
                         {0., 0.,24.,16.,18.,17.,16.,23.,25.},
                         {0., 0., 0.,15.,22.,24.,23.,18.,16.},
                         {0., 0., 0., 0.,24.,17.,16.,25.,24.},
                         {0., 0., 0., 0., 0.,22.,23.,24.,15.},
                         {0., 0., 0., 0., 0., 0.,24.,15.,16.},
                         {0., 0., 0., 0., 0., 0., 0.,18.,17.},
                         {0., 0., 0., 0., 0., 0., 0., 0.,25.},
                         {0., 0., 0., 0., 0., 0., 0., 0., 0.}};
    int sol[] = new int[n + 1];

    GraphAlgo.minSumMatching(n, weight, sol);
    System.out.println("Optimal matching:");
    for (int i=1; i<=n; i++)
      System.out.println("  " + i + " -- " + sol[i]);
    System.out.println("\nTotal optimal matching cost = " + weight[0][0]);
  }
}

 