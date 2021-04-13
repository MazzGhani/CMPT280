public interface ListADT<I> {
     void insertFirst(I x);

     I deleteFirst();

     I firstItem();

     boolean isEmpty();

     boolean isFull();



}
