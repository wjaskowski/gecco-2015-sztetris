package put.ci.cevo.games.tetris;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.carrotsearch.hppc.IntArrayList;
import com.google.common.base.Preconditions;
import org.apache.commons.math3.random.RandomDataGenerator;
import put.ci.cevo.games.board.RectSize;
import put.ci.cevo.rl.environment.Environment;
import put.ci.cevo.rl.environment.Transition;
import put.ci.cevo.util.Pair;
import put.ci.cevo.util.RandomUtils;

public class Tetris implements Environment<TetrisState, TetrisAction> {

	private final List<Tetromino> tetrominoes;
	private final RectSize boardSize;

	/**
	 * Creates default Tetris environment with all 7 available Tetrominoes
	 */
	public Tetris() {
		this(Arrays.asList(Tetromino.values()), TetrisBoard.DEFAULT_BOARD_SIZE);
	}

	public Tetris(List<Tetromino> tetrominoes, RectSize boardSize) {
		Preconditions.checkArgument(tetrominoes.size() > 0);
		this.tetrominoes = tetrominoes;
		this.boardSize = boardSize;
	}

	public static Tetris newSZTetris() {
		return new Tetris(Arrays.asList(Tetromino.S, Tetromino.Z), TetrisBoard.DEFAULT_BOARD_SIZE);
	}

	@Override
	public Transition<TetrisState, TetrisAction> computeTransition(TetrisState state, TetrisAction action) {
		TetrisState nextState = state.clone();
		int numClearedLines = nextState.placeTetromino(action.getPosition(), action.getRotation());
		return new Transition<>(state, action, nextState, Math.max(numClearedLines, 0), (numClearedLines == -1));
	}

	@Override
	public TetrisState getNextState(TetrisState afterState, RandomDataGenerator random) {
		return new TetrisState(afterState.getBoard(), RandomUtils.pickRandom(tetrominoes, random));
	}

	@Override
	public List<TetrisAction> getPossibleActions(TetrisState state) {
		List<TetrisAction> actions = new ArrayList<>(20);
		Tetromino tetromino = state.getTetromino();

		//No moves are possible until tetromino is added
		if (tetromino == null) {
			return actions;
		}

		//TODO: In the case of SZ-tetris we could memorize the actions list - it won't change.
		for (int rot = 0; rot < tetromino.getPossibleRotations(); rot++) {
			for (int pos = 0; pos <= state.getBoard().getWidth() - tetromino.getWidth(rot); pos++) {
				actions.add(new TetrisAction(pos, rot));
			}
		}
		return actions;
	}

	@Override
	public TetrisState sampleInitialStateDistribution(RandomDataGenerator random) {
		return new TetrisState(new TetrisBoard(), RandomUtils.pickRandom(tetrominoes, random));
	}

	@Override
	public boolean isTerminalState(TetrisState state) {
		return state.isTerminal();
	}

	@Override
	public double getAgentPerformance(double totalReward, int numSteps, TetrisState finalState) {
		return 0;
	}

	public List<Tetromino> getTetrominoes() {
		return tetrominoes;
	}

	public RectSize getBoardSize() {
		return boardSize;
	}

	public static Pair<IntArrayList, Integer> simulateAction(TetrisState state, TetrisAction action) {
		return state.getBoard().simulatePlaceTetromino(state.getTetromino(), action.getPosition(), action.getRotation());
	}

	/**
	 * This function is used by delta move evaluators. It is important to run it twice with the same argument; otherwise
	 * it breaks the state of BoardState (e.g., it does not update skyline)
	 * @param positions list of margin-based positions
	 */
	public static void swapValues(TetrisState state, IntArrayList positions) {
		TetrisBoard board = state.getBoard();
		for (int i = 0; i < positions.size(); ++i) {
			int pos = positions.buffer[i];
			board.setValue(pos, board.getValue(pos) == 0 ? 1 : 0);
		}
	}

}
