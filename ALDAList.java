package alda.linear;

/**
 * Detta är listinterfacet ni ska implementera. Det är en förenklad version av
 * det som finns i java.util.List där ni hittar dokumentationen av de metoderna.
 * Observera dock att remove(element) och contains(element) här tar en parameter
 * av typen E och inte Object, och att List i orginal returnerar en boolean
 * vilket vi struntar i.
 * 
 * @author Henrik
 */
public interface ALDAList<E> extends Iterable<E> {

	public void add(E element);

	public void add(int index, E element);

	public E remove(int index);

	public boolean remove(E element);

	public E get(int index);

	public boolean contains(E element);

	public int indexOf(E element);

	public void clear();

	public int size();

}