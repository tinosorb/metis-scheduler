package GraphAlgorithms;

public class Test_edgeConnectivity extends Object {
  
  public static void main(String args[]) {
  
    int k;
    int n = 8;
    int m = 13;
    int nodei[] = {0,4,8,3,1,2,4,3,3,4,6,5,8,1};
    int nodej[] = {0,7,1,7,5,8,6,7,1,2,8,3,4,7};

    k = GraphAlgo.edgeConnectivity(n,m,nodei,nodej);
    System.out.println("The edge connectivity of the graph = " + k);
  }
}

