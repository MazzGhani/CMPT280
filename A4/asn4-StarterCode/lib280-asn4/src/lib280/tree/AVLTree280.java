package lib280.tree;
//Mazz Ghani mag151 11252417 CMPT 280
import lib280.base.Dispenser280;
import lib280.base.Searchable280;
import lib280.exception.ContainerEmpty280Exception;
import lib280.exception.NoCurrentItem280Exception;


public class AVLTree280<I extends Comparable<? super I>> extends OrderedSimpleTree280<I> implements
        Searchable280<I>, Dispenser280<I>  {
    protected BinaryNode280<I> currentNode; // the node the cursor is on

    protected BinaryNode280<I> nextNode; // next in line for the node

    protected BinaryNode280<I> parentNode; // parent node of the current node

    protected BinaryNode280<I> rootNode; // root node, begins the tree

    /**	Do searches continue?. */
    protected boolean searchesContinue= false;
    /**	Are equality comparisons done using object reference comparisons?. */
    protected boolean objectReferenceComparison = false;



    public AVLTree280(){
            super();
            this.rootNode=null;
        }

        public AVLTreeNode280<I> createNode(I x){
            AVLTreeNode280<I> newT = new AVLTreeNode280<I>(x);
            newT.setHeight(0);
            newT.setLHeight(0);
            newT.setRHeight(0);
            return newT;
        }
    @Override
    public boolean isEmpty(){
        return this.rootNode()==null;
       }

    @Override
    public boolean isFull(){
        return false;
       }

    @Override
    public boolean itemExists() {
        return currentNode!=null;
    }

    @Override
    public I item() throws NoCurrentItem280Exception {
        if(!itemExists()){
            throw new NoCurrentItem280Exception("No current item , ERROR");
        }
        return currentNode.item();
    }

    public  void insert(I x){
        this.insert(x,null);
    }

    private void insert(I x, AVLTreeNode280<I> o) {

        if (isEmpty())
            rootNode = createNewNode(x);
        else if (x.compareTo(rootItem()) < 0)
        {
            OrderedSimpleTree280<I> leftTree = rootLeftSubtree();
            leftTree.insert(x);
            setRootLeftSubtree(leftTree);
        }
        else
        {
            OrderedSimpleTree280<I> rightTree = rootRightSubtree();
            rightTree.insert(x);
            setRootRightSubtree(rightTree);
        }
    }

    @Override
    protected boolean below() {
        return (currentNode == null) && (parentNode != null || isEmpty());
    }

    @Override
    protected boolean above() {
        return (parentNode == null) && (currentNode == null);
    }

    @Override
    public OrderedSimpleTree280<I> rootLeftSubtree() throws ContainerEmpty280Exception {
        return super.rootLeftSubtree();
    }

    @Override
    public OrderedSimpleTree280<I> rootRightSubtree() throws ContainerEmpty280Exception {
        return super.rootRightSubtree();
    }

    @Override
    public void search(I x)
    {
        boolean found = false;
        if (!searchesContinue || above())
        {
            parentNode = null;
            currentNode = rootNode;
        }
        else if (!below())
        {
            parentNode = currentNode;
            currentNode = currentNode.rightNode();
        }
        while (!found && itemExists())
        {
            if (x.compareTo(item()) < 0)
            {
                parentNode = currentNode;
                currentNode = parent.leftNode();
            }
            else if (x.compareTo(item()) > 0)
            {
                parentNode = currentNode;
                currentNode = parentNode.rightNode();
            }
            else
                found = true;
        }
    }

    @Override
    public boolean has(I x) {
        // save cursor state and search restart state.
        BinaryNode280<I> saveParent = parentNode;
        BinaryNode280<I> saveCur = currentNode;
        boolean saveSearchesContinue = this.searchesContinue;

        // Always start at the root.
        this.parentNode = null;
        this.currentNode = this.rootNode;
        this.restartSearches();

        // Search
        this.search(x);
        boolean result = itemExists();

        // Restore cursor state and search restart state.
        this.parentNode = saveParent;
        this.currentNode = saveCur;
        this.searchesContinue = saveSearchesContinue;

        return result;
    }

    @Override
    public void restartSearches(){
        searchesContinue=false;
    }

    @Override
    public void resumeSearches(){
        searchesContinue=true;
    }
    @Override
    public boolean membershipEquals(I x, I y)
    {
        if (objectReferenceComparison)
            return x == y;
        else if ((x instanceof Comparable) && (y instanceof Comparable))
            return 0 == x.compareTo(y);
        else if (x.equals(y))
            return true;
        else
            return false;
    }

    public static void main(String[] args) {
        AVLTree280<Integer> Tree= new AVLTree280<Integer>();

        Tree.insert(10);
        Tree.insert(20);
        Tree.insert(30);
        Tree.insert(40);
        Tree.insert(50);
        System.out.println(Tree);
        Tree.insert(15);
        Tree.insert(25);
        Tree.insert(0);
        Tree.insert(15);



    }
}
