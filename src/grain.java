

public class grain {

	boolean[] LFSR = new boolean[80];
	boolean[] NFSR = new boolean[80];
	
	
	
	public void clockLfsr(){
		boolean feedback = LFSR[62] ^ LFSR[51] ^ LFSR[38] ^ LFSR[23] ^ LFSR[13] ^ LFSR[0];
		for (int i = 0; i < LFSR.length - 1; i++) {
			LFSR[i] = LFSR[i+1];
		}
		
		LFSR[LFSR.length-1] = feedback;
	}

	
	
	public void clockNfsr(){
		boolean feedback = LFSR[0] ^ g();
		for (int i = 0; i < NFSR.length -1; i++) {
			NFSR[i] = NFSR[i+1];
		}
		NFSR[79]= feedback;
	}
	
	
	
	//function g
	public boolean g(){
		boolean g;
		 g = NFSR[62]^NFSR[60]^NFSR[52]^NFSR[45]^NFSR[37]^NFSR[33]^NFSR[28]^NFSR[2]^NFSR[14]^NFSR[9]^NFSR[0]^(NFSR[63] & NFSR[60])^(NFSR[37] & NFSR[33])^(NFSR[15] & NFSR[9])^(NFSR[60] & NFSR[52] & NFSR[45])^(NFSR[33] & NFSR[28] & NFSR[21])^(NFSR[63] & NFSR[45] & NFSR[28] & NFSR[9])^(NFSR[60] & NFSR[52] & NFSR[37] & NFSR[33])^(NFSR[63] & NFSR[60] & NFSR[21] & NFSR[15])^(NFSR[63] & NFSR[60] & NFSR[52] & NFSR[45] & NFSR[37])^(NFSR[33] & NFSR[28] & NFSR[21] & NFSR[15] & NFSR[9])^(NFSR[52] & NFSR[45] & NFSR[37] & NFSR[33] & NFSR[28] & NFSR[21]);
		return g;
	}
	
	//generates the streamkey z's
	public boolean z(){
		boolean z;
		
		z = NFSR[0] ^ NFSR[63] & (true ^ LFSR[64] ^ LFSR[46] & (LFSR[3]^LFSR[25]^LFSR[64]) ) ^ (LFSR[25] ^ LFSR[3] & LFSR[46] & (LFSR[25]^LFSR[64]) ^ LFSR[64] & (LFSR[3] ^ LFSR[46]));
		
		return z;
	}
	
	
	public grain(){
		for (int i = 0; i < LFSR.length; i++) {
			LFSR[i] = Math.random() < 0.50 ? true : false ;
		}
		for (int i = 0; i < NFSR.length; i++) {
			NFSR[i] = Math.random() < 0.50 ? true : false ;
		}
	}
}
