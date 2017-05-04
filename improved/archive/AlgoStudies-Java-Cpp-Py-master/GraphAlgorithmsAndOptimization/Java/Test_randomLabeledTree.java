package GraphAlgorithms;

public class Test_randomLabeledTree extends Object {
  
  public static void main(String args[]) {

    int n = 7;
    long seed = 1;
    int sol[] = new int[n+1];

    GraphAlgo.randomLabeledTree(n,seed,sol);
    System.out.print("List of edges:\n from: ");
    for (int k=1; k<=n-1; k++)
      System.out.print("  " + k);
    System.out.print("\n   to: ");
    for (int k=1; k<=n-1; k++)
      System.out.print("  " + sol[k]);
    System.out.println();    
  }
}

