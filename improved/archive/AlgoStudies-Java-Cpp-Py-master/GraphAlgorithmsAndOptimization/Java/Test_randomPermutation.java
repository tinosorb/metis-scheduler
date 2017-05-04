package GraphAlgorithms;
import java.util.Random;

public class Test_randomPermutation extends Object {
  
  public static void main(String args[]) {

    int n = 9;
    long seed = 1;
    int perm[] = new int[n+1];
    Random ran = new Random(seed);
    
    GraphAlgo.randomPermutation(n, ran, perm);
    System.out.println("A random permuation of " + n + " elements:");
    for (int i=1; i<=n; i++)
      System.out.print(" " + perm[i]);
    System.out.println();
  }
}

