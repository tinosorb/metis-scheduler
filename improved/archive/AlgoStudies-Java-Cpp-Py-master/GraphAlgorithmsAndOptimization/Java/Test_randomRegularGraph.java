package GraphAlgorithms;

public class Test_randomRegularGraph extends Object {
  
  public static void main(String args[]) {

    int k;
    int n = 8;
    int degree = 3;
    long seed = 1;
    int edges = (n * degree) / 2;
    int nodei[] = new int[edges+1];
    int nodej[] = new int[edges+1];
      
    k = GraphAlgo.randomRegularGraph(n,degree,seed,nodei,nodej);
    if (k != 0)
      System.out.println("invalid input data, error code = " + k);
    else {   
      System.out.print("List of edges:\n from: ");
      for (k=1; k<=edges; k++)
        System.out.print("  " + nodei[k]);
      System.out.print("\n   to: ");
      for (k=1; k<=edges; k++)
        System.out.print("  " + nodej[k]);
      System.out.println();    
    }
  }
}

