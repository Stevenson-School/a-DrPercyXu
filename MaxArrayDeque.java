import java.util.Comparator;

public class MaxArrayDeque<T> extends ArrayDeque<T>{
    private Comparator<T> c;
    public MaxArrayDeque(){
        super();
    }
    public MaxArrayDeque(Comparator<T> c){
        this.c = c;
    }

    public T max(){
        T max = get(0);
        for(T item: this){
            if(c.compare(item, max)>0){
                max = item;
            }
        }
        return max;
    }

    public T max(Comparator<T> c){
        T max = get(0);
        for(T item: this){
            if(c.compare(item, max)>0){
                max = item;
            }
        }
        return max;
    }

    public static void main(String[] args) {
        Comparator<Integer> sc = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        };
        Comparator<Integer> sc2 = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        };
        MaxArrayDeque<Integer> test = new MaxArrayDeque<>(sc2);
        test.addFirst(1);
        test.addLast(2);
        test.addLast(3);
        System.out.println(test.max(sc));
    }
}
