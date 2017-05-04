package GraphAlgorithms;

public class Test_randomSpanningTree extends Object {
  
  public static void main(String args[]) {

    int n = 8;
    long seed = 1;
    boolean weighted=true;
    int minweight = 90;
    int maxweight = 99;
    int nodei[] = new int[n];
    int nodej[] = new int[n];
    int weight[] = new int[n];
      
    GraphAlgo.randomSpanningTree(n,seed,weighted,minweight,maxweight,
                                 nodei,nodej,weight);
    System.out.println("List of edges:\n   from to  weight");
    for (int k=1; k<=n-1; k++)
      System.out.println("     " + nodei[k] + "   " + nodej[k] +
                         "    " + weight[k]);
  }
}

