package GraphAlgorithms;

public class Test_graphIsomorphism extends Object {
  
  public static void main(String args[]) {
  
    int k;
    int n = 5;
    int map1[] = new int[n + 1];
    int map2[] = new int[n + 1];
    int adj1[][] = {{0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 1, 1, 0},
                    {0, 0, 0, 0, 1, 1},
                    {0, 1, 0, 0, 0, 1},
                    {0, 1, 1, 0, 0, 0},
                    {0, 0, 1, 1, 0, 0}};
    int adj2[][] = {{0, 0, 0, 0, 0, 0},
                    {0, 0, 1, 0, 0, 1},
                    {0, 1, 0, 1, 0, 0},
                    {0, 0, 1, 0, 1, 0},
                    {0, 0, 0, 1, 0, 1},
                    {0, 1, 0, 0, 1, 0}};

    k = GraphAlgo.graphIsomorphism(n,adj1,adj2,map1,map2);
    if (k != 0)
      System.out.println("Input graphs are non-isomorphic, " +
                         "return code = " + k);
    else {
      System.out.print("Input graphs are isomorphic." +
                       "\n\n first graph node re-labeling: ");
      for (k=1; k<=n; k++)
        System.out.print("  " + map1[k]);
      System.out.print("\n second graph node re-labeling:");
      for (k=1; k<=n; k++)
        System.out.print("  " + map2[k]);
      System.out.println();
    }
  }
}

