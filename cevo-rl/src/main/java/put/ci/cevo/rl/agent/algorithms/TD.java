package put.ci.cevo.rl.agent.algorithms;

import put.ci.cevo.rl.agent.functions.ValueFunction;
import put.ci.cevo.rl.environment.Action;
import put.ci.cevo.rl.environment.State;
import put.ci.cevo.rl.environment.Transition;

public class TD<S extends State, A extends Action> implements LearningAlgorithm<S, A> {

	protected double alpha;

	public TD(double learningRate) {
		this.alpha = learningRate;
	}

	@Override
	public void learnFromTransition(Transition<S, A> transition, ValueFunction<S, A> valueFunction) {
		double expectedReward = transition.getReward();
		if (!transition.isTerminal()) {
			expectedReward += valueFunction.getValue(transition.getState(), transition.getAction());
		}

		valueFunction.update(transition.getState(), transition.getAction(), expectedReward, alpha);
	}
}
