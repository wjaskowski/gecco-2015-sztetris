package put.ci.cevo.games.encodings.ntuple;

import put.ci.cevo.games.board.BoardState;
import put.ci.cevo.games.encodings.ntuple.eval.BoardNTupleEvaluator;
import put.ci.cevo.rl.evaluation.StateValueFunction;

public class NTuplesStateValueFunction<S extends BoardState> implements StateValueFunction<S> {

	private final NTuples ntuples;
	private final BoardNTupleEvaluator boardEvaluator = new BoardNTupleEvaluator();

	public NTuplesStateValueFunction(NTuples ntuples) {
		this.ntuples = ntuples;
	}

	@Override
	public double getValue(S state) {
		return boardEvaluator.evaluate(ntuples, state.getBoard());
	}

	@Override
	public void update(S state, double expectedValue, double learningRate) {
		double currentValue = boardEvaluator.evaluate(ntuples, state.getBoard());
		double error = expectedValue - currentValue;
		double delta = error * learningRate;

		//TODO: is there a better way to change values in LUTs of particular tuples?
		for (NTuple tuple : ntuples.getAll()) {
			tuple.getWeights()[tuple.address(state.getBoard())] += delta;
		}
	}
}
