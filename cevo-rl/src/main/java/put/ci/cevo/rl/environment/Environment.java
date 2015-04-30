package put.ci.cevo.rl.environment;

import java.util.List;
import java.util.function.Consumer;

import org.apache.commons.math3.random.RandomDataGenerator;
import put.ci.cevo.rl.agent.Agent;

public interface Environment<S extends State, A extends Action> {

	Transition<S, A> computeTransition(S state, A action);

	//TODO: Consider returning also a reward
	S getNextState(S afterState, RandomDataGenerator random);

	List<A> getPossibleActions(S state);

	S sampleInitialStateDistribution(RandomDataGenerator random);

	boolean isTerminalState(S state);

	@Deprecated
	double getAgentPerformance(double totalReward, int numSteps, S finalState);

	/**
	 * Executes the episode till the terminalState
	 * @param stateListener executed at every transition
	 * @return total reward
	 */
	default double interact(Agent<S, A> agent, RandomDataGenerator random, Consumer<Transition<S, A>> stateListener) {
		double totalReward = 0;
		S currentState = sampleInitialStateDistribution(random);

		while (!isTerminalState(currentState)) {
			A action = agent.chooseAction(currentState, random);
			Transition<S, A> transition = computeTransition(currentState, action);
			totalReward += transition.getReward();
			currentState = getNextState(transition.getAfterState(), random);
			stateListener.accept(transition);
		}

		return totalReward;
	}

	default double interact(Agent<S, A> agent, RandomDataGenerator random) {
		return interact(agent, random, x -> {});
	}
}
