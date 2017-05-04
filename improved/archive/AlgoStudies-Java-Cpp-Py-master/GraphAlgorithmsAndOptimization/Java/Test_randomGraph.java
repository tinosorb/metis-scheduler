package GraphAlgorithms;

public class Test_randomGraph extends Object {
  
  public static void main(String args[]) {

    int k;
    int n = 5;
    int m = 8;
    long seed = 1;
    boolean simple=true, directed=false, acyclic=false, weighted=true;
    int minweight = 90;
    int maxweight = 99;
    int nodei[] = new int[m+1];
    int nodej[] = new int[m+1];
    int weight[] = new int[m+1];
    
    k = GraphAlgo.randomGraph(n,m,seed,simple,directed,acyclic,
               weighted,minweight,maxweight,nodei,nodej,weight);
    if (k != 0)
      System.out.println("Invalid input data, error code = " + k);
    else {
      System.out.println("List of edges:\n   from to  weight");
      for (k=1; k<=m; k++)
        System.out.println("     " + nodei[k] + "   " + nodej[k] +
                           "    " + weight[k]);
    }
  }
}

