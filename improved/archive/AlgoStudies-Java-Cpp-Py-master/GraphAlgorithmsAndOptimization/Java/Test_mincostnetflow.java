package GraphAlgorithms;

public class Test_mincostnetflow extends Object {
  
  public static void main(String args[]) {
  
    int nodes = 7;
    int edges = 11;
    int numdemand = 2;
    int nodedemand[][] = {{0, 0}, {5, 25}, {2, -25}};
    int nodei[]    = {0,  5,  1,  1,  7,  3,  4,  5,  6,  6,  7,  3};
    int nodej[]    = {0,  1,  3,  6,  4,  7,  2,  3,  2,  7,  2,  6};
    int arccost[]  = {0,  3,  2,  5,  6,  4,  2,  4,  7,  2,  8,  3};
    int lowbound[] = {0,  0,  4,  0,  0,  0,  0,  0,  2,  5,  3,  0};
    int upbound[]  = {0, 40, 20, 10, 40, 40, 20, 30, 20, 30, 30, 50};
    int f;
    int arcsol[][] = new int[2][edges + 1];
    int flowsol[] = new int[edges + 1];

    f = GraphAlgo.minCostNetworkFlow(nodes, edges, numdemand, 
                  nodedemand, nodei, nodej, arccost, upbound,
                  lowbound, arcsol, flowsol);
    if (f > 0)
      System.out.println(" Infeasible, return code = " + f);
    else {
      System.out.println("Optimal solution found.\n  from to  flow");
      for (int i=1; i<=arcsol[0][0]; i++)
        System.out.printf("%5d %3d %4d\n",arcsol[0][i],arcsol[1][i],
                          flowsol[i]);
      System.out.println("\nTotal cost of the flow = " + arccost[0]);
    }
  }
}

