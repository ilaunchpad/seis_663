import java.math.BigInteger;
import java.util.Arrays;
import java.util.Scanner;

public class RSA {

	protected int n;
	protected int e;
	protected int d;

	public RSA() {

		int p = Helper.generatePrimeNumber();
		int q = Helper.generatePrimeNumber();

		while (isOutOfRange(p, q)) {
			p = Helper.generatePrimeNumber();
			q = Helper.generatePrimeNumber();
		}
		
		this.n = p * q;
		int t = (p - 1) * (q - 1);
		computeE(t);
		computeD(t);
	}

	public RSA(int e, int n) {
		this.e = e;
		this.n = n;

	}

	public void setD(int d) {
		this.d = d;
	}

	public void computeE(int t) {

		boolean check = false;

		while (check == false) {

			this.e = Helper.generatePrimeNumber();

			if (this.e < t && Helper.isCoPrime(this.e, t) == true) {
				check = true;
			}

		}

	}

	public int modInverse(int exponent, int totient) {

		BigInteger BIexponent = BigInteger.valueOf(exponent);
		BigInteger BItotient = BigInteger.valueOf(totient);
		BigInteger result = BIexponent.modInverse(BItotient);

		return result.intValue();

	}

	public void computeD(int t) {

		this.d = modInverse(e, t);
	}

	public int[] encrypt() {

		Scanner input = new Scanner(System.in);

		System.out.println("Enter the message");
		String plainText = input.nextLine();

		int CoTONu[] = new int[plainText.length()];
		for (int i = 0; i < CoTONu.length; i++) {
			CoTONu[i] = Helper.convertLetterToNumber(plainText.charAt(i));
			CoTONu[i] = Helper.powerMode(CoTONu[i], e, n);
		}

		return CoTONu;

	}

	public String decrypt(int[] encrypted) {
		char CoToLe[] = new char[encrypted.length];
		int buff;
		for (int i = 0; i < CoToLe.length; i++) {
			buff = Helper.calcDecrypt(encrypted[i], d, n);
			CoToLe[i] = Helper.convertNumberToLetter(buff);
		}
		String message = "";

		for (int i = 0; i < CoToLe.length; i++) {
			message += CoToLe[i];

		}
		return message;
	}
	
	
	public static boolean isOutOfRange(int p, int q) {

		BigInteger bigP = BigInteger.valueOf(p);
		BigInteger bigQ = BigInteger.valueOf(q);

		BigInteger result = bigP.multiply(bigQ);

		if (result.longValue() > Integer.MAX_VALUE) {

			return true;
		}
		return false;

	}
	

	public String toString() {

		return this.n + " " + this.e + " " + this.d;

	}

	public static void main(String[] args) {

		Scanner input = new Scanner(System.in);

		System.out.println("Welcome RSA  Cryptosystem \n");

		System.out.println("Please select the option below : (1-3)");
		System.out.println("1 : Keys Genration");
		System.out.println("2 : Auto Encryption and Decryption");
		System.out.println("3 : Provide a key");

		String userinput = input.next();

		if (userinput.equals("1")) {
			RSA r1 = new RSA();
			System.out.println("Public key =" + "(" + r1.e + " ," + r1.n + ")");
			System.out.println("Private key =" + "(" + r1.d + " ," + r1.n + ")");

		} else if (userinput.equals("2")) {
			RSA r1 = new RSA();

			int encryptMessage[] = r1.encrypt();

			System.out.println("The below is the Encrypted Message : \n");
			Helper.writerEncryptionFile("test.txt", encryptMessage);

			System.out.println("\n \n Do you want Decrypt ? : \n");
			String userInput = input.next();
			if (userInput.equalsIgnoreCase("Yes")) {

				System.out.println("\n \n The below is the Decrypted Message :\n");
				System.out.println(r1.decrypt(Helper.readEncryptionFile("test.txt")));

			}

		} else if (userinput.equals("3")) {

			System.out.println("Enter the value of e of public key \n");
			int e = input.nextInt();
			System.out.println("Enter the value of n of public key  \n");
			int n = input.nextInt();

			RSA r1 = new RSA(e, n);

			int encryptMessage[] = r1.encrypt();

			System.out.println("The below is the Encrypted Message : \n");
			Helper.writerEncryptionFile("test.txt", encryptMessage);

			System.out.println(" \n \n Enter the value of private key");
			int d = input.nextInt();
			r1.setD(d);

			System.out.println("\n \n The below is the Decrypted Message :");
			System.out.println(r1.decrypt(Helper.readEncryptionFile("test.txt")));

		}

	}
}
