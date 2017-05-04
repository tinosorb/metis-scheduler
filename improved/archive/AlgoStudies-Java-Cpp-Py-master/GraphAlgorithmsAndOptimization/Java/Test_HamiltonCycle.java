package GraphAlgorithms;

public class Test_HamiltonCycle extends Object {
  
  public static void main(String args[]) {

    int n=20, m=30;
    int cycle[] = new int[n+1];
    int nodei[] = 
      {0,5,2,4,1,3,2,4,1,3, 5,11, 9, 7,10,13,12,10, 7,15,14,19,17,13,16,20,17,18,15,18,16};
    int nodej[] = 
      {0,1,3,5,2,4,7,9,6,8,10, 6,14,12,15, 8, 6,11,13, 9, 8,14,12,18,11,19,16,19,20,17,20};
    boolean directed = false;

    GraphAlgo.HamiltonCycle(n,m,directed,nodei,nodej,cycle);
    if (cycle[0] != 0)
      System.out.println("No Hamilton cycle is found.");
    else {
      System.out.println("A Hamilton cycle is found:");
      for (int i=1; i<=n; i++)
        System.out.print("  " + cycle[i]);
      System.out.println();
    }
  }
}

