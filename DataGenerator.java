import java.io.*;
import java.util.Scanner;
import java.security.SecureRandom;
import java.math.BigInteger;

public class DataGenerator {
    static final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    static SecureRandom rnd = new SecureRandom();

    public static String randomString(int len) {
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++)
            sb.append(AB.charAt(rnd.nextInt(AB.length())));
        return sb.toString();
    }

    public static void main (String[] args) throws IOException {

        Scanner scanner = new Scanner(System.in);
        System.out.print("Number of records: ");
        int num = Integer.parseInt(scanner.next());
	System.out.print("Skewness (number of duplicated chunks this file contains): ");
        int skew = Integer.parseInt(scanner.next());
        
        PrintWriter writer = new PrintWriter("data.txt", "UTF-8");

        for (int j = 0; j < skew; j++) {
		String record = randomString(100);
		for (int i = 0; i < num/3; i++)
            		writer.println(record);
        }
	writer.close();	
    }
}
