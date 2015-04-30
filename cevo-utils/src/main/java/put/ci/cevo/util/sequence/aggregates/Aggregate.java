package put.ci.cevo.util.sequence.aggregates;

public interface Aggregate<S, T> {

	S aggregate(S accumulator, T element);

}
