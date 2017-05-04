package GraphAlgorithms;

public class Test_randomMaximumFlowNetwork extends Object {
  
  public static void main(String args[]) {

    int n = 8;
    int m = 10;
    long seed = 1;
    int minweight = 90;
    int maxweight = 99;
    int nodei[] = new int[m+1];
    int nodej[] = new int[m+1];
    int weight[] = new int[m+1];
      
    GraphAlgo.randomMaximumFlowNetwork(n,m,seed,minweight,maxweight,
                                       nodei,nodej,weight);
    System.out.println("List of edges:\n from to  weight");
    for (int k=1; k<=nodei[0]; k++)
      System.out.println("   " + nodei[k] + "   " + nodej[k] +
                         "    " + weight[k]);
  }
}

