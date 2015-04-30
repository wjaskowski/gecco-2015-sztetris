package put.ci.cevo.games.tetris;

import com.carrotsearch.hppc.IntArrayList;
import put.ci.cevo.rl.evaluation.ActionEvaluator;
import put.ci.cevo.rl.evaluation.StateValueFunction;
import put.ci.cevo.util.Pair;

public class StateValueFunctionTetrisActionEvaluator implements ActionEvaluator<TetrisState, TetrisAction> {

	private final StateValueFunction<TetrisState> stateValueFunction;

	public StateValueFunctionTetrisActionEvaluator(StateValueFunction<TetrisState> stateValueFunction) {
		this.stateValueFunction = stateValueFunction;
	}

	@Override
	public double evaluateAction(TetrisState state, TetrisAction action) {
		Pair<IntArrayList, Integer> result = Tetris.simulateAction(state, action);
		IntArrayList changedPositions = result.first();
		double reward = result.second();

		double eval = -stateValueFunction.getValue(state);

		// Make the action...
		Tetris.swapValues(state, changedPositions);

		eval += stateValueFunction.getValue(state);

		// ... and revert it
		Tetris.swapValues(state, changedPositions);

		return eval + reward;
	}
}
