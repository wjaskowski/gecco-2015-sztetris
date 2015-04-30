package put.ci.cevo.rl.evaluation;

import com.google.common.base.Preconditions;
import put.ci.cevo.rl.environment.FeaturesExtractor;
import put.ci.cevo.rl.environment.State;
import put.ci.cevo.util.vectors.DoubleVector;

/**
 * Provides a value function which is is a sum of weighted features
 */
public class LinearFeaturesStateValueFunction<S extends State> implements StateValueFunction<S> {
	private final DoubleVector parameters;
	FeaturesExtractor<S> featuresExtractor;

	public LinearFeaturesStateValueFunction(FeaturesExtractor<S> featuresExtractor, DoubleVector parameters) {
		Preconditions.checkArgument(parameters.size() == featuresExtractor.featuresCount());
		this.featuresExtractor = featuresExtractor;
		this.parameters = parameters;
	}

	@Override
	public double getValue(S state) {
		double[] features = featuresExtractor.getFeatures(state);
		return parameters.dot(DoubleVector.of(features));
	}

	@Override
	public void update(S state, double expectedValue, double learningRate) {
		double[] features = featuresExtractor.getFeatures(state);
		double currentValue = parameters.dot(DoubleVector.of(features));
		double error = expectedValue - currentValue;
		double delta = error * learningRate;

		double[] updates = new double[parameters.size()];
		for (int i = 0; i < parameters.size(); i++) {
			updates[i] = delta * features[i];
		}

		parameters.add(new DoubleVector(updates));
	}
}
