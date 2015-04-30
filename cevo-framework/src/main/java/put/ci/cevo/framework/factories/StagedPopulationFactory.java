package put.ci.cevo.framework.factories;

import put.ci.cevo.util.random.ThreadedContext;
import java.util.List;

public interface StagedPopulationFactory<S, T> {

	List<T> createPopulation(int generation, List<S> solutions, int populationSize, ThreadedContext context);

}
