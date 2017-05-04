package GraphAlgorithms;

public class Test_knapsack extends Object {
  
  public static void main(String args[]) {
  
    int n = 10;
    int m = 2;
    int depth = -1;
    int profit[] = {0, 46, 50, 45, 58, 74, 52, 36, 30, 79, 61};
    int weight[] = {0, 81, 83, 43, 34, 68, 58, 60, 72, 42, 28};
    int capacity[] = {0, 125, 146};
    int sol[] = new int[n + 1];

    GraphAlgo.multipleKnapsack(n, m, profit, weight, capacity, depth, sol);
    if (sol[0] > 0) {
      System.out.println("Optimal solution found:");
      for (int i=1; i<=n; i++)
        System.out.print("  " + sol[i]);
      System.out.println("\n\nTotal profit = " + sol[0]);
    }
    else
      System.out.println("Error returned = " + sol[0]);
  }
}

