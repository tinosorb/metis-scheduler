package GraphAlgorithms;

public class Test_maximumNetworkFlow extends Object {
  
  public static void main(String args[]) {
  
    int n=7, m=11;
    int nodei[]    = {0, 5, 5, 1, 1, 3, 3, 6, 7, 7, 6, 4,
                         0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    int nodej[]    = {0, 3, 1, 3, 6, 7, 6, 7, 4, 2, 2, 2,
                         0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    int capacity[] = {0,30,40,20,10,40,50,30,40,30,20,20,
                         0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    int source=5, sink=2;   
    int minimumcut[] = new int[n+1];
    int nodeflow[] = new int[n+1];    
    int arcflow[] = new int[m+m+1];

    GraphAlgo.maximumNetworkFlow(n,m,nodei,nodej,capacity,source,sink,
                                 minimumcut,arcflow,nodeflow);
    System.out.print("Nodes in the minimum cut set: ");
    for (int i=1; i<=n; i++)
      if (minimumcut[i] == 1)
        System.out.print("  " + i);
    System.out.println("\n\nAmount of flow through each node:" +
                       "\n  node  flow");
    for (int i=1; i<=n; i++)
      System.out.println("    " + i + "    " + nodeflow[i]);
    System.out.println("\nAmount of flow on each edge:\n  from to  flow");
    for (int i=1; i<=arcflow[0]; i++)
      System.out.printf("%5d%4d%5d\n",nodei[i],nodej[i],arcflow[i]);
  }
}
