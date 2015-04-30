package put.ci.cevo.util.sequence.events;

import java.util.EventListener;

public interface SequenceListener<T> extends EventListener {

	void onBeforeNext();

	void onNext(T elem);

	void onFinished();

}
