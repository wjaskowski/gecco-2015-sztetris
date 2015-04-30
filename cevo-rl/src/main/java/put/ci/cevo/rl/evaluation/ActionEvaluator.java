package put.ci.cevo.rl.evaluation;

import put.ci.cevo.rl.environment.Action;
import put.ci.cevo.rl.environment.State;

public interface ActionEvaluator<S extends State, A extends Action> {
	/**
	 * How good a given action in a given state is. Larger value is better.
	 */
	double evaluateAction(S state, A action);
}
