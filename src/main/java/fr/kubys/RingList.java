package fr.kubys;

import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.stream.IntStream;


public class RingList<E> implements List<E> {

    private final List<E> elements;
    private int step = 1; // a
    private int offset = 0; // b

    public RingList(Collection<E> elements) {
        this.elements = List.copyOf(elements);
    }

    @SafeVarargs
    public RingList(E... elements) {
        this.elements = Arrays.asList(elements);
    }

    public void increment(int n) {
        step *= n;
        offset *= n;
    }

    public void cut(int n) {
        offset -= n;
    }

    public void deal() {
        step *= -1;
        offset = (offset * -1) - 1;
    }

    public List<E> mapToRingList() {
        List<E> ringList = new ArrayList<>(Collections.nCopies(10, null));

        IntStream.range(0, size()).forEach(i -> ringList.set(mapToRingList(i), elements.get(i)));

        return ringList;
    }

    private int mapToRingList(int index) {
        return Math.floorMod((step * index + offset), size());
    }

    @Override
    public E get(int index) {
        return mapToRingList().get(index);
    }

    @Override
    public E set(int index, E element) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void add(int index, E element) {
        throw new UnsupportedOperationException();
    }

    @Override
    public E remove(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int indexOf(Object o) {
        return mapToRingList().indexOf(o);
    }

    @Override
    public int lastIndexOf(Object o) {
        return mapToRingList().lastIndexOf(o);
    }

    @NotNull
    @Override
    public ListIterator<E> listIterator() {
        return listIterator(0);
    }

    @NotNull
    @Override
    public ListIterator<E> listIterator(int index) {
        return new RingListIterator(0);
    }

    @NotNull
    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        return mapToRingList().subList(fromIndex, toIndex);
    }

    @Override
    public String toString() {
        return mapToRingList().toString() +
                " : RingList{" +
                "offset=" + offset +
                ", step=" + step +
                "}";
    }

    @Override
    public int size() {
        return elements.size();
    }

    @Override
    public boolean isEmpty() {
        return elements.size() == 0;
    }

    @Override
    public boolean contains(Object o) {
        return elements.contains(o);
    }

    @NotNull
    @Override
    public Iterator<E> iterator() {
        return new RingListIterator(0);
    }

    @NotNull
    @Override
    public Object[] toArray() {
        return mapToRingList().toArray();
    }

    @NotNull
    @Override
    public <T> T[] toArray(@NotNull T[] a) {
        return mapToRingList().toArray(a);
    }

    @Override
    public boolean add(E e) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean remove(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean containsAll(@NotNull Collection<?> c) {
        return elements.containsAll(c);
    }

    @Override
    public boolean addAll(@NotNull Collection<? extends E> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean addAll(int index, @NotNull Collection<? extends E> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean removeAll(@NotNull Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean retainAll(@NotNull Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException();
    }

    private class RingListIterator implements ListIterator<E> {
        int currentIndex;

        RingListIterator(int currentIndex) {
            this.currentIndex = currentIndex;
        }

        // returns false if next element does not exist
        @Override
        public boolean hasNext() {
            return currentIndex < size();
        }

        // return current data and update pointer
        @Override
        public E next() {
            if (hasNext()) return get(currentIndex++);
            else throw new NoSuchElementException();
        }

        @Override
        public boolean hasPrevious() {
            return currentIndex > 0;
        }

        @Override
        public E previous() {
            if (hasPrevious()) return get(currentIndex--);
            else throw new NoSuchElementException();
        }

        @Override
        public int nextIndex() {
            return currentIndex + 1;
        }

        @Override
        public int previousIndex() {
            return currentIndex - 1;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

        @Override
        public void set(E e) {
            throw new UnsupportedOperationException();
        }

        @Override
        public void add(E e) {
            throw new UnsupportedOperationException();
        }
    }
}
