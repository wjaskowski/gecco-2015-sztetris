package put.ci.cevo.rl.evaluation;

import put.ci.cevo.rl.environment.Action;
import put.ci.cevo.rl.environment.Environment;
import put.ci.cevo.rl.environment.State;
import put.ci.cevo.rl.environment.Transition;

/**
 * An action evaluator which evaluates actions based on the whole transition (state, action, afterstate, reward).
 * This is why it needs to have access to the environment
 */
public abstract class TransitionEvaluator<S extends State, A extends Action> implements ActionEvaluator<S, A> {

	final private Environment<S, A> environment;

	public TransitionEvaluator(Environment<S, A> environment) {
		this.environment = environment;
	}

	@Override
	public final double evaluateAction(S state, A action) {
		return evaluateTransition(environment.computeTransition(state, action));
	}

	public abstract double evaluateTransition(Transition<S, A> transition);
}
