package GraphAlgorithms;

public class Test_maximumConnectivity extends Object {
  
  public static void main(String args[]) {

    int n = 8;
    int k = 4;
    int nk2 = (n * k) / 2;
    int nodei[] = new int[nk2+1];
    int nodej[] = new int[nk2+1];
    
    GraphAlgo.maximumConnectivity(n,k,nodei,nodej);
    System.out.println("List of edges:");
    for (int p=1; p<=nk2; p++)
      System.out.print("  " + nodei[p]);
    System.out.println();
    for (int p=1; p<=nk2; p++)
      System.out.print("  " + nodej[p]);
    System.out.println(); 
  }
}

