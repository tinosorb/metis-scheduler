package GraphAlgorithms;

public class Test_EulerCircuit extends Object {
  
  public static void main(String args[]) {

    int n=6, m=10;
    boolean directed;
    int nodei[] = {0, 4, 1, 6, 1, 4, 1, 2, 2, 1, 3};
    int nodej[] = {0, 2, 5, 4, 2, 3, 6, 5, 6, 4, 6};
    int trail[] = new int[m+1];
    
    directed = false;
    GraphAlgo.EulerCircuit(n, m, directed, nodei, nodej, trail);
    if (trail[0] != 0)
      System.out.println("Input graph is non-Eulerian");
    else {
      System.out.println("Edge numbers of the Euler circuit:");
      for (int i=1; i<=m; i++)
        System.out.print("  " + trail[i]);
      System.out.println();
    }
  }
}

