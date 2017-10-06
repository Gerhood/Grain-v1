import java.util.HashSet;


public class CNF extends HashSet<Clause> {
	
	private String variableEquivalent;
	
	public String getVariableEquivalent() {
		return variableEquivalent;
	}
	
	public CNF(String variableEquivalent) {
		this.variableEquivalent = variableEquivalent;
	}
	
	public void addClauses(CNF cnf) {
		for (Clause c : cnf)
			add(c);
	}

}
