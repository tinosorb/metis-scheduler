import java.io.*;
import java.util.Scanner;

public class HashAssign{

    public static int getPartition(String key, int numReduceTasks) {
        return (key.hashCode() & Integer.MAX_VALUE) % numReduceTasks;

    }

    public static void main (String[] args) throws FileNotFoundException {

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the file path: ");
        String file = scanner.next();
        System.out.print("Enter the number of reducers: ");
        int rnum = Integer.parseInt(scanner.next());

        // File file = new File(filepath);
        try(BufferedReader br = new BufferedReader(new FileReader(file))) {
            String key;
            int hashvalue;
            int[] assignment = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};

            for(String line; (line = br.readLine()) != null; ) {
                // process the line.
                if (line.length() >= 10) {
			        key = line.substring(0,10);
                // System.out.println("Key is: " + key);
                	hashvalue = getPartition(key,rnum);
                	assignment[hashvalue] += 1;
                // System.out.println("Hashed to reducer: " + hashvalue);
            	}
	        }
            for (int element: assignment) {
                System.out.print(element+",");
            }
            // line is not visible here.
        } catch(IOException ie) {
            ie.printStackTrace();
        }

        System.out.println("Finished!");
    }

}
