public class bottleneckAssignment32 {

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
        int n = 32;
        int cost[][] = {{89104, 3560, 13596, 40742, 58460, 51902, 30244, 80674, 73794, 67816, 10926, 89826, 52532, 63042, 65564, 1822, 7806, 94226, 28126, 90264, 67166, 93766, 36186, 13444, 25292, 3384, 50614, 86900, 92104, 76258, 2404, 74090, 62326},
{17710, 58250, 19198, 83646, 26868, 67930, 65882, 40864, 23410, 5186, 60078, 25344, 57630, 36958, 37992, 29858, 75740, 8654, 34160, 91876, 79484, 72878, 85832, 91530, 17044, 55028, 98654, 40052, 2238, 19832, 6656, 29664, 33894},
{92534, 64854, 72570, 73272, 14886, 56890, 26130, 81844, 87114, 66846, 18164, 46580, 83112, 38894, 49496, 32116, 7872, 99186, 9602, 88336, 74564, 49956, 57018, 29592, 50562, 68844, 91200, 84528, 13156, 25696, 77664, 7972, 90140},
{62746, 88082, 98492, 65964, 53440, 47062, 76528, 60500, 20566, 16708, 48168, 88378, 32476, 31492, 81356, 21952, 35438, 55276, 78500, 35826, 80130, 76622, 62960, 51778, 74380, 92450, 33240, 9488, 10280, 18638, 69556, 59966, 2010},
{33632, 64002, 24798, 71518, 18014, 43142, 78908, 59488, 99762, 29646, 18726, 95826, 35120, 93014, 97682, 17376, 50406, 93934, 442, 24240, 56746, 3190, 83496, 42614, 78188, 11726, 34272, 39822, 79260, 81816, 8082, 65486, 56830},
{57366, 91508, 69862, 54354, 9182, 13088, 49668, 90636, 39656, 74412, 92156, 51102, 91674, 38626, 47560, 67520, 65428, 60584, 10526, 60556, 74030, 12660, 28608, 60022, 31142, 65094, 4404, 67350, 5610, 77388, 69750, 45700, 38610},
{42122, 93764, 45388, 4146, 41920, 162, 44846, 70468, 95952, 29154, 7872, 98756, 51068, 25732, 12722, 7698, 63052, 12720, 21604, 48780, 88166, 47404, 83916, 28108, 75350, 97044, 57382, 66500, 27422, 22788, 78300, 67072, 58590},
{95422, 70340, 51954, 97854, 9810, 45942, 13124, 48950, 50086, 5102, 65966, 52488, 79336, 97758, 35068, 69642, 90682, 18788, 68708, 14262, 99734, 8750, 536, 10426, 16726, 14892, 11248, 68268, 20938, 70584, 25120, 23476, 37404},
{1946, 7944, 15086, 53370, 58012, 42752, 58518, 59152, 89608, 72558, 5402, 8294, 50152, 57650, 96846, 43576, 53148, 44688, 72750, 80370, 58602, 17180, 87422, 40402, 15384, 2562, 32406, 44980, 53166, 84738, 16550, 73160, 28864},
{57554, 51818, 86048, 90212, 73908, 27370, 49768, 20650, 65082, 18634, 10432, 86762, 48802, 58554, 36420, 61408, 67636, 79296, 29128, 53426, 93724, 37834, 9236, 57200, 77334, 1294, 45688, 79960, 43892, 78730, 10372, 33052, 40030},
{71358, 7556, 16784, 16992, 57658, 29746, 35618, 42520, 98550, 32190, 98978, 84604, 41538, 2680, 61170, 31464, 5788, 10068, 29262, 97250, 83360, 99566, 93922, 46332, 4980, 41696, 30150, 372, 68482, 21642, 72768, 81684, 40712},
{15498, 48744, 67932, 42960, 92646, 14288, 95516, 62612, 40426, 51564, 68398, 96488, 30176, 67670, 40270, 77892, 12276, 17960, 33910, 15878, 42134, 75428, 99446, 6430, 60278, 15654, 91072, 32940, 82682, 74464, 6284, 36252, 36454},
{19680, 55524, 33840, 88102, 41758, 28990, 78680, 45626, 7074, 39054, 33826, 49782, 89882, 30706, 77294, 98286, 72250, 84860, 67228, 51898, 83934, 1802, 33620, 14576, 56090, 8586, 69618, 34398, 36190, 81182, 49844, 17132, 35576},
{866, 67156, 84354, 63392, 12642, 58004, 81996, 60522, 56430, 65756, 60314, 69732, 71244, 56576, 24554, 71380, 8626, 66524, 86246, 81798, 4898, 50954, 5158, 14974, 7014, 94210, 78498, 71946, 88460, 19458, 90406, 78232, 33000},
{55728, 49800, 86046, 97740, 96226, 93116, 22372, 10470, 37500, 77838, 80902, 9914, 65910, 71282, 26628, 24656, 51644, 6594, 32498, 11976, 47704, 33416, 6914, 70800, 59868, 89784, 31736, 20398, 25612, 83040, 39236, 8924, 46382},
{72972, 8742, 89228, 44608, 91550, 70750, 57682, 55554, 77972, 38422, 31872, 26396, 24922, 46786, 58982, 50142, 30748, 13562, 24900, 10266, 12774, 50026, 87338, 58404, 14350, 71964, 74902, 19710, 65904, 83732, 492, 83290, 47814},
{18310, 68448, 88798, 35574, 41422, 61572, 34094, 91974, 77384, 29128, 25950, 22552, 70684, 20956, 23928, 77816, 388, 34448, 69338, 5570, 44924, 6648, 74066, 52424, 36562, 80212, 53990, 65182, 58160, 4256, 26014, 8458, 84934},
{79580, 43692, 4634, 7656, 30030, 87558, 35038, 88540, 30620, 60756, 21358, 8744, 71168, 85014, 5308, 95892, 5738, 67588, 17704, 21306, 90664, 31260, 32162, 5482, 93238, 73452, 74962, 4074, 83644, 53422, 95294, 42134, 88548},
{96908, 84870, 43314, 25828, 34116, 49030, 28354, 97774, 87076, 69168, 86796, 18178, 28308, 53888, 74076, 9118, 62726, 56538, 70450, 5974, 35492, 83802, 16526, 63288, 60456, 13114, 57892, 56530, 70584, 72704, 81492, 75942, 728},
{16970, 53218, 57748, 87300, 29090, 55650, 3494, 54532, 65272, 80288, 7350, 85134, 57242, 47430, 44402, 73626, 92760, 14078, 81564, 80532, 78902, 92822, 17590, 74122, 46074, 81086, 63110, 85760, 72282, 51282, 65692, 50610, 43406},
{92830, 3998, 76854, 20730, 84300, 16008, 93992, 55798, 74246, 19870, 95046, 88066, 34354, 77764, 45698, 50228, 10134, 77974, 58136, 40212, 41954, 27362, 7812, 24994, 39746, 14848, 45442, 44228, 95832, 1006, 51906, 91826, 47194},
{33028, 77390, 90074, 18172, 81248, 53292, 98246, 874, 844, 83072, 40432, 32484, 52484, 37398, 75602, 602, 77666, 72044, 2268, 52824, 12688, 12150, 17808, 16902, 25334, 56334, 34160, 17412, 76818, 70374, 43354, 94082, 30194},
{34012, 2894, 37334, 65862, 91198, 20664, 42770, 17110, 80532, 67664, 52242, 11330, 63842, 91512, 44642, 14450, 27264, 25358, 8734, 47646, 66700, 81440, 99154, 81830, 97830, 55714, 18644, 67454, 3828, 71244, 95486, 25118, 54870},
{38242, 10470, 23974, 33916, 85822, 38476, 95932, 78420, 67302, 91236, 30770, 82680, 81288, 18540, 91254, 34628, 93936, 34442, 31354, 41640, 44944, 69396, 14078, 90854, 79140, 99410, 724, 56480, 27242, 16508, 54458, 11924, 45308},
{31560, 70890, 1182, 35918, 32214, 24554, 97376, 67400, 18318, 81936, 69900, 46512, 37098, 11928, 88812, 22856, 55620, 39984, 9734, 38556, 79732, 31862, 72812, 20286, 22604, 47668, 68968, 93326, 19668, 47662, 74400, 61240, 88794},
{66988, 49090, 84578, 16260, 17584, 76400, 2062, 96066, 89410, 11432, 80372, 9444, 70870, 3618, 70860, 46650, 60796, 282, 84678, 23666, 91026, 90468, 13874, 27128, 6968, 80236, 44026, 40276, 12842, 55616, 15088, 66676, 17168},
{78212, 30388, 24906, 17546, 63222, 200, 82362, 34248, 7236, 68952, 70030, 16432, 55488, 75140, 48258, 35924, 26210, 2916, 53290, 8880, 58832, 48756, 63356, 37304, 36714, 80230, 7814, 23742, 75448, 50490, 87134, 92264, 44266},
{33160, 5338, 28882, 19842, 65896, 42228, 63182, 53128, 49984, 29798, 56276, 18070, 98394, 9604, 656, 96492, 55528, 7964, 94576, 92542, 12820, 45636, 11744, 55946, 60504, 41068, 18604, 81298, 14842, 3472, 41368, 57860, 48970},
{61390, 55886, 79478, 67726, 40890, 91322, 90036, 81046, 62958, 82006, 45574, 43388, 66590, 58144, 12446, 69472, 94840, 47532, 31660, 12126, 65782, 74586, 48300, 86778, 99618, 69310, 20420, 40236, 33220, 84392, 76720, 7820, 64830},
{79686, 84756, 80744, 41592, 20222, 50346, 13016, 50942, 23790, 38864, 82824, 18952, 52788, 70910, 46902, 75276, 73342, 81260, 19906, 20036, 4624, 6174, 17468, 72320, 51536, 34642, 31046, 61648, 38316, 16820, 14774, 35084, 28626},
{85560, 2018, 19896, 97166, 49222, 13824, 5222, 81690, 19964, 55132, 16138, 24626, 45364, 7936, 24058, 54172, 53770, 85542, 16866, 97536, 88978, 81476, 54836, 59822, 85278, 46900, 23988, 51350, 25180, 58488, 82366, 87606, 91886},
{64408, 88544, 77676, 78152, 34272, 95648, 23418, 29032, 4366, 5154, 58924, 89876, 35926, 28150, 78032, 91622, 91530, 73530, 74376, 60222, 35874, 19276, 31782, 39690, 28630, 82598, 48398, 52888, 80928, 27808, 23766, 26890, 48606},
{55236, 80150, 94104, 24070, 43592, 75136, 28226, 82032, 10862, 15864, 19956, 31774, 86134, 65240, 98388, 25074, 58284, 86282, 65416, 97220, 78874, 39808, 2390, 94258, 9962, 92928, 6060, 76304, 9264, 71062, 45536, 24644, 86856}};
        int sol[] = new int[n + 1];

        SolvebottleneckAssignment(n, cost, sol);
        System.out.println("Optimal solution:\n" + "  row  column");
        for (int i=1; i<=n; i++)
            System.out.println(" " + i + " - " + sol[i]);
            System.out.println("\nBottleneck value = " + cost[0][0]);
        }
}