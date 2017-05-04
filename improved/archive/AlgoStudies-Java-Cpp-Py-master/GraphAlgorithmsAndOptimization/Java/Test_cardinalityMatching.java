package GraphAlgorithms;

public class Test_cardinalityMatching extends Object {
  
  public static void main(String args[]) {
  
    int n = 13;
    int m = 13;
    int inode[] = {0, 4, 3, 1,12, 6, 8,13,10, 1, 9, 1,10,3};
    int jnode[] = {0, 2,13, 5, 7,11, 1, 9,12, 6, 3,11, 2,1};
    int pair[] = new int[n + 1];

    GraphAlgo.cardinalityMatching(n, m, inode, jnode, pair);
    System.out.println("Maximum matching:\n");
    for (int i=1; i<=n; i++)
      System.out.print("  " + pair[i]);
    System.out.println("\n\nNumber of unmatched nodes = " + pair[0]);
  }
}

