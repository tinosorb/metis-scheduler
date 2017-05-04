public class Assignment {
    public static void SolveAssignment (int n, int cost[][], int sol[]) {
        int h, i, j, k, tmpcol, tmprow, m, optcost, n1, temp;
        int tmpsetcol = 0, tmpsetrow = 0, r = 0, l = 0;
        int unexplorecol[] = new int[n + 1];
        int labelcol[] = new int[n + 1];
        int labelrow[] = new int[n + 1];
        int lastunassignrow[] = new int[n + 1];
        int nextunassignrow[] = new int[n + 1];
        int setlabelcol[] = new int[n + 1];
        int setlabelrow[] = new int[n + 1];
        int unexplorerow[] = new int[n + 2];
        int unassginrow[] = new int[n + 2];
        boolean skip = false, outer, middle;

        // initialize
        n1 = n + 1;
        for (j = 1; j <= n; j++) {
            sol[j] = 0;
            lastunassignrow[j] = 0;
            nextunassignrow[j] = 0;
            unassginrow[j] = 0;
        }
        unassginrow[n1] = 0;
        optcost = 0;

        // cost matrix reduction
        for (j = 1; j <= n; j++) {
            temp = cost[1][j];
            for (l = 2; l <= n; l++)
                if (cost[l][j] < temp) temp = cost[l][j];
            optcost += temp;
            for (i = 1; i <= n; i++)
                cost[i][j] -= temp;
        }
        for (i = 1; i <= n; i++) {
            temp = cost[i][1];
            for (l = 2; l <= n; l++)
                if (cost[i][l] < temp) temp = cost[i][l];
            optcost += temp;
            l = n1;
            for (j = 1; j <= n; j++) {
                cost[i][j] -= temp;
                if (cost[i][j] == 0) {
                    cost[i][l] = -j;
                    l = j;
                }
            }
        }

        // choosing initial solution
        k = n1;
        for (i = 1; i <= n; i++) {
            tmpcol = n1;
            j = -cost[i][n1];
            skip = false;
            do {
                if (sol[j] == 0) {
                    skip = true;
                    break;
                }
                tmpcol = j;
                j = -cost[i][j];
            } while (j != 0);
            if (skip) {
                sol[j] = i;
                cost[i][tmpcol] = cost[i][j];
                nextunassignrow[i] = -cost[i][j];
                lastunassignrow[i] = tmpcol;
                cost[i][j] = 0;
                continue;
            }
            tmpcol = n1;
            j = -cost[i][n1];
            do {
                r = sol[j];
                tmprow = lastunassignrow[r];
                m = nextunassignrow[r];
                while (true) {
                    if (m == 0) break;
                    if (sol[m] == 0) {
                        nextunassignrow[r] = -cost[r][m];
                        lastunassignrow[r] = j;
                        cost[r][tmprow] = -j;
                        cost[r][j] = cost[r][m];
                        cost[r][m] = 0;
                        sol[m] = r;
                        sol[j] = i;
                        cost[i][tmpcol] = cost[i][j];
                        nextunassignrow[i] = -cost[i][j];
                        lastunassignrow[i] = tmpcol;
                        cost[i][j] = 0;
                        skip = true;
                        break;
                    }
                    tmprow = m;
                    m = -cost[r][m];
                }
                if (skip) break;
                tmpcol = j;
                j = -cost[i][j];
            } while (j != 0);
            if (skip) continue;
            unassginrow[k] = i;
            k = i;
        }
        middle = false;
        while (true) {
            outer = false;
            if (!middle) {
                if (unassginrow[n1] == 0) {
                    cost[0][0] = optcost;
                    return;
                }

                // search for a new assignment
                for (i = 1; i <= n; i++) {
                    unexplorecol[i] = 0;
                    labelcol[i] = 0;
                    labelrow[i] = 0;
                    unexplorerow[i] = 0;
                }
                unexplorerow[n1] = -1;
                tmpsetcol = 0;
                tmpsetrow = 1;
                r = unassginrow[n1];
                labelrow[r] = -1;
                setlabelrow[1] = r;
            }
            if ((cost[r][n1] != 0) || middle) {
                do {
                    if (!middle) {
                        l = -cost[r][n1];
                        if (cost[r][l] != 0) {
                            if (unexplorerow[r] == 0) {
                                unexplorerow[r] = unexplorerow[n1];
                                unexplorecol[r] = -cost[r][l];
                                unexplorerow[n1] = r;
                            }
                        }
                        skip = false;
                    }
                    while (true) {
                        if (!middle) {
                            if (labelcol[l] == 0) break;
                            if (unexplorerow[r] == 0) {
                                skip = true;
                                break;
                            }
                        }
                        middle = false;
                        l = unexplorecol[r];
                        unexplorecol[r] = -cost[r][l];
                        if (cost[r][l] == 0) {
                            unexplorerow[n1] = unexplorerow[r];
                            unexplorerow[r] = 0;
                        }
                    }
                    if (skip) break;
                    labelcol[l] = r;
                    if (sol[l] == 0) {
                        while (true) {
                            // assigning a new row
                            sol[l] = r;
                            m = n1;
                            while (true) {
                                temp = -cost[r][m];
                                if (temp == l) break;
                                m = temp;
                            }
                            cost[r][m] = cost[r][l];
                            cost[r][l] = 0;
                            if (labelrow[r] < 0) break;
                            l = labelrow[r];
                            cost[r][l] = cost[r][n1];
                            cost[r][n1] = -l;
                            r = labelcol[l];
                        }
                        unassginrow[n1] = unassginrow[r];
                        unassginrow[r] = 0;
                        outer = true;
                        break;
                    }
                    if (outer) break;
                    tmpsetcol++;
                    setlabelcol[tmpsetcol] = l;
                    r = sol[l];
                    labelrow[r] = l;
                    tmpsetrow++;
                    setlabelrow[tmpsetrow] = r;
                } while (cost[r][n1] != 0);
                if (outer) continue;
                if (unexplorerow[n1] > 0) middle = true;
            }
            if (!middle) {
                // current cost matrix reduction
                h = Integer.MAX_VALUE;
                for (j = 1; j <= n; j++)
                    if (labelcol[j] == 0)
                        for (k = 1; k <= tmpsetrow; k++) {
                            i = setlabelrow[k];
                            if (cost[i][j] < h) h = cost[i][j];
                        }
                optcost += h;
                for (j = 1; j <= n; j++)
                    if (labelcol[j] == 0)
                        for (k = 1; k <= tmpsetrow; k++) {
                            i = setlabelrow[k];
                            cost[i][j] -= h;
                            if (cost[i][j] == 0) {
                                if (unexplorerow[i] == 0) {
                                    unexplorerow[i] = unexplorerow[n1];
                                    unexplorecol[i] = j;
                                    unexplorerow[n1] = i;
                                }
                                l = n1;
                                while (true) {
                                    temp = -cost[i][l];
                                    if (temp == 0) break;
                                    l = temp;
                                }
                                cost[i][l] = -j;
                            }
                        }
                if (tmpsetcol != 0)
                    for (i = 1; i <= n; i++) {
                        if (labelrow[i] == 0) {
                            for (k = 1; k <= tmpsetcol; k++) {
                                j = setlabelcol[k];
                                if (cost[i][j] <= 0) {
                                    l = n1;
                                    while (true) {
                                        temp = -cost[i][l];
                                        if (temp == j) break;
                                        l = temp;
                                    }
                                    cost[i][l] = cost[i][j];
                                    cost[i][j] = h;
                                    continue;
                                }
                                cost[i][j] += h;
                            }
                        }
                    }
            }
            r = unexplorerow[n1];
            middle = true;
        }
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

        SolveAssignment(n, cost, sol);
        System.out.println("Optimal assignment:\n" + "  column  row");
        for (int i=1; i<=n; i++)
            System.out.println("     " + i + "  -  " + sol[i]);
        System.out.println("\nTotal assignment cost = " + cost[0][0]);
    }

}

