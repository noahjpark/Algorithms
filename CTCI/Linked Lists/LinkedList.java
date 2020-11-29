public class LinkedList<T extends Comparable<T>> implements List<T> {

    private Node<T> head;
    boolean isSorted;

    public LinkedList(){
        this.head = null;
        this.isSorted = true;
    }

    public boolean add(T element){
        if(element == null){
            return false;
        }
        if(this.head == null){
            this.head = new Node<>(element, null);
        }
        else{
            Node<T> cur = this.head;
            while(cur.getNext() != null){
                cur = cur.getNext();
            }
            cur.setNext(new Node<>(element, null));
        }

        this.isSorted = false;
        return true;
    }

    public boolean add(int index, T element){
        if(element == null || index < 0 || index >= this.size()){
            return false;
        }
        if(index == 0){
            // 0 -> 1 -> 2 -> null
            Node<T> newHead = this.head; // newHead = 0 -> 1 -> 2 -> null
            this.head = new Node<>(element, newHead); // 5 -> 0 -> 1 -> 2 -> null
        }
        else {
            //     p     c
            // 0 -> 1 -> 5 -> 2 -> null
            Node<T> cur = this.head;
            Node<T> prev = cur;
            int i = 0;
            while (i != index) {
                prev = cur;
                cur = cur.getNext();
                i++;
            }
            prev.setNext(new Node<>(element, cur));
        }
        this.isSorted = false;
        return true;
    }

    public void clear(){
        if(this.head != null) {
            this.head.setNext(null);
            this.head = null;
            this.isSorted = true;
        }
    }

    public boolean contains(T element){
        if(element == null){
            return false;
        }
        if(!this.isSorted){
            Node<T> cur = this.head;
            while(cur != null){
                if(cur.getData().compareTo(element) == 0){
                    return true;
                }
                cur = cur.getNext();
            }
        }

        else {
            Node<T> cur = this.head;
            while (cur.getData().compareTo(element) < 0) {
                cur = cur.getNext();
            }
            if (cur.getData().compareTo(element) == 0) {
                return true;
            }
        }

        return false;
    }

    public T get(int index){
        if(index < 0 || index >= this.size()){
            return null;
        }

        int i = 0;
        Node<T> cur = this.head;
        while(i < index){
            cur = cur.getNext();
            i++;
        }
        return cur.getData();
    }

    public int indexOf(T element){
        if(element == null || !this.contains(element)){
            return -1;
        }

        int i = 0;

        if(!this.isSorted){
            Node<T> cur = this.head;
            while(cur != null){
                if(cur.getData().compareTo(element) == 0){
                    break;
                }
                cur = cur.getNext();
                i++;
            }
        }

        else {
            Node<T> cur = this.head;
            while (cur.getData().compareTo(element) < 0) {
                cur = cur.getNext();
                i++;
            }
        }

        return i;
    }

    public boolean isEmpty(){
        if(this.head == null){
            return true;
        }
        return false;
    }

    public int lastIndexOf(T element){
        if(element == null || !this.contains(element)){
            return -1;
        }

        int i = 0;
        int j = 0;

        if(!this.isSorted){
            Node<T> cur = this.head;
            while(cur != null){
                if(cur.getData().compareTo(element) == 0){
                    j = i;
                }
                cur = cur.getNext();
                i++;
            }
            return j;
        }

        else {
            Node<T> cur = this.head;
            while (cur.getData().compareTo(element) < 0) {
                cur = cur.getNext();
                i++;
            }
            while(cur.getNext().getData().compareTo(element) == 0){
                cur = cur.getNext();
                i++;
            }
        }

        return i;

    }

    public T set(int index, T element){
        if(element == null || index < 0 || index >= this.size()){
            return null;
        }

        Node<T> cur = this.head;
        int i = 0;
        while(i < index){
            cur = cur.getNext();
            i++;
        }
        T target = cur.getData();
        cur.setData(element);

        return target;
    }

    public int size(){
        if(this.head == null){
            return 0;
        }
        int count = 0;
        Node<T> cur = this.head;
        while(cur != null){
            count++;
            cur = cur.getNext();
        }
        return count;
    }

    public void sort(boolean order){
        if(this.head == null || this.head.getNext() == null){
            this.isSorted = true;
            return;
        }

        // not sorted and descending
        if(!this.isSorted && !order){
            Node<T> cur;
            Node<T> prev = this.head;
            while(prev.getNext() != null){
                cur = prev.getNext();
                while(cur != null){
                    if(cur.getData().compareTo(prev.getData()) > 0){
                        T temp = prev.getData();
                        prev.setData(cur.getData());
                        cur.setData(temp);
                    }
                    cur = cur.getNext();
                }
                prev = prev.getNext();
            }
        }

        // not sorted and ascending
        else if(!this.isSorted && order){
            Node<T> cur;
            Node<T> prev = this.head;
            while(prev.getNext() != null){
                cur = prev.getNext();
                while(cur != null){
                    if(cur.getData().compareTo(prev.getData()) < 0){
                        T temp = prev.getData();
                        prev.setData(cur.getData());
                        cur.setData(temp);
                    }
                    cur = cur.getNext();
                }
                prev = prev.getNext();
            }
            this.isSorted = true;
        }

        // sorted and descending
        else if(this.isSorted && !order){
            //                     prev   cur    next
            //null <-  0  <-  1  <-  2   <-  3    null
            Node<T> cur = this.head;
            Node<T> prev = null;
            Node<T> next;
            while(cur != null){
                next = cur.getNext();
                cur.setNext(prev);
                prev = cur;
                cur = next;
            }
            this.head = prev;
            this.isSorted = false;
        }
    }

    public boolean remove(T element){
        if(element == null || !this.contains(element)){
            return false;
        }

        if(this.head.getData().compareTo(element) == 0){
            this.head = this.head.getNext();
        }

        else {
            Node<T> cur = this.head;
            Node<T> prev = cur;
            while (cur != null) {
                if (cur.getData().compareTo(element) == 0) {
                    prev.setNext(cur.getNext());
                    break;
                }
                prev = cur;
                cur = cur.getNext();
            }
        }

        return true;
    }

    public T remove(int index){
        if(index < 0 || index >= this.size()){
            return null;
        }

        T target = null;

        if(index == 0){
            target = this.head.getData();
            head = head.getNext();
        }

        else{
            Node<T> cur = this.head;
            Node<T> prev = cur;
            int i = 0;
            while(cur != null){
                if(i == index){
                    prev.setNext(cur.getNext());
                    target = cur.getData();
                    break;
                }
                prev = cur;
                cur = cur.getNext();
                i++;
            }
        }

        return target;
    }

    public String toString(){
        Node<T> cur = this.head;
        String s = "";
        int i = 1;
        while(cur != null){
            s += ("Element " + i + ": " + cur.getData() + "\n");
            cur = cur.getNext();
            i++;
        }
        return s;
    }

    public static void main(String[] args){
        LinkedList<String> l = new LinkedList<>();
        l.add("delta");
        l.add("alpha");
        l.add("zeta");
        l.add("aardvark");
        System.out.println(l);

        LinkedList<Double> l2 = new LinkedList<>();
        l2.add(0.5);
        l2.add(1.2);
        l2.add(3.5);
        System.out.println(l2);
        l2.isSorted = true;
        l2.sort(false);
        System.out.println(l2);
    }

}
