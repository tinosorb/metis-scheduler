package GraphAlgorithms;

public class Test_allPairsShortestPaths extends Object {
  
  public static void main(String args[]) {

    int startnode,endnode;
    int n=6;
    int big = 99;
    int path[] = new int[n+1];
    int dist[][] = {{0,  0,  0,  0,  0,  0,  0},
                    {0,  0, 99,  3, 99, 99,  3},
                    {0, 99,  0, 99, 99, 99, -4},
                    {0, 99, 99,  0,  7, 99,  4},
                    {0, 99,  2, 99,  0, 99, 99},
                    {0, -2,  9,  8, 99,  0,  4},
                    {0,  3, 99,  4,  3,  4,  0}}; 

    startnode = 5;
    endnode = 2;
    GraphAlgo.allPairsShortestPaths(n,dist,big,startnode,endnode,path);
    System.out.println("The shortest distance matrix between all nodes:");
    for (int i=1; i<=n; i++) {
      for (int j=1; j<=n; j++)
        System.out.printf("%4d",dist[i][j]);
      System.out.println();
    }
    System.out.print("\nShortest path from node " + startnode +
                     " to node " + endnode + " is: ");
    for (int i=1; i<=path[0]; i++)
      System.out.print("  " + path[i]);
    System.out.println();
  }
}

