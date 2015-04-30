package put.ci.cevo.util.sequence.events;

public interface SequenceEventsProvider<T> {

	void addListener(SequenceListener<T> listener);

}
