
public class LinkedList<I> implements Cursor<I> {
    protected LinkedNode<I> head;
    protected int numEl;
    protected I item;
    protected LinkedNode<I> position;
    protected LinkedNode<I> prevPosition;

    public LinkedList(){
        this.head=null;
        this.numEl=0;
        this.position=null;
        this.prevPosition=null;
    }

    public boolean isEmpty(){
        return this.head==null;
    }


    public boolean isFull(){
        return false;
    }


    public void insertFirst(I x){
        LinkedNode<I> newItem= new LinkedNode<I>(x);
        newItem.setNextNode(this.head);
        this.head=newItem;
        this.numEl++;
    }


    public I deleteFirst(){
        if(this.isEmpty()){
            throw new RuntimeException("EMPTY");
        }
        LinkedNode<I> oldhead=this.head;
        this.head= oldhead.getNextNode();
        oldhead.setNextNode(null);
        this.numEl--;
        return oldhead.getItem();
    }


    public I firstItem(){
        if(this.isEmpty()){
            throw new RuntimeException("EMPTY");
        }
        return this.head.getItem();
    }

    public boolean itemExists(){
        return this.position!= null;
    }

    public I item(){
        if(!itemExists()){
            throw new RuntimeException("EMPTYY"); }

        return this.position.getItem();
    }

    public void goFirst(){
        if(this.isEmpty()){
            throw new RuntimeException("EMPTYY"); }
        this.position= this.head;
        this.prevPosition=null;
    }

    public void goForth(){
        if(after()){
            throw new RuntimeException("EMPTYY"); }
        if(after()){
            goFirst();
        }
        else{
            this.prevPosition=this.position;
            this.position=this.position.getNextNode();
        }
    }


    @Override
    public void goLast() throws RuntimeException {

    }


    @Override
    public boolean before() {
        return (this.prevPosition==null)&& (this.position==null);
    }

    @Override
    public boolean after() {
        return (this.position==null) && (this.prevPosition!=null)|| this.isEmpty();
    }

    public static void main(String[] args){
        LinkedList<Double> L = new LinkedList<Double>();
        Double x;

        for(int i=0;i<5;i++){
            L.insertFirst(Math.random());
        }
        L.goFirst();
        System.out.println("");
        while(L.itemExists()){
            System.out.println(L.item() + " ");
            L.goForth();
        }
    }
}
