package put.ci.cevo.rl.evaluation;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.math3.random.RandomDataGenerator;
import put.ci.cevo.rl.agent.Agent;
import put.ci.cevo.rl.environment.Action;
import put.ci.cevo.rl.environment.Environment;
import put.ci.cevo.rl.environment.State;
import put.ci.cevo.util.RandomUtils;

/**
 * Agent that chooses its actions maximizing the value the actionEvaluator returns
 */
public final class ActionEvaluatingAgent<S extends State, A extends Action> implements Agent<S, A> {

	private static final double EPSILON = 1e-6;

	private final Environment<S, A> environment;
	private final ActionEvaluator<S, A> actionEvaluator;

	public ActionEvaluatingAgent(Environment<S, A> environment, ActionEvaluator<S, A> actionEvaluator) {
		this.environment = environment;
		this.actionEvaluator = actionEvaluator;
	}

	@Override
	public A chooseAction(S state, RandomDataGenerator random) {
		List<A> bestActions = new ArrayList<>();
		double bestEval = Double.NEGATIVE_INFINITY;

		for (A action : environment.getPossibleActions(state)) {
			double eval = actionEvaluator.evaluateAction(state, action);
			if (eval == bestEval || Math.abs(eval - bestEval) < EPSILON) {
				bestActions.add(action);
			} else if (bestEval < eval) {
				bestEval = eval;
				bestActions.clear();
				bestActions.add(action);
			}
		}

		return RandomUtils.pickRandom(bestActions, random);
	}
}
