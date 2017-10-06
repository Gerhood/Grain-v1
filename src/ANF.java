import java.util.HashSet;
import java.util.Iterator;

public class ANF extends HashSet<Monomial>{
	
	public CNF toCNF() {
		if (size() < 4)
			throw new RuntimeException();
		
		CNF ret = new CNF(null);
		Iterator<Monomial> it = iterator();
		int m = 1;
		SimpleANF first = new SimpleANF();
		for (int i = 0; i < 3; ++i) {
			CNF cnf = it.next().toCNF();
			ret.addClauses(cnf);
			first.add(cnf.getVariableEquivalent());
		}
		first.add("M" + m);
		ret.addClauses(first.toCNF());
		while (it.hasNext()) {
			SimpleANF anf = new SimpleANF();
			anf.add("M" + m++);
			CNF cnf = it.next().toCNF();
			ret.addClauses(cnf);
			anf.add(cnf.getVariableEquivalent());
			if (it.hasNext()) {
				cnf = it.next().toCNF();
				ret.addClauses(cnf);
				anf.add(cnf.getVariableEquivalent());
			}				
			if (it.hasNext())
				anf.add("M" + m);
			ret.addClauses(anf.toCNF());
		}
		return ret;
	}

	public static void main(String[] args) {
		boolean z;
		
		ANF anf = new ANF();
		anf.add(new Monomial(new String[]{"3", "64"}));
		anf.add(new Monomial(new String[]{"46", "64"}));
		anf.add(new Monomial(new String[]{"63", "64"}));
		anf.add(new Monomial(new String[]{"46", "25", "3"}));
		anf.add(new Monomial(new String[]{"36", "64", "3"}));
	}
	
}
