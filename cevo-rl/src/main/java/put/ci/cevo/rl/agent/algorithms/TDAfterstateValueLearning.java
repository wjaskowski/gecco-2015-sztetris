package put.ci.cevo.rl.agent.algorithms;

import org.apache.commons.math3.random.RandomDataGenerator;
import put.ci.cevo.rl.agent.Agent;
import put.ci.cevo.rl.environment.Action;
import put.ci.cevo.rl.environment.Environment;
import put.ci.cevo.rl.environment.State;
import put.ci.cevo.rl.environment.Transition;
import put.ci.cevo.rl.evaluation.ActionEvaluatingAgent;
import put.ci.cevo.rl.evaluation.AfterstateEvaluator;
import put.ci.cevo.rl.evaluation.StateValueFunction;
import put.ci.cevo.util.RandomUtils;

/**
 * Temporal difference learning algorithm. Learns an afterstate value function
 */
public class TDAfterstateValueLearning<S extends State, A extends Action> {

	private StateValueFunction<S> afterstateValueFunction;
	private Environment<S, A> environment;
	private Agent<S, A> agent;

	/**
	 * @param afterstateValueFunction function to learn
	 * @param agent                   responsible for making decisions (might decide basing on afterstateValueFunction)
	 */
	public TDAfterstateValueLearning(Environment<S, A> environment, StateValueFunction<S> afterstateValueFunction,
			Agent<S, A> agent) {
		this.environment = environment;
		this.afterstateValueFunction = afterstateValueFunction;
		this.agent = agent;
	}

	/**
	 * A shorter version where the afterstateValueFunction is used directly for making actions. It may be slower.
	 */
	public TDAfterstateValueLearning(Environment<S, A> environment, StateValueFunction<S> afterstateValueFunction) {
		this(environment, afterstateValueFunction, new ActionEvaluatingAgent<>(environment, new AfterstateEvaluator<>(
				environment, afterstateValueFunction)));
	}

	public void fastLearningEpisode(double explorationRate, double learningRate, RandomDataGenerator random) {
		S state = environment.sampleInitialStateDistribution(random);
		Transition<S, A> transition;       // state -> afterstate
		Transition<S, A> nextTransition = null;   // nextState -> nextAfterstate

		while (!environment.isTerminalState(state)) {
			// To explore or not to explore?
			if (random.nextUniform(0, 1) < explorationRate) {
				A randomAction = RandomUtils.pickRandom(environment.getPossibleActions(state), random);
				transition = environment.computeTransition(state, randomAction);
			} else {
				// If not to explore then we already computed the transition
				// (our transition was nextTransition in the previous iteration if there was a previous iteration)
				if (nextTransition == null)
					nextTransition = environment.computeTransition(state, agent.chooseAction(state, random));
				transition = nextTransition;
			}

			S nextState = environment.getNextState(transition.getAfterState(), random);

			double correctAfterStateValue = 0;
			if (!environment.isTerminalState(nextState)) {
				A nextAction = agent.chooseAction(nextState, random);
				nextTransition = environment.computeTransition(nextState, nextAction);
				correctAfterStateValue = nextTransition.getReward() + afterstateValueFunction.getValue(
						nextTransition.getAfterState());
			}

			// If the next state is a terminal state than we do not need to compute the correctAfterStateValue, because
			// the correct value of the "terminal" afterstate is always 0.
			afterstateValueFunction.update(transition.getAfterState(), correctAfterStateValue, learningRate);
			state = nextState;
		}
	}
}
