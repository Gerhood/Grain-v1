
import org.sat4j.minisat.SolverFactory;
import org.sat4j.specs.IProblem;
import org.sat4j.specs.ISolver;

public class grain {

	boolean[] LFSR = new boolean[80];
	boolean[] NFSR = new boolean[80];

	final int MAXVAR = 1000000;
	final int NBCLAUSES = 500000;

	ISolver solver = SolverFactory.newDefault();

	// prepare the solver to accept MAXVAR variables. MANDATORY for MAXSAT solving

	
	String key = "00000001001000110100010101100111100010011010101111001101111011110001001000110100";
	String IV = "0000000100100011010001010110011110001001101010111100110111101111";

	
	int[] test1 = {1,2,3};
	int[] test2 = {-1,2,-3};
	int[] test3 = {1,-2,3};
	

//	public void clockLfsr() {
//		boolean feedback = LFSR[62] ^ LFSR[51] ^ LFSR[38] ^ LFSR[23] ^ LFSR[13]
//				^ LFSR[0];
//		for (int i = 0; i < LFSR.length - 1; i++) {
//			LFSR[i] = LFSR[i + 1];
//		}
//
//		LFSR[LFSR.length - 1] = feedback;
//	}
//
//	public void clockNfsr() {
//		boolean feedback = LFSR[0] ^ g();
//		for (int i = 0; i < NFSR.length - 1; i++) {
//			NFSR[i] = NFSR[i + 1];
//		}
//		NFSR[79] = feedback;
//	}

	// function g
	public boolean g() {
		boolean g;
		g = NFSR[62]
				^ NFSR[60]
				^ NFSR[52]
				^ NFSR[45]
				^ NFSR[37]
				^ NFSR[33]
				^ NFSR[28]
				^ NFSR[21]
				^ NFSR[14]
				^ NFSR[9]
				^ NFSR[0]
				^ (NFSR[63] & NFSR[60])
				^ (NFSR[37] & NFSR[33])
				^ (NFSR[15] & NFSR[9])
				^ (NFSR[60] & NFSR[52] & NFSR[45])
				^ (NFSR[33] & NFSR[28] & NFSR[21])
				^ (NFSR[63] & NFSR[45] & NFSR[28] & NFSR[9])
				^ (NFSR[60] & NFSR[52] & NFSR[37] & NFSR[33])
				^ (NFSR[63] & NFSR[60] & NFSR[21] & NFSR[15])
				^ (NFSR[63] & NFSR[60] & NFSR[52] & NFSR[45] & NFSR[37])
				^ (NFSR[33] & NFSR[28] & NFSR[21] & NFSR[15] & NFSR[9])
				^ (NFSR[52] & NFSR[45] & NFSR[37] & NFSR[33] & NFSR[28] & NFSR[21]);
		return g;
	}

	// generates the streamkey z's
	public boolean z() {
		boolean z;

		z = (LFSR[3] & LFSR[64]) ^ (LFSR[46] & LFSR[64])
				^ (LFSR[63] & LFSR[64]) ^ (LFSR[46] & LFSR[64] & LFSR[3])
				^ (LFSR[46] & NFSR[63] & LFSR[3])
				^ (LFSR[46] & NFSR[63] & LFSR[25])
				^ (LFSR[46] & NFSR[63] & LFSR[64]) ^ LFSR[25] ^ NFSR[63]
				^ NFSR[1] ^ NFSR[2] ^ NFSR[4] ^ NFSR[10] ^ NFSR[31] ^ NFSR[43]
				^ NFSR[56];
		return z;
	}

	public void clockBasis() {
		for (int k = 0; k < 160; k++) {

			boolean feedbackLFSR = LFSR[62] ^ LFSR[51] ^ LFSR[38] ^ LFSR[23]
					^ LFSR[13] ^ LFSR[0];
			boolean feedbackNFSR = LFSR[0] ^ g();
			boolean keyStream = z();

			for (int i = 0; i < LFSR.length - 1; i++) {
				LFSR[i] = LFSR[i + 1];
			}
			for (int i = 0; i < NFSR.length - 1; i++) {
				NFSR[i] = NFSR[i + 1];
			}

			NFSR[79] = feedbackNFSR;
			LFSR[79] = feedbackLFSR ^ keyStream;

			if (keyStream)
				System.out.print("1 ");
			else
				System.out.print("0 ");
		}

		System.out.println();
	}

	public void encrypt() {
		boolean feedbackLFSR = LFSR[62] ^ LFSR[51] ^ LFSR[38] ^ LFSR[23]
				^ LFSR[13] ^ LFSR[0];
		boolean feedbackNFSR = LFSR[0] ^ g();
		boolean keyStream = z();

		for (int i = 0; i < LFSR.length - 1; i++) {
			LFSR[i] = LFSR[i + 1];
		}
		for (int i = 0; i < NFSR.length - 1; i++) {
			NFSR[i] = NFSR[i + 1];
		}

		NFSR[79] = feedbackNFSR;
		LFSR[79] = feedbackLFSR;

		if (keyStream)
			System.out.print("1 ");
		else
			System.out.print("0 ");
	}

	public grain() {
		if (key.length() != 80) {
			System.out.println("der key ist nicht 80 bits lang");
		}
		for (int i = 0; i < key.length(); i++) {
			NFSR[i] = key.charAt(i) == '1' ? true : false;
		}

		for (int i = 0; i < IV.length(); i++) {
			LFSR[i] = IV.charAt(i) == '1' ? true : false;
		}

		for (int i = IV.length(); i < LFSR.length; i++) {
			LFSR[i] = true;
		}

		clockBasis();

	}

	public static void main(String[] args) {
		grain g = new grain();
		for (int i = 0; i < 80; i++) {
			g.encrypt();
		}
	}
	
	
}
