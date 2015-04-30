package put.ci.cevo.rl.agent.algorithms;

import put.ci.cevo.rl.agent.functions.ValueFunction;
import put.ci.cevo.rl.environment.Action;
import put.ci.cevo.rl.environment.State;
import put.ci.cevo.rl.environment.Transition;

public interface LearningAlgorithm<S extends State, A extends Action> {

	void learnFromTransition(Transition<S, A> transition, ValueFunction<S, A> valueFunction);
}
