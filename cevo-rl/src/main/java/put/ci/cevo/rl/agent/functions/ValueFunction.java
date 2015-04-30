package put.ci.cevo.rl.agent.functions;

import put.ci.cevo.rl.environment.Action;
import put.ci.cevo.rl.environment.State;

//TODO: We have quite a mess here having several similar entities: ValueFunction, StateValueFunction, StateActionValueFunction, RealFunction, ActionEvaluator (and its derivatives)
//This seems to be actually an ActionValueFunction. There cannot be a general ValueFunction that can work for both
// state-value function and action-value function
public interface ValueFunction<S extends State, A extends Action> {

	double getValue(S state, A action);

	void update(S state, A action, double expectedValue, double learningRate);
}
