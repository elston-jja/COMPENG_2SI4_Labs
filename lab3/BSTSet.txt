public class BSTSet{

    private TNode root;
    
    public BSTSet()
    {
        this.root = null;
    }

    public BSTSet(int[] input)
    {
        for(int i = 0; i < input.length; i++)
        {
            this.add(input[i]);
        }
        
    }

    // add node
    public void add(int v)
    {
        TNode node = root;
        TNode parent = null;
        if(root == null)
            root = new TNode(v, null, null);
        else
            if(!isIn(v))
            {
                {
                    while(node != null)
                    {
                        parent = node;
                        if(v < node.element)
                            node = node.left;
                        else node = node.right;
                    }
                    if(v < parent.element)
                    {
                        TNode lNode = new TNode(v,null,null);
                        parent.left = lNode;
                    }
                    else
                    {
                        TNode rNode = new TNode(v, null,null);
                        parent.right = rNode;
                    }
                    
                }
            }
    }

    // remove node
    public boolean remove(int v)
    {
        TNode parent, replace, node = root;
        int t;

        if(root == null)
            return false;

        node = find(v);  // returns reference to node

        if(node == null) // could not find node in tree
        {
            printf("Could not find " + v + " in tree");
            return false;
        }

        parent = getParent(v);

        if(parent == null) // root case
        {
            if(node.left == null)
            {
                root = node.right;
                return true;
            }
            else if(node.right == null)
            {
                root = node.left;
                return true;
            }
            else if(node.right != null && node.left != null)               
            { 
                replace = findMinSubtree(node.right);
                parent  = getParent(replace.element);
                switchElements(replace, root);
                parent.left = replace.right;
                
            }
            else
                System.out.println("You should not reach here!");
        }
        else
        {        
            if(node.left == null)
            {
                if(node.element >= parent.element)
                {
                    parent.right = node.right;
                }
                else
                    parent.left = node.right;
            
            }
            else if(node.right == null)
            {
        
                if(node.element >= parent.element)
                    parent.right = node.right;
                else
                    parent.left = node.left;
            }
            else
            {
                replace = findMinSubtree(node.right);  // find the node to replace the value
                
                parent = getParent(replace.element);
                printf("Parent of " + replace.element + " is " + parent.element);

                if(parent == node)
                {
                    parent = getParent(node.element);
                    if(parent.element < node.element)
                    {
                        parent.right = node.right;
                        node.right.left = node.left;
                    }
                    else
                    {
                        parent.left = node.right;
                        node.right.left = node.left;
                    }
                    
                    return true;
                }
                
                switchElements(node, replace); //switch the values
                parent.left = replace.right;
            }
        }
        return true;
    }


    // join two sets
    public BSTSet union(BSTSet m)
    {
        BSTSet ret = new BSTSet();
        addTrees(m.root, ret);
        addTrees(this.root, ret);
        return ret;
    }

    // recursively join the trees
    private void addTrees(TNode curr, BSTSet newSet)
    {
        if(curr != null)
        {
            newSet.add(curr.element);
            addTrees(curr.left, newSet);
            addTrees(curr.right, newSet);
        }
    }

    // get the set of elements that exist in both sets
    public BSTSet intersection(BSTSet m)
    {
        BSTSet ret = new BSTSet();
        intersect(m.root, ret, m, this);
        intersect(this.root, ret, m, this);

        return ret;
    }

    // recersively find the elements that are in both sets and add them to the final set
    private void intersect(TNode curr, BSTSet newSet, BSTSet set1, BSTSet set2)
    {
        if(curr != null)
        {
    
            
            if(set1.isIn(curr.element) && set2.isIn(curr.element))
                newSet.add(curr.element);

            intersect(curr.left, newSet, set1, set2);
            intersect(curr.right, newSet, set1, set2);
        }
        
    }

    // subtract two different sets
    public BSTSet difference(BSTSet m)          
    {
        BSTSet ret = new BSTSet();
        diff(this.root, ret, this, m);

        return ret;
    }


    // recursively find the difference
    private void diff(TNode curr, BSTSet newSet, BSTSet set1, BSTSet set2)
    {
        if(curr != null)
        {
            if(set1.isIn(curr.element) && !set2.isIn(curr.element))
                newSet.add(curr.element);

            diff(curr.left, newSet, set1, set2);
            diff(curr.right, newSet, set1, set2);
        }
    }
    
    
    private void switchElements(TNode n1, TNode n2)
    {

        int temp;
        
        if(n2== null || n1 == null)
            throw new NullPointerException();

        temp = n1.element;
        n1.element = n2.element;
        n2.element = temp;
    }


    private TNode find(int v)
    {
        TNode node, parent;
        node = root;
        
        if(!isIn(v))
            return null;

        while(node != null)
        {             
            if(v < node.element)
            {
                if(node.left != null)
                {
                    node = node.left;
                }
            }
            else if (v > node.element)
            {
                if (node.right != null)
                {                  
                    node = node.right;
                }       
            }
            else break;
        }
        return node;
    }
    
    private void moveSubTree(TNode n1, TNode n2,  TNode p1, TNode p2)
    {
        if(p1 == null)
            root = n2;
        else if (p1.left == n1)
            p1.left = n2;
        else
            p1.right = n2;

        if(n2 != null)
            p2 = p1;              
    }



    private TNode getParent(int v)
    {
        TNode node = root;
        TNode parent = null;

        if(!isIn(v))
            return null;
        
        if(node.element == v)
            return null;

        
        while(node != null)
        {
            if(v < node.element)
            {
                parent = node;
                node = node.left;
            }
            else if( v > node.element)
            {
                parent = node;
                node = node.right;
            }
            if(node.element == v)
                break;
        }
        return parent;
    }


    
    private TNode findMinSubtree(TNode node)
    {
        while(node != null)
        {
            printf("node: " + node);
            if(node.left == null)
                return node;

            node = node.left;
        }
        return null;
    }

    private TNode findMinSubtreeParent(TNode node)
    {
        TNode parent = null;
        while(node.left != null)
        {
            parent = node;
            node = node.left;
        }
        return parent;
    }


    public int size()
    {
        int size = nodeWalk(this.root);
        return size + 1;
    }

    private int nodeWalk(TNode node)
    {
        if(node == null)
            return 0;

        int seen = 0;
        
        if(node.left != null && node.right != null)
            seen += 2;
        else if(node.left != null)
            seen += 1;
        else if(node.right != null)
            seen += 1;

        seen += nodeWalk(node.left) + nodeWalk(node.right); 
        return seen;
    }

    

    public boolean isIn(int v)
    {
        TNode node = root;
        
        if(node == null)
            return false;
                

        while(node != null)
        {
            if(v > node.element)
            {
                if(node.right != null)
                    node = node.right;
                else
                    return false;
            }
            else if(v < node.element)
            {
                if(node.left != null)
                    node = node.left;
                else
                    return false;
            }
            else
                return true;
        }
        return false;
    }
    
    public int height()
    {
        if(this.root == null)
            return -1;
        
        int left = depth(this.root.left);
        int right = depth(this.root.right);

        if(left > right)
            return left;
        else
            return right;
    }

    public int depth(TNode node)
    {
        if(node == null)
        {
            return 0;
        }
        int left = depth(node.left);
        int right = depth(node.right);
        
        if(left > right)
            return left +1;
        else
            return right+1;
    }
    
    
    public void printBSTSet()
    {
        if(root == null)
        {
            System.out.println("The set is empty");
        }
        else
        {
            System.out.print("The set elements are: ");
            printBSTSet(root);
            System.out.print("\n");
        }
    }   

    private void printBSTSet (TNode t)
    {
        if(t!=null)
        {
            printBSTSet(t.left);
            System.out.print(" " + t.element + ", ");
            printBSTSet(t.right);
        }
    }
    
    private int findTreeMin()
    {
        TNode node = root;
        
        if(!isEmptyTree())
        {
            while(node.left != null)
            {
                node = node.left;
            }
            return node.element;
        }
        else
        {
            throw new NullPointerException("Tree is empty");
        }
    }
    

    public boolean isEmptyTree()
    {
        if(root == null)
            return true;
        return false;
    }

    public void plotTree(TNode node)
    {
        if(node != null)
        {
            printf(node.element);
            
            if(node.left != null)
                printf("left element: " + node.left.element);
            else
                printf("left element: null");
            
            if(node.right != null)
                printf("right element: " + node.right.element);
            else
                printf("right element: null");
            
            
            if(node.left != null)
            {
                plotTree(node.left);
            }
            if(node.right != null)
            {
                plotTree(node.right);
            }
        }
    }

    public void printTree()
    {
        plotTree(root);
    }

    public void printf(Object string)
    {
        System.out.println(string);
    }

    public void printNonRec()
    {
        Stack <TNode> s = new Stack();
        TNode curr = this.root;

        while(curr != null || !s.isEmpty())
        {
            while(curr != null)
            {
                s.push(curr);
                curr = curr.left;
            }
            //printf(s.top());
            curr = s.top();
            printf(curr.element);
            curr = curr.right;
            s.pop();
        }
        
    }
        
}
