import java.io.*;
import java.util.Scanner;
import java.util.Arrays;
//import java.util.stream.*;
//Only available after Java 8 ;(

public class HashAssignDir {

    public static int getPartition(String key, int numReduceTasks) {
        return (key.hashCode() & Integer.MAX_VALUE) % numReduceTasks;

    }

    public static void main (String[] args) throws FileNotFoundException {

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the dir path: ");
        String file = scanner.next();
        System.out.print("Enter the number of reducers: ");
        int rnum = Integer.parseInt(scanner.next());

        // File file = new File(filepath);
        File dir = new File(file);
        File[] directoryListing = dir.listFiles();

        if (directoryListing != null) {
            for (File child : directoryListing) {
                System.out.println("File name: " + child.getName());
		try(BufferedReader br = new BufferedReader(new FileReader(child))) {
                    String key;
                    int hashvalue;
                    int[] assignment = new int[rnum];
		    int[] matrix = new int[rnum];

		    for (int i = 0; i < rnum; i++) {
                  	assignment[i] = 0;
			matrix[i] = 0;
		    }

                    for (String line; (line = br.readLine()) != null; ) {
                        // process the line.
                        //if (line.contains(" ")) {
                        //    String[] splits = line.split(" ");
                        //    key = splits[0];
                        if (line.length() >= 10) {
			  key = line.substring(0,10);
                            // System.out.println("Key is: " + key);
                          hashvalue = getPartition(key, rnum);
                          assignment[hashvalue] += 1;
                            // System.out.println("Hashed to reducer: " + hashvalue);
                        } else {
  			  line = line+"0000000000";
			  key = line.substring(0,10);
			  hashvalue = getPartition(key, rnum);
                          assignment[hashvalue] += 1;
			}
                    }
                    //for (int element : assignment) {
                    System.out.println(Arrays.toString(assignment));
                    //}
 		    // System.out.print(System.lineSeparator());
		    // int sum = IntStream.of(assignment).sum();
		    int sum = 0;
		    for (int element : assignment)
			sum += element;
		    for (int i = 0; i < rnum; i++) {
			matrix[i] = sum - assignment[i];
		    }
		    System.out.println(Arrays.toString(matrix));
                } catch (IOException ie) {
                    ie.printStackTrace();
                }
            }
            // line is not visible here.
        }

        System.out.println("Finished!");
    }

}
