package lib280.tree;
//Mazz Ghani mag151 11252417 CMPT 280
import lib280.base.Dispenser280;
import lib280.base.Searchable280;



public class AVLTree280<I extends Comparable<? super I>> extends OrderedSimpleTree280<I> implements
        Searchable280<I>, Dispenser280<I>  {


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
