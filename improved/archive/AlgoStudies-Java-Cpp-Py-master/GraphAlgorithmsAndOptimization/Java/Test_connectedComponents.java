package GraphAlgorithms;

public class Test_connectedComponents extends Object {
  
  public static void main(String args[]) {

    int n=9, m=10;
    int component[] = new int[n+1];
    int nodei[] = {0,4,9,1,5,4,3,9,7,6,1};
    int nodej[] = {0,3,5,7,2,1,7,2,4,8,3};

    GraphAlgo.connectedComponents(n,m,nodei,nodej,component);
    System.out.println("Total number of components = " + component[0]);
    System.out.print("\n             Node: ");
    for (int i=1; i<=n; i++)
      System.out.print("  " + i);
    System.out.print("\n Component Number: ");
    for (int i=1; i<=n; i++)
      System.out.print("  " + component[i]);
    System.out.println();
  }
}

