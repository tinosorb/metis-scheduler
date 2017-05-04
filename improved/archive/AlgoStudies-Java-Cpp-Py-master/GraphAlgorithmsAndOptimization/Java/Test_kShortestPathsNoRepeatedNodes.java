package GraphAlgorithms;

public class Test_kShortestPathsNoRepeatedNodes extends Object {
  
  public static void main(String args[]) {

    int n = 6;
    int m = 13;
    int k = 4;
    int source = 5;
    int sink = 4;
    int nodei[] =  {0,2, 1,5,3,4, 2,5,6,1,6,3, 3,5};
    int nodej[] =  {0,4, 6,1,6,2, 6,2,5,3,4,4, 5,3};
    int weight[] = {0,2,-3,4,1,3,-4,9,4,3,5,7,-2,8};

    int path[][] = new int[k+1][n+2];

    GraphAlgo.kShortestPathsNoRepeatedNodes(n,m,nodei,nodej,weight,
                                            k,source,sink,path);
    if (path[0][0] == 0)
      System.out.println("No path is found from node " +
                         source + " to node " + sink);
    else {
      System.out.print("Number of shortest paths found from node " +
                 source + " to node " + sink + " is " + path[0][0] +
                 "\nTheir shortest path lengths are ");
      for (int i=1; i<=path[0][0]; i++)
        System.out.print("  " + path[i][n+1]);
      System.out.print("\n\nThe following are " + path[0][0] + 
             " shortest paths from node " + source + " to node " + 
             sink + "\n       path length    Nodes in the path");
      for (int i=1; i<=path[0][0]; i++) {
        System.out.printf("\n %2d:     %3d        ",i,path[i][n+1]);
        for (int j=1; j<=path[i][0]; j++)
          System.out.printf("%3d",path[i][j]);
      }
      System.out.println();
    }
  }
}

