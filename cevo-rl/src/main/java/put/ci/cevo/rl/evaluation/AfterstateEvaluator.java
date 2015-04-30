package put.ci.cevo.rl.evaluation;

import put.ci.cevo.rl.environment.Action;
import put.ci.cevo.rl.environment.Environment;
import put.ci.cevo.rl.environment.State;
import put.ci.cevo.rl.environment.Transition;

public final class AfterstateEvaluator<S extends State, A extends Action> extends TransitionEvaluator<S,A> {

	private final StateValueFunction<S> valueFunction;

	public AfterstateEvaluator(Environment<S, A> environment, StateValueFunction<S> valueFunction) {
		super(environment);
		this.valueFunction = valueFunction;
	}

	@Override
	public double evaluateTransition(Transition<S, A> transition) {
		//TODO: FIXME. This is Tetris-specific
		if (transition.isTerminal())
			return Integer.MIN_VALUE;
		return transition.getReward() + valueFunction.getValue(transition.getAfterState());
	}
}
