package GraphAlgorithms;

public class Test_kShortestPaths extends Object {
  
  public static void main(String args[]) {

    int pathnodes[] = {20, 6};
    int n = 6;
    int m = 13;
    int k = 3;
    int source = 5;
    int sink = 4;
    int nodei[] =  {0,2, 1,5,3,4, 2,5,6,1,6,3, 3,5};
    int nodej[] =  {0,4, 6,1,6,2, 6,2,5,3,4,4, 5,3};
    int weight[] = {0,2,-3,4,1,3,-4,9,4,3,5,7,-2,8};
    int dist[][] = new int[n+1][k+1];
    int path[][] = new int[pathnodes[1]+1][pathnodes[0]+2];

    GraphAlgo.kShortestPaths(n,m,nodei,nodej,weight,k,source,sink,
                             pathnodes,dist,path);
    System.out.println("k Shortest path lengths (k = " + k + "):");
    for (int i=1; i<=n; i++) {
      System.out.print("\nfrom node " + source + " to node " + i + ":  ");
      for (int j=1; j<=k; j++)
        System.out.printf("%4d",dist[i][j]);
    }
    if (pathnodes[0] == 0)
      System.out.println("\nNumber of arcs in a path exceeds " +
          pathnodes[0] + ". Remedy: increase size of pathnodes[0]."); 
    else
      if (pathnodes[1] == 0)
        System.out.println("\nThere is no path from node " + source +
                           " to node " + sink);
      else {
        System.out.print("\n\nThe following are " + pathnodes[1] + 
              " shortest paths from node " + source + " to node " + 
              sink + "\n       path length    Nodes in the path");
        for (int i=1; i<=pathnodes[1]; i++) {
          System.out.printf("\n %2d:     %3d        ", i, 
                            path[i][pathnodes[0]+1]);
          for (int j=1; j<=path[i][0]; j++)
            System.out.printf("%3d",path[i][j]);
        }
        System.out.println();
      }
  }
}


