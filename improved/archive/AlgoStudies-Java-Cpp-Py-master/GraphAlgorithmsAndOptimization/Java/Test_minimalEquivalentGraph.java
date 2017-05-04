package GraphAlgorithms;

public class Test_minimalEquivalentGraph extends Object {
  
  public static void main(String args[]) {
    int n=5;
    int m=10;
    int nodei[] = {0,4,5,3,1,5,1,4,2,3,5};
    int nodej[] = {0,3,1,2,4,2,3,2,1,5,4};
    boolean arc[] = new boolean[m+1];

    GraphAlgo.minimalEquivalentGraph(n,m,nodei,nodej,arc);
    System.out.print("Edges of the minimal equivalent graph:\n");
    for (int k=1; k<=m; k++)
      if (arc[k])
        System.out.printf(" %4d%3d\n", nodei[k], nodej[k]);
  }
}

