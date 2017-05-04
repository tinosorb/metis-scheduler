package GraphAlgorithms;

public class Test_allCliques extends Object {
  
  public static void main(String args[]) {

    int n=6;
    int m=11;
    int nodei[] = {0, 2, 4, 1, 3, 5, 3, 2, 6, 1, 2, 3};
    int nodej[] = {0, 3, 5, 6, 4, 1, 5, 1, 5, 4, 6, 1};
    int clique[][] = new int[n][n+1];

    GraphAlgo.allCliques(n,m,nodei,nodej,clique);
    System.out.println("Number of cliques = " + clique[0][0]);
    for (int i=1; i<=clique[0][0]; i++) {
      System.out.print("\nnodes of clique " + i + ": ");
      for (int j=1; j<=clique[i][0]; j++)
        System.out.printf("%3d", clique[i][j]);
    }
    System.out.println();
  }
}

