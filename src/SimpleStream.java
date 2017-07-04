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
	
	String key = "1000101100";
	
	//Konstruktor der den lsfr mit dem übergeben Key initialisiert
	SimpleStream(){
		for (int i = 0; i < lfsr.length; i++) {
			lfsr[i] = key.charAt(i) == '1' ? true : false;
		}
	}
	
	//feedbackfunktion
	public boolean f(){
		boolean z;
		z = lfsr[0] ^ lfsr[3] ^ lfsr[9] ^lfsr[5];
		return z;
	}
	
	//einmaliges clocken der Stromschiffre
	public void clockSimpleStream(){
		boolean feedback = f();
		
		for (int i = 0; i < lfsr.length -1; i++) {
			lfsr[i] = lfsr[i+1];
			
			lfsr[9] = feedback;
		}
	}
	
	//einfügung der Klauseln in den Satsolver
	public void addClaususSimple(int z){
		
		int[] clause1 = new int[4];
		int[] clause2 = new int[4];
		int[] clause3 = new int[4];
		int[] clause4 = new int[4];
		int[] clause5 = new int[4];
		int[] clause6 = new int[4];
		int[] clause7 = new int[4];
		int[] clause8 = new int[4];
		
		if(z == 1){						//Falls Feedback == 1 füge enstprechende Klauseln nach Wahrheitstabelle ein.
			clause1[0] = -lfsrPrim.get(0); clause1[1] = -lfsrPrim.get(3); clause1[2] = lfsrPrim.get(9); clause1[3] = lfsrPrim.get(5);
			clause2[0] = -lfsrPrim.get(0); clause2[1] = lfsrPrim.get(3); clause2[2] = -lfsrPrim.get(9); clause2[3] = lfsrPrim.get(5);
			clause3[0] = -lfsrPrim.get(0); clause3[1] = lfsrPrim.get(3); clause3[2] = lfsrPrim.get(9); clause3[3] = -lfsrPrim.get(5);
			clause4[0] = -lfsrPrim.get(0); clause4[1] = -lfsrPrim.get(3); clause4[2] = -lfsrPrim.get(9); clause4[3] = -lfsrPrim.get(5);
			clause5[0] = lfsrPrim.get(0); clause5[1] = lfsrPrim.get(3); clause5[2] = lfsrPrim.get(9); clause5[3] = lfsrPrim.get(5);
			clause6[0] = lfsrPrim.get(0); clause6[1] = -lfsrPrim.get(3); clause6[2] = -lfsrPrim.get(9); clause6[3] = lfsrPrim.get(5);
			clause7[0] = lfsrPrim.get(0); clause7[1] = -lfsrPrim.get(3); clause7[2] = lfsrPrim.get(9); clause7[3] = -lfsrPrim.get(5);
			clause8[0] = lfsrPrim.get(0); clause8[1] = lfsrPrim.get(3); clause8[2] = -lfsrPrim.get(9); clause8[3] = -lfsrPrim.get(5);
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
		}
		else{						//ansonsten feedback 0 und füge dementprechend Klausen im Satsolver ein
			clause1[0] = -lfsrPrim.get(0); clause1[1] = lfsrPrim.get(3); clause1[2] = lfsrPrim.get(9); clause1[3] = lfsrPrim.get(5);
			clause2[0] = lfsrPrim.get(0); clause2[1] = -lfsrPrim.get(3); clause2[2] = lfsrPrim.get(9); clause2[3] = lfsrPrim.get(5);
			clause3[0] = lfsrPrim.get(0); clause3[1] = lfsrPrim.get(3); clause3[2] = -lfsrPrim.get(9); clause3[3] = lfsrPrim.get(5);
			clause4[0] = lfsrPrim.get(0); clause4[1] = lfsrPrim.get(3); clause4[2] = lfsrPrim.get(9); clause4[3] = -lfsrPrim.get(5);
			clause5[0] = lfsrPrim.get(0); clause5[1] = -lfsrPrim.get(3); clause5[2] = -lfsrPrim.get(9); clause5[3] = -lfsrPrim.get(5);
			clause6[0] = -lfsrPrim.get(0); clause6[1] = -lfsrPrim.get(3); clause6[2] = -lfsrPrim.get(9); clause6[3] = lfsrPrim.get(5);
			clause7[0] = -lfsrPrim.get(0); clause7[1] = -lfsrPrim.get(3); clause7[2] = lfsrPrim.get(9); clause7[3] = -lfsrPrim.get(5);
			clause8[0] = -lfsrPrim.get(0); clause8[1] = lfsrPrim.get(3); clause8[2] = -lfsrPrim.get(9); clause8[3] = -lfsrPrim.get(5);
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
		}
	}
	
	//Funktion zum Finden der möglichen Belegungen
	public void solutionFinderSimple(){
		int[] model = null;
		String[] help;
		ArrayList<String> lfsr = new ArrayList<String>();
		
		IProblem problem = solver;
		try {
			if(problem.isSatisfiable())			//Findung einer möglichen Belgung
				 model = problem.findModel();	// und speicher in model[]
			
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
		
		for (int i = 1; i < key.length+1; i++) {  		//Aufüllen der Ergebnisses (key[])
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
		
		for (int i = 0; i < 200000; i++) {

			if(ss.f()){
				ss.addClaususSimple(1);
			}else{
			ss.addClaususSimple(0);
			}
			
			ss.clockSimpleStream();
			ss.lfsrPrim.clock();
		}
		
		ss.solutionFinderSimple();
	}
	
	
	
}
