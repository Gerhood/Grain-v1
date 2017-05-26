
import java.util.ArrayList;

import org.sat4j.core.VecInt;
import org.sat4j.minisat.SolverFactory;
import org.sat4j.specs.ContradictionException;
import org.sat4j.specs.IProblem;
import org.sat4j.specs.ISolver;
import org.sat4j.specs.TimeoutException;

public class grain {

	boolean[] LFSR = new boolean[80];
	boolean[] NFSR = new boolean[80];
	ArrayPrimitive lfsrPrim = new ArrayPrimitive(80, "1");
	ArrayPrimitive nfsrPrim = new ArrayPrimitive(80, "2");
	static int newVar = 1;
	static int cutVar = 1;
	ISolver solver = SolverFactory.newDefault();

	

	String key = "00000001001000110100010101100111100010011010101111001101111011110001001000110100";
	String IV = "0000000100100011010001010110011110001001101010111100110111101111";


	
	public static int getNewVar(){
		String outPut = "3" + newVar;
		newVar += 1;
		return Integer.parseInt(outPut);
		
	}
	public static int getCutVar(){
		String outPut = "4" + cutVar;
		cutVar += 1;
		return Integer.parseInt(outPut);
		
	}
	
	public void addClauses(int z){
		
		//1
		int var1 = getNewVar();
		int[] clause1 = {var1, -lfsrPrim.get(3), -lfsrPrim.get(64)};
		int[] clause2 = {-var1, lfsrPrim.get(3)};
		int[] clause3 = {-var1, lfsrPrim.get(64)};
		try {
			solver.addClause(new VecInt(clause1));
			solver.addClause(new VecInt(clause2));
			solver.addClause(new VecInt(clause3));
		} catch (ContradictionException e) {
			e.printStackTrace();
		}
		//2
		int var2 = getNewVar();
		clause1[0]= var2; clause1[1] = -lfsrPrim.get(46); clause1[2] = -lfsrPrim.get(64); 
		clause2[0] = -var2 ; clause2[1]= lfsrPrim.get(46); 
		clause3[0] = -var2 ; clause3[1]= lfsrPrim.get(64); 
		try {
			solver.addClause(new VecInt(clause1));
			solver.addClause(new VecInt(clause2));
			solver.addClause(new VecInt(clause3));
		} catch (ContradictionException e) {
			e.printStackTrace();
		}
		//3
		int var3 = getNewVar();
		clause1[0]= var3; clause1[1] = -lfsrPrim.get(63); clause1[2] = -lfsrPrim.get(64); 
		clause2[0] = -var3 ; clause2[1]= lfsrPrim.get(63); 
		clause3[0] = -var3 ; clause3[1]= lfsrPrim.get(64); 
		try {
			solver.addClause(new VecInt(clause1));
			solver.addClause(new VecInt(clause2));
			solver.addClause(new VecInt(clause3));
		} catch (ContradictionException e) {
			e.printStackTrace();
		}
		//4
		int var4 = getNewVar();
		clause1 = new int[4];
		clause1[0] = var4; clause1[1] = -lfsrPrim.get(46); clause1[2] = -lfsrPrim.get(64); clause1[3] = -lfsrPrim.get(3);
		clause2[0] = -var4; clause2[1] = lfsrPrim.get(46);
		clause3[0] = -var4; clause3[1] = lfsrPrim.get(64);
		int[] clause4 = {-var4, lfsrPrim.get(3)};
		try {
			solver.addClause(new VecInt(clause1));
			solver.addClause(new VecInt(clause2));
			solver.addClause(new VecInt(clause3));
			solver.addClause(new VecInt(clause4));
		} catch (ContradictionException e) {
			e.printStackTrace();
		}
		//5
		int var5 = getNewVar();
		clause1[0] = var5; clause1[1] = -lfsrPrim.get(46); clause1[2] = -nfsrPrim.get(63); clause1[3] = -lfsrPrim.get(3);
		clause2[0] = -var5; clause2[1] = lfsrPrim.get(46);
		clause3[0] = -var5; clause3[1] = nfsrPrim.get(63);
		clause4[0] = -var5; clause4[1] = lfsrPrim.get(3);
		try {
			solver.addClause(new VecInt(clause1));
			solver.addClause(new VecInt(clause2));
			solver.addClause(new VecInt(clause3));
			solver.addClause(new VecInt(clause4));
		} catch (ContradictionException e) {
			e.printStackTrace();
		}
		//6
		int var6 = getNewVar();
		clause1[0] = var6; clause1[1] = -lfsrPrim.get(46); clause1[2] = -nfsrPrim.get(63); clause1[3] = -lfsrPrim.get(25);
		clause2[0] = -var6; clause2[1] = lfsrPrim.get(46);
		clause3[0] = -var6; clause3[1] = nfsrPrim.get(63);
		clause4[0] = -var6; clause4[1] = lfsrPrim.get(25);
		try {
			solver.addClause(new VecInt(clause1));
			solver.addClause(new VecInt(clause2));
			solver.addClause(new VecInt(clause3));
			solver.addClause(new VecInt(clause4));
		} catch (ContradictionException e) {
			e.printStackTrace();
		}
		//7
		int var7 = getNewVar();
		clause1[0] = var7; clause1[1] = -lfsrPrim.get(46); clause1[2] = -nfsrPrim.get(63); clause1[3] = -lfsrPrim.get(64);
		clause2[0] = -var7; clause2[1] = lfsrPrim.get(46);
		clause3[0] = -var7; clause3[1] = nfsrPrim.get(63);
		clause4[0] = -var7; clause4[1] = lfsrPrim.get(64);
		try {
			solver.addClause(new VecInt(clause1));
			solver.addClause(new VecInt(clause2));
			solver.addClause(new VecInt(clause3));
			solver.addClause(new VecInt(clause4));
		} catch (ContradictionException e) {
			e.printStackTrace();
		}
		//add rest of clauses
		int m1 = getCutVar();
		int m2 = getCutVar();
		int m3 = getCutVar();
		int m4 = getCutVar();
		int m5 = getCutVar();
		int m6 = getCutVar();
		clause1 = new int[4];
		clause2 = new int[4];
		clause3 = new int[4];
		clause4 = new int[4];
		int[] clause5 = new int[4];
		int[] clause6 = new int[4];
		int[] clause7 = new int[4];
		int[] clause8 = new int[4];
		
		if(z == 0){
			//1 out of 7 Cutting the big equation down
			clause1[0] = -var1; clause1[1] = var2; clause1[2] = var3; clause1[3] = m1;
			clause2[0] = var1; clause2[1] = -var2; clause2[2] = var3; clause2[3] = m1;
			clause3[0] = var1; clause3[1] = var2; clause3[2] = -var3; clause3[3] = m1;
			clause4[0] = var1; clause4[1] = var2; clause4[2] = var3; clause4[3] = -m1;
			clause5[0] = var1; clause5[1] = -var2; clause5[2] = -var3; clause5[3] = -m1;
			clause6[0] = -var1; clause6[1] = -var2; clause6[2] = -var3; clause6[3] = m1;
			clause7[0] = -var1; clause7[1] = -var2; clause7[2] = var3; clause7[3] = -m1;
			clause8[0] = -var1; clause8[1] = var2; clause8[2] = -var3; clause8[3] = -m1;
			try {
				solver.addClause(new VecInt(clause1));
				solver.addClause(new VecInt(clause2));
				solver.addClause(new VecInt(clause3));
				solver.addClause(new VecInt(clause4));
			} catch (ContradictionException e) {
				e.printStackTrace();
			}
		}	
		else {//1 out of 7 Cutting the big equation down
			clause1[0] = -var1; clause1[1] = -var2; clause1[2] = var3; clause1[3] = m1;
			clause2[0] = -var1; clause2[1] = var2; clause2[2] = -var3; clause2[3] = m1;
			clause3[0] = -var1; clause3[1] = var2; clause3[2] = var3; clause3[3] = -m1;
			clause4[0] = -var1; clause4[1] = -var2; clause4[2] = -var3; clause4[3] = -m1;
			clause5[0] = var1; clause5[1] = var2; clause5[2] = var3; clause5[3] = m1;
			clause6[0] = var1; clause6[1] = -var2; clause6[2] = -var3; clause6[3] = m1;
			clause7[0] = var1; clause7[1] = -var2; clause7[2] = var3; clause7[3] = -m1;
			clause8[0] = var1; clause8[1] = var2; clause8[2] = -var3; clause8[3] = -m1;
			try {
				solver.addClause(new VecInt(clause1));
				solver.addClause(new VecInt(clause2));
				solver.addClause(new VecInt(clause3));
				solver.addClause(new VecInt(clause4));
			} catch (ContradictionException e) {
				e.printStackTrace();
			}
		}
			// 2 out of 7 cutting down equation
			clause1[0] = -m1; clause1[1] = var4; clause1[2] = var5; clause1[3] = m2;
			clause2[0] = m1; clause2[1] = -var4; clause2[2] = var5; clause2[3] = m2;
			clause3[0] = m1; clause3[1] = var4; clause3[2] = -var5; clause3[3] = m2;
			clause4[0] = m1; clause4[1] = var4; clause4[2] = var5; clause4[3] = -m2;
			clause5[0] = m1; clause5[1] = -var4; clause5[2] = -var5; clause5[3] = -m2;
			clause6[0] = -m1; clause6[1] = -var4; clause6[2] = -var5; clause6[3] = m2;
			clause7[0] = -m1; clause7[1] = -var4; clause7[2] = var5; clause7[3] = -m2;
			clause8[0] = -m1; clause8[1] = var4; clause8[2] = -var5; clause8[3] = -m2;
			try {
				solver.addClause(new VecInt(clause1));
				solver.addClause(new VecInt(clause2));
				solver.addClause(new VecInt(clause3));
				solver.addClause(new VecInt(clause4));
			} catch (ContradictionException e) {
				e.printStackTrace();
			}
			
			//3 out of 7 cutting down
			clause1[0] = -m2; clause1[1] = var6; clause1[2] = var7; clause1[3] = m3;
			clause2[0] = m2; clause2[1] = -var6; clause2[2] = var7; clause2[3] = m3;
			clause3[0] = m2; clause3[1] = var6; clause3[2] = -var7; clause3[3] = m3;
			clause4[0] = m2; clause4[1] = var6; clause4[2] = var7; clause4[3] = -m3;
			clause5[0] = m2; clause5[1] = -var6; clause5[2] = -var7; clause5[3] = -m3;
			clause6[0] = -m2; clause6[1] = -var6; clause6[2] = -var7; clause6[3] = m3;
			clause7[0] = -m2; clause7[1] = -var6; clause7[2] = var7; clause7[3] = -m3;
			clause8[0] = -m2; clause8[1] = var6; clause8[2] = -var7; clause8[3] = -m3;
			try {
				solver.addClause(new VecInt(clause1));
				solver.addClause(new VecInt(clause2));
				solver.addClause(new VecInt(clause3));
				solver.addClause(new VecInt(clause4));
			} catch (ContradictionException e) {
				e.printStackTrace();
			}
			
			//4 out of 7 cutting down
			clause1[0] = -m3; clause1[1] = lfsrPrim.get(25); clause1[2] = nfsrPrim.get(63); clause1[3] = m4;
			clause2[0] = m3; clause2[1] = -lfsrPrim.get(25); clause2[2] = nfsrPrim.get(63); clause2[3] = m4;
			clause3[0] = m3; clause3[1] = lfsrPrim.get(25); clause3[2] = -nfsrPrim.get(63); clause3[3] = m4;
			clause4[0] = m3; clause4[1] = lfsrPrim.get(25); clause4[2] = nfsrPrim.get(63); clause4[3] = -m4;
			clause5[0] = m3; clause5[1] = -lfsrPrim.get(25); clause5[2] = -nfsrPrim.get(63); clause5[3] = -m4;
			clause6[0] = -m3; clause6[1] = -lfsrPrim.get(25); clause6[2] = -nfsrPrim.get(63); clause6[3] = m4;
			clause7[0] = -m3; clause7[1] = -lfsrPrim.get(25); clause7[2] = nfsrPrim.get(63); clause7[3] = -m4;
			clause8[0] = -m3; clause8[1] = lfsrPrim.get(25); clause8[2] = -nfsrPrim.get(63); clause8[3] = -m4;
			try {
				solver.addClause(new VecInt(clause1));
				solver.addClause(new VecInt(clause2));
				solver.addClause(new VecInt(clause3));
				solver.addClause(new VecInt(clause4));
			} catch (ContradictionException e) {
				e.printStackTrace();
			}
			//5 out of 7 cutting down
			clause1[0] = -m4; clause1[1] = nfsrPrim.get(1); clause1[2] = nfsrPrim.get(2); clause1[3] = m5;
			clause2[0] = m4; clause2[1] = -nfsrPrim.get(1); clause2[2] = nfsrPrim.get(2); clause2[3] = m5;
			clause3[0] = m4; clause3[1] = nfsrPrim.get(1); clause3[2] = -nfsrPrim.get(2); clause3[3] = m5;
			clause4[0] = m4; clause4[1] = nfsrPrim.get(1); clause4[2] = nfsrPrim.get(2); clause4[3] = -m5;
			clause5[0] = m4; clause5[1] = -nfsrPrim.get(1); clause5[2] = -nfsrPrim.get(2); clause5[3] = -m5;
			clause6[0] = -m4; clause6[1] = -nfsrPrim.get(1); clause6[2] = -nfsrPrim.get(2); clause6[3] = m5;
			clause7[0] = -m4; clause7[1] = -nfsrPrim.get(1); clause7[2] = nfsrPrim.get(2); clause7[3] = -m5;
			clause8[0] = -m4; clause8[1] = nfsrPrim.get(1); clause8[2] = -nfsrPrim.get(2); clause8[3] = -m5;
			try {
				solver.addClause(new VecInt(clause1));
				solver.addClause(new VecInt(clause2));
				solver.addClause(new VecInt(clause3));
				solver.addClause(new VecInt(clause4));
			} catch (ContradictionException e) {
				e.printStackTrace();
			}
			
			//6 out of 7 cutting down
			clause1[0] = -m5; clause1[1] = nfsrPrim.get(4); clause1[2] = nfsrPrim.get(10); clause1[3] = m6;
			clause2[0] = m5; clause2[1] = -nfsrPrim.get(4); clause2[2] = nfsrPrim.get(10); clause2[3] = m6;
			clause3[0] = m5; clause3[1] = nfsrPrim.get(4); clause3[2] = -nfsrPrim.get(10); clause3[3] = m6;
			clause4[0] = m5; clause4[1] = nfsrPrim.get(4); clause4[2] = nfsrPrim.get(10); clause4[3] = -m6;
			clause5[0] = m5; clause5[1] = -nfsrPrim.get(4); clause5[2] = -nfsrPrim.get(10); clause5[3] = -m6;
			clause6[0] = -m5; clause6[1] = -nfsrPrim.get(4); clause6[2] = -nfsrPrim.get(10); clause6[3] = m6;
			clause7[0] = -m5; clause7[1] = -nfsrPrim.get(4); clause7[2] = nfsrPrim.get(10); clause7[3] = -m6;
			clause8[0] = -m5; clause8[1] = nfsrPrim.get(4); clause8[2] = -nfsrPrim.get(10); clause8[3] = -m6;
			try {
				solver.addClause(new VecInt(clause1));
				solver.addClause(new VecInt(clause2));
				solver.addClause(new VecInt(clause3));
				solver.addClause(new VecInt(clause4));
			} catch (ContradictionException e) {
				e.printStackTrace();
			}
			
			//7 out of 7 cutting down
			clause1[0] = -m6; clause1[1] = nfsrPrim.get(31); clause1[2] = nfsrPrim.get(43); clause1[3] = nfsrPrim.get(56);
			clause2[0] = m6; clause2[1] = -nfsrPrim.get(31); clause2[2] = nfsrPrim.get(43); clause2[3] = nfsrPrim.get(56);
			clause3[0] = m6; clause3[1] = nfsrPrim.get(31); clause3[2] = -nfsrPrim.get(43); clause3[3] = nfsrPrim.get(56);
			clause4[0] = m6; clause4[1] = nfsrPrim.get(31); clause4[2] = nfsrPrim.get(43); clause4[3] = -nfsrPrim.get(56);
			clause5[0] = m6; clause5[1] = -nfsrPrim.get(31); clause5[2] = -nfsrPrim.get(43); clause5[3] = -nfsrPrim.get(56);
			clause6[0] = -m6; clause6[1] = -nfsrPrim.get(31); clause6[2] = -nfsrPrim.get(43); clause6[3] = nfsrPrim.get(56);
			clause7[0] = -m6; clause7[1] = -nfsrPrim.get(31); clause7[2] = nfsrPrim.get(43); clause7[3] = -nfsrPrim.get(56);
			clause8[0] = -m6; clause8[1] = nfsrPrim.get(31); clause8[2] = -nfsrPrim.get(43); clause8[3] = -nfsrPrim.get(56);
			try {
				solver.addClause(new VecInt(clause1));
				solver.addClause(new VecInt(clause2));
				solver.addClause(new VecInt(clause3));
				solver.addClause(new VecInt(clause4));
			} catch (ContradictionException e) {
				e.printStackTrace();
			}
	}
	
	public boolean g() {
		boolean g;
		g = NFSR[62] ^ NFSR[60] ^ NFSR[52] ^ NFSR[45] ^ NFSR[37] ^ NFSR[33] ^ NFSR[28] ^ NFSR[21] ^ NFSR[14] ^ NFSR[9]
				^ NFSR[0] ^ (NFSR[63] & NFSR[60]) ^ (NFSR[37] & NFSR[33]) ^ (NFSR[15] & NFSR[9])
				^ (NFSR[60] & NFSR[52] & NFSR[45]) ^ (NFSR[33] & NFSR[28] & NFSR[21])
				^ (NFSR[63] & NFSR[45] & NFSR[28] & NFSR[9]) ^ (NFSR[60] & NFSR[52] & NFSR[37] & NFSR[33])
				^ (NFSR[63] & NFSR[60] & NFSR[21] & NFSR[15]) ^ (NFSR[63] & NFSR[60] & NFSR[52] & NFSR[45] & NFSR[37])
				^ (NFSR[33] & NFSR[28] & NFSR[21] & NFSR[15] & NFSR[9])
				^ (NFSR[52] & NFSR[45] & NFSR[37] & NFSR[33] & NFSR[28] & NFSR[21]);
		return g;
	}

	// generates the streamkey z's
	public boolean z() {
		boolean z;

		z = (LFSR[3] & LFSR[64]) ^ (LFSR[46] & LFSR[64]) ^ (LFSR[63] & LFSR[64]) ^ (LFSR[46] & LFSR[64] & LFSR[3])
				^ (LFSR[46] & NFSR[63] & LFSR[3]) ^ (LFSR[46] & NFSR[63] & LFSR[25]) ^ (LFSR[46] & NFSR[63] & LFSR[64])
				^ LFSR[25] ^ NFSR[63] ^ NFSR[1] ^ NFSR[2] ^ NFSR[4] ^ NFSR[10] ^ NFSR[31] ^ NFSR[43] ^ NFSR[56];
		return z;
	}

	public void clockBasis() {
		for (int k = 0; k < 160; k++) {

			boolean feedbackLFSR = LFSR[62] ^ LFSR[51] ^ LFSR[38] ^ LFSR[23] ^ LFSR[13] ^ LFSR[0];
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

	public void clockOneTime() {
		boolean feedbackLFSR = LFSR[62] ^ LFSR[51] ^ LFSR[38] ^ LFSR[23] ^ LFSR[13] ^ LFSR[0];
		boolean feedbackNFSR = LFSR[0] ^ g();


		for (int i = 0; i < LFSR.length - 1; i++) {
			LFSR[i] = LFSR[i + 1];
		}
		for (int i = 0; i < NFSR.length - 1; i++) {
			NFSR[i] = NFSR[i + 1];
		}

		NFSR[79] = feedbackNFSR;
		LFSR[79] = feedbackLFSR;


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
	}
	
	public void  solutionFinder(){
		int[] model = null;
		String[] help;
		ArrayList<String> lfsr = new ArrayList<String>();
		ArrayList<String> nfsr = new ArrayList<String>();
		
		IProblem problem = solver;
		try {
			if(problem.isSatisfiable())
				 model = problem.findModel();
			
		} catch (TimeoutException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		help = new String[model.length];
		for (int i = 0; i < model.length; i++) {
			help[i] = ""+ model[i];
		}
		for (int i = 0; i < model.length; i++) {
			
			switch(help[i].charAt(1)){
			
			case '1':
				
				lfsr.add(help[i].substring(2));
			
			break;
			case '2':
				nfsr.add(help[i].substring(2));
			break;
			}
		}
		int[] iv = new int[80];
		int[] key=new int[Integer.parseInt(nfsr.get(lfsr.size()-1))];

		int counter = 0;
		int tmp;
		
		for (int i = 0; i < iv.length; i++) {
			tmp = Integer.parseInt(lfsr.get(counter));
			if(tmp == i){
				iv[i] = 0;
				counter++;
			}
			else{
				iv[i] = 1;
			}
		}
		counter = 0;
		for (int i = 0; i < key.length; i++) {
			tmp = Integer.parseInt(nfsr.get(counter));
			if(tmp == i){
				key[i] = 0;
				counter++;
			}
			else{
				key[i] = 1;
			}
		}
		System.out.println("LFSR inhalt: ");
		for (int i = 0; i < iv.length; i++) {
			System.out.print(iv[i]);
		}
		System.out.println();
		System.out.println("NFSR inhalt: ");
		for (int i = 0; i < key.length; i++) {
			System.out.print(key[i]);
		}
	}

	public static void main(String[] args) {

		grain g = new grain();
		
		for (int i = 0; i < 100; i++) {

			if(g.z()){
				g.addClauses(1);
			}else
			g.addClauses(0);
			
			g.clockOneTime();
			g.lfsrPrim.clock();
			g.nfsrPrim.clock();
		}
		System.out.println();
		g.solutionFinder();
		System.out.println("\n"+ cutVar+", "+newVar);

	}
}
