import java.util.ArrayList;

import org.sat4j.core.VecInt;
import org.sat4j.minisat.SolverFactory;
import org.sat4j.specs.ContradictionException;
import org.sat4j.specs.IProblem;
import org.sat4j.specs.ISolver;
import org.sat4j.specs.TimeoutException;


public class SimpleStream {

	boolean lfsr[] = new boolean[10]; //lfsr
	ArrayPrimitive lfsrPrim = new ArrayPrimitive(10, "1"); //Eigene Datenstruktur für die Eingabe des Sat4J's
	ISolver solver = SolverFactory.newDefault(); //Satsolver
	static int cutVar = 1;
	
	//String key = "0110110101";
	//String key = "0011100001";
	
	String key = "0111001011";
	//String key = "0010011111";
	
	//Konstruktor der den lsfr mit dem übergeben Key initialisiert
	SimpleStream(){
		for (int i = 0; i < lfsr.length; i++) {
			lfsr[i] = key.charAt(i) == '1' ? true : false;
		}
	}
	
	
	public static int getCutVar(){
		String outPut = "4" + cutVar;
		cutVar += 1;
		return Integer.parseInt(outPut);
	}
	
	
	//feedback-funktion
	public boolean f(){
		return lfsr[0] ^ lfsr[3] ^ lfsr[9] ^lfsr[5]^ lfsr[7];
	}
	
	//output-function
	public boolean output(){
		return lfsr[1] ^ lfsr[6];
	}
	
	//einmaliges clocken der Stromschiffre
	public void clockSimpleStream(){
		boolean feedback = f();
		
		for (int i = 0; i < lfsr.length -1; i++) {
			lfsr[i] = lfsr[i+1];
			
			
		}
		lfsr[9] = feedback;
	}
	
	//einfügung der Klauseln in den Satsolver
	public void addClaususSimple(){
		
		int[] clause1 = new int[4];
		int[] clause2 = new int[4];
		int[] clause3 = new int[4];
		int[] clause4 = new int[4];
		int[] clause5 = new int[4];
		int[] clause6 = new int[4];
		int[] clause7 = new int[4];
		int[] clause8 = new int[4];
		
		int m1 = getCutVar();
		
				//feedback based clauses
			clause1[0] = -lfsrPrim.get(0); clause1[1] = lfsrPrim.get(3); clause1[2] = lfsrPrim.get(9); clause1[3] = m1;
			clause2[0] = lfsrPrim.get(0); clause2[1] = -lfsrPrim.get(3); clause2[2] = lfsrPrim.get(9); clause2[3] = m1;
			clause3[0] = lfsrPrim.get(0); clause3[1] = lfsrPrim.get(3); clause3[2] = -lfsrPrim.get(9); clause3[3] = m1;
			clause4[0] = lfsrPrim.get(0); clause4[1] = lfsrPrim.get(3); clause4[2] = lfsrPrim.get(9); clause4[3] = -m1;
			clause5[0] = lfsrPrim.get(0); clause5[1] = -lfsrPrim.get(3); clause5[2] = -lfsrPrim.get(9); clause5[3] = -m1;
			clause6[0] = -lfsrPrim.get(0); clause6[1] = -lfsrPrim.get(3); clause6[2] = -lfsrPrim.get(9); clause6[3] = m1;
			clause7[0] = -lfsrPrim.get(0); clause7[1] = -lfsrPrim.get(3); clause7[2] = lfsrPrim.get(9); clause7[3] = -m1;
			clause8[0] = -lfsrPrim.get(0); clause8[1] = lfsrPrim.get(3); clause8[2] = -lfsrPrim.get(9); clause8[3] = -m1;
			
			for (int i = 0; i < 4; i++) {
				System.out.print(clause1[i]+" ");
				
			}
			System.out.println(0);
			for (int i = 0; i < 4; i++) {
				System.out.print(clause2[i]+" ");
				
			}
			System.out.println(0);
			for (int i = 0; i < 4; i++) {
				System.out.print(clause3[i]+" ");
				
			}
			System.out.println(0);
			for (int i = 0; i < 4; i++) {
				System.out.print(clause4[i]+" ");
				
			}
			System.out.println(0);
			for (int i = 0; i < 4; i++) {
				System.out.print(clause5[i]+" ");
				
			}
			System.out.println(0);
			for (int i = 0; i < 4; i++) {
				System.out.print(clause8[i]+" ");
				
			}
			System.out.println(0);
			for (int i = 0; i < 4; i++) {
				System.out.print(clause7[i]+" ");
				
			}
			System.out.println(0);
			for (int i = 0; i < 4; i++) {
				System.out.print(clause6[i]+" ");
				
			}
			System.out.println(0);
			try {
				solver.addClause(new VecInt(clause1));
				solver.addClause(new VecInt(clause2));
				solver.addClause(new VecInt(clause3));
				solver.addClause(new VecInt(clause4));
				solver.addClause(new VecInt(clause5));
				solver.addClause(new VecInt(clause6));
				solver.addClause(new VecInt(clause7));
				solver.addClause(new VecInt(clause8));
			} catch (ContradictionException e) {
				e.printStackTrace();
			}
		
			
			clause1[0] = -lfsrPrim.get(5); clause1[1] = lfsrPrim.get(7); clause1[2] = lfsrPrim.get(9)+1; clause1[3] = m1;
			clause2[0] = lfsrPrim.get(5); clause2[1] = -lfsrPrim.get(7); clause2[2] = lfsrPrim.get(9)+1; clause2[3] = m1;
			clause3[0] = lfsrPrim.get(5); clause3[1] = lfsrPrim.get(7); clause3[2] = -(lfsrPrim.get(9)+1); clause3[3] = m1;
			clause4[0] = lfsrPrim.get(5); clause4[1] = lfsrPrim.get(7); clause4[2] = lfsrPrim.get(9)+1; clause4[3] = -m1;
			clause5[0] = lfsrPrim.get(5); clause5[1] = -lfsrPrim.get(7); clause5[2] = -(lfsrPrim.get(9)+1); clause5[3] = -m1;
			clause6[0] = -lfsrPrim.get(5); clause6[1] = -lfsrPrim.get(7); clause6[2] = -(lfsrPrim.get(9)+1); clause6[3] = m1;
			clause7[0] = -lfsrPrim.get(5); clause7[1] = -lfsrPrim.get(7); clause7[2] = lfsrPrim.get(9)+1; clause7[3] = -m1;
			clause8[0] = -lfsrPrim.get(5); clause8[1] = lfsrPrim.get(7); clause8[2] = -(lfsrPrim.get(9)+1); clause8[3] = -m1;
			

			for (int i = 0; i < 4; i++) {
				System.out.print(clause1[i]+" ");
				
			}
			System.out.println(0);
			for (int i = 0; i < 4; i++) {
				System.out.print(clause2[i]+" ");
				
			}
			System.out.println(0);
			for (int i = 0; i < 4; i++) {
				System.out.print(clause3[i]+" ");
				
			}
			System.out.println(0);
			for (int i = 0; i < 4; i++) {
				System.out.print(clause4[i]+" ");
				
			}
			System.out.println(0);
			for (int i = 0; i < 4; i++) {
				System.out.print(clause5[i]+" ");
				
			}
			System.out.println(0);
			for (int i = 0; i < 4; i++) {
				System.out.print(clause8[i]+" ");
				
			}
			System.out.println(0);
			for (int i = 0; i < 4; i++) {
				System.out.print(clause7[i]+" ");
				
			}
			System.out.println(0);
			for (int i = 0; i < 4; i++) {
				System.out.print(clause6[i]+" ");
				
			}
			System.out.println(0);
			
			try {
				solver.addClause(new VecInt(clause1));
				solver.addClause(new VecInt(clause2));
				solver.addClause(new VecInt(clause3));
				solver.addClause(new VecInt(clause4));
				solver.addClause(new VecInt(clause5));
				solver.addClause(new VecInt(clause6));
				solver.addClause(new VecInt(clause7));
				solver.addClause(new VecInt(clause8));
			} catch (ContradictionException e) {
				e.printStackTrace();
			}
			
			
			//output based clauses
			clause1 = new int[2];
			clause2 = new int[2];
			
			
			if(this.output()){
				clause1[0] = -lfsrPrim.get(1); clause1[1] = lfsrPrim.get(6);
				clause2[0] = lfsrPrim.get(1); clause2[1] = -lfsrPrim.get(6);
			}else{
				clause1[0] = lfsrPrim.get(1); clause1[1] = lfsrPrim.get(6);
				clause2[0] = -lfsrPrim.get(1); clause2[1] = -lfsrPrim.get(6);
			}
			for (int i = 0; i < 2; i++) {
				System.out.print(clause1[i]+" ");
				
			}
			System.out.println(0);
			for (int i = 0; i < 2; i++) {
				System.out.print(clause2[i]+" ");
				
			}
			System.out.println(0);
			try {
				solver.addClause(new VecInt(clause1));
				solver.addClause(new VecInt(clause2));
			} catch (ContradictionException e) {
				e.printStackTrace();
			}
	}
	
	//Funktion zum Finden der möglichen Belegungen
	public void solutionFinderSimple(){
		int[] model = null;
		String[] help;
		ArrayList<String> lfsr = new ArrayList<String>();
		
		IProblem problem = solver;
		try {
			if(problem.isSatisfiable()){			//Findung einer möglichen Belgung
				 model = problem.findModel();	// und speicher in model[]
			
			}else{
				System.out.println("es wurde kein model gefunden");
			return;
			}
			
		} catch (TimeoutException e) {
			e.printStackTrace();
		} 
		help = new String[model.length];  		//casten der Integer in model[] zu Strings
		for (int i = 0; i < model.length; i++) {// und speicher in help[]
			help[i] = ""+ model[i];
		}
		
//		//Syso Help
//		System.out.println("Help:");
//		for (int i = 0; i < 20; i++) {
//			System.out.print(help[i]+" ");
//		}
//		System.out.println();
//		System.out.println("Model:");
//		// Syso Help
//		for (int i = 0; i < 20; i++) {
//			System.out.print(model[i]+" ");
//		}
//		System.out.println();
//		// Syso model
		
		for (int i = 0; i < help.length; i++) { //filterung aller negativen Variablen
			if(help[i].charAt(0) == '-'){		// und Speicher in lfsr<>
				lfsr.add(help[i].substring(2));
			}
		}
		// Syso inhalt des Lfsrs
//		for (int i = 0; i < 20; i++) {
//			System.out.print(lfsr.get(i)+" ");
//		}
//		System.out.println();
		int[] key=new int[10];

		int counter = 0;
		int tmp;
		
		for (int i = 1; i < key.length+1; i++) {  		//Aufüllen des Ergebnisses (key[])
			tmp = Integer.parseInt(lfsr.get(counter));	
			if(tmp == i){									
				key[i-1] = 0; 							//0 wenn die Variable in lfsr<> ist
				counter++;
			}
			else{
				key[i-1] = 1;							//ansonsten 1
			}
		}
		
		System.out.println("Key: ");
		for (int i = 0; i < key.length; i++) {
			System.out.print(key[i]);
		}
	}
	
	public static void main(String[] args){
		
		SimpleStream ss = new SimpleStream();
		int[] keystream = new int[20];
		for (int i = 0; i < 100; i++) {

			ss.addClaususSimple();
			ss.clockSimpleStream();
			
			if(i<20){
				if(ss.output()){
					keystream[i] = 1;
				}else{
					keystream[i] = 0;
				}
			}
			ss.lfsrPrim.clock();
			
			
		}
		
		ss.solutionFinderSimple();
		System.out.println();
		System.out.println("keystream:");
		for (int i = 0; i < keystream.length; i++) {
			System.out.print(keystream[i]);
		}
	}
	
	
	//01100010011111111011
}
