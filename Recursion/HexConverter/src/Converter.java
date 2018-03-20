import java.util.Scanner;

public class Converter {
    public static String[] hexSymbols = {"0","1","2","3","4","5","6","7","8","8","A","B","C","D","E","F"};
	public static void main(String[] args) {
	    Scanner sc = new Scanner(System.in);
		int inputValue = sc.nextInt();
		System.out.print("Hex: ");
		System.out.println(convertToHex("",inputValue));
		System.out.print("Binary: ");
        System.out.println(convertToBinary("",inputValue));
        System.out.print("Octal: ");
        System.out.println(convertToOctal("",inputValue));
	}

	public static String convertToHex (String hex, int decimal) {
	    String finalHex;
	    if (decimal > 15) {
	        int remainder = (decimal - decimal%16)/16;
	        String outHex = hexSymbols[decimal%16] + hex;
	        finalHex = convertToHex(outHex, remainder);
        } else {
	        finalHex = hexSymbols[decimal] + hex;
        }

	    return finalHex;
    }

    public static String convertToBinary (String binary, int decimal) {
        String finalBinary;
        if (decimal > 1) {
            int remainder = (decimal - decimal%2)/2;
            String outBinary = decimal%2 + binary;
            finalBinary = convertToBinary(outBinary, remainder);
        } else {
            finalBinary = decimal + binary;
        }

        return finalBinary;
    }

    public static String convertToOctal (String oct, int decimal) {
        String finalOct;
        if (decimal > 8) {
            int remainder = (decimal - decimal%8)/8;
            String outOct = decimal%8 + oct;
            finalOct = convertToOctal(outOct, remainder);
        } else {
            finalOct = decimal + oct;
        }

        return finalOct;
    }



}
