package GraphAlgorithms;

public class Test_planarity extends Object {
  
  public static void main(String args[]) {
  
    int n = 7;
    int m = 11;
    int nodei[] = {0, 1, 5, 2, 7, 1, 3, 5, 7, 4, 1, 1};
    int nodej[] = {0, 5, 2, 4, 4, 7, 5, 6, 3, 6, 4, 2};

    if (GraphAlgo.planarityTesting(n, m, nodei, nodej))
      System.out.println("The input graph is planar.");
    else
      System.out.println("The input graph is nonplanar.");
  }
}

