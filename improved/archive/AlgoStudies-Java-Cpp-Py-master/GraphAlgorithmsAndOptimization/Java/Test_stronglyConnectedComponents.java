package GraphAlgorithms;

public class Test_stronglyConnectedComponents extends Object {
  
  public static void main(String args[]) {
    int n=11;
    int m=14;
    int nodei[] = {0,7,10,1,6,3, 4,2,11,8,9, 6,4, 2,5};
    int nodej[] = {0,4, 6,7,8,6,11,5, 7,3,2,10,1,11,9};
    int component[] = new int[n+1];

    GraphAlgo.stronglyConnectedComponents(n,m,nodei,nodej,component);
    System.out.println("Number of strongly connected components = " + 
                       component[0]);
    for (int i=1; i<=component[0]; i++) {
      System.out.printf("\n Nodes in component " + i + ": ");
      for (int j=1; j<=n; j++)
        if (component[j] == i)
          System.out.printf("%3d",j);
    }
    System.out.println();
  }
}

