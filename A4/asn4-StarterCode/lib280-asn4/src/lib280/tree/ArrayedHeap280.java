package lib280.tree;

import lib280.base.Dispenser280;
import lib280.exception.ContainerFull280Exception;
import lib280.exception.DuplicateItems280Exception;
import lib280.exception.NoCurrentItem280Exception;

public class ArrayedHeap280<I extends Comparable<? super I>> extends ArrayedBinaryTree280<I> implements Dispenser280<I> {
    /**
     * Constructor.
     *
     * @param cap Maximum number of elements that can be in the lib280.tree.
     */
    public ArrayedHeap280(int cap) {
        super(cap);
    }

    @Override
    public void insert(I x) throws ContainerFull280Exception, DuplicateItems280Exception {

    }

    @Override
    public void deleteItem() throws NoCurrentItem280Exception {

    }
}
