package lib280.tree;
//Mazz Ghani mag151 11252417 CMPT 280
import lib280.base.Dispenser280;
import lib280.base.Searchable280;
import lib280.exception.ContainerEmpty280Exception;
import lib280.exception.ContainerFull280Exception;
import lib280.exception.DuplicateItems280Exception;
import lib280.exception.NoCurrentItem280Exception;


public class AVLTree280<I extends Comparable<? super I>> extends LinkedSimpleTree280<I> implements
        Searchable280<I>, Dispenser280<I>  {

    @Override
    public I item() throws NoCurrentItem280Exception {
        return null;
    }

    @Override
    public boolean itemExists() {
        return false;
    }

    @Override
    public void insert(I x) throws ContainerFull280Exception, DuplicateItems280Exception {

    }

    @Override
    public void deleteItem() throws NoCurrentItem280Exception {

    }

    @Override
    public boolean has(I y) {
        return false;
    }

    @Override
    public boolean membershipEquals(I x, I y) {
        return false;
    }

    @Override
    public void search(I x) {

    }

    @Override
    public void restartSearches() {

    }

    @Override
    public void resumeSearches() {

    }

    public static void main(String[] args) {
        AVLTree280<Integer> Tree= new AVLTree280<Integer>();

        Tree.insert(10);
        Tree.insert(20);
        Tree.insert(30);
        Tree.insert(40);
        Tree.insert(50);
        System.out.print(Tree);
        Tree.insert(15);
        Tree.insert(25);
        Tree.insert(0);
        Tree.insert(15);

    }
}
