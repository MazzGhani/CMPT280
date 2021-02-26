package lib280.tree;

/**
 * AVL Node that extends the BinaryNode to help keep track of the heights of the Tree
 */
public class AVLNode280<I extends Comparable<? super I>> extends BinaryNode280<I> {

    int height;

    /**
     * Construct a new node with item x.
     *
     * @param x the item placed in the new node
     * @timing Time = O(1)
     */
    public AVLNode280(I x) {
        super(x);
        height=1; // height starts at 1
    }

    /**
     * Gets the left AVL Node of this node
     * @return left node
     */
    public AVLNode280<I> leftNode(){
        return (AVLNode280<I>) super.leftNode();
    }

    /**
     * Gets the right AVL Node of this node
     * @return right node
     */
    public AVLNode280<I> rigthNode(){
        return (AVLNode280<I>) super.rightNode();
    }

    /**
     * Sets the height for the tree, mostly used to change it
     * once the imbalanced is corrected
     * @param x settign the height for the tree
     */
    protected void setHeight(int x){
        this.height=x;
    }
    /**
     * Gets the height of the left side of the tree
     * @return left side height
     */
    protected int getLeftHeight(){
        if(this.leftNode==null){
            return  0;
        }
        return ((AVLNode280<I>) this.leftNode).height;
    }
    /**
     * Gets the height for the right side of the tree
     * @return right side height
     */
    protected int getRightHeight(){
        if(this.rightNode==null){
            return  0;
        }
        return ((AVLNode280<I>) this.rightNode).height;
    }

}