public class BilinkedNode<I> extends LinkedNode<I> {

    protected BilinkedNode<I> previousNode;
    protected LinkedNode<I> tail;

    public BilinkedNode(I x) {
        super(x);
        this.previousNode=null;
        this.tail=null;
    }

    public BilinkedNode<I> previousNode(){
        return this.previousNode;
    }

    public void setPreviousNode(BilinkedNode<I> x){
        this.previousNode= x;
    }


}
