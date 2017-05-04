package GraphAlgorithms;

public class Test_randomIsomorphicGraphs extends Object {
  
  public static void main(String args[]) {

    int k;
    int n = 5;
    int m = 7;
    long seed = 1;
    boolean simple=true, directed=false;
    int map[] = new int[n+1];
    int firsti[] = new int[m+1];
    int firstj[] = new int[m+1];
    int secondi[] = new int[m+1];
    int secondj[] = new int[m+1];
      
    k = GraphAlgo.randomIsomorphicGraphs(n,m,seed,simple,directed,
                                firsti,firstj,secondi,secondj,map);
    if (k != 0)
      System.out.println("Invalid input data, error code = " + k);
    else {
      System.out.println("List of edges:\n  First Graph    Second Graph" +
                         "\n   from to         from to ");
      for (k=1; k<=m; k++)
        System.out.println("     " + firsti[k] + "   " + firstj[k] +
                           "           " + secondi[k] + "   " + secondj[k]);
      System.out.println("\n  Node mapping:\n   First Graph   Second Graph");
      for (k=1; k<=n; k++)
        System.out.println("        " + k + "           " + map[k]);
    }
  }
}

