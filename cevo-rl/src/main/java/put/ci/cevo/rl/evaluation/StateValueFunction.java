package put.ci.cevo.rl.evaluation;

import put.ci.cevo.rl.environment.State;

public interface StateValueFunction<S extends State> {
	double getValue(S state);

	void update(S state, double expectedValue, double learningRate);
}
