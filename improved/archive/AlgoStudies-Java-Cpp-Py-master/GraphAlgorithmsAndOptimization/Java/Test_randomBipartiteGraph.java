package GraphAlgorithms;

public class Test_randomBipartiteGraph extends Object {

  public static void main(String args[]) {

    int n1 = 3;
    int n2 = 4;
    int m = 6;
    long seed = 1;
    int nodei[] = new int[m+1];
    int nodej[] = new int[m+1];

    GraphAlgo.randomBipartiteGraph(n1,n2,m,seed,nodei,nodej);
    System.out.println("List of edges:\n   from to");
    for (int k=1; k<=nodei[0]; k++)
      System.out.println("     " + nodei[k] + "   " + nodej[k]);
  }
}

