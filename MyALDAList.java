package alda.linear;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyALDAList<E> implements Iterable<E>, ALDAList<E>{
	private Node first;
	private Node last; // kanske inte behöver den?
	private int theSize;
	private static class Node<E> {
		E data;	
		Node next;

		public Node(E data){
			this.data = data;
		}

	} // inre classen Node
	Node<E> getLast(){
		Node<E> nu = first;
		for(int i =0; i<theSize; i ++){
			if(nu.next == null){
				return nu;
			}
		}
		return null;
	}
	public Iterator<E> iterator() {
		return new ListIterator();
	}

	private  class ListIterator implements Iterator<E>{ // listiterator
		private Node<E> node = first;
		private Node<E> ForNode = null;
		private boolean tabort = false;


		public boolean hasNext() {
			return node != null;
		}

		public E next() {
			if(!hasNext())
				throw new NoSuchElementException();
			E next = node.data;
			ForNode = node;
			node = node.next;
			tabort = false;
			return next;
		}


		public void remove() {
			if(ForNode == null || tabort){
				throw new IllegalStateException();
			}
			MyALDAList.this.remove(ForNode.data);
			tabort = true;

		}

	} // inre classen ListIterator

	public void add(E element){
		Node<E> tmp = new Node<E> (element);

		if(first==null){
			first=new Node<E>(element);
			last = first;
			theSize ++;
		}
		else{
			Node<E> forra= null;
			Node<E> nu = first;

			for(int i =0; i<theSize; i++){
				if(i == size()-1){
					nu.next = tmp;
					last = tmp;
				}

				nu = nu.next;
			}
			theSize++;

		}

	} // add

	public void add(int index, E element){

		if(index <0 || index >theSize)
			throw new IndexOutOfBoundsException();
		Node<E> node = first;
		Node<E> forra = null;
		Node<E> tmp = null;

		if(first == null){// om det inte finns några el.

			first = new Node<E> (element);
			last = first;
			theSize++;
		}

		else{
			for(int i=0; i<index; i++){
				forra = node;
				node = node.next;			
			}
			if(forra == null){
				first = new Node<E> (element);
				first.next = node;
				last = getLast();
				theSize ++;

			}
			else{
				if(forra.next == last){ // om den e sista
					tmp = new Node<E> (element);
					forra.next = tmp;
					tmp.next = last;
					theSize ++;
				}

				else{
					tmp = new Node<E> (element);
					forra.next = tmp;
					tmp.next = node;
					last = getLast();
					theSize ++;
				}
			}
		}

	}// add med Indexering

	public E remove(int index){

		if(index <0 || index >size()-1)
			throw new IndexOutOfBoundsException();
		Node<E> fore = null;
		Node<E> efter= first;
		for(int i =0; i<theSize; i++){
			if(i == index){
				if(fore == null && efter.next == null){
					first = null;
					last = null;
					theSize = 0;

					return efter.data;
				}
				else if(fore == null && efter.next != null){
					first = efter.next;
					last = getLast();
					theSize--;

					return efter.data;
				}
				else if(fore != null && efter.next == null){
					last = fore;
					theSize--;

					return efter.data;
				}
				else if(fore != null && efter.next != null){
					fore.next = efter.next;
					last= getLast();
					theSize--;

					return efter.data;

				}



			}
			fore = efter;
			efter = efter.next;
		}

		return null;

	}// remove med index

	public boolean remove(E element){
		Node<E> fore = null;
		Node<E> efter = first;
		for(int i =0; i<theSize; i++){
			if(efter.data.equals(element)){
				if(fore != null && efter.next != null){
					fore.next = efter.next;
					theSize--;
					return true;

				}
				else if(fore == null && efter.next != null){
					first = efter.next;
					last = getLast();
					theSize--;
					return true;
				}
				else if(fore != null && efter.next == null){
					last = fore;
					theSize--;
					return true;
				}
				else if(fore == null && efter.next == null){
					first = null;
					last = null;
					theSize --;
					return true;
				}
			}
			fore = efter;
			if(efter.next == null)
				return false;
			efter = efter.next;

		}
		return false;
	}// remove

	public E get(int index){

		if(first == null || index<0 || index>size()-1)
			throw new IndexOutOfBoundsException();
		Node<E> node = first;

		for(int i=0; i<index; i++){
			node = node.next;	
		}

		return node.data;


	} // get(index)

	public boolean contains(E element){
		Node<E> n = new Node<E>(element);
		Node<E> koll = first;
		for(int i =0; i<theSize; i ++){
			if(koll.data.equals(element)){
				return true;
			}
			koll = koll.next;
		}
		return false;
	}// contains

	public int indexOf(E element){
		Node<E> n = new Node<E>(element);
		Node<E> koll = first;
		for(int i =0; i <theSize; i ++){
			if(koll.data.equals(element)){
				return i ;
			}
			koll=koll.next;
		}
		return -1;
	}// index of 

	public void clear(){

		first = null;
		last = null;
		theSize =0;

	}// clear 

	public int size(){
		return theSize;
	}

	public String toString(){

		String result ="[";
		if(first != null){
			Node<E> n = first; 
			for(int x=0; x<this.size(); x++){
				if(n == first)
					result +=n.data;
				else
					result += ", "+n.data;
				if(n.next == null)
					break;
				n = n.next;
			}
		}
		result +=("]");
		return result.toString();
	}// toString

}// MyALDAList 
