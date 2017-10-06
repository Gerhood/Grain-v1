import java.util.HashSet;

//import org.sat4j.core.VecInt;


public class Clause extends HashSet<String> {
	
	public Clause() {
		super();
	}
	
	public Clause(String[] values) {
		this();
		for (String s : values) {
			add(s);
		}
	}

	//public VecInt toVecInt() { return new VecInt(stream().mapToInt(i -> i).toArray()); }
	
}
