package put.ci.cevo.framework.state.listeners;

import put.ci.cevo.framework.state.EvolutionState;

public interface EvolutionStateListener {

	void onNextGeneration(EvolutionState state);

}