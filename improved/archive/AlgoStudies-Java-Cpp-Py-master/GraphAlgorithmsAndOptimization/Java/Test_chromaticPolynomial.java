package GraphAlgorithms;

public class Test_chromaticPolynomial extends Object {
  
  public static void main(String args[]) {

    int n=8, m=12;
    int chpoly1[] = new int[n+1];
    int chpoly2[] = new int[n+1];
    int chpoly3[] = new int[n+1];
    int nodei[] = {0, 1, 1, 1, 2, 2, 3, 3, 3, 4, 4, 5, 6};
    int nodej[] = {0, 2, 5, 6, 7, 8, 4, 7, 8, 5, 6, 7, 8};

    GraphAlgo.chromaticPolynomial(n,m,nodei,nodej,chpoly1,chpoly2,chpoly3);
    System.out.print("Chromatic polynomial 1st form: ");
    for (int i=1; i<=n; i++)
      System.out.printf("%4d",chpoly1[i]);
    System.out.print("\nChromatic polynomial 2nd form: ");
    for (int i=1; i<=n; i++)
      System.out.printf("%4d",chpoly2[i]);
    System.out.print("\nChromatic polynomial 3rd form: ");
    for (int i=1; i<=n; i++)
      System.out.printf("%4d",chpoly3[i]);
    System.out.println();
  }
}

