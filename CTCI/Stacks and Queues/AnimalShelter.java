// Noah Park
/*

Problem: An animal shelter, which holds only dogs and cats, operates on a
strictly "first in, first out" basis. People must adopt either the "oldest"
(based on arrival time) of all animals at the shelter, or they can select whether
they would prefer a dog or a cat (and will receive the oldest animal of that type).
They cannot select which specific animal they would like. Create the data structures
to maintain this system and implement operations such as enqueue, dequeueAny, dequeueDog,
and dequeueCat. You may use the built-in LinkedList data structure.

*/

import java.util.LinkedList;

public class AnimalShelter {

    // Queue to store the animals
    // Realistically should store an animal class instead of a string
    // I was really tired though
    private LinkedList<String> q;

    // Initialize an empty queue
    public AnimalShelter(){
        q = new LinkedList<>();
    }

    // Add the animal to the front
    public void enqueue(String animal){
        q.addFirst(animal);
    }

    // Remove the last (oldest) element
    public String dequeueAny(){
        return q.removeLast();
    }

    // Remove the last instance of a dog by iterating through
    // the queue and finding where the last index is and calling
    // remove on that index
    public String dequeueDog(){
        String s = null;
        int i = 0;
        int index = 0;
        for(String animal : q){
            if(animal.equals("dog")){
                s = animal;
                index = i;
            }
            i++;
        }
        q.remove(index);
        return s;
    }

    // Remove the last instance of a cat by iterating through
    // the queue and finding where the last index is and calling
    // remove on that index
    public String dequeueCat(){
        String s = null;
        int i = 0;
        int index = 0;
        for(String animal : q){
            if(animal.equals("cat")){
                s = animal;
                index = i;
            }
            i++;
        }
        q.remove(index);
        return s;
    }

    // Prints the queue out for debugging purposes
    public String toString(){
        String s = "";
        for(String i : q){
            s += i;
            s += " -> ";
        }
        s += "END";
        return s;
    }

    // Basic testing of the AnimalShelter class
    public static void main(String[] args){
        AnimalShelter a = new AnimalShelter();
        for(int i = 0; i < 10; i++){
            if(i % 2 == 0){
                a.enqueue("dog");
            }
            else{
                a.enqueue("cat");
            }
        }
        System.out.println(a);
        System.out.println(a.dequeueAny());
        System.out.println(a);
        System.out.println(a.dequeueAny());
        System.out.println(a);
        System.out.println(a.dequeueCat());
        System.out.println(a);
        System.out.println(a.dequeueDog());
        System.out.println(a);
    }

}
