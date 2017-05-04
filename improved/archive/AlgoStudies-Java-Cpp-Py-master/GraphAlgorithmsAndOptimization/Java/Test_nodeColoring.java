package GraphAlgorithms;

public class Test_nodeColoring extends Object {
  
  public static void main(String args[]) {

    int n=14, m=19;
    int color[] = new int[n+1];
    int nodei[] = {0,2,13,1,2,7,3, 3, 4,9,13,14,6,9, 7,14,11, 5,12, 5};
    int nodej[] = {0,1, 1,6,3,2,4,14,13,4,10, 6,9,7,10,10, 8,12, 8,11};

    GraphAlgo.nodeColoring(n,m,nodei,nodej,color);
    System.out.print("The chromatic number is " + color[0] + "\n\n  Node : ");
    for (int i=1; i<=n; i++)
      System.out.printf("%3d",i);
    System.out.print("\n  Color: ");
    for (int i=1; i<=n; i++)
      System.out.printf("%3d",color[i]);
    System.out.println();    
  }
}

