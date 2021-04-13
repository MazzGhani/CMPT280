public class LinkedNode<I> {

    protected I item;
    protected LinkedNode<I> nextNode;

    public LinkedNode(I x){
        nextNode=null;
        item= x;
    }

    public I getItem(){
        return this.item;
    }

    public LinkedNode<I> getNextNode(){
        return this.nextNode;
    }

    public void setItem(I x){
        this.item= x;
    }

    public void setNextNode(LinkedNode<I> newNode){
        this.nextNode= newNode;
    }

}
