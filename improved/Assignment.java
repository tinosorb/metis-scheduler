public static void assignment(int n, int cost[][], int sol[]) {
        int h, i, j, k, tmpcol, tmprow, m, optcost, n1, temp; int tmpsetcol=0, tmpsetrow=0, r=0, l=0;
        int unexplorecol[] = new int[n + 1];
        int labelcol[] = new int[n + 1];
        int labelrow[] = new int[n + 1];
        int lastunassignrow[] = new int[n + 1]; int nextunassignrow[] = new int[n + 1]; int setlabelcol[] = new int[n + 1]; int setlabelrow[] = new int[n + 1]; int unexplorerow[] = new int[n + 2]; int unassignrow[] = new int[n + 2]; boolean skip=false, outer, middle;
// initialize
        n1 = n + 1;
        for (j=1; j<=n; j++) {
        sol[j] = 0; lastunassignrow[j] = 0; nextunassignrow[j] = 0; unassignrow[j] = 0;
        }
        unassignrow[n1] = 0; optcost = 0;
// cost matrix reduction for (j=1; j<=n; j++) {
        temp = cost[1][j]; for (l=2; l<=n; l++)
        if (cost[l][j] < temp) temp = cost[l][j]; optcost += temp;
        for (i=1; i<=n; i++)
        cost[i][j] -= temp; }
        for (i=1; i<=n; i++) { temp = cost[i][1]; for (l=2; l<=n; l++)
        if (cost[i][l] < temp) temp = cost[i][l]; optcost += temp;
        l = n1;
        for (j=1; j<=n; j++) {
        cost[i][j] -= temp;
        if ( cost[i][j] == 0 ) {
        cost[i][l] = -j;
        l = j; }
        } }