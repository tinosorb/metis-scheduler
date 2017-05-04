package GraphAlgorithms;

public class Test_ChinesePostmanTour extends Object {
  
  public static void main(String args[]) {

    int n=6, m=10, startnode=1;
    int sol[][] = new int[m+1][3];
    int trail[] = new int[m+m+2];  
    int nodei[] = {0,3,1,1,6,1,1,1,2,4,3};
    int nodej[] = {0,6,2,3,5,5,6,4,6,6,4};
    int cost[]  = {0,5,2,6,4,5,4,3,3,4,3}; 

    GraphAlgo.ChinesePostmanTour(n,m,startnode,nodei,nodej,cost,sol,trail);
    if (sol[0][0] != 0)
      System.out.println("Error code returned = " + sol[0][0]);
    else {
      System.out.println("Optimal solution found.\n\nDuplicate edges:");
      for (int i=1; i<=sol[3][0]; i++)
        System.out.println(" " + sol[i][1] + " - " + sol[i][2]);
      System.out.println("\nOptimal tour:");
      for (int i=1; i<=trail[0]; i++) 
        System.out.print("  " + trail[i]);
      System.out.println("\n\nOptimal tour total cost = " + sol[1][0]);
    }
  }
}

