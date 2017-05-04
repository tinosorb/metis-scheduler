package GraphAlgorithms;

public class Test_shortestPathTree extends Object {
  
  public static void main(String args[]) {

    int n = 6;
    int m = 11;
    int root = 5;
    int treearc[] = new int[n+1];
    int mindistance[] = new int[n+1];
    int nodei[] =  {0,4,6,5,3,5, 1,3,6,1, 2,5};
    int nodej[] =  {0,2,4,2,6,1, 6,4,5,3, 6,3};
    int weight[] = {0,3,5,9,1,4,-3,7,4,3,-4,8};

    GraphAlgo.shortestPathTree(n,m,nodei,nodej,weight,root,mindistance,treearc);
    for (int i=1; i<=n; i++)
      if (i != root)
        System.out.println("Shortest path distance from node " + 
             root + " to node " + i + " is  " + mindistance[i]);
    System.out.println("\nEdges in the shortest path tree are:");
    for (int i=1; i<=n; i++)
      if (i != root)
        System.out.println("  (" + treearc[i] + ", " + i + ")");
  }
}

