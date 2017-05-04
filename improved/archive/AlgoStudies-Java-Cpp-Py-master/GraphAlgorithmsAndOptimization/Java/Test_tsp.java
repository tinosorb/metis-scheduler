package GraphAlgorithms;

public class Test_tsp extends Object {
  
  public static void main(String args[]) {
  
    int n = 5;
    int dist[][] = {{0,   0,  0,    0,   0,   0},
  	                {0, 999,  27,  21,  90,  45},
                    {0,  12, 999,  69,  11,  73}, 
                    {0,  50,  14, 999,  55,  26},
                    {0,  55,  66,  71, 999,  42},
                    {0,  96,  34,  84,  52, 999}};
    int sol[] = new int[n + 1];

    GraphAlgo.travelingSalesmanProblem(n, dist, sol);
    System.out.print("Minimum cost cycle: ");
    for (int i=1; i<=n; i++)
      System.out.print("   " + sol[i]);
    System.out.println("\n\nTotal cost = " + dist[0][0]);
  }
}

