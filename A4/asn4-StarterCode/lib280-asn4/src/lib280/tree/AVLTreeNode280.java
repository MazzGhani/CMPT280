package lib280.tree;

public class AVLTreeNode280<I extends Comparable<? super I>> extends BinaryNode280 {


    protected int key, height, lHeight,rHeight;

    /**
     * Construct a new node with item x.
     *
     * @param x the item placed in the new node
     * @timing Time = O(1)
     */
    public AVLTreeNode280(Comparable x) {
        super(x);
    }

    public int getHeight() {
        return this.height;
    }

    public void setHeight(int height){
        this.height= height;
    }

    public int getlHeight(){
        return this.lHeight;
    }

    public void setLHeight(int Lheight){
        this.lHeight= Lheight;
    }


    public int getrHeight() {
        return rHeight;
    }

    public void setRHeight(int Rheight){
        this.rHeight= Rheight;
    }

    public AVLTreeNode280<I> leftNode() {
        return (AVLTreeNode280<I>)super.leftNode();
    }

    public AVLTreeNode280<I> rightNode() {
        return (AVLTreeNode280<I>) super.rightNode();
    }
}
