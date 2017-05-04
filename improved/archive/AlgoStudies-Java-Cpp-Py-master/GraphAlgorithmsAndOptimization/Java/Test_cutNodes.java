package GraphAlgorithms;

public class Test_cutNodes extends Object {
  
  public static void main(String args[]) {

    int k;
    int n=11;
    int m=14;
    int nodei[] = {0, 4,9,1,3,6,5,4, 2,11,9,7, 6, 1,6};
    int nodej[] = {0,11,5,7,8,8,2,1,11, 7,2,4,10,11,3};
    int cutnode[] = new int[n+1];

    k = GraphAlgo.cutNodes(n,m,nodei,nodej,cutnode); 
    System.out.print("number of components = " + k +
                     "\n  number of cutnodes = " + cutnode[0] +
                     "\n\nThe cut nodes are: ");
    for (int i=1; i<=cutnode[0]; i++)
      System.out.printf("%4d", cutnode[i]);
    System.out.println();
  }
}

