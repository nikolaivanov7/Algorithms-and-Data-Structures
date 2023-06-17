package dedoMraz;

/*Дедо Мраз преку целата година води список од деца кои биле добри и нивните адреси за да им
        достави подароци. Така е и со децата од Скопје, арно ама градот Скопје решил да менува називи на
        улици и Дедо Мраз во последен момент добива листа од изменети називи на улици за Скопје, Македонија.
        Проверете за дадено дете дали Дедо Мраз треба да му достави подарок (дали го има детето во списокот на добри деца)
        и ако треба на која адреса ќе му го достави. Дополнително за децата од Скопје да се испечати новата адреса доколку
        името на улицата е променето.

        Влез: Во првата линија е даден број N на деца кои биле добри. Во наредните N линии се
        дадени името на детете и неговата адреса во посебни редови (Адресата е во формат ИмеНаУлица Број Град Држава).
        Потоа е даден број M на улици од Скопје кои го промениле своите име. Во наредните M линии дадени прво старите па
        новите имиња на улици разделени со празно место. Во последниот ред е дадено името на детете кое треба да се провери.

        Излез: Ако даденото дете не било добро (т.е. го нема во списокот на добри деца)
        да се испечати Nema poklon, а ако било добро да се испечати валидната адреса на која ќе се достави поклонот
        (т.е. ако името на улицата е од Скопје, Македонија и се променило, да се испечати адресата со
        новиот назив на улицата).
Input:
5
JohnDoe
DriveAvenue 231 NewYork USA
JaneDoe
GreenStreet 821 Chicago USA
PecePecevski
Vodnjanska 30 Skopje Macedonia
MartaMartevska
Vostanichka 81 Prilep Macedonia
EstebanPerez
InsurgentesSur 7325 MexicoCity Mexico
5
MarshalTito Makedonija
Vodnjanska MajkaTereza
Leninova Aminta3
MajkaTereza AdolfCiborovski
Vojvodina KiroGligorov
PecePecevski
Output:
MajkaTereza 30 Skopje Macedonia
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

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

public class DedoMraz
{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        CBHT<String,Array<Ulica>> namesWithStreets = new CBHT<>(2*N);
        for(int i = 0; i < N; i++)
        {
            String ime = br.readLine();
            String line = br.readLine();
            String [] parts = line.split(" ");
            Ulica ulica = new Ulica(parts[0],Integer.valueOf(parts[1]),parts[2],parts[3]);
            namesWithStreets.insert(ime,new Array<>(N));
            namesWithStreets.search(ime).element.value.insertLast(ulica);
        }
        int M = Integer.parseInt(br.readLine());
        CBHT<String,String> changedStreets = new CBHT<>(2*M);
        for(int i = 0; i < M; i++)
        {
            String street = br.readLine();
            String [] part = street.split(" ");
            changedStreets.insert(part[0],part[1]);
        }
        String name = br.readLine();
        if(namesWithStreets.search(name) != null)
        {
            if(changedStreets.search(namesWithStreets.search(name).element.value.get(0).getIme()) != null){
            System.out.printf("%s %s",changedStreets.search(namesWithStreets.search(name).element.value.get(0).getIme()).element.value,namesWithStreets.search(name).element.value.get(0).toString());
        }
        }
        else {
            System.out.println("Nema poklon");
        }
    }
}
class Ulica
{
    String ime;
    int broj;
    String grad;
    String drzhava;

    public Ulica(String ime, int broj, String grad, String drzhava) {
        this.ime = ime;
        this.broj = broj;
        this.grad = grad;
        this.drzhava = drzhava;
    }

    public String getIme() {
        return ime;
    }

    public int getBroj() {
        return broj;
    }

    public String getGrad() {
        return grad;
    }

    public String getDrzhava() {
        return drzhava;
    }

    @Override
    public String toString() {
        return String.format("%d %s %s",broj,grad,drzhava);
    }
}
