public class BSTSetTest{

    public static void main(String[] args)
    {
        int[] arr = {6,1,2,3,7,32,53,20};
        int[] brr = {1,3};
        //int[] arr = {};
        BSTSet a = new BSTSet(arr);
        BSTSet b = new BSTSet(brr);
        //a.printBSTSet();
        //a.printf("----------------------");
        //System.out.println(a.isIn(-1));
        //a.printTree();
        //a.printBSTSet();
        //a.remove(1);
        BSTSet u = a.union(b);
        BSTSet i = a.intersection(b);
        BSTSet d = a.difference(b);
        int height = a.height();
        int size = a.size();
        System.out.printf("u: ");u.printBSTSet();
        System.out.printf("i: ");i.printBSTSet();
        System.out.printf("d: ");d.printBSTSet();
        System.out.printf("height: " + height + "\n");
        System.out.printf("size: " + size + "\n");
        a.printNonRec();
        //a.getParent(2);
        //a.printTree();
        //a.printBSTSet();
    }
}


// cant union with nothing and we need to copy the other tree if we have nothing in the current tree
