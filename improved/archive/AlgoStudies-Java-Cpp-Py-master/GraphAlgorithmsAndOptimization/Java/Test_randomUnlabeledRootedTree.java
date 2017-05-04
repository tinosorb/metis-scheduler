package GraphAlgorithms;

public class Test_randomUnlabeledRootedTree extends Object {
  
  public static void main(String args[]) {

    int n = 5;
    long seed = 1;
    int sol[] = new int[n+1];

    GraphAlgo.randomUnlabeledRootedTree(n,seed,sol);
    System.out.println("List of edges:\n   from to");
    for (int k=2; k<=n; k++)
      System.out.println("     " + k + "   " + sol[k]);
  }
}

