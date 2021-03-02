package lib280.tree;
//Mazz Ghani mag151 11252417 CMPT 280
import lib280.base.Dispenser280;
import lib280.base.Searchable280;
import lib280.exception.*;

/**
 * IMPORTANT---- alot of the functions below are taken from the OrderedSImpleTree
 * the vars below are also taken from the there as well
 * These were taken , mostly to help understand/ make the funcitons
 * needed for the AVL tree balance correction
 * I've tried my best to get the insert/deleteItem to work
 * The same goes for the rotation/double rotation/ Balance function
 * But sadly I couldnt get the insert/ delete method to work,
 * hopefully my other functions make sense if those two functions worked
 */

// To help clarifiy which function I used from other classes
    // There's the @Override , aswell as |**| in the comments

public class AVLTree280<I extends Comparable<? super I>> extends LinkedSimpleTree280<I> implements
        Searchable280<I>, Dispenser280<I>  {

    /**	The current node as set by search. */
    // |**|
    protected BinaryNode280<I> cur;
    // |**|
    /**	The parent node of the current node as set by search. */
    protected BinaryNode280<I> parent;
    // |**|
    /**	Do searches continue?. */
    protected boolean searchesContinue = false;
    // |**|
    /**	Are equality comparisons done using object reference comparisons?. */
    protected boolean objectReferenceComparison = false;

    /**
     * Creating an empty tree
     * */
    public AVLTree280(){
        super();
        this.setRootNode(null);
    }

    @Override  // |**|
    /**
     * Creating aa new node for the tree
     * @param item whats being placed in the new node
     * */
    protected AVLNode280<I> createNewNode(I item) {
        return new AVLNode280<I>(item);
    }

    @Override // |**|
    /**
     * Checking if the tree is empty
     * */
    public boolean isEmpty() {
        return super.isEmpty();
    }

    @Override // |**|
    /**
     * Checking if the tree is full
     * */
    public boolean isFull() {
        return super.isFull();
    }

    @Override // |**|
    /**
     * Contents of the item */
    public I item() throws NoCurrentItem280Exception {
        if(!itemExists()){
            throw new NoCurrentItem280Exception("No Current item");
        }
        return cur.item();
    }

    @Override // |**|
    /**
     * DOes the item exist ? */
    public boolean itemExists() {
        return cur!=null;
    }

    @Override // |**|
    /**
     * Setting the contents of the root into x
     * */
    public void setRootItem(I x) throws ContainerEmpty280Exception {
        super.setRootItem(x);
    }

    @Override // /**/
    /**
     * Delete the current item, */
    public void deleteItem() throws NoCurrentItem280Exception {
        if(!this.itemExists()){ // if there's no tiem , then throw an exception
            throw new NoCurrentItem280Exception("No item here , doesnt exist ERROR");
        }
        AVLNode280<I> cursorParent= (AVLNode280<I>) this.cur;
        AVLNode280<I> cursor=((AVLNode280<I>) this.cur).rigthNode();
        while(cursor!= null && cursor.leftNode()!=null){
            cursorParent=cursor;
            cursor=cursor.leftNode();
        }
        this.cur=cursor;
        this.parent=cursorParent;

    }



    @Override  // |**|
    /**
     * Checks if the tree has the item x
     * @param x the item that we wanna search for */
    public boolean has(I x) {
        // save cursor state and search restart state.
        BinaryNode280<I> saveParent = parent;
        BinaryNode280<I> saveCur = cur;
        boolean saveSearchesContinue = this.searchesContinue;

        // Always start at the root.
        this.parent = null;
        this.cur = this.rootNode;
        this.restartSearches();

        // Search
        this.search(x);
        boolean result = itemExists();

        // Restore cursor state and search restart state.
        this.parent = saveParent;
        this.cur = saveCur;
        this.searchesContinue = saveSearchesContinue;

        return result;
    }

    @Override // |**|
    /**
     * DOes X == to Y , helps to switch the nodes later so we can get the
     * Placement
     * @param x- first item to comapre
     * @param y- second item to compare*/
    public boolean membershipEquals(I x, I y) {
        if (objectReferenceComparison)
            return x == y;
        else if ((x instanceof Comparable) && (y instanceof Comparable))
            return 0 == x.compareTo(y);
        else if (x.equals(y))
            return true;
        else
            return false;
    }

    @Override // |**|
    /**
     * Searching the item in the tree
     * then go to the item we're searching
     * @param x- the item we're searching for */
    public void search(I x) {
        boolean found = false;
        if (!searchesContinue || above())
        {
            parent = null;
            cur = rootNode;
        }
        else if (!below())
        {
            parent = cur;
            cur = cur.rightNode();
        }
        while (!found && itemExists())
        {
            if (x.compareTo(item()) < 0)
            {
                parent = cur;
                cur = parent.leftNode();
            }
            else if (x.compareTo(item()) > 0)
            {
                parent = cur;
                cur = parent.rightNode();
            }
            else
                found = true;
        }
    }

    // |**|
    /**
     * Checking if the current postion is above the root or not in the tree */
    protected boolean above(){
        return (parent == null) && (cur == null);

    }

    // |**|
    /**
     * Checking if the current position is below the root or not in the tree  */
    protected boolean below(){
        return (cur == null) && (parent != null || isEmpty());

    }

    @Override // |**|
    public void restartSearches() {
        searchesContinue = false;
    }

    @Override // |**|
    public void resumeSearches() {
        searchesContinue = true;
    }

    @Override // |**|
    /**
     * Right subtree of the root */
    public LinkedSimpleTree280<I> rootRightSubtree() throws ContainerEmpty280Exception {
        return super.rootRightSubtree();
    }

    @Override // |**|
    /**
     * Left subtree of the root  */
    public LinkedSimpleTree280<I> rootLeftSubtree() throws ContainerEmpty280Exception {
        return super.rootLeftSubtree();
    }
    // |**|
    /**
     * Clearing the tree, blank slate  */
    public void clear(){
        super.clear();
        this.parent=null;
        this.cur=null;
    }

    @Override
    public void insert(I x) throws ContainerFull280Exception, DuplicateItems280Exception {
        this.insert(x, (AVLNode280<I>) this.rootNode());
    }

    private void insert(I x, AVLNode280<I> rootNode) {
        if(rootNode==null){ // if the node is empty, jsut put the item in it
            rootNode= new AVLNode280<I>(x);
        }
        if(rootNode.item==x){ // if the node being isnerted is the same as the item already there
            throw new DuplicateItems280Exception("Ite already exists");
        }
        if(rootNode.item.compareTo(x)>0){ // if x is greater than the current node
            insert(x,rootNode.leftNode()); // put on the left side of the tree
        }
        else{ // else its on the right side
           insert(x,rootNode.rigthNode());
        }
        //getting the height, and changing it function goes here
        // set height / update the height for the tree /////
        restoreAVlProperty(rootNode); // give the balanced version of the tree
    }

    public void delete(I x){
        this.delete(x,this.rootNode());
    }

    private void delete(I x, BinaryNode280<I> rootNode) {
        if(rootNode==null){// if there's nothign to delete
            throw new ItemNotFound280Exception("Error , there's nothign to delete ");
        }
        if(rootNode.item==x){ // if the item is found
            if(rootNode.rightNode==null){ // if the right side has a child
                rootNode.rightNode(); // the current node is the right child of the node
            }
            if (rootNode.leftNode==null){ // same with above but with left
                rootNode.leftNode();
            }
        if(rootNode.item.compareTo(x)>0){} // if x is less than the current node
            delete(x,rootNode.leftNode()); // delete the left side
        }
        else{ // for the right side this time, so same as ^
            delete(x,rootNode.rightNode());
        }
        // updating heights for each sides function goes here
         // Return the balanced tree
    }

    ///////////////// Rotations/ restore / Imbalnce////////////////
    /**
     * Rotating the select nodes to the left
     * Example
     *  A
     *   \      ---->             B
     *    B     L ROTATION       / \
     *     \         --->       A   C
     *      C
     * */
    private AVLNode280<I> rotateLeft(AVLNode280<I> rootNode){
        AVLNode280<I> node = (AVLNode280<I>) rootNode.rightNode(); // right node in "node"
        rootNode.setRightNode(node.leftNode()); //root node's right node is now the left node
        node.setLeftNode(rootNode); // now the right node is the new rott node
        return node;
    }
    /**
     * Rotating the select nodes to the left
     * Example
     *        C
     *      /      ---->            B
     *     B     R ROTATION        / \
     *    /         --->          A   C
     *   A
     * */
    private AVLNode280<I> rotateRight(AVLNode280<I> rootNode){
        AVLNode280<I> node= (AVLNode280<I>) rootNode.leftNode();
        rootNode.setLeftNode(node.rightNode());
        node.setRightNode(rootNode);
        return node;
    }
    /**
     * Formula to determine the imbalance in the tree, then to
     * show the new balanced tree
     * */
    private int bf(AVLNode280<I> node){
        return node.getLeftHeight() - node.getRightHeight();
    }

    private AVLNode280<I> restoreAVlProperty(AVLNode280<I> rootNode){
        if(bf(rootNode)<=1){
            return rootNode;
        }
        if(bf(rootNode)==2){ // it cant be equal to 2, becasue we need a diff of atleast 1 aka left heavy
            if(bf(rootNode.leftNode())>=0){ // if its the left side
                return rotateRight(rootNode); // LL imbalance , so rotate right
            }
            else{ // OR might L -R imbalance
                rootNode.leftNode=rotateLeft(rootNode.leftNode());
            }
        }
        else{// ELSE ittle be right heavy
            if(bf((AVLNode280<I>) rootNode.rightNode())<=0){ // for RR imbalacne
                return rotateLeft(rootNode); // Left rotation
            }
            else{ // OR RL imbalance
                rootNode.rightNode=rotateRight(rootNode.rigthNode()); // right rotation
                return rotateLeft(rootNode);
            }
        }
        return rootNode;
    }


    public static void main(String[] args) {
        /**
         * will for sure come out as an error/nothing comes out,
         * really just proactive of white/black box testing
         * */
        AVLTree280<Integer> Tree= new AVLTree280<Integer>();
        if(Tree.isEmpty()){
            System.out.println("GREAT should be empty, nothing's inserted");
        }
        else{
            System.out.println("ERROR, there shouldn't be anything in here ");
        }

        Tree.insert(10);
        if(Tree.rootNode().item()!=10){
            System.out.println("ERROR , the root should be 10");
        }
        Tree.insert(5);
        Tree.insert(20);
        Tree.insert(2);
        Tree.insert(7);
        Tree.insert(15);
        Tree.insert(42);
        System.out.println("Tree now:");
        System.out.print(Tree);
        System.out.println("----------------------------------------------");
        System.out.println("TESTING DELETE FUNCTION");
        System.out.println("----------------------------------------------");
        Tree.delete(7);
        System.out.println(Tree);
        System.out.println("TESTING CLEAR FUNCTION ");
        Tree.clear();
        if(Tree.isEmpty()){
            System.out.println("Clear function worked");
        }
        else{
            System.out.println("ERROR, clear function did not work ");
        }
        System.out.println("----------------------------------------------");
        System.out.println("TESTING RIGHT ROTATION");
        System.out.println("----------------------------------------------");

        Tree.insert(3);
        Tree.insert(2);
        Tree.insert(1);

        System.out.println("----------------------------------------------");
        System.out.println("TESTING Left ROTATION");
        System.out.println("----------------------------------------------");

        Tree.insert(1);
        Tree.insert(2);
        Tree.insert(3);







    }
}
