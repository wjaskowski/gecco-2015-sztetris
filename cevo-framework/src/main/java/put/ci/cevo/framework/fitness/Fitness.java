package put.ci.cevo.framework.fitness;

public interface Fitness extends Comparable<Fitness> {

	double fitness();
	
	Fitness negate();
	
	boolean betterThan(Fitness other);
	
}
