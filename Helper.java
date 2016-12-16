
import java.io.*;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Random;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;

public class Helper {

	// This method converts character to Askii .
	public static int convertLetterToNumber(char character) {

		return (int) character;

	}

	// This method converts Integer (Askii code) to character.
	public static char convertNumberToLetter(int number) {

		return (char) number;

	}

	// This method checks if number given by the parameter is prime or not.
	public static boolean isPrimee(int primeNumberCheck) {

		if (primeNumberCheck == 1 || primeNumberCheck == 0) {
			return false;
		}

		for (int i = 2; i < primeNumberCheck; i++) {

			if (primeNumberCheck % i == 0) {
				return false;
			}

		}

		return true;

	}

	// This method generates a prime number by getting a random value and
	// searching for a prime number.
	public static int generatePrimeNumber() {

		Random rng = new Random();
		int value = 0;

		while (isPrimee(value) == false) {

			value = rng.nextInt(100000);
		}

		return value;

	}

	/*
	 * This method checks if two numbers given by parameters are Co-prime or
	 * not.
	 */
	public static boolean isCoPrime(int firstvalue, int secondValue) {

		if (firstvalue == 0 || firstvalue == 1 || secondValue == 0 || secondValue == 1) {
			return false;
		}

		int counter = 0;

		for (int i = 1; i <= firstvalue; i++) {

			if (firstvalue % i == 0 && secondValue % i == 0) {
				counter++;
			}

		}

		if (counter == 1) {
			return true;
		}

		return false;
	}
	
	
	
	public static int powerMode(int message, int exponent, int n) {

		BigInteger BImessage = BigInteger.valueOf(message);
		BigInteger BIexponent = BigInteger.valueOf(exponent);
		BigInteger BIn = BigInteger.valueOf(n);
		BigInteger result = BImessage.modPow(BIexponent, BIn);

		return result.intValue();

	}

	public static int calcDecrypt(int message, int d, int n){
		BigInteger BImessage = BigInteger.valueOf(message);
		BigInteger BIdecrypt = BigInteger.valueOf(d);
		BigInteger BIn = BigInteger.valueOf(n);
		BigInteger result = BImessage.modPow(BIdecrypt, BIn);

		return result.intValue();
	}
	
	public static void writerEncryptionFile(String fileOut, int cipher[]) {

		try {
			BufferedWriter out = new BufferedWriter(new FileWriter(fileOut));
			for (int i = 0; i < cipher.length; i++) {
				out.write((Integer.toString(cipher[i])));
				out.newLine();
				System.out.print(cipher[i]);
			}

			out.close();
		} catch (IOException e) {
			System.out.println("Cant Find the file");
		}

	}
    public static int[] readEncryptionFile(String fileOut){
		int [] encrypted = new int [0];

		BufferedReader reader = null;
		try{

			File file = new File(fileOut);
			reader = new BufferedReader(new FileReader(file));
			String line ;
			int i = 0;
			while((line = reader.readLine())!=null ){
				
				//keeping the array growing until the end of file
				int[] encDataCopy = Arrays.copyOf(encrypted, encrypted.length + 1);
				encDataCopy[i]=Integer.parseInt(line);
				encrypted=encDataCopy;
				i++;
				
			}

		}catch (IOException e){
			System.out.print(e);
		}
		return encrypted;
	}
}