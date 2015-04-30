package put.ci.cevo.games.tetris.agents;

import org.apache.commons.math3.random.RandomDataGenerator;
import put.ci.cevo.games.encodings.ntuple.NTuples;
import put.ci.cevo.games.tetris.Tetris;
import put.ci.cevo.games.tetris.TetrisAction;
import put.ci.cevo.games.tetris.TetrisState;
import put.ci.cevo.rl.agent.Agent;
import put.ci.cevo.rl.evaluation.ActionEvaluatingAgent;

public class DeltaNTuplesTetrisAgent implements Agent<TetrisState, TetrisAction> {

	private final ActionEvaluatingAgent<TetrisState, TetrisAction> agent;

	public DeltaNTuplesTetrisAgent(Tetris tetris, NTuples ntuples) {
		this.agent = new ActionEvaluatingAgent<>(tetris,
				new DeltaNTuplesTetrisActionEvaluator(ntuples, tetris.getBoardSize()));
	}

	@Override
	public TetrisAction chooseAction(TetrisState state, RandomDataGenerator random) {
		return agent.chooseAction(state, random);
	}
}
