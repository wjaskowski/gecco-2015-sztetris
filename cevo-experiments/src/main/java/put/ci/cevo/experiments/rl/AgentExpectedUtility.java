package put.ci.cevo.experiments.rl;

import java.util.List;

import put.ci.cevo.framework.measures.Measurement;
import put.ci.cevo.framework.measures.PerformanceMeasure;
import put.ci.cevo.rl.agent.Agent;
import put.ci.cevo.rl.environment.Action;
import put.ci.cevo.rl.environment.Environment;
import put.ci.cevo.rl.environment.State;
import put.ci.cevo.util.CollectionUtils;
import put.ci.cevo.util.RandomFactory;
import put.ci.cevo.util.annotations.AccessedViaReflection;
import put.ci.cevo.util.random.ThreadedContext;

/**
 * General expected utility for measuring average agent rewards for environments
 */
public class AgentExpectedUtility<S extends State, A extends Action> implements PerformanceMeasure<Agent<S, A>> {

	private final int sampleSize;
	private final Environment<S, A> environment;

	@AccessedViaReflection
	public AgentExpectedUtility(Environment<S, A> environment, int sampleSize) {
		this.environment = environment;
		this.sampleSize = sampleSize;
	}

	@Override
	public Measurement measure(RandomFactory<Agent<S, A>> agentFactory, ThreadedContext context) {
		List<Double> result = context.invoke(
				(nothing, context1) -> {
					Agent<S, A> agent = agentFactory.create(context1);
					return environment.interact(agent, context1.getRandomForThread());
				},
				CollectionUtils.range(sampleSize)
		).toList();

		return new Measurement.Builder().addRaw(result).build();
	}
}
