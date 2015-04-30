package put.ci.cevo.rl.agent;

import org.apache.commons.math3.random.RandomDataGenerator;
import put.ci.cevo.rl.environment.Action;
import put.ci.cevo.rl.environment.State;

public interface Agent<S extends State, A extends Action> {

	A chooseAction(S state, RandomDataGenerator random);
}
