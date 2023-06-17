package prodavnica;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

class MapEntry<K extends Comparable<K>,E> implements Comparable<K> {

    // Each MapEntry object is a pair consisting of a key (a Comparable
    // object) and a value (an arbitrary object).
    K key;
    E value;

    public MapEntry (K key, E val) {
        this.key = key;
        this.value = val;
    }

    public int compareTo (K that) {
        // Compare this map entry to that map entry.
        @SuppressWarnings("unchecked")
        MapEntry<K,E> other = (MapEntry<K,E>) that;
        return this.key.compareTo(other.key);
    }

    public String toString () {
        return "<" + key + "," + value + ">";
    }
}
class SLLNode<E> {
    protected E element;
    protected SLLNode<E> succ;

    public SLLNode(E elem, SLLNode<E> succ) {
        this.element = elem;
        this.succ = succ;
    }

    @Override
    public String toString() {
        return element.toString();
    }
}

class Array<E> {
    private E data[]; // declared to be an Object since it would be too
    // complicated with generics
    private int size;

    public Array(int capacity) {
        this.data = (E[]) new Object[capacity];
        this.size = 0;
    }

    public void insertLast(E o) {
        //check if there is enough capacity, and if not - resize
        if(size + 1 > data.length)
            this.resize();
        data[size++] = o;
    }

    public void insert(int position, E o) {
        // before all we check if position is within range
        if (position >= 0 && position <= size) {
            //check if there is enough capacity, and if not - resize
            if(size + 1 > data.length)
                this.resize();
            //copy the data, before doing the insertion
            for(int i=size;i>position;i--) {
                data[i] = data[i-1];
            }
            data[position] = o;
            size++;
        } else {
            System.out.println("Ne mozhe da se vmetne element na taa pozicija");
        }
    }

    public void set(int position, E o) {
        if (position >= 0 && position < size)
            data[position] = o;
        else
            System.out.println("Ne moze da se vmetne element na dadenata pozicija");
    }

    public E get(int position) {
        if (position >= 0 && position < size)
            return data[position];
        else
            System.out.println("Ne e validna dadenata pozicija");
        return null;
    }

    public int find(E o) {
        for (int i = 0; i < size; i++) {
            if(o.equals(data[i]))
                return i;
        }
        return -1;
    }

    public int getSize() {
        return size;
    }

    public void delete(int position) {
        // before all we check if position is within range
        if (position >= 0 && position < size) {
            // first resize the storage array
            E[] newData = (E[]) new Object[size - 1];
            // copy the data prior to the delition
            for (int i = 0; i < position; i++)
                newData[i] = data[i];
            // move the data after the deletion
            for (int i = position + 1; i < size; i++)
                newData[i - 1] = data[i];
            // replace the storage with the new array
            data = newData;
            size--;
        }
    }

    public void resize() {
        // first resize the storage array
        E[] newData = (E[]) new Object[size*2];
        // copy the data
        for (int i = 0; i < size; i++)
            newData[i] = data[i];
        // replace the storage with the new array
        this.data = newData;
    }

    @Override
    public String toString() {
        String ret = new String();
        if(size>0) {
            ret = "{";
            ret += data[0];
            for(int i=1;i<size;i++) {
                ret += "," + data[i];
            }
            ret+="}";
            return ret;
        }
        else {
            ret = "Prazna niza!";
        }
        return ret;
    }

}
class CBHT<K extends Comparable<K>, E> {

    // An object of class CBHT is a closed-bucket hash table, containing
    // entries of class MapEntry.
    private SLLNode<MapEntry<K,E>>[] buckets;

    @SuppressWarnings("unchecked")
    public CBHT(int m) {
        // Construct an empty CBHT with m buckets.
        buckets = (SLLNode<MapEntry<K,E>>[]) new SLLNode[m];
    }

    private int hash(K key) {
        // Translate key to an index of the array buckets.
        return Math.abs(key.hashCode()) % buckets.length;
    }

    public SLLNode<MapEntry<K,E>> search(K targetKey) {
        // Find which if any node of this CBHT contains an entry whose key is
        // equal
        // to targetKey. Return a link to that node (or null if there is none).
        int b = hash(targetKey);
        for (SLLNode<MapEntry<K,E>> curr = buckets[b]; curr != null; curr = curr.succ) {
            if (targetKey.equals(((MapEntry<K, E>) curr.element).key))
                return curr;
        }
        return null;
    }

    public void insert(K key, E val) {		// Insert the entry <key, val> into this CBHT.
        MapEntry<K, E> newEntry = new MapEntry<K, E>(key, val);
        int b = hash(key);
        for (SLLNode<MapEntry<K,E>> curr = buckets[b]; curr != null; curr = curr.succ) {
            if (key.equals(((MapEntry<K, E>) curr.element).key)) {
                // Make newEntry replace the existing entry ...
                curr.element = newEntry;
                return;
            }
        }
        // Insert newEntry at the front of the 1WLL in bucket b ...
        buckets[b] = new SLLNode<MapEntry<K,E>>(newEntry, buckets[b]);
    }

    public void delete(K key) {
        // Delete the entry (if any) whose key is equal to key from this CBHT.
        int b = hash(key);
        for (SLLNode<MapEntry<K,E>> pred = null, curr = buckets[b]; curr != null; pred = curr, curr = curr.succ) {
            if (key.equals(((MapEntry<K,E>) curr.element).key)) {
                if (pred == null)
                    buckets[b] = curr.succ;
                else
                    pred.succ = curr.succ;
                return;
            }
        }
    }

    public String toString() {
        String temp = "";
        for (int i = 0; i < buckets.length; i++) {
            temp += i + ":";
            for (SLLNode<MapEntry<K,E>> curr = buckets[i]; curr != null; curr = curr.succ) {
                temp += curr.element.toString() + " ";
            }
            temp += "\n";
        }
        return temp;
    }

}

class Product
{
    String tezhina;
    int cena;
    int kolicina;

    public Product(String tezhina, int cena, int kolicina) {
        this.tezhina = tezhina;
        this.cena = cena;
        this.kolicina = kolicina;
    }

    public Product(int kolicina) {
        this.tezhina = null;
        this.cena = 0;
        this.kolicina = kolicina;
    }

    public int getKolicina() {
        return kolicina;
    }

    public String getTezhina() {
        return tezhina;
    }

    public int getCena() {
        return cena;
    }

    @Override
    public String toString() {
        return String.format("%s %d %d",tezhina,cena,kolicina);
    }
}

public class Prodavnica {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        CBHT<String, Array<Product>> tabela = new CBHT<>(2*N);
        for(int i = 0; i < N; i++)
        {
            String line = br.readLine();
            String [] parts = line.split(" ");
            Product product = new Product(parts[1],Integer.valueOf(parts[2]),Integer.valueOf(parts[3]));
            tabela.insert(parts[0],new Array<>(N));
            tabela.search(parts[0]).element.value.insertLast(product);
        }
        int M = Integer.parseInt(br.readLine());
        for(int j = 0; j < M; j++)
        {
            String line = br.readLine();
            String [] parts = line.split(" ");
            Product product = new Product(parts[1],Integer.valueOf(parts[2]),Integer.valueOf(parts[3]));
            tabela.search(parts[0]).element.value.insertLast(product);
        }

        String line = br.readLine();
        while(!line.equals("TRGOVECKRAJ"))
        {
            String [] parts = line.split(" ");
            Product product = new Product(Integer.valueOf(parts[1]));
            int nova = 0;
            String finalTezina = " ";
            int finalPrice = 0;
            int updated = 0;
            if(tabela.search(parts[0]) != null) {
                while (tabela.search(parts[0]).element.value.getSize() != 0) {
                    nova += tabela.search(parts[0]).element.value.get(0).getKolicina();
                    finalTezina = tabela.search(parts[0]).element.value.get(0).getTezhina();
                    finalPrice = tabela.search(parts[0]).element.value.get(0).getCena();
                    tabela.search(parts[0]).element.value.delete(0);
                }
                if (Integer.valueOf(parts[1]) > nova) {
                    updated = 0;
                } else {
                    updated = nova - Integer.valueOf(parts[1]);
                }
                Product finalProduct = new Product(finalTezina, finalPrice, updated);
                tabela.insert(parts[0], new Array<>(N));
                tabela.search(parts[0]).element.value.insertLast(finalProduct);
                int vkupno = Integer.parseInt(parts[1]) * tabela.search(parts[0]).element.value.get(0).getCena();

                System.out.printf("Kupeno: %d parcinja %s za kupna cena od %d\nPreostanuvaat uste %d parcinja\n",updated!=0 ? nova-updated: nova,parts[0],vkupno,tabela.search(parts[0]).element.value.get(0).getKolicina());

            }
            line = br.readLine();
        }
    }
}
