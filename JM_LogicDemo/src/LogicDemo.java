import java.util.ArrayList;

public class LogicDemo
{

	public static void main(String[] args)
	{
//		displayNot();
//		displayAnd();
//		displayOr();
//		displayAAndTrue();
//		displayAOrTrue();
//		displayAAndFalse();
//		displayAOrFalse();
//		displayAAndNotA();
//		displayAOrNotA();
//		displayDistributeOr();
//		displayDistributeAnd();
//		displayDeMorgan1();
//		displayDeMorgan2();
        challenge1();
        challenge2();
        challenge3();
        challenge4();
	}
	/**
	 * creates a row of dashes, with a "+" at the tab stops
	 * @param N - how long the resulting string should be
	 * @return - a string composed of hyphens ("-"), with "+" signs at locations 0, 8, 16, etc., with total length N.
	 */
	public static String getNDashes(int N)
	{
		String result = "";
		for (int i=0; i<N; i++)
			if (i%8 == 0)
				result+= "+";
			else
				result+= "-";
		return result;
	}
	
	/**
	 * displays a truth table of A and not A.
	 * +-------+-------+
	 * | A     | !A    |
	 * +-------+-------+
	 * | false | ????? |
	 * | true  | ????? |
	 * +-------+-------+
	 */
	public static void displayNot()
	{
		System.out.println("##################################################");
		System.out.println("Not");
		System.out.println(getNDashes(17));
		System.out.println("|   A\t| !A\t|");
		System.out.println(getNDashes(17));
		//some sort of loop.... that creates a boolean variable "A" and iterates through all possible values of A.
			// in this loop, you'll print out the following...
			//System.out.println("| "+A+"\t| "+!A+"\t|");
        boolean A;
        for (int i = 0; i<2; i++) {
            if (i%2 == 1) {
                A = true;
            } else {
                A = false;
            }
            System.out.println("| "+A+"\t| "+!A+"\t|");
        }
		System.out.println(getNDashes(17)); // bottom of chart.
		
	}
	/**
	 * displays a truth table of A, B, and A && B
	 * +-------+-------+-------+
	 * | A     | B     | A&&B  |
	 * +-------+-------+-------+
	 * | false | false | ????? |
	 * | false | true  | ????? |
	 * | true  | false | ????? |
	 * | true  | true  | ????? |
	 * +-------+-------+-------+
	 */
	public static void displayAnd()
	{
		System.out.println("##################################################");
		System.out.println("And");
		System.out.println(getNDashes(25));
		System.out.println("|   A\t|   B\t|  A&&B\t|");
		System.out.println(getNDashes(25));
		//some sort of nested loop.... that creates boolean variables "A" and "B" and iterates through all possible combinations of A,B.
		// in this loop, you'll print out the following...	
//			System.out.println("| "+A+"\t|" +B+"\t| "+(A&&B)+"\t|");
        boolean A;
        boolean B;
        for (int p = 0; p < 2; p++) {
            if (p%2 == 1) {
                A = true;
            } else {
                A = false;
            }
            for (int q = 0; q < 2; q++) {
                if (q%2 == 1) {
                    B = true;
                } else {
                    B = false;
                }
                System.out.println("| "+A+"\t|" +B+"\t| "+(A&&B)+"\t|");
            }
        }
		System.out.println(getNDashes(25));
		
	}
	/**
	 * displays a truth table of A, B, and A || B
	 * +-------+-------+-------+
	 * | A     | B     | A||B  |
	 * +-------+-------+-------+
	 * | false | false | ????? |
	 * | false | true  | ????? |
	 * | true  | false | ????? |
	 * | true  | true  | ????? |
	 * +-------+-------+-------+
	 */
	public static void displayOr()
	{
        System.out.println("##################################################");
        System.out.println("Or");
        System.out.println(getNDashes(25));
        System.out.println("|   A\t|   B\t|  A||B\t|");
        System.out.println(getNDashes(25));
        boolean A;
        boolean B;
        for (int p = 0; p < 2; p++) {
            if (p%2 == 1) {
                A = true;
            } else {
                A = false;
            }
            for (int q = 0; q < 2; q++) {
                if (q%2 == 1) {
                    B = true;
                } else {
                    B = false;
                }
                System.out.println("| "+A+"\t|" +B+"\t| "+(A||B)+"\t|");
            }
        }
        System.out.println(getNDashes(25));
	}
	/**
	 * displays a truth table of A and (A && true).
	 * +-------+-------+
	 * | A     | A&&T  |
	 * +-------+-------+
	 * | false | ????? |
	 * | true  | ????? |
	 * +-------+-------+
	 */
	public static void displayAAndTrue()
	{
        System.out.println("##################################################");
        System.out.println("AAndTrue");
        System.out.println(getNDashes(17));
        System.out.println("|   A\t| A&&T\t|");
        System.out.println(getNDashes(17));
        boolean A;
        for (int i = 0; i<2; i++) {
            if (i%2 == 1) {
                A = true;
            } else {
                A = false;
            }
            System.out.println("| "+A+"\t| "+(A&&true)+"\t|");
        }
        System.out.println(getNDashes(17)); // bottom of chart.

	}
	/**
	 * displays a truth table of A and (A || true).
	 * +-------+-------+
	 * | A     | A||T  |
	 * +-------+-------+
	 * | false | ????? |
	 * | true  | ????? |
	 * +-------+-------+
	 */
	public static void displayAOrTrue()
	{
        System.out.println("##################################################");
        System.out.println("AOrTrue");
        System.out.println(getNDashes(17));
        System.out.println("|   A\t| A||T\t|");
        System.out.println(getNDashes(17));
        boolean A;
        for (int i = 0; i<2; i++) {
            if (i%2 == 1) {
                A = true;
            } else {
                A = false;
            }
            System.out.println("| "+A+"\t| "+(A||true)+"\t|");
        }
        System.out.println(getNDashes(17)); // bottom of chart.
	}
	/**
	 * displays a truth table of A and (A && false).
	 * +-------+-------+
	 * | A     | A&&F  |
	 * +-------+-------+
	 * | false | ????? |
	 * | true  | ????? |
	 * +-------+-------+
	 */
	public static void displayAAndFalse()
	{
        System.out.println("##################################################");
        System.out.println("AAndFalse");
        System.out.println(getNDashes(17));
        System.out.println("|   A\t| A&&F\t|");
        System.out.println(getNDashes(17));
        boolean A;
        for (int i = 0; i<2; i++) {
            if (i%2 == 1) {
                A = true;
            } else {
                A = false;
            }
            System.out.println("| "+A+"\t| "+(A&&false)+"\t|");
        }
        System.out.println(getNDashes(17)); // bottom of chart.
	}
	/**
	 * displays a truth table of A and (A || false).
	 * +-------+-------+
	 * | A     | A||F  |
	 * +-------+-------+
	 * | false | ????? |
	 * | true  | ????? |
	 * +-------+-------+
	 */
	public static void displayAOrFalse()
	{
        System.out.println("##################################################");
        System.out.println("AOrFalse");
        System.out.println(getNDashes(17));
        System.out.println("|   A\t| A||F\t|");
        System.out.println(getNDashes(17));
        boolean A;
        for (int i = 0; i<2; i++) {
            if (i%2 == 1) {
                A = true;
            } else {
                A = false;
            }
            System.out.println("| "+A+"\t| "+(A||false)+"\t|");
        }
        System.out.println(getNDashes(17)); // bottom of chart.
	}
	/**
	 * displays a truth table of A and (A && !A).
	 * +-------+-------+-------+
	 * | A     | !A    | A&&!A |
	 * +-------+-------+-------+
	 * | false | ????? | ????? |
	 * | true  | ????? | ????? |
	 * +-------+-------+-------+
	 */
	public static void displayAAndNotA()
	{
        System.out.println("##################################################");
        System.out.println("AAndNotA");
        System.out.println(getNDashes(25));
        System.out.println("|   A\t|  !A\t| A&&!A\t|");
        System.out.println(getNDashes(25));
        boolean A;
        for (int p = 0; p < 2; p++) {
            if (p%2 == 1) {
                A = true;
            } else {
                A = false;
            }
            System.out.println("| "+A+"\t|" +!A+"\t| "+(A&&!A)+"\t|");
        }
        System.out.println(getNDashes(25));
	}
	/**
	 * displays a truth table of A and (A || !A).
	 * +-------+-------+-------+
	 * | A     | !A    | A||!A |
	 * +-------+-------+-------+
	 * | false | ????? | ????? |
	 * | true  | ????? | ????? |
	 * +-------+-------+-------+
	 */
	public static void displayAOrNotA()
	{
        System.out.println("##################################################");
        System.out.println("AOrNotA");
        System.out.println(getNDashes(25));
        System.out.println("|   A\t|  !A\t| A||!A\t|");
        System.out.println(getNDashes(25));
        boolean A;
        for (int p = 0; p < 2; p++) {
            if (p%2 == 1) {
                A = true;
            } else {
                A = false;
            }
            System.out.println("| "+A+"\t| " +!A+"\t| "+(A||!A)+"\t|");
        }
        System.out.println(getNDashes(25));
	}
	/**
	 * displays a truth table of A, B, C, (B && C), (A || B), (A || C), (A || (B && C))
	 * 
	 * D=B&&C, E = A||B, G = A||C, H = A || (B&&C), J = E&&G
	 * (Note: I am skipping letters "F" and "I" because they might get confused with "false" and "or".
	 * +--------+-------+-------+-------+-------+-------+-------+-------+
	 * | A      | B     | C     | D     | E     | G     | H     | J     |
	 * +--------+-------+-------+-------+-------+-------+-------+-------+
	 * | false  | false | false | ????? | ????? | ????? | ????? | ????? |
	 * | false  | false | true  | ????? | ????? | ????? | ????? | ????? |
	 * | false  | true  | false | ????? | ????? | ????? | ????? | ????? |
	 * | false  | true  | true  | ????? | ????? | ????? | ????? | ????? |
	 * | true   | false | false | ????? | ????? | ????? | ????? | ????? |
	 * etc..
	 * +--------+-------+-------+-------+-------+-------+-------+-------+
	 */
	public static void displayDistributeOr()
	{
        System.out.println("##################################################");
        System.out.println("DistributeOr");
        System.out.println(getNDashes(65));
        System.out.println("|   A\t|   B\t|   C\t|   D\t|   E\t|   G\t|   H\t|   J\t|");
        System.out.println(getNDashes(65));
        boolean A;
        boolean B;
        boolean C;
        boolean D;
        boolean E;
        boolean G;
        boolean H;
        boolean J;
        for (int p = 0; p < 2; p++) {
            if (p%2 == 1) {
                A = true;
            } else {
                A = false;
            }
            for (int q = 0; q < 2; q++) {
                if (q%2 == 1) {
                    B = true;
                } else {
                    B = false;
                }
                for (int z = 0; z < 2; z++) {
                    if (z%2 == 1) {
                        C = true;
                    } else {
                        C = false;
                    }
                    D = B&&C;
                    E = A||B;
                    G = A||C;
                    H = A || (B&&C);
                    J = E&&G;
                    System.out.println("| "+A+"\t| " +B+"\t| " +C+"\t| " +D+"\t| " +E+"\t| " +G+"\t| " +H+"\t| " +J+"\t| ");
                }
            }
        }
        System.out.println(getNDashes(65));
	}
	/**
	 * displays a truth table of A, B, C, (B || C), (A && B), (A && C), (A && (B || C))
	 * 
	 * D=B||C, E = A&&B, G = A&&C, H = A && (B||C), J = E || G
	 * (Note: I am skipping letters "F" and "I" because they might get confused with "false" and "or".
	 * +--------+-------+-------+-------+-------+-------+-------+-------+
	 * | A      | B     | C     | D     | E     | G     | H     | J     |
	 * +--------+-------+-------+-------+-------+-------+-------+-------+
	 * | false  | false | false | ????? | ????? | ????? | ????? | ????? |
	 * | false  | false | true  | ????? | ????? | ????? | ????? | ????? |
	 * | false  | true  | false | ????? | ????? | ????? | ????? | ????? |
	 * | false  | true  | true  | ????? | ????? | ????? | ????? | ????? |
	 * | true   | false | false | ????? | ????? | ????? | ????? | ????? |
	 * etc..
	 * +--------+-------+-------+-------+-------+-------+-------+-------+
	 */
	public static void displayDistributeAnd()
	{
        System.out.println("##################################################");
        System.out.println("DistributeAnd");
        System.out.println(getNDashes(65));
        System.out.println("|   A\t|   B\t|   C\t|   D\t|   E\t|   G\t|   H\t|   J\t|");
        System.out.println(getNDashes(65));
        boolean A;
        boolean B;
        boolean C;
        boolean D;
        boolean E;
        boolean G;
        boolean H;
        boolean J;
        for (int p = 0; p < 2; p++) {
            if (p%2 == 1) {
                A = true;
            } else {
                A = false;
            }
            for (int q = 0; q < 2; q++) {
                if (q%2 == 1) {
                    B = true;
                } else {
                    B = false;
                }
                for (int z = 0; z < 2; z++) {
                    if (z%2 == 1) {
                        C = true;
                    } else {
                        C = false;
                    }
                    D = B||C;
                    E = A&&B;
                    G = A&&C;
                    H = A && (B||C);
                    J = E || G;
                    System.out.println("| "+A+"\t| " +B+"\t| " +C+"\t| " +D+"\t| " +E+"\t| " +G+"\t| " +H+"\t| " +J+"\t| ");
                }
            }
        }
        System.out.println(getNDashes(65));
	}
	/**
	 * displays a truth table of A, B, !A, !B, (A||B), !(A||B), (!A || !B), (!A && !B)
	 * 
	 * C=(A||B), D = !(A||B), E = (!A || !B), G = (!A && !B)
	 * Note: skipping "F" because it might get confused with "False."
	 * +--------+-------+-------+-------+-------+-------+-------+-------+
	 * | A      | B     | !A    | !B    | C     | D     | E     | G     |
	 * +--------+-------+-------+-------+-------+-------+-------+-------+
	 * | false  | false | false | ????? | ????? | ????? | ????? | ????? |
	 * | false  | false | true  | ????? | ????? | ????? | ????? | ????? |
	 * | false  | true  | false | ????? | ????? | ????? | ????? | ????? |
	 * | false  | true  | true  | ????? | ????? | ????? | ????? | ????? |
	 * | true   | false | false | ????? | ????? | ????? | ????? | ????? |
	 * etc..
	 * +--------+-------+-------+-------+-------+-------+-------+-------+
	 */
	public static void displayDeMorgan1()
	{
        System.out.println("##################################################");
        System.out.println("DeMorgan1");
        System.out.println(getNDashes(65));
        System.out.println("|   A\t|   B\t|  !A\t|  !B\t|   C\t|   D\t|   E\t|   G\t|");
        System.out.println(getNDashes(65));
        boolean A;
        boolean B;
        boolean C;
        boolean D;
        boolean E;
        boolean G;
        for (int p = 0; p < 2; p++) {
            if (p%2 == 1) {
                A = true;
            } else {
                A = false;
            }
            for (int q = 0; q < 2; q++) {
                if (q%2 == 1) {
                    B = true;
                } else {
                    B = false;
                }
                C = (A||B);
                D = !(A||B);
                E = (!A || !B);
                G = (!A && !B);
                System.out.println("| "+A+"\t| " +B+"\t| " +!A+"\t| " +!B+"\t| " +C+"\t| " +D+"\t| " +E+"\t| " +G+"\t| ");
            }
        }
        System.out.println(getNDashes(65));
	}
	/**
	 * displays a truth table of A, B, !A, !B, (A&&B), !(A&&B), (!A || !B), (!A && !B)
	 * 
	 * C=(A&&B), D = !(A&&B), E = (!A || !B), G = (!A && !B)
	 * Note: skipping "F" because it might get confused with "False."
	 * +--------+-------+-------+-------+-------+-------+-------+-------+
	 * | A      | B     | !A    | !B    | C     | D     | E     | G     |
	 * +--------+-------+-------+-------+-------+-------+-------+-------+
	 * | false  | false | false | ????? | ????? | ????? | ????? | ????? |
	 * | false  | false | true  | ????? | ????? | ????? | ????? | ????? |
	 * | false  | true  | false | ????? | ????? | ????? | ????? | ????? |
	 * | false  | true  | true  | ????? | ????? | ????? | ????? | ????? |
	 * | true   | false | false | ????? | ????? | ????? | ????? | ????? |
	 * etc..
	 * +--------+-------+-------+-------+-------+-------+-------+-------+
	 */
	public static void displayDeMorgan2()
	{
        System.out.println("##################################################");
        System.out.println("DeMorgan2");
        System.out.println(getNDashes(65));
        System.out.println("|   A\t|   B\t|  !A\t|  !B\t|   C\t|   D\t|   E\t|   G\t|");
        System.out.println(getNDashes(65));
        boolean A;
        boolean B;
        boolean C;
        boolean D;
        boolean E;
        boolean G;
        for (int p = 0; p < 2; p++) {
            if (p%2 == 1) {
                A = true;
            } else {
                A = false;
            }
            for (int q = 0; q < 2; q++) {
                if (q%2 == 1) {
                    B = true;
                } else {
                    B = false;
                }
                C = (A&&B);
                D = !(A&&B);
                E = (!A || !B);
                G = (!A && !B);
                System.out.println("| "+A+"\t| " +B+"\t| " +!A+"\t| " +!B+"\t| " +C+"\t| " +D+"\t| " +E+"\t| " +G+"\t| ");
            }
        }
        System.out.println(getNDashes(65));
	}

	public static void challenge1() {
        System.out.println("##################################################");
	    boolean A;
        for (int p = 0; p < 2; p++) {
            if (p % 2 == 0) {
                A = true;
            } else {
                A = false;
            }
            System.out.println(A);
        }
    }

    public static void challenge2() {
        System.out.println("##################################################");
        boolean[] possibles = {true,false};
        for (boolean A:possibles) {
            System.out.println(A);
        }
    }

    public static void challenge3() {
        System.out.println("##################################################");
        ArrayList<Boolean> possibles = new ArrayList<>();
        possibles.add(true);
        possibles.add(false);
        for (boolean A:possibles) {
            System.out.println(A);
        }
    }

    public static void challenge4() {
        System.out.println("##################################################");
	    int hasRun = 0;
	    boolean A = true;
	    while (hasRun < 2) {
	        hasRun ++;
	        A = !A;
	        System.out.println(A);
        }
    }
	
}
