package GraphAlgorithms;

public class Test_randomIsomorphicRegularGraphs extends Object {
  
  public static void main(String args[]) {

    int k;
    int n = 6;
    int degree = 3;
    long seed = 1;
    int m = (n * degree) / 2;
    int map[] = new int[n+1];
    int firsti[] = new int[m+1];
    int firstj[] = new int[m+1];
    int secondi[] = new int[m+1];
    int secondj[] = new int[m+1];
      
    k = GraphAlgo.randomIsomorphicRegularGraphs(n,degree,seed,
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

