package GraphAlgorithms;

public class Test_minimumSpanningTreePrim extends Object {
  
  public static void main(String args[]) {

    int n=6;
    int tree[] = new int[n+1];
    int dist[][] = {{0,  0,  0,  0,  0,  0,  0},
                    {0,  0, 20, 99, 99, 30, 30},
                    {0, 20,  0, 99, 20, 99, 30},
                    {0, 40, 99,  0, 99, 20, 40},
                    {0, 99, 20, 99,  0, 99, 40},
                    {0, 30, 99, 20, 99,  0, 99},
                    {0, 30, 30, 40, 40, 99,  0}}; 

    GraphAlgo.minimumSpanningTreePrim(n, dist, tree);
    System.out.println("Minimum spanning tree edges: "); 
    for (int i=1; i<=n-1; i++)
      System.out.println("  " + i + " " + tree[i]);
  }
}

