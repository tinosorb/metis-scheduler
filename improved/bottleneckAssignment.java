import java.lang.Math;
import java.util.Arrays;

public class bottleneckAssignment {

	public static void SolvebottleneckAssignment(int n, int cost[][], int sol[]) {

	    int i, minimum, bottleneckvalue, cand, p, v, w, start, temp; int q=0, u=0;
	    int aux1[] = new int[n + 1];
	    int aux2[] = new int[n + 1];
	    int aux3[] = new int[n + 1];
        int aux4[] = new int[n + 1];
	    boolean skip=false;

        // initialize
	    bottleneckvalue = -Integer.MAX_VALUE;
	    for (i=1; i<=n; i++)
		    aux1[i]=0;
	    for (i=0; i<=n-1; i++) {
		    q = n - i;
            aux2[q] = q;
            minimum = Integer.MAX_VALUE;
            p = 1;
            do {
                cand = cost[q][p];
                if (cand <= bottleneckvalue) {
                    minimum = bottleneckvalue;
                    u = p;
                    p = n + 1;
                } else {
                    if (cand < minimum) {
                        minimum = cand;
                        u = p;
                    }
                    p++;
                }
            } while (p <= n);

            if (aux1[u] == 0) {
                aux1[u] = q;
                sol[q] = u;
            }
            else
                sol[q] = 0;
            if (minimum > bottleneckvalue) bottleneckvalue = minimum;
        }

        // search for an augmenting path
        for (u=1; u<=n; u++)
            if (aux1[u] == 0) {
                w = 1;
                start = 1;
                skip = false;
                for (i=1; i<=n; i++) {
                    q = aux2[i];
                    aux4[q] = cost[q][u];
                    aux3[q] = u;
                    if (aux4[q] <= bottleneckvalue) {
                        if (sol[q] == 0) {
                            skip = true; break;
                        }
                        aux2[i] = aux2[w];
                        aux2[w] = q;
                        w++;
                    }
                }

                if (!skip) {
                    while (true) {
                        if (w == start) {
                            minimum = Integer.MAX_VALUE;
                            for (i=w; i<=n; i++) {
                                q = aux2[i];
                                cand = aux4[q];
                                if (cand <= minimum) {
                                    if (cand < minimum) {
                                        w = start;
                                        minimum = cand;
                                    }
                                    aux2[i] = aux2[w];
                                    aux2[w] = q;
                                    w++;
                                }
                            }

                            bottleneckvalue = minimum;
                            for (i=start; i<=w-1; i++) {
                                q = aux2[i];
                                if (sol[q] == 0) {
                                    skip = true;
                                    break;
                                }
                            }
                        }

                        if (skip) break;
                        v = aux2[start];
                        start++;
                        p = sol[v];
                        for (i=w; i<=n; i++) {
                            q = aux2[i];
                            cand = cost[q][p];
                            if (cand < aux4[q]) {
                                aux3[q] = p;
                                if (cand <= bottleneckvalue) {
                                    if (sol[q] == 0) {
                                        skip = true; break;
                                    }
                                    aux2[i] = aux2[w];
                                    aux2[w] = q;
                                    w++;
                                }
                                aux4[q] = cand;
                            }
                        }
                        if (skip) break;
                    }
                }

                // augment
                skip = false;
                do {
                    p = aux3[q];
                    sol[q] = p;
                    temp = q;
                    q = aux1[p];
                    aux1[p] = temp;
                } while (p != u);
            }
        cost[0][0] = bottleneckvalue;
    }

    public static void main(String args[]) {
        int n = Integer.parseInt(args[0]);
        int cost[][] = new int[n+1][n+2];
        for (int i = 0; i < n+1; i++) {
            for (int j = 0; j < n+2; j++) {
                cost[i][j] = (int)(Math.random() * 1000);
                //System.out.println(cost[i][j]);  // 3 digits
            }
        }
        //for (int[] row : cost)
        //    System.out.println(Arrays.toString(row));
        int sol[] = new int[n + 1];

        SolvebottleneckAssignment(n, cost, sol);
        System.out.println("Optimal solution:\n" + "  row  column");
        for (int i=1; i<=n; i++)
            System.out.println(" " + i + " - " + sol[i]);
            System.out.println("\nBottleneck value = " + cost[0][0]);
        }
}
