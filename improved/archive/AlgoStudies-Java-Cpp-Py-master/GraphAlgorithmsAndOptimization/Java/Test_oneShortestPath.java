package GraphAlgorithms;

public class Test_oneShortestPath extends Object {
  
  public static void main(String args[]) {

    int k;
    int n=6, m=11;
    int source = 5, sink = 4;
    int path[] = new int[n+1];
    int nodei[] =  {0,4,6,5,3,5,6,3,6,1,2,5};
    int nodej[] =  {0,2,4,2,6,1,1,4,5,3,6,3};
    int weight[] = {0,1,3,5,3,2,2,7,4,1,2,5};

    k = GraphAlgo.oneShortestPath(n,m,nodei,nodej,weight,source,sink,path);
    System.out.println("The shortest path from node " + source + 
                       " to node " + sink + " is: ");
    for (int i=1; i<=path[0]; i++)
      System.out.print("  " + path[i]);
    System.out.println("\nLength of the shortest path is " + k);
  }
}

