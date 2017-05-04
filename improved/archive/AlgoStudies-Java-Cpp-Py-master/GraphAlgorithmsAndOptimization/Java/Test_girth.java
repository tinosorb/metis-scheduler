package GraphAlgorithms;

public class Test_girth extends Object {
  
  public static void main(String args[]) {

    int g;
    int n = 10;
    int m = 15;
    int nodei[] = {0,9,1,4,1,1,2,2,3,5,3,3,4, 7,10,10};
    int nodej[] = {0,5,4,7,6,9,6,8,5,8,6,7,8,10, 9,2};
        
    g = GraphAlgo.girth(n,m,nodei,nodej);
    System.out.println("The girth of the graph = " + g);
  }
}

