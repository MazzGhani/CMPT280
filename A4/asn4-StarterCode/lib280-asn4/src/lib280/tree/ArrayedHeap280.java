package lib280.tree;
//Mazz Ghani mag151 11252417 CMPT 280
import lib280.base.Dispenser280;
import lib280.exception.ContainerEmpty280Exception;
import lib280.exception.ContainerFull280Exception;

/**
* Heap extends the Binary tree. Array will now be ordered, elemts can be comparable to each other
* */
public class ArrayedHeap280<I extends Comparable<? super I>> extends ArrayedBinaryTree280<I> implements Dispenser280<I> {
    /**
     * Constructor.
     *
     * @param cap Maximum number of elements that can be in the lib280.tree.
     */
    public ArrayedHeap280(int cap) {
        super(cap);
        items= (I[]) new Comparable[capacity+1]; // helps make each item comparable in the item list
    }

    /**
     * Inserts item x
     * @param x item to be inserted
     * @throws ContainerFull280Exception throw an exception if the container is full.
     */
    public void insert(I x) throws ContainerFull280Exception {
        if(this.isFull()){ // throwing just in case its full
            throw new ContainerFull280Exception("Heap has reached its cap, FULL");
        }
        if (this.count==0){ // just in case the counter is 0
            this.currentNode++; // pointer is now the root
        }
        this.count++;
        this.items[count]=x; // we insert on the left first
        this.currentNode=this.count;
        int counter=count;
        int par= findParent(counter);
        // checkign where to insert the newly added item
        while(counter>1 && items[counter].compareTo(items[par])>0){

            I temp =items[par]; // in the left most side
            items[par]=items[counter];
            items[counter]= temp;
        }

    }

    /**
     * Removes the item at the top of the heap.
     *
     * @throws ContainerEmpty280Exception if the heap is empty.
     *
     */
    public void deleteItem() throws ContainerEmpty280Exception {
        if(this.isEmpty()){
            throw new ContainerEmpty280Exception("Impossible to delete item from empty heap");
        }
        if(this.count>1){
            this.currentNode=1;
            this.items[currentNode]= this.items[count];
        }
        this.count--;
        if( this.count == 0) {
            this.currentNode = 0;
            return;
        }
        int n=1;
        while(findLeftChild(n)<=count){
            int child= findLeftChild(n);
            if(child+1 <=count && items[child].compareTo(items[child+1])<0){
                child++;
            }
            if( items[n].compareTo(items[child]) < 0 ) {
                I temp = items[n];
                items[n] = items[child];
                items[child] = temp;
            }
            else return;
        }


    }

//////////////////////////////// GIVEN //////////////////////////////
    /**
     * Helper for the regression test.  Verifies the heap property for all nodes.
     */
    private boolean hasHeapProperty() {
        for(int i=1; i <= count; i++) {
            if( findRightChild(i) <= count ) {  // if i Has two children...
                // ... and i is smaller than either of them, , then the heap property is violated.
                if( items[i].compareTo(items[findRightChild(i)]) < 0 ) return false;
                if( items[i].compareTo(items[findLeftChild(i)]) < 0 ) return false;
            }
            else if( findLeftChild(i) <= count ) {  // if n has one child...
                // ... and i is smaller than it, then the heap property is violated.
                if( items[i].compareTo(items[findLeftChild(i)]) < 0 ) return false;
            }
            else break;  // Neither child exists.  So we're done.
        }
        return true;
    }

    /**
     * Regression test
     */
    public static void main(String[] args) {

        ArrayedHeap280<Integer> H = new ArrayedHeap280<Integer>(10);

        // Empty heap should have the heap property.
        if(!H.hasHeapProperty()) System.out.println("Does not have heap property.");

        // Insert items 1 through 10, checking after each insertion that
        // the heap property is retained, and that the top of the heap is correctly i.
        for(int i = 1; i <= 10; i++) {
            H.insert(i);
            if(H.item() != i) System.out.println("Expected current item to be " + i + ", got " + H.item());
            if(!H.hasHeapProperty()) System.out.println("Does not have heap property.");
        }

        // Remove the elements 10 through 1 from the heap, chekcing
        // after each deletion that the heap property is retained and that
        // the correct item is at the top of the heap.
        for(int i = 10; i >= 1; i--) {
            // Remove the element i.
            H.deleteItem();
            // If we've removed item 1, the heap should be empty.
            if(i==1) {
                if( !H.isEmpty() ) System.out.println("Expected the heap to be empty, but it wasn't.");
            }
            else {
                // Otherwise, the item left at the top of the heap should be equal to i-1.
                if(H.item() != i-1) System.out.println("Expected current item to be " + i + ", got " + H.item());
                if(!H.hasHeapProperty()) System.out.println("Does not have heap property.");
            }
        }

        System.out.println("Regression Test Complete.");
    }
}
