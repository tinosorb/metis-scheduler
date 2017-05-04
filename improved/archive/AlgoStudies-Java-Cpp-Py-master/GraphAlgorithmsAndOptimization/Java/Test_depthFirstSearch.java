package GraphAlgorithms;

public class Test_depthFirstSearch extends Object {
  
  public static void main(String args[]) {
    int n=11;
    int m=13;
    int nodei[] = {0,7,1,6,3, 4,2,11,8,9, 6,4, 2,5};
    int nodej[] = {0,4,7,8,6,11,5, 7,3,2,10,1,11,9};
    int parent[] = new int[n+1];
    int sequence[] = new int[n+1];
    
    GraphAlgo.depthFirstSearch(n,m,nodei,nodej,parent,sequence);
    System.out.printf("Depth first search results:\n   Node:");
    for (int i=1; i<=n; i++)
      System.out.printf("%4d",i);
    System.out.printf("\n Parent:");
    for (int i=1; i<=n; i++)
      System.out.printf("%4d",parent[i]);
    System.out.printf("\n  Order:");
    for (int i=1; i<=n; i++)
      System.out.printf("%4d",sequence[i]);
    System.out.println();
  }
}

