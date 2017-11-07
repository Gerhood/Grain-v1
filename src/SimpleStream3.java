import java.util.ArrayList;

import org.sat4j.minisat.SolverFactory;
import org.sat4j.specs.ISolver;


public class SimpleStream3 {

	int lfsrLength = 18;
	boolean[] lfsrState = {false, true, false, true, true, false, true, true, false, true, true, false, true, true, false, true, true, false};
	static int steps = 30;
	ISolver solver = SolverFactory.newDefault(); //Satsolver
	
	
	public boolean lfsrFeedback(){
		for (int i = 0; i < lfsrState.length; i++) {
			if(this.lfsrState[i]){
				System.out.print(1+" ");
			}else{
				System.out.print(0 +" ");
			}
		}
		System.out.println();
		
		return lfsrState[0] ^ lfsrState[10] ^ lfsrState[17];
	}
	
	public boolean lfsrOutput(){
	
		return lfsrState[3]^lfsrState[14];
		
	}
	
	public void performStep(){
		boolean feedback = lfsrFeedback();
		
		for (int i = 0; i < lfsrState.length-1; i++) {
			lfsrState[i] = lfsrState[i+1];
		}
		lfsrState[17] = feedback;
	}
	
	public int[] generateKeystream(int steps){
		ArrayList<Integer> keystream = new ArrayList<Integer>();
		
		for (int i = 0; i < steps; i++) {
			if(lfsrOutput())
				keystream.add(1);
			else
				keystream.add(0);
			
			performStep();
			
		}
		
		int[] tmpKeystream = new int[keystream.size()];
		
		for (int i = 0; i < tmpKeystream.length; i++) {
			tmpKeystream[i] = keystream.get(i);
		}
		
		return tmpKeystream;
	}
	
	
	public void printDimacs(int[] keystream){
		int numberOfVariables = lfsrLength + keystream.length;
		int numberOfClauses = keystream.length * (8+2);
		
		System.out.println("p cnf "+numberOfVariables+" "+numberOfClauses);
		for(int i = 0; i < keystream.length ; i++){
			
			
			//feedback-based clauses
			System.out.println( "-1"+(1 + (i))+" 1"+ (11+(i))+" 1"+(18+(i))+" 1"+(lfsrLength+i+1)+" 0");
			System.out.println( "1"+(1 + (i))+" "+ "-1"+(11+(i))+" 1"+(18+(i))+" 1"+(lfsrLength+i+1)+" 0");
			System.out.println( "1"+(1 + (i))+" 1"+ (11+(i))+" "+"-1"+(18+(i))+" 1"+(lfsrLength+i+1)+" 0");
			System.out.println( "1"+(1 + (i))+" 1"+ (11+(i))+" 1"+(18+(i))+" "+"-1"+(lfsrLength+i+1)+" 0");
			System.out.println( "1"+(1 + (i))+" "+ "-1"+(11+(i))+" "+ "-1"+(18+(i))+" "+ "-1"+(lfsrLength+i+1)+" 0");
			System.out.println( "-1"+(1 + (i))+" 1"+ (11+(i))+" "+"-1"+(18+(i))+" "+ "-1"+(lfsrLength+i+1)+" 0");
			System.out.println( "-1"+(1 + (i))+" "+ "-1"+(11+(i))+" 1"+(18+(i))+" "+ "-1"+(lfsrLength+i+1)+" 0");
			System.out.println( "-1"+(1 + (i))+" "+ "-1"+(11+(i))+" "+"-1"+(18+(i))+" 1"+(lfsrLength+i+1)+" 0");
	
			//outputbased clauses
			if(keystream[i]==0){
				System.out.println( "-1"+(4 +(i)) +" 1"+ (15+(i))+" 0");
				System.out.println( "1"+(4 +(i)) +" "+ "-1"+(15+(i))+" 0");
			}else{
				System.out.println( "1"+(4 +(i)) +" 1"+ (15+(i))+" 0");
				System.out.println( "-1"+(4 +(i)) +" "+ "-1"+(15+(i))+" 0");
			}
		}
		
		
	}
	
	
	public static void main(String[] args){
		SimpleStream3 s3 = new SimpleStream3();
		int[] myKeystream = s3.generateKeystream(30);
		for (int i = 0; i < myKeystream.length; i++) {
			System.out.print(myKeystream[i]+" ");
		}
		System.out.println();
		
		s3.printDimacs(myKeystream);
	}
	
	
}
