package GraphAlgorithms;

public class Test_allShortestPathLength extends Object {
  
  public static void main(String args[]) {

    int n = 6;
    int m = 11;
    int root = 5;
    int mindistance[] = new int[n+1];
    int nodei[] =  {0,4,6,5,3,5,6,3,6,1,2,5};
    int nodej[] =  {0,2,4,2,6,1,1,4,5,3,6,3};
    int weight[] = {0,1,3,9,3,2,2,7,4,1,2,8};
    boolean directed[] = {false, true, true, true, false, true,
                          false, true, false, true, true, true};

    GraphAlgo.allShortestPathLength(n,m,nodei,nodej,directed,
                                    weight,root,mindistance);
    for (int i=1; i<=n; i++)
      if (i != root)
        System.out.println("Shortest path distance from node " + 
             root + " to node " + i + " is  " + mindistance[i]);
  }
}

