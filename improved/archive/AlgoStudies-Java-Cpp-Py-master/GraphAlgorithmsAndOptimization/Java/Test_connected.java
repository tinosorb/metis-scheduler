package GraphAlgorithms;

public class Test_connected extends Object {
  
  public static void main(String args[]) {
  
    int n = 8;
    int m = 10;
    int nodei[] = {0, 8, 3, 5, 1, 3, 7, 2, 6, 7, 4};
    int nodej[] = {0, 4, 7, 2, 6, 8, 4, 1, 5, 8, 3};

    if (GraphAlgo.connected(n, m, nodei, nodej))
      System.out.println("The input graph is connected.");
    else
      System.out.println("The input graph is not connected.");
  }
}

