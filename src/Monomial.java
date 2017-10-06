import java.util.HashSet;


public class Monomial extends HashSet<String> {
	
	static int V = 1;
	
	public Monomial() {
		super();
	}
	
	public Monomial(String[] values) {
		this();
		for (String i : values)
			add(i);
	}
	
	public CNF toCNF() {
		CNF cnf = new CNF("V" + V);
		Clause first = new Clause(new String[]{"V" + V});
		for (String s : this) {
			first.add("-" + s);
			cnf.add(new Clause(new String[]{"-V" + V, s}));
		}
		cnf.add(first);
		++V;
		return cnf;
	}
}
