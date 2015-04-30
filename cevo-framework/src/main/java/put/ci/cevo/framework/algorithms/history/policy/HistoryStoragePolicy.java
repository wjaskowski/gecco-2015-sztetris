package put.ci.cevo.framework.algorithms.history.policy;

import put.ci.cevo.framework.state.EvolutionState;

public interface HistoryStoragePolicy {

	boolean qualifies(EvolutionState state);

}
