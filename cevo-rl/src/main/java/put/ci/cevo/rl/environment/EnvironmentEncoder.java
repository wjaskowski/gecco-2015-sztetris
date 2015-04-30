package put.ci.cevo.rl.environment;

//TODO: Should be removed in favour of FeatureExtractor + something new for actions
public interface EnvironmentEncoder<S extends State, A extends Action> {

	double[] encode(S state);

	double[] encode(A action);
}
