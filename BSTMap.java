import javax.naming.AuthenticationNotSupportedException;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.Stack;

public class BSTMap<K extends Comparable<K>, V> implements MapAdv<K, V>{
    BSTNode root;
    int size = 0;

    private class BSTNode{
        K key;
        BSTNode left, right;
        V value;
        public BSTNode(){
            key = null;
            value = null;
            left = null;
            right = null;
        }
        public BSTNode(K key, V value){
            this.key = key;
            this.value = value;
            left = null;
            right = null;
        }
    }
    @Override
    public Set<K> keySet() {
        Set<K> kset = new LinkedHashSet<>();
        MakeSet(root, kset);
        return kset;
    }

    private void MakeSet(BSTNode x, Set<K> set) {
        if (x == null) return;

        MakeSet(x.left, set);
        set.add(x.key);
        MakeSet(x.right, set);
    }
    @Override
    public Iterator<K> iterator() {
        return keySet().iterator();
    }

    public void clear(){
        root = null;
        size = 0;
    }
    private BSTNode find(BSTNode T, K sk) {
        if (T == null) return null;
        if (sk.equals(T.key)) return T;
        else if (sk.compareTo(T.key)<0) return find(T.left, sk);
        else return find(T.right, sk);
    }
    private BSTNode putHelp(BSTNode T, K sk, V value) {
        if (T == null) return new BSTNode(sk, value);
        if (sk.equals(T.key)) return null;
        else if (sk.compareTo(T.key)<0){
            T.left = putHelp(T.left, sk, value);
        }else{
            T.right = putHelp(T.right, sk, value);
        }
        return T;
    }
    @Override
    public boolean containsKey(K key) {
        return find(root, key)!=null;
    }

    @Override
    public V get(K key) {
        BSTNode res = find(root, key);
        if(res==null){
            return null;
        }else{
            return res.value;
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void put(K key, V value) {
        root = putHelp(root, key, value);
        size++;
    }


    @Override
    public V remove(K key) {
        return null;
    }

    @Override
    public V remove(K key, V value) {
        return null;
    }

    public static void main(String[] args) {
        BSTMap<String, Integer> b = new BSTMap<String, Integer>();
        b.put("hi", 12);
        b.put("are", 13);
        b.put("you", 14);
        b.put("today", 15);
        b.put("how", 16);
        for (String i: b.keySet()) {
            System.out.println(b.get(i));
        }
    }
}
