package put.ci.cevo.framework.algorithms;

import put.ci.cevo.framework.termination.EvolutionTarget;
import put.ci.cevo.util.random.ThreadedContext;

public interface OptimizationAlgorithm {

	void evolve(EvolutionTarget target, ThreadedContext context);

}
