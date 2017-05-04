package GraphAlgorithms;

public class Test_minimumSpanningTreeKruskal extends Object {
  
  public static void main(String args[]) {

    int n=6, m=9;
    int treearc1[] = new int[n];
    int treearc2[] = new int[n];
    int nodei[] =  {0, 4, 6, 5, 1, 3, 1, 2, 1, 6};
    int nodej[] =  {0, 2, 1, 3, 2, 6, 5, 6, 3, 4};
    int weight[] = {0,20,30,20,20,40,30,30,40,40};

    GraphAlgo.minimumSpanningTreeKruskal(n,m,nodei,nodej,weight,
                                         treearc1,treearc2);
    System.out.println("Minimum spanning tree edges: "); 
    for (int i=1; i<=treearc1[0]; i++)
      System.out.println("  " + treearc1[i] + " " + treearc2[i]);
  }
}

