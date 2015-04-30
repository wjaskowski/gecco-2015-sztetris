package put.ci.cevo.util.filter;

import java.util.Collection;
import java.util.Iterator;

import org.apache.commons.collections15.Predicate;

/**
 * The filter interface - basically a function from objects of some type to boolean space.
 * 
 */
public interface Filter<T> extends Predicate<T>, com.google.common.base.Predicate<T> {

	boolean qualifies(T object);

	Filter<T> not();

	Filter<T> or(Filter<T> other);

	Filter<T> and(Filter<T> other);

	Filter<T> xor(Filter<T> other);

	boolean any(Iterable<? extends T> objects);

	boolean every(Iterable<? extends T> objects);

	boolean none(Iterable<? extends T> objects);

	T find(Iterable<? extends T> objects);

	int apply(Iterable<? extends T> features);

	int apply(Iterator<? extends T> featuresIterator);

	Collection<T> select(Iterable<? extends T> features);

	Collection<T> select(Iterator<? extends T> featuresIterator);

}
