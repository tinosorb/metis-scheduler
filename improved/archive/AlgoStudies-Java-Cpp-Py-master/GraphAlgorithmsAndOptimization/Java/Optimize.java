package Optimization;

import java.lang.Math.*;

public class Optimize extends Object {


public static void dualSimplex(boolean maximize, int n, int m,
                 double a[][], double epsilon, int basicvar[])
{
  int i,j,k,index,nm,q,option;
  int idx1=0,idx2=0,p=0;
  int aux1[] = new int[n - m + 1];
  int aux2[] = new int[n - m + 1];
  double thres,diff,amt;
  boolean infeasible,unbound,abort,iterate;

  option = (maximize ? 1 : -1);
  infeasible = false;
  unbound = false;
  nm = n - m;
  a[0][0] = 0.0;
  for (i=0; i<=nm; i++) {
    amt = 0.0;
    for (j=1; j<=m; j++)
      amt += a[j][i] * a[0][nm+j];
    a[0][i] = amt - a[0][i];
  }
  for (i=nm+1; i<=n; i++)
    for (j=1; j<=m; j++)
      a[j][i] = ((i == nm+j) ? 1.0 : 0.0);
  i = 0;
  while ((!infeasible) && (i < nm)) {
    i++;
    amt = a[0][i];
    infeasible = (Math.abs(amt) > epsilon) && (amt*option < 0);
    if (!infeasible) basicvar[m+i] = i;
  }
  if (!infeasible) {
    for (i=1; i<=m; i++) 
      basicvar[i] = nm + i;
    iterate = true;
    do {
      thres = 0.0;
      abort = true;
      i = 0;
      do {
        i++;
        j = m;
        amt = a[i][0];
        if (amt < -epsilon) {
          iterate = false;
          while ((j < n) && !iterate) {
            j++;
            q = basicvar[j];
            iterate = (a[i][q] < -epsilon);
          }
          if (!iterate)
            unbound = true;
          else {
            abort = false;
            if (amt-thres < -epsilon) {
              thres = amt;
              p = i;
            }
          }
        }
      } while (iterate && (i < m));
      if (iterate) {
        if (abort) {
          unbound = false;
          iterate = false;
        }
        else {
          thres = Double.MAX_VALUE;
          for (j=1; j<=nm; j++)
            aux2[j] = m + j;
          for (i=0; i<=m; i++)
            if ((i != 1) && (!abort)) {
              k = 0;
              for (j=1; j<=nm; j++)
                aux1[j] = aux2[j];
              index = 1;
              for (j=m+1; j<=n; j++)
                if (j == aux1[index]) {
                  index++;
                  q = basicvar[j];
                  amt = a[p][q];
                  if (amt < -epsilon) {
                    amt = Math.abs(a[i][q] / amt);
                    diff = amt - thres;
                    if (Math.abs(diff) < epsilon) {
                      k++;
                      aux2[k] = j;
                      abort = false;
                    }
                    else
                      if (diff < 0.0) {
                        thres = amt;
                        idx1 = j;
                        idx2 = q;
                        aux2[1] = 1;
                        k = 1;
                        for (q=2; q<=nm; q++)
                          aux2[q] = 0;
                        abort = true;
                      }
                  }
                }
            }
          thres = 1.0 / a[p][idx2];
          basicvar[idx1] = basicvar[p];
          i = (p == 0 ? 1 : 0);
          do {
            amt = a[i][idx2] * thres;
            a[i][0] -= a[p][0] * amt;
            for (j=m+1; j<=n; j++) {
              q = basicvar[j];
              a[i][q] -= a[p][q] * amt;
            }
            i += ((i == p-1) ? 2 : 1);
          } while (i <= m);
          for (j=m+1; j<=n; j++) {
            q = basicvar[j];
            a[p][q] *= thres;
          }
          a[p][0] *= thres;
          for (i=0; i<=m; i++)
            a[i][idx2] = ((i == 1) ? 1.0 : 0.0);
          basicvar[p] = idx2;
        }
      }
    } while (iterate);
  }
  basicvar[m+1] = (infeasible ? 1 : 0); 
  basicvar[m+2] = (unbound ? 1 : 0);
}


public static void integerProgramming(int n, int m, int a[][])
{
  int i,j,k,l,np,num,r,r1,s,t,count,c,denom,temp,p;
  boolean b,iter,nofeas=false;

  for (j=1; j<=n; j++)
    a[m+j][j] = -1;
  m += n;
  count = 0;
  np = n+1;
  do {
    count++;
    r = 0;
    do {
      r++;
      iter = a[r][np] < 0;
    } while (!iter && (r != m));
    if (iter) {
      k = 0;
      do {
        k++;
        iter = a[r][k] < 0;
      } while (!iter && (k != n));
      nofeas = !iter;
      if (iter) {
        l = k;
        for (j=k+1; j<=n; j++)
          if (a[r][j] < 0) {
            i = -1;
            do {
              i++;
              s = a[i][j] - a[i][l];
            } while (s == 0);
            if (s < 0) l = j;
          }
        s = 0;
        while (a[s][l] == 0) 
          s++;
        num = -a[r][l];
        denom = 1;
        for (j=1; j<=n; j++)
          if ((a[r][j] < 0) && (j != l)) {
            i = s - 1;
            b = true;
            while (b && (i >= 0)) {
              b = (a[i][j] == 0);
              i--;
            }
            if (b) {
              i = a[s][j];
              r1 = a[s][l];
              temp = i / r1;
              if (temp*r1 > i) temp--;
              if ((temp+1)*r1 <= i) temp++;
              t = temp;
              if ((t*r1 == i) && (t > 1)) {
                i = s;
                do {
                  i++;
                  r1 = t*a[i][l] - a[i][j];
                } while (r1 == 0);
                if (r1 > 0) t--;
              }
              c = -a[r][j];
              if (c*denom > t*num) {
                num = c;
                denom = t;
              }
            }
          }
        for (j=1; j<=np; j++)
          if (j != l) {
            p = a[r][j] * denom;
            temp = p / num;
            if (temp*num > p) temp--;
            if ((temp+1)*num <= p) temp++;
            c = temp;
            if (c != 0)
              for (i=0; i<=m; i++)
                a[i][j] += c * a[i][l];
          }
      }
    }
  } while (iter && !nofeas);
  a[0][0] = -a[0][n+1];
  a[0][n+1] = (nofeas ? 1 : 0);
  for (j=1; j<=n; j++)
    a[m-n+j][0] = a[m-n+j][n+1];
}


public static void mixedIntegerProgram(int numvar, int numcontraints,
                   int numintvar, double upbound[], int constraintype[],
                   double tableau[][])
{
  int n = numvar + 1;
  int m = numcontraints + 1;
  
  int controlb,controld,i,j,idx1,idx5,idx9,nminus1,loopcount;
  int p,q,r,numiterations,mille;
  int controla=0,controlc=0,controle=0,pivotcol=0;
  int k=0,idx2=0,idx3=0,idx4=0,idx6=0,idx7=0,idx8=0,pivotrow=0;
  int aux1[] = new int[n+1];
  int aux2[] = new int[n+1];
  int aux3[] = new int[n+1];
  int aux4[] = new int[m+1];
  int aux5[] = new int[numintvar+1];
  int aux6[] = new int[numintvar+1];
  int aux7[] = new int[numintvar+1];
  int aux8[] = new int[numintvar+2];
  int aux9[][] = new int[m+1][numintvar+1]; 
  double objestimate,objvaltolerance,vala,vald,vale,valf,valg;
  double valj,valh,quan1,ajw,quan4,amt1,amt2,amt3;
  double quan5,quan6,quan7,quan8,tmp,quan9;
  double vali=0.,threshold=0.,quan3=0.,quan2=0.,valb=0.,valc=0.;
  double large=1.e34,verylarge=1.e35;
  double origobj[] = new double[n];
  double wk1[] = new double[n + 1];
  double wk2[] = new double[numintvar+2];
  double wk3[] = new double[numintvar+2];
  double wk4[] = new double[numintvar+2];
  double wk5[][] = new double[m+1][n+1];
  double wk6[][] = new double[m+2][n*(n+1)/2+1];
  boolean tempbest=false,skip=false;
  boolean skipa=false,skipb=false,skipc=false,skipd=false,skipe=false;
  boolean overa=false,overb=false,overc=false,prow=false;

  quan8 = 1.0;
  controlb = 1;
  aux8[1] = 1;
  controld = 1;
  numiterations = 0;
  mille = 1000;
  idx5 = mille;
  quan9 = 0.00001;

  for (i=0; i<=n; i++) {
    tableau[0][i] = 0.;
    tableau[m+1][i] = 0.;
  }
  tableau[1][1] = 0.;  
  nminus1 = n - 1;
  for (i=1; i<=nminus1; i++)
    origobj[i] = tableau[1][i+1];
  for (i=1; i<=n; i++)
    wk1[i] = 0.;
  loopcount = 1;
  objestimate = verylarge;
  objvaltolerance = 0.1;
  constraintype[1] = 0;

  if (m >= 2) {
    for (i=2; i<=m; i++)
      if (constraintype[i] < 0)
        tableau[i][1] = -tableau[i][1];
      else
        for (j=2; j<=n; j++)
          tableau[i][j] = -tableau[i][j];
  }
  for (i=2; i<=n; i++)
    if (upbound[i-1] <= 0) upbound[i-1] = (double) mille;
  // set solution vector of to zero and save original upper bounds
  for (i=2; i<=n; i++)
    aux2[i-1] = 0;
  // initialize row and column identifiers and slacks
  if (m >= 2)
    for (i=2; i<=m; i++)
      if (constraintype[i] != 0) constraintype[i] = 1 - i;
  vala = tableau[1][1];
  aux1[1] = 0;
  for (j=2; j<=n; j++)
    if (tableau[1][j] < 0.) {
      for (i=1; i<=m; i++) {
        tableau[i][1] += tableau[i][j] * upbound[j-1];
        tableau[i][j] = -tableau[i][j];
      }
      aux1[j] = mille + j - 1;
    }
    else
      aux1[j] = j - 1;
  // finish initializing the tableau
  iterate:
  while (true) {
    // reverse sign is column of zero slack row is negative
    if (!tempbest) {
      if (!skipd) {
        if (m >= 2) {
          for (k=2; k<=m; k++)
            if (constraintype[k] == 0) {
              if (tableau[k][1] < 0.)
                for (p=1; p<=n; p++) 
                  tableau[k][p] = -tableau[k][p];
            }
        }
        // next pivot step, start dual lp, choose pivot row
        valc = 0.0;
        if (m >= 2) {
          for (i=2; i<=m; i++)
            if (tableau[i][1] > 0.)
              if (tableau[i][1] > valc) {
                valc = tableau[i][1];
                pivotrow = i;
              }
        }
        // if no positive value then linear programming is finished,
        // primal feasible
        if (valc <= 0.) {
          tempbest = true;
          prow = true;
          continue iterate;
        }
      }
      skipd = false;
      // choose pivot column
      valc = -verylarge;
      skip = false;
      if (n >= 2) {
        pivotcol = 0;
        for (j=2; j<=n; j++)
          if (tableau[pivotrow][j] < 0.) {
            valf = tableau[1][j] / tableau[pivotrow][j];
            if (valf > valc) {
              valc = valf;
              pivotcol = j;
            }
            else
              if (valf == valc) 
                if (tableau[pivotrow][j] < tableau[pivotrow][pivotcol])
                  pivotcol = j;
          }
        if (pivotcol != 0) skip = true;
      }
      if (!skip) {
        switch (controld) {
          case 1:
            constraintype[0] = 1;
            return;
          case 2: 
            if (vali == 0.)
              wk3[idx6] = -1.;
            else
              wk4[idx6] = (double) mille;
            overc = true;
            break;
          case 3:
            if (controla == 1)
              wk4[idx6] = (double) mille;
            else 
              if (controla == 2)
                wk3[idx6] = -1.;
              else {
                if ((wk4[idx6] - wk2[idx6] - 1.) == 0.)
                  wk4[idx6] = (double) mille;
                else {
                  wk3[idx6] = -1.;
                }
              }
            overc = true;
            break;
          case 4:
            idx6--;
            overb = true;
            break;
          case 5:      
            if (controlc == 1) wk4[idx6] = (double) mille;
            overc = true;
            break;
        }
      }
      skip = false;
      if (!overa && !overb && !overc) {
        // pivot step
        valj = tableau[pivotrow][pivotcol];
        // update tableau
        for (j=1; j<=n; j++)
          if (tableau[pivotrow][j] != 0.) {
            if (j != pivotcol) {
              valh = tableau[pivotrow][j] / valj;
              for (i=1; i<=m; i++) 
                if (tableau[i][pivotcol] != 0.) {
                  if (i != pivotrow) {
                    tableau[i][j] -= valh * tableau[i][pivotcol];
                    if (Math.abs(tableau[i][j]) <= quan9) tableau[i][j] = 0.;
                  }
                }
            }
          }
        for (j=1; j<=n; j++)
          tableau[pivotrow][j] /= valj;
        // exchange row and column identifiers
        idx1 = constraintype[pivotrow];
        constraintype[pivotrow] = aux1[pivotcol];
        if (idx1 == 0) {
        // if pivot row is zero slack, set modifiers pivot column zero.
          for (i=1; i<=m; i++)
            tableau[i][pivotcol] = tableau[i][n];
          aux1[pivotcol] = aux1[n];
          n--;
        }
        else {
          for (i=1; i<=m; i++)
            tableau[i][pivotcol] = -tableau[i][pivotcol] / valj;
          aux1[pivotcol] = idx1;
          tableau[pivotrow][pivotcol] = 1. / valj;
        }
        // count the number of iterations
        numiterations++;
        if ((constraintype[pivotrow] + mille) == 0) {
          for (j=1; j<=n; j++)
            tableau[pivotrow][j] = tableau[m][j];
          constraintype[pivotrow] = constraintype[m];
          m--;
        }
      }
    }
    if (!prow) {
      if (!overa && !overb && !overc) {
        tempbest = false;
        switch (controld) {
          case 1: 
            continue iterate;
          case 2: 
            if (tableau[1][1] < threshold) continue iterate;
            if (vali == 0.)
              wk3[idx6] = -1.;
            else
              wk4[idx6] = (double) mille;
            overc = true;
            break;
          case 3:
            if (tableau[1][1] < threshold) continue iterate;
            if (controla == 1)
              wk4[idx6] = (double) mille;
            else 
              if (controla == 2)
                wk3[idx6] = -1.;
              else {
                if ((wk4[idx6] - wk2[idx6] - 1.) == 0.)
                  wk4[idx6] = (double) mille;
                else {
                  wk3[idx6] = -1.;
                }
              }
            overc = true;
            break;
          case 4:
            if (tableau[1][1] < threshold) continue iterate;
            idx6--;
            overb = true;
            break;
          case 5:      
            if (tableau[1][1] < threshold) continue iterate;
            if (controlc == 1) wk4[idx6] = (double) mille;
            overc = true;
            break;
        }
      }
    }
    prow = false;
    tempbest = false;
    if (!overa && !overb && !overc) {
      // if a basis variable exceed its upper bound,
      // pivot on the corresponding row
      if (m >= 2) {
        for (i=2; i<=m; i++)
          if (constraintype[i] > 0) {
            j = constraintype[i];
            if (j > mille) j -= mille;
            if ((upbound[j] + tableau[i][1]) < 0.) {
              if ((quan9 + upbound[j] + tableau[i][1]) < 0.) {
                tableau[i][1] = -tableau[i][1] - upbound[j];
                for (k=2; k<=n; k++)
                  tableau[i][k] = -tableau[i][k];
                pivotrow = i;
                constraintype[i] = 
                     (j != constraintype[i]) ? j : (constraintype[i] + mille);
                skipd = true;
                continue iterate;
              }
              else
                tableau[i][1] = -upbound[j];
            }
          }
      }
      // end of linear programming
      if (m >= 2) {
        for (i=2; i<=m; i++)
          if (constraintype[i] > 0) {
            if (constraintype[i] > mille) {
              j = constraintype[i] - mille;
              wk1[j] = upbound[j] + tableau[i][1];
            }
            else {
              j = constraintype[i];
              wk1[j] = -tableau[i][1];
            }
          }
      }
      // set solution vector values for non-basic variables
      for (i=2; i<=n; i++)
        if (aux1[i] > 0) {
          if (aux1[i] > mille) {
            j = aux1[i] - mille;
            wk1[j] = upbound[j];
          }
          else {
            j = aux1[i];
            wk1[j] = 0.;
          }
        }
      skipe = false;
      switch (controld) {
        case 1:
          break;
        case 2: 
          overa = true;
          break;
        case 3:
          overa = true;
          break;
        case 4:
          controlb = 2;
          skipe = true;
          break;
        case 5:      
          n = idx8;
          for (i=1; i<=m; i++) {
            constraintype[i] = aux4[i];
            for (j=1; j<=n; j++) 
              tableau[i][j] = wk5[i][j];
          }
          for (j=1; j<=n; j++) 
            aux1[j] = aux3[j];
          overa = true;
          break;
      }
    }
    if (!overa && !overb && !overc) {
      if (!skipe) {
        // Continuous solution complete
        // compute absolute tolerance
        valb = tableau[1][1];
        vala = Math.abs(vala - tableau[1][1]);
        if (objvaltolerance > 0.)
          threshold = objvaltolerance * vala + valb;
        else
          if (objvaltolerance == 0.) threshold = verylarge;
        // determine whether continuous solution is mixed integer solution
        skip = false;
        if (m >= 2) {
          for (i=2; i<=m; i++)
            if (constraintype[i] > 0) {
              if (constraintype[i] <= mille) {
                if (constraintype[i] > numintvar) continue;
              }
              else {
                if ((constraintype[i] - mille - numintvar) > 0) continue;
              }
              quan5 = tableau[i][1];
              quan6 = quan9;
              quan7 = quan8;
              tmp = -quan5 - ((int)(-quan5 / quan7)) * quan7;
              if (tmp > quan6) {
                if ((1.0 - tmp - quan6) > 0) {
                  skip = true;
                  break;
                }
              }
            }
        }
        if (!skip) {
          // either continuous solution is integer solution, 
          // or no integer variables are requested
          for (i=1; i<=nminus1; i++)
            tableau[0][i] = wk1[i];
          tmp = 0.;
          for (i=1; i<=nminus1; i++)
            tmp += wk1[i] * origobj[i];
          tableau[0][0] = tmp;
          constraintype[0] = 0;
          return;
        }
        skip = false;
        idx5 = 0;
      }
    }
    // integer programming start
    iterateIP1:
    while (true) {
      if (!overa && !overb && !overc) {
        if (!skipe)
          idx6 = 1;
      }
      iterateIP2:
      while (true) {
        if (!overa && !overb && !overc) {
          skipe = false;
          valc = -quan8;
          aux8[idx6+1] = aux8[idx6];
          // choose next integer variable to be constrained
          // try nonbasic variables first, 
          // choose one with largest shad price
          for (i=2; i<=n; i++)
            if (aux1[i] > 0) {
              if (aux1[i] < mille) {
                if (aux1[i] > numintvar) continue;
              }
              else {
                if ((aux1[i] - mille - numintvar) > 0) continue;
              }
              if (valc < tableau[1][i]) {
                idx3 = i;
                valc = tableau[1][i];
              }
            }
        }
        // if none left, try basic variables
        if (((valc + quan8) != 0.) || overa || overb || overc) {
          if (!overa && !overb && !overc) {          
            // variable chosen
            aux2[idx6] = aux1[idx3];
            wk3[idx6] = -1.;
            aux5[idx6] = idx3;
            aux6[idx6] = 0;
            wk2[idx6] = 0.;
            // if objective function value + shadow price exceeds tolerance,
            // indicate upward direction infeasible
            skip = false;
            if ((tableau[1][1] + tableau[1][idx3] - threshold) >= 0.) {
              wk4[idx6] = (double) mille;
              if (idx6 > 1) {
                aux7[idx6] = 0;
                skip = true;
              }
            }
            else {
              wk4[idx6] = 1.;
              if (idx6 != 1) {
                // save the tableau 
                if (idx6 < idx5) skip = true;
              }
            }
            if (!skip) {
              idx9 = aux8[idx6];
              for (j=1; j<=m; j++) {
                aux9[j][idx6] = constraintype[j];
                for (k=1; k<=n; k++) {
                  i = idx9 + k - 1;
                  if (j <= 1) wk6[m+1][i] = aux1[k];
                  wk6[j][i] = tableau[j][k];
                }
              }
              aux7[idx6] = n;
              aux8[idx6+1] = idx9 + n;
            }
            skip = false;
            aux1[idx3] = aux1[n];
            for (j=1; j<=m; j++)
              tableau[j][idx3] = tableau[j][n];
            n--;
          }
          if (!overb && !overc) {
            overa = false;
            if (idx6 < numintvar) {
              // constrain next integer variable
              idx6++;
              continue iterateIP2;
            }
            // feasible integer solution obtained
            threshold = tableau[1][1];
            objestimate = 1.;
            // store current best mixed integer solution
            for (i=1; i<=numintvar; i++)
              if (aux2[i] != 0) {
                if (aux2[i] <= mille) {
                  j = aux2[i];
                  wk1[j] = wk2[i];
                }
                else {
                  j = aux2[i] - mille;
                  wk1[j] = upbound[j] - wk2[i];
                }
              }
            for (i=1; i<=nminus1; i++)
              tableau[0][i] = wk1[i];
            tmp = 0.;
            for (i=1; i<=nminus1; i++)
              tmp += wk1[i] * origobj[i];
            tableau[0][0] = tmp;
          }
          iteratecur:
          while (true) {
            if (!overb && !overc) {
              if (idx6 <= 0) {
                if (loopcount == 0) {
                  // Optimality has been established
                  constraintype[0] = 0;
                  return;
                }
                if (objestimate < verylarge) {
                  // Optimality has been established
                  constraintype[0] = 0;
                  return;
                }
                loopcount++;
                threshold = (double)(loopcount) * objvaltolerance * vala + valb;
                n = aux7[1];
                for (i=1; i<=m; i++) {
                  constraintype[i] = aux9[i][1];
                  for (j=1; j<=n; j++)
                    tableau[i][j] = wk6[i][j];
                }
                for (k=1; k<=n; k++)
                  aux1[k] = (int)wk6[m+1][k];
                continue iterateIP1;
              }
            }
            if (!overb) {
              overc = false;
              k = (aux2[idx6] <= mille) ? aux2[idx6] : aux2[idx6] - mille;
              idx7 = aux5[idx6];
            }
            overb = false;
            if (wk3[idx6] < 0.) {
              if (wk4[idx6] > upbound[k]) {
                idx6--;
                continue iteratecur;
              }
              else {
                //  top end feasible
                controla = 1;
                if (aux6[idx6] == 0) {
                  if (idx6 < idx5) {
                    controlc = 1;
                    if (idx6 != 1) {
                      controla = 4;
                      idx4 = idx6 - 1;
                      idx6 = 1;
                    }
                  }
                }
              }
            }
            else
              controla = (wk4[idx6] > upbound[k]) ? 2 : 3;
            while (true) {
              // retrieve saved tableau
              n = aux7[idx6];
              idx9 = aux8[idx6];
              for (p=1; p<=m; p++) {
                constraintype[p] = aux9[p][idx6];
                for (q=1; q<=n; q++) {
                  r = idx9 + q - 1;
                  if (p <= 1) aux1[q] = (int)wk6[m+1][r];
                  tableau[p][q] = wk6[p][r];
                }
              }
              switch (controla) {
                case 1:
                  break;
                case 2: 
                  wk2[idx6] = wk3[idx6];
                  wk3[idx6] -= 1.;
                  tableau[idx7][1] += wk2[idx6];
                  constraintype[idx7] = 0;
                  if (Math.abs(tableau[idx7][1]) <= quan9) tableau[idx7][1] = 0.;
                  controld = 3;
                  tempbest = true;
                  continue iterate;
                case 3:
                  vald = verylarge;
                  vale = -verylarge;
                  for (p=2; p<=n; p++)
                    if (tableau[idx7][p] != 0.) {
                      if (tableau[idx7][p] > 0.) { 
                        valf = tableau[1][p] / tableau[idx7][p];
                        if (valf < vald) vald = valf;
                      }
                      else {
                        valg = tableau[1][p] / tableau[idx7][p];
                        if (valg > vale) vale = valg;
                      }
                    }
                  if (vald >= verylarge) {
                    // bottom end infeasible
                    wk3[idx6] = -1.;
                    break;
                  }
                  if ((vale + verylarge) <= 0.) {
                    // top end infeasible
                    wk4[idx6] = (double) mille;
                    wk2[idx6] = wk3[idx6];
                    wk3[idx6] -= 1.;
                  } 
                  else {
                    amt2 = Math.abs(vald * (tableau[idx7][1] + wk3[idx6]));
                    amt3 = Math.abs(vale * (tableau[idx7][1] + wk4[idx6]));
                    if (amt2 <= amt3) {
                      wk2[idx6] = wk3[idx6];
                      wk3[idx6] -= 1.;                      
                    }
                    else
                      break;
                  }
                  tableau[idx7][1] += wk2[idx6];
                  constraintype[idx7] = 0;
                  if (Math.abs(tableau[idx7][1]) <= quan9) tableau[idx7][1] = 0.;
                  controld = 3;
                  tempbest = true;
                  continue iterate;
                case 4:
                  for (p=1; p<=idx4; p++) {
                    q = aux5[p];
                    aux1[q] = aux1[n];
                    for (j=1; j<=m; j++) {
                      if (wk2[p] >= 1.) {
                        if (wk2[p] == 1.)
                          tableau[j][1] += tableau[j][q];
                        else
                          tableau[j][1] += wk2[p] * tableau[j][q];
                        controlc = 2;
                      }
                      tableau[j][q] = tableau[j][n];
                    }
                    n--;
                  }
                  idx6 = idx4 + 1;
                  controla = 1;
              }
              wk2[idx6] = wk4[idx6];
              wk4[idx6] += 1.;
              if (aux6[idx6] != 0) {
                tableau[idx7][1] += wk2[idx6];
                constraintype[idx7] = 0;
                if (Math.abs(tableau[idx7][1]) <= quan9) tableau[idx7][1] = 0.;
                controld = 3;
                tempbest = true;
                continue iterate;
              }
              for (p=1; p<=m; p++) {
                tableau[p][1] += wk2[idx6] * tableau[p][idx7];
                if (Math.abs(tableau[p][1]) <= quan9) tableau[p][1] = 0.;
                tableau[p][idx7] = tableau[p][n];
              }
              aux1[idx7] = aux1[n];
              n--;
              if (tableau[1][1] >= threshold) {
                idx6--;
                continue iteratecur;
              }
              if (idx6 < idx5) {
                for (i=1; i<=m; i++) {
                  aux4[i] = constraintype[i];
                  for (j=1; j<=n; j++) 
                    wk5[i][j] = tableau[i][j];
                }
                for (j=1; j<=n; j++) 
                  aux3[j] = aux1[j];
                idx8 = n;
                controld = 5;
                continue iterate;
              }
              controld = 3;
              tempbest = true;
              continue iterate;
            }
          }
        }
        // choose next integer variable to be constrained
        if (idx6 < idx5)
          idx5 = idx6;
        else
          if (idx6 == idx5) {
            if (controlb == 1) {
              controld = 4;
              continue iterate;
            }
          }
        controlb = 1;
        valc = -quan8;
        skip = false;
        skipa = false;
        skipb = false;
        if (m >= 2) {
          for (idx7=2; idx7<=m; idx7++)
            if (constraintype[idx7] > 0) {
              if (constraintype[idx7] < mille) {
                if (constraintype[idx7] > numintvar) continue;
              }
              else {
                if ((constraintype[idx7] - mille - numintvar) > 0) continue;
              }
              vald = verylarge;
              vale = -verylarge;
              quan1 = -tableau[idx7][1] + quan9;
              quan3 = (double)((int)(quan1));
              quan4 = quan3 + 1.;
              if (n <= 1) {
                skip = true;
                break;
              }
              for (p=2; p<=n; p++)
                if (tableau[idx7][p] != 0.) {
                  if (tableau[idx7][p] > 0.) { 
                    valf = tableau[1][p] / tableau[idx7][p];
                    if (valf < vald) vald = valf;
                  }
                  else {
                    valg = tableau[1][p] / tableau[idx7][p];
                    if (valg > vale) vale = valg;
                  }
                }
              if ((vale + large) <= 0.) {
                skipa = true;
                break;
              }
              if (vald >= large) {
                skipb = true;
                break;
              }
              amt2 = Math.abs(vald * (tableau[idx7][1] + quan3));
              amt3 = Math.abs(vale * (tableau[idx7][1] + quan4));
              amt1 = Math.abs(amt2 - amt3);
              if (amt1 > valc) {
                valc = amt1;
                quan2 = quan3;
                idx2 = idx7;
                vali = (amt2 <= amt3) ? 0. : 1.;
              }
            }
        }
        skipc = false;
        if (!skipa) {
          if (!skipb) {
            if (!skip) {
              quan3 = quan2;
              idx7 = idx2;
              wk2[idx6] = quan3 + vali;
              wk3[idx6] = wk2[idx6] - 1.;
              wk4[idx6] = wk2[idx6] + 1.;
              skipc = true;
            }
            skip = false;
            if (!skipc) {
            // if no. of cols=1 and right hand side=0,
            // then do not proceed to linear programming
              if (Math.abs(tableau[idx7][1] + quan3) > quan9) {
                idx6--;
                overb = true;
                continue iterateIP2;
              }
              wk3[idx6] = -1.;
              wk4[idx6] = (double) mille;
              wk2[idx6] = quan3;
              aux2[idx6] = constraintype[idx7];
              constraintype[idx7] = 0;
              overa = true;
              continue iterateIP2;
            }
          }
          if (!skipc) {
            // constraining variable in lower direction infeasible
            wk3[idx6] = -1.;
            if (Math.abs(tableau[idx7][1] + quan3) <= quan9) {
              vali = 0.;
              wk2[idx6] = quan3 + vali;
              wk4[idx6] = wk2[idx6] + 1.;
              skipc = true;
            }
            else {
              wk4[idx6] = quan3 + 2.;
              vali = 1.;
              wk2[idx6] = quan3 + vali;
              skipc = true;
            }
          }
        }
        if (!skipc) {
          // constraining variable in upper direction infeasible
          wk4[idx6] = (double) mille;
          wk3[idx6] = quan3 - 1.;
          vali = 0.;
          wk2[idx6] = quan3 + vali;
        }
        // save the tableau
        skipc = false;
        idx8 = n;
        idx9 = aux8[idx6];
        for (p=1; p<=m; p++) {
          aux9[p][idx6] = constraintype[p];
          for (q=1; q<=n; q++) {
            r = idx9 + q - 1;
            if (p <= 1) wk6[m+1][r] = aux1[q];
            wk6[p][r] = tableau[p][q];
          }
        }
        aux7[idx6] = n;
        aux8[idx6+1] = idx9 + n;
        tableau[idx7][1] += wk2[idx6];
        aux5[idx6] = idx7;
        aux2[idx6] = constraintype[idx7];
        aux6[idx6] = 1;
        constraintype[idx7] = 0;
        if (Math.abs(tableau[idx7][1]) <= quan9) tableau[idx7][1] = 0.;
        controld = 2;
        // return to carry out linear programming
        continue iterate;
      }
    }
  }
}


public static int quadraticProgramming(int n, int m, double a[][], double b[],
                    double c[][], double d[], int maxiterations, double sol[])
{
  int mplusn,m2n2,m2n3,m2n3plus1,big,unrestricted;
  int i,j,iterations,temp,column,row=0;
  int index[] = new int[n+m+1];
  double total,candidate,dividend;
  double tableau[][] = new double[n+m+1][2*m+3*n+2];
  double price[] = new double[2*m+3*n+2];
  double change[] = new double[2*m+3*n+2];
  double net[] = new double[2*m+3*n+2];
  double gain[] = new double[2*m+3*n+2];
  double fraction[] = new double[n+m+1];

  for (i=0; i<=n; i++)
    sol[i] = 0.;
  for (i=0; i<=n; i++)
    for (j=0; j<=n; j++)
      if (i != j) c[i][j] /= 2.;
  big = Integer.MAX_VALUE;
  mplusn = m + n;
  m2n2 = m + m + n + n;
  m2n3 = m2n2 + n;
  m2n3plus1 = m2n3 + 1;
  for (i=1; i<=mplusn; i++)
    for (j=1; j<=m2n3plus1; j++)
      tableau[i][j] = 0.;
  for (i=1; i<=m; i++)
    tableau[i][1] = b[i];
  for (i=m+1; i<=mplusn; i++)
    tableau[i][1] = -d[i-m];
  for (i=1; i<=m; i++) 
    for (j=1; j<=n; j++)
      tableau[i][j+1] = a[i][j];
  for (i=1; i<=n; i++) 
    for (j=1; j<=n; j++)
      tableau[i+m][j+1] = 2. * c[i][j];
  for (i=m+1; i<=mplusn; i++)
    for (j=n+2; j<=mplusn+1; j++)
      tableau[i][j] = a[j-n-1][i-m];
  for (i=1; i<=mplusn; i++) {
    temp = i + mplusn + n + 1;
    for (j=m2n2+2; j<=m2n3plus1; j++)
      if (j == temp) tableau[i][j] = 1.;
  }
  for (i=m+1; i<=mplusn; i++) {
    temp = i - m + mplusn + 1;
    for (j=mplusn+2; j<=m2n3plus1; j++)
      if (j == temp) tableau[i][j] = -1.;
  }
  for (j=1; j<=m2n3; j++)
    price[j] = 0.;
  for (i=1; i<=m; i++)
    price[n+1+i] = tableau[i][1];
  for (j=m2n2+2; j<=m2n3plus1; j++)
    price[j] = big - 1;
  for (i=1; i<=mplusn; i++)
    index[i] = m2n3 - mplusn + i;
  iterations = 0;
  while (true) {
    // iteration start
    iterations++;
    for (j=1; j<=m2n3plus1; j++)
      gain[j] = 0.;
    for (j=1; j<=m2n3plus1; j++) {
      total = 0.;
      for (i=1; i<=mplusn; i++)
        total += price[index[i]+1] * tableau[i][j];
      gain[j] = total;
      change[j] = price[j] - gain[j];
    }
    // search for the pivot element
    column = 0;
    candidate = 0.;
    // get the variable with largest gain
    for (i=2; i<=m2n3plus1; i++)
      if (change[i] < candidate) {
        candidate = change[i];
        column = i;
      }
    if (column <= 0) break;
    unrestricted = 0;
    for (i=1; i<=mplusn; i++) {
      if (tableau[i][column] > 0)
        fraction[i] = tableau[i][1] / tableau[i][column];
      else {
        unrestricted++;
        if (unrestricted == mplusn)
          // objective function is unbounded
          return 1;
        else
          fraction[i] = Double.MAX_VALUE;
      }
    }
    // remove limiting variable
    for (i=1; i<=mplusn; i++)
      if (fraction[i] >= 0) {
        if (fraction[i] > big) fraction[i] = big;
        candidate = fraction[i];
        row = i;
        break;
      }
    for (i=1; i<=mplusn; i++)
      if (candidate > fraction[i]) {
        candidate = fraction[i];
        row = i;
      }
    // perform pivoting and introduce new variable
    dividend = tableau[row][column];
    for (j=1; j<=m2n3plus1; j++)
      tableau[row][j] /= dividend;
    for (i=1; i<=mplusn; i++)
      if (i != row) {
        for (j=1; j<=m2n3plus1; j++)
          net[j] = tableau[row][j] * tableau[i][column] / 
                                     tableau[row][column];
        for (j=1; j<=m2n3plus1; j++)
          tableau[i][j] -= net[j];
      }
    price[row] = price[column];
    index[row] = column - 1;
    // recompute the price
    for (j=1; j<=m2n2+1; j++)
      price[j] = 0.;
    for (i=1; i<=mplusn; i++) {
      if (index[i] <= mplusn)
        temp = index[i] + mplusn + 1;
      else {
        if (index[i] > m2n2) continue;
        temp = index[i] - (mplusn - 1);
      }
      price[temp] = tableau[i][1];
    }
    if (iterations >= maxiterations)
      // maximum number of iterations exceeded
      return 2;
  }
  // return the optimal solution
  total = 0.;
  for (i=1; i<=mplusn; i++)
    if (index[i] <= n) total += d[index[i]] * tableau[i][1];
  sol[0] = total;
  total =0.;
  for (i=1; i<=mplusn; i++)
    for (j=1; j<=mplusn; j++) {
      if (index[i] > n) continue;
      if (index[j] > n) continue;
      total += c[index[i]][index[j]] * tableau[i][1] * tableau[j][1];
    }
  sol[0] += total;
  for (i=1; i<=mplusn; i++) 
    if ((tableau[i][1] != 0) && (index[i] <= n))
      sol[index[i]] = tableau[i][1];
  return 0;
}


public static void revisedSimplex(boolean maximize, int n, int m,
                    double a[][], double epsilon, int basicvar[])
{
  int i,j,k,m2,p,idx=0;
  double objcoeff[] = new double[n + 1];
  double varsum[] = new double[n + 1];
  double optbasicval[] = new double[m + 3];
  double aux[] = new double [m + 3];
  double work[][] = new double[m + 3][m + 3];
  double part,sum;
  boolean infeasible,unbound,abort,out,iterate;

  if (maximize)
    for (j=1; j<=n; j++)
      a[0][j] = -a[0][j];
  infeasible = false;
  unbound = false;
  m2 = m + 2;
  p = m + 2;
  out = true;
  k = m + 1;
  for (j=1; j<=n; j++) {
    objcoeff[j] = a[0][j];
    sum = 0.0;
    for (i=1; i<=m; i++) 
      sum -= a[i][j];
    varsum[j] = sum;
  }
  sum = 0.0;
  for (i=1; i<=m; i++) {
    basicvar[i] = n + i;
    optbasicval[i] = a[i][0];
    sum -= a[i][0];
  }
  optbasicval[k] = 0.0;
  optbasicval[m2] = sum;
  for (i=1; i<=m2; i++) {
    for (j=1; j<=m2; j++)
      work[i][j] = 0.0;
    work[i][i] = 1.0;
  }
  iterate = true;
  do {
    //  phase 1
    if ((optbasicval[m2] >= -epsilon) && out) {
      out = false;
      p = m + 1;
    }
    part = 0.0;
    //  phase 2
    for (j=1; j<=n; j++) {
      sum = work[p][m+1] * objcoeff[j] + work[p][m+2] * varsum[j];
      for (i=1; i<=m; i++) 
        sum += work[p][i] * a[i][j];
      if (part > sum) {
        part = sum;
        k = j;
      }
    }
    if (part > -epsilon) {
      iterate = false;
      if (out) 
        infeasible = true;
      else
        a[0][0] = -optbasicval[p];
    }
    else {
      for (i=1; i<=p; i++) {
        sum = work[i][m+1] * objcoeff[k] + work[i][m+2] * varsum[k];
        for (j=1; j<=m; j++) 
          sum += work[i][j] * a[j][k];
        aux[i] = sum;
      }
      abort = true;
      for (i=1; i<=m; i++)
        if (aux[i] >= epsilon) {
          sum = optbasicval[i] / aux[i];
          if (abort || (sum < part)) {
            part = sum;
            idx = i;
          }
          abort = false;
        }
      if (abort) {
        unbound  = true;
        iterate = false;
      }
      else {
        basicvar[idx] = k;
        sum = 1.0 / aux[idx];
        for (j=1; j<=m; j++) 
          work[idx][j] *= sum;
        i = ((idx == 1) ? 2 : 1);
        do {
          sum = aux[i];
          optbasicval[i] -= part * sum;
          for (j=1; j<=m; j++) 
            work[i][j] -= work[idx][j] * sum;
          i += ((i == idx-1) ? 2 : 1);
        } while (i <= p);
        optbasicval[idx] = part;
      }
    }
  } while (iterate);
  // return results
  basicvar[m+1] = (infeasible ? 1 : 0);
  basicvar[m+2] = (unbound ? 1 : 0);
  for (i=1; i<=m; i++)
    a[i][0] = optbasicval[i];
  if (maximize) {
    for (j=1; j<=n; j++)
      a[0][j] = -a[0][j];
    a[0][0] = -a[0][0];
  }
}


public static void setCovering(int n, int m, int a[][],
                               int c[], int sol[])
{
  int i,j;
  int b[] = new int[m + 1];

  for (i=0; i<=m; i++)
    for (j=0; j<=n; j++)
      a[i][j] = (a[i][j] == 1) ? -1 : 0;
  for (i=0; i<=m; i++)
    b[i] = -1;
  zeroOneIntegerProgramming(true, n, m, a, b, c, sol);
}


public static void setPartitioning(int n, int m, int a[][],
                                   int c[], int sol[])
{
  int i,j,sum,mult;
  int b[] = new int[m + 1];
  int newc[] = new int[n+1];

  for (i=0; i<=m; i++)
    for (j=0; j<=n; j++)
      a[i][j] = (a[i][j] == 1) ? -1 : 0;
  for (i=0; i<=m; i++)
    b[i] = -1;

// transform the cost vector, original cost vector not changed 
  sum = 0;
  for (j=1; j<=n; j++)
    sum += c[j];
  mult = sum + 1;
  for (j=1; j<=n; j++) {
  	sum = 0;
  	for (i=1; i<=m; i++)
  	  sum += a[i][j];
  	newc[j] += mult*(-sum);
  }
// use the transformed cost vector to solve the set partitioning problem
  Optimize.zeroOneIntegerProgramming(true, n, m, a, b, newc, sol);
  if (a[0][0] == 0) {
//  obtain the optimal objective funciton value by the solution vector
    sum = 0;
    for (i=1; i<=n; i++) {
      sum += (sol[i] == 0) ? 0 : c[i]; 
    }
    sol[0] = sum;
  }
}


public static void zeroOneIntegerProgramming(boolean minimize, int n,
                        int m, int a[][], int b[], int c[], int sol[])
{
  int i,j,k,optvalue,elm1=0,elm2,elm3,elm4,idx,sub1,sub2,sub3;
  int item1,item2,item3;
  int ccopy[] = new int[n + 1];
  int aux1[] = new int[n + 1];
  int aux2[] = new int[n + 1];
  int aux3[] = new int[n + 1];
  int aux4[] = new int[n + 2];
  int aux5[] = new int[m + 1];
  int aux6[] = new int[m + 1];
  int aux7[] = new int[m + 1];
  boolean cminus[] = new boolean[n + 1];
  boolean optimalfound,backtrack=false,outer;

// scan for the negative objective coefficients
  if (!minimize)
    for (j=1; j<=n; j++)
      c[j] = -c[j];
  for (j=1; j<=n; j++) {
    cminus[j] = false;
    ccopy[j] = c[j];
  }
  for (j=1; j<=n; j++)
    if (c[j] < 0) {
      cminus[j] = true;
      c[j] = -c[j];
      for (i=1; i<=m; i++) {
        b[i] -= a[i][j];
        a[i][j] = -a[i][j];
      }
    }

  for (i=1; i<=m; i++)
    aux5[i] = b[i];
  elm4 = 1;
  for (j=1; j<=n; j++) {
    aux3[j] = 0;
    elm4 += c[j];
  }
  optvalue = elm4 + elm4;
  sub2 = 0;
  sub3 = 0;
  elm4 = 0;
  aux4[1] = 0;
  optimalfound = false;
  iterate:
  while (true) {
    if (backtrack) {
//    backtracking
      backtrack = false;
      outer = false;
      for (j=1; j<=n; j++) 
        if (aux3[j] < 0) aux3[j] = 0;
      if (sub2 > 0)
        do {
          sub1 = sub3;
          sub3 -= aux4[sub2+1];
          for (j=sub3+1; j<=sub1; j++)
            aux3[aux2[j]] = 0;
          sub1 = Math.abs(aux1[sub2]);
          aux4[sub2] += sub1;
          for (j=sub3-sub1+1; j<=sub3; j++) {
            sub1 = aux2[j];
            aux3[sub1] = 2;
            elm4 -= c[sub1];
            for (i=1; i<=m; i++)
              aux5[i] += a[i][sub1];
          }
          sub2--;
          if (aux1[sub2+1] >= 0) {
            outer = true;
            continue iterate;
          }
        } while (sub2 != 0);
      if (outer) continue;
      sol[0] = optvalue;
      a[0][0] = (optimalfound ? 0 : 1);
      for (j=1; j<=n; j++)
        if (cminus[j]) {
          sol[j] = ((sol[j] == 0) ? 1 : 0);
          sol[0] += ccopy[j]; 
        }
      for (j=1; j<=n; j++)
        c[j] = ccopy[j];
      if (!minimize) sol[0] = -sol[0];
      return;
    } 
    sub1 = 0;
    idx = 0;
    for (i=1; i<=m; i++) {
      item1 = aux5[i];
      if (item1 < 0) {
//      infeasible constraint i 
        sub1++;
        elm3 = 0;
        elm1 = item1;
        elm2 = -Integer.MAX_VALUE;
        for (j=1; j<=n; j++)
          if (aux3[j] <= 0)
            if (c[j] + elm4 >= optvalue) {
              aux3[j] = 2;
              aux4[sub2+1]++;
              sub3++;
              aux2[sub3] = j;
            }
            else {
              item2 = a[i][j];
              if (item2 < 0) {
                elm1 -= item2;
                elm3 += c[j];
                if (elm2 < item2) elm2 = item2;
              }
            }
        if (elm1 < 0) {
          backtrack = true;
          continue iterate;
        }
        if (elm1 + elm2 < 0) {
          if (elm3 + elm4 >= optvalue) {
            backtrack = true;
            continue iterate;
          }
          for (j=1; j<=n; j++) {
            item2 = a[i][j];
            item3 = aux3[j];
            if (item2 < 0) {
              if (item3 == 0) {
                aux3[j] = -2;
                for (k=1; k<=idx; k++) {
                  aux7[k] -= a[aux6[k]][j];
                  if (aux7[k] < 0) {
                    backtrack = true;
                    continue iterate;
                  }
                }
              }
            }
            else
              if (item3 < 0) {
                elm1 -= item2;
                if (elm1 < 0) {
                  backtrack = true;
                  continue iterate;
                }
                elm3 += c[j];
                if (elm3 + elm4 >= optvalue) {
                  backtrack = true;
                  continue iterate;
                }
              }
          }
          idx++;
          aux6[idx] = i;
          aux7[idx] = elm1;
        }
      }
    }
    if (sub1 == 0) {           
//    updating the best solution
      optvalue = elm4;
      optimalfound = true;
      for (j=1; j<=n; j++)
        sol[j] = ((aux3[j] == 1) ? 1 : 0);
      backtrack = true;
      continue iterate;
    }
    if (idx == 0) {
      sub1 = 0;
      elm3 = -Integer.MAX_VALUE;
      for (j=1; j<=n; j++)
        if (aux3[j] == 0) {
          elm2 = 0;
          for (i=1; i<=m; i++) {
            item1 = aux5[i];
            item2 = a[i][j];
            if (item1 < item2) elm2 += (item1 - item2);
          }
          item1 = c[j];
          if ((elm2 > elm3) || (elm2 == elm3) && (item1 < elm1)) {
            elm1 = item1;
            elm3 = elm2;
            sub1 = j;
          }
        }
      if (sub1 == 0) {
        backtrack = true;
        continue iterate;
      }
      sub2++;
      aux4[sub2+1] = 0;
      sub3++;
      aux2[sub3] = sub1;
      aux1[sub2] = 1;
      aux3[sub1] = 1;
      elm4 += c[sub1];
      for (i=1; i<=m; i++) 
        aux5[i] -= a[i][sub1];
    }
    else {
      sub2++;
      aux1[sub2] = 0;
      aux4[sub2+1] = 0;
      for (j=1; j<=n; j++)
        if (aux3[j] < 0) {
          sub3++;
          aux2[sub3] = j;
          aux1[sub2]--;
          elm4 += c[j];
          aux3[j] = 1;
          for (i=1; i<=m; i++) 
            aux5[i] -= a[i][j];
        }
    }
  }
}

}