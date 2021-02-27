package lib280.tree;
//Mazz Ghani mag151 11252417 CMPT 280
import lib280.base.Dispenser280;
import lib280.base.Searchable280;
import lib280.exception.ContainerEmpty280Exception;
import lib280.exception.ContainerFull280Exception;
import lib280.exception.DuplicateItems280Exception;
import lib280.exception.NoCurrentItem280Exception;

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
     * Constructor for the AVLTree 280*/
    public AVLTree280(){
        super();
        this.setRootNode(null);
    }

    @Override  // |**|
    protected AVLNode280<I> createNewNode(I item) {
        return new AVLNode280<I>(item);
    }

    @Override // |**|
    public boolean isEmpty() {
        return super.isEmpty();
    }

    @Override // |**|
    public boolean isFull() {
        return super.isFull();
    }

    @Override // |**|
    public I item() throws NoCurrentItem280Exception {
        if(!itemExists()){
            throw new NoCurrentItem280Exception("No Current item");
        }
        return cur.item();
    }

    @Override // |**|
    public boolean itemExists() {
        return cur!=null;
    }

    @Override // |**|
    public void setRootItem(I x) throws ContainerEmpty280Exception {
        super.setRootItem(x);
    }

    @Override
    public void deleteItem() throws NoCurrentItem280Exception {
        if(!this.itemExists()){
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
    protected boolean above(){
        return (parent == null) && (cur == null);

    }

    // |**|
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
    public LinkedSimpleTree280<I> rootRightSubtree() throws ContainerEmpty280Exception {
        return super.rootRightSubtree();
    }

    @Override // |**|
    public LinkedSimpleTree280<I> rootLeftSubtree() throws ContainerEmpty280Exception {
        return super.rootLeftSubtree();
    }
    // |**|
    public void clear(){
        super.clear();
        this.parent=null;
        this.cur=null;
    }

    @Override
    public void insert(I x) throws ContainerFull280Exception, DuplicateItems280Exception {
        this.insert(x, (AVLNode280<I>) this.rootNode());
    }

    private void insert(I x, AVLNode280<I> o) {
    }

    public void delete(I x){
        this.delete(x,this.rootNode());
    }

    private void delete(I x, BinaryNode280<I> rootNode) {
    }

    ///////////////// Rotations/ restore / Imbalnce////////////////

    private AVLNode280<I> rotateLeft(AVLNode280<I> rootNode){
        AVLNode280<I> node = (AVLNode280<I>) rootNode.rightNode();
        rootNode.setRightNode(node.leftNode());
        node.setLeftNode(rootNode);
        return node;
    }

    private AVLNode280<I> rotateRight(AVLNode280<I> rootNode){
        AVLNode280<I> node= (AVLNode280<I>) rootNode.leftNode();
        rootNode.setLeftNode(node.rightNode());
        node.setRightNode(rootNode);
        return node;
    }

    private int bf(AVLNode280<I> node){
        return node.getLeftHeight() - node.getRightHeight();
    }

    private AVLNode280<I> restoreAVlProperty(AVLNode280<I> rootNode){
        if(bf(rootNode)<=1){
            return rootNode;
        }
        if(bf(rootNode)==2){
            if(bf(rootNode.leftNode())>=0){
                return rotateRight(rootNode);
            }
            else{
                rootNode.leftNode=rotateLeft(rootNode.leftNode());
            }
        }
        else{
            if(bf((AVLNode280<I>) rootNode.rightNode())<=0){
                return rotateLeft(rootNode);
            }
            else{
                rootNode.rightNode=rotateRight(rootNode.rigthNode());
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

        Tree.insert(10);
        Tree.insert(5);

        System.out.print(Tree);
    }
}
