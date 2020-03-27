import java.util.Arrays; 

// In-place heapsort algorithm used
// https://www.geeksforgeeks.org/heap-sort/

public class MaxHeap {

    private int[] heap;
    private int pointer = 1;
    private int size = 1;
    
    public MaxHeap(int size){
        heap = new int[size+1];
        this.size = size + 1;
    }
    
    public MaxHeap(Integer[] Array){
        
        heap = new int[Array.length+1];
        
        for(int i = 0; i < Array.length; i ++) {
            insert(Array[i]);
        }
        this.size = Array.length + 1;
    }

    public void insert(int n){
        checkSize();
        heap[pointer] = n;
        bubbleUp();
        pointer++;
    }

    public int deleteMax(){
        
        if(pointer == 1){throw new IllegalArgumentException();}
        
        int max = heap[1];

        if(pointer == 2){
            heap[1] = 0;
            pointer--;
        }
        else{
            heap[1] = heap[pointer-1];
            heap[pointer-1] = 0;
            pointer--;
            bubbleDown();
        }
        return max;
        
    }
    
    public String toString(){
        String ret = "";
        int len = ((size % 2 == 0) ? size - 1 : size);
        for(int i = 1; i < len; i++)
        {
            ret += heap[i] + ",";
        }
        return ret;
    }

    public int getSize()
    {
        return pointer-1;
    }

    public int getCapacity()
    {
        return heap.length - getSize()-1;
    }
    

    public static void heapsort(Integer[] arrayToSort){


        // Ensure we are not sorting an empty array
        if(arrayToSort.length == 0)
            throw new IllegalArgumentException();

        int heapSize = arrayToSort.length;


        // Build the heap by running heapify on every node
        // Node: runs from floor(heapSize/2)-1 as we do not need
        // to visit children
        for(int i =  heapSize/2-1 ; i >= 0 ; i--)
            heapify(arrayToSort, heapSize, i);

        // The max element is stored at arrayToSort[0] as we built the heap
        // Swich the root element 0 and the last element (initialy heapSize-1)
        // Then run heapify with size not including the last element swapped.
        // By doing this, we always put the max element (the head) at index i and run heapify
        // of heapSize = i. This sorts the array from back to front
        for (int i  = heapSize-1 ; i >= 0; i--)
        {
            int temp = arrayToSort[0];
            arrayToSort[0] = arrayToSort[i];
            arrayToSort[i] = temp;
            heapify(arrayToSort, i, 0);
        }

        // Reverse then ascending order array
        for(int i = 0; i < arrayToSort.length/2; i++)
        {
            int temp = arrayToSort[i];
            arrayToSort[i] = arrayToSort[arrayToSort.length-i-1];
            arrayToSort[arrayToSort.length-i-1] = temp;
        }
        
        
    }


    private static void heapify(Integer[] arr, int heapSize, int i)
    {
        // check if the the subtree volates the Max-Heap condition
        int l = 2*i + 1;
        int r = 2*i + 2;
        int largest = i;
        
        if (l < heapSize && arr[l] > arr[largest])
            largest = l;
        if (r < heapSize && arr[r] > arr[largest])
            largest = r;
        
        // if there is an element in the subtree,
        // then swap the elements to fix the conflict
        if(largest != i)
        {

            // Could be replaced by a private method called swap
            int temp = arr[i];
            arr[i] = arr[largest];
            arr[largest] = temp;

            // Fix elements in the reoccuring subtrees via swaping
            heapify(arr, heapSize, largest);
        }
    }


    // private helper functions
    private  int parentIndex(int index) {return index/2;}
    private int leftIndex(int index){return 2*index;}
    private int rightIndex(int index){return 2*index + 1;}

    private int parent(int index){return heap[parentIndex(index)];}
    private int left(int index){return heap[leftIndex(index)];}
    private int right(int index){return heap[rightIndex(index)];}

    private boolean hasLeft(int index){return leftIndex(index) < size;}
    private boolean hasRight(int index){return rightIndex(index) < size;}
    private boolean hasParent(int index){return index > 1;}
    
    private void bubbleUp(){
        int i = pointer;
        while(hasParent(i) && heap[i] > parent(i))
        {
            swap(i, parentIndex(i));
            i = parentIndex(i);
        }
    }

    private void bubbleDown()
    {
        int i = 1;

        while(hasLeft(i)){

            int lc = leftIndex(i);

            if(hasRight(i) && left(i) < right(i)){
                lc = rightIndex(i);
            }
            
            if(heap[i] > heap[lc]){
                break;
            }
            else{
                swap(i, lc);
                i = lc;
            }
        }
    }
    

    private void swap(int i1, int i2)
    {
        int temp = heap[i1];
        heap[i1] = heap[i2];
        heap[i2] = temp;
    }

    private void checkSize(){
        if(pointer == size){
            heap = Arrays.copyOf(heap, size*2);
            size *= 2;
        }
    }

    
    
    private void printf(Object t){System.out.println(t);}
    
    private void printHeapArr()
    {
        for(int i = 0; i < size; i++)
        {
            printf( i + ": " + heap[i]);
        }
    }

    private void printBranches()
    {
        for(int i = pointer-1; i >= 1; i--)
        {
            // end of heap
            if(heap[i] == 0){break;}
            
            printf("Current: " + heap[i]);
            
            if(hasLeft(i)){
                if(left(i) != 0)
                    printf("Left: " + left(i));
                else
                    printf("Left: ---");
                
                if(left(i) > heap[i]){
                    printf("Violation Found");
                }
            }
            else{printf("Left: ---");}
            if(hasRight(i)){
                if(left(i) != 0)
                    printf("Right: " + right(i));
                else
                    printf("Right: ---");
               

                if(right(i) > heap[i]){
                    printf("Violation Found");
                }
            }
            else{printf("Right: ---");}
            
            
        }
    }
        
}
