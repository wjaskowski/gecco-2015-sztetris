package put.ci.cevo.util.info;

/**
 * Stores information about progress in processing and periodically (not more often than once every 10 seconds) prints
 * info about progress status to console.
 */
public interface ProgressInfo {

	void processed();

	void processed(Object object);

	void multiProcessed(long numElements);

	void finished();

}
