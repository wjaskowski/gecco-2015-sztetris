package put.ci.cevo.framework.algorithms;

import put.ci.cevo.framework.state.listeners.EvolutionStateListener;
import put.ci.cevo.framework.state.listeners.LastGenerationListener;

public interface GenerationalOptimizationAlgorithm extends OptimizationAlgorithm {

	void addNextGenerationListener(EvolutionStateListener listener);

	void removeNextGenerationListener(EvolutionStateListener listener);

	void addLastGenerationListener(LastGenerationListener listener);

	void removeLastGenerationListener(LastGenerationListener listener);

}
