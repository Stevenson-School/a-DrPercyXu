import java.util.Deque;
import java.util.Iterator;

public class ArrayDeque<T> implements Iterable<T>{
    private final double RFACTOR = 1.5;
    protected T[] items;
    private int size;
    private int nextFirst;
    private int nextLast;

    // Constructor
    public ArrayDeque() {
        items = (T[]) new Object[8];
        size = 0;
        nextFirst = 0;
        nextLast = 1;
    }

    private int minusOne(int index) {
        return (index - 1 + items.length) % items.length;
    }

    private int plusOne(int index) {
        return (index + 1) % items.length;
    }

    public T[] resize(double factor){
        T[] newA = (T[]) new Object[(int)(size*factor)];
        for (int i = 0; i < size; i++) {
            newA[i] = get(i);
        }
        nextFirst = newA.length-1;
        nextLast = size;
        return newA;
    }
    public void addFirst(T item) {
        if(size==items.length){
            items = resize(RFACTOR);
        }
        items[nextFirst] = item;
        nextFirst = minusOne(nextFirst);
        size++;
    }

    public void addLast(T item) {
        if(size==items.length){
            items = resize(RFACTOR);
        }
        items[nextLast] = item;
        nextLast = plusOne(nextLast);
        size++;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        if((double)(size)/items.length<=0.25){
            items = resize(2);
        }
        nextFirst = plusOne(nextFirst);
        T removedItem = items[nextFirst];
        items[nextFirst] = null;
        size--;
        return removedItem;
    }

    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        if((double)(size/items.length)<=0.25){
            items = resize(2);
        }
        nextLast = minusOne(nextLast);
        T removedItem = items[nextLast];
        items[nextLast] = null;
        size--;
        return removedItem;
    }

    public T get(int index) {
        if (index >= size) {
            return null;
        }
        return items[(nextFirst + 1 + index) % items.length];
    }

    public void printDeque() {
        for (int i = 0; i < size; i++) {
            System.out.print(get(i) + " ");
        }
        System.out.println();
    }

    @Override
    public Iterator<T> iterator() {
        return new MyIterator();
    }

    private class MyIterator implements Iterator<T>{
        int pos;
        public MyIterator() { pos = 0; }
        public boolean hasNext(){
            return pos<size;
        }
        public T next(){
            T item = get(pos);
            pos++;
            return item;
        }
    }

    @Override
    public String toString(){
        StringBuilder newS = new StringBuilder("{");
        for(T s : this) {
            newS.append(s);
            newS.append(", ");
        }
        newS.replace(newS.length()-2, newS.length(), "}");
        return newS.toString();
    }

    @Override
    public boolean equals(Object object) {
        if (object instanceof ArrayDeque o) {
            if (size() != o.size()) {
                return false;
            }
            int pos = 0;
            for (T item : this) {
                if (item!=o.get(pos++)) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        ArrayDeque<Integer> test = new ArrayDeque<>();
        test.addFirst(1);
        test.addLast(2);
        test.addLast(3);
        test.addFirst(8);
        test.addFirst(7);
        test.addFirst(6);
        test.addLast(4);
        test.addFirst(5);
        test.addFirst(9);
        test.addLast(10);
        ArrayDeque<Integer> test2 = new ArrayDeque<>();
        test2.addFirst(2);
        test2.addLast(3);
        test2.addFirst(1);
        test2.addFirst(8);
        test2.addFirst(7);
        test2.addFirst(6);
        System.out.println(test.equals(test2));
        System.out.println(test.toString());
        System.out.println(test2.toString());
//        test.printDeque();
//        System.out.println(test.removeFirst());
//        System.out.println(test.removeFirst());
//        System.out.println(test.removeFirst());
//        System.out.println(test.removeFirst());
//        System.out.println(test.removeFirst());
//        System.out.println(test.removeFirst());
//        System.out.println(test.removeFirst());
//        System.out.println(test.removeFirst());
//        test.printDeque();
    }
}
