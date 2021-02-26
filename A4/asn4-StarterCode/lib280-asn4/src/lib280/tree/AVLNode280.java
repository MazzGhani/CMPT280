package lib280.tree;

/**
 * AVL Node that extends the BinaryNode to help keep track of the heights of the Tree
 */
public class AVLNode280<I extends Comparable<? super I>> extends BinaryNode280<I> {
    protected int height; // general height for the tree

    /**
     * Creating the Node
     * @param x this is stored in the tree
     */
    public AVLNode280(I x) {
        super(x);
        height = 1; // bigger then 0 so starts at 1
    }

    /**
     * Set height of the current node
     * @param x new height
     */
    protected void setHeight(int x) {
        this.height = x;
    }

    /**
     * Gets the left height of the root node
     * @return height of the left sub node
     */
    protected int getLHeight() {
        if (this.leftNode == null) {
            return 0;
        }
        return ((AVLNode280<I>) this.leftNode).height;
    }

    /**
     * gets the right height of the root node
     * @return height of right sub node
     */
    protected int getRHeight() {
        if (this.rightNode == null) {
            return 0;
        }
        return ((AVLNode280<I>) this.rightNode).height;
    }

    /**
     * Gets the left AVL Node of this node
     * @return left node
     */
    @Override
    public AVLNode280<I> leftNode() {
        return (AVLNode280<I>) super.leftNode();
    }

    /**
     * gets the right node of this avl node
     * @return right avl node
     */
    @Override
    public AVLNode280<I> rightNode() {
        return (AVLNode280<I>) super.rightNode();
    }

    /**
     * Modified to string method to display information regarding the current node's height
     * left height and right heights
     * @return String containing crucial information regarding the node to be printedB
     */
    @Override
    public String toString() {
        return "Item: " + this.item + "  | Height: " + this.height + " | Left Height: " +
                this.getLHeight() + " | Right Height: " + this.getRHeight();
    }
}