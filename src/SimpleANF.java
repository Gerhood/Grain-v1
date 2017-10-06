import java.util.HashSet;


public class SimpleANF extends HashSet<String> {
	
	public CNF toCNF() {
		String[] asArray = (String[]) toArray();
		for (int b = 0; b < (1 << size()); ++b) {
			boolean res = (b & 1) == 1;
			for (int i = 0; i < size(); ++i) {
				
			}
		}
		return null;
	}
	
}
