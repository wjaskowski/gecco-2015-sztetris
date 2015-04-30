package put.ci.cevo.framework.state.listeners;

import put.ci.cevo.framework.state.EvolutionState;

public interface LastGenerationListener {

	void onLastGeneration(EvolutionState state);

}
