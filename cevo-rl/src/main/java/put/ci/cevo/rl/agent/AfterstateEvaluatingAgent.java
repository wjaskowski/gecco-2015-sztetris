package put.ci.cevo.rl.agent;

import org.apache.commons.math3.random.RandomDataGenerator;
import put.ci.cevo.rl.environment.Action;
import put.ci.cevo.rl.environment.Environment;
import put.ci.cevo.rl.environment.State;
import put.ci.cevo.rl.evaluation.ActionEvaluatingAgent;
import put.ci.cevo.rl.evaluation.AfterstateEvaluator;
import put.ci.cevo.rl.evaluation.StateValueFunction;

/**
 * Evaluates all afterstates using stateValueFunction and makes an action leading the afterstate of the highest values
 */
public class AfterstateEvaluatingAgent<S extends State, A extends Action> implements Agent<S, A> {

	private final ActionEvaluatingAgent<S, A> agent;

	public AfterstateEvaluatingAgent(Environment<S, A> environment, StateValueFunction<S> stateValueFunction) {
		this.agent = new ActionEvaluatingAgent<>(environment, new AfterstateEvaluator<>(environment, stateValueFunction));
	}

	@Override
	public A chooseAction(S state, RandomDataGenerator random) {
		return agent.chooseAction(state, random);
	}
}
