import java.util.Random;

public class TMaxHeap{

    public static void main(String[] args)
    {
        Random random = new Random();
	
	printf("Building Heap");
        MaxHeap h = new MaxHeap(5);
	
	// Creating tree I know the final structure
	printf("Inserting: 5,8,6,12,6");

	printf("Testing insert:");
	h.insert(5);
	printf("Inserted: 5");
        h.insert(8);
	printf("Inserted: 8");	 	
        h.insert(6);
	printf("Inserted: 6");
        h.insert(12);
	printf("Inserted: 12");
        h.insert(6);
	printf("Inserted: 6");

	// Testing Get methods
	printf("Testing Get Methods:");
	printf("Heap Size: " + h.getSize());
	printf("Heap Capacity: " + h.getCapacity());
	printf("Printing Heap:");
	printf(h.toString());
	
	// check for increase in size
	printf("Checking for size increase: ");
	printf("Testing insert into full heap");
	printf("Inserting 20");
	h.insert(20);
	printf("Printing New Heap");
	printf(h.toString());
	printf("Heap Size:" + h.getSize());
	printf("Heap Capacity:" + h.getCapacity());



	printf("Testing deleteMax");
	printf("Deleting Max: " + h.deleteMax());
	
	
	
	printf("Creating array of random integers");
        Integer[] arr = new Integer[5];
        
        for(int i = 0; i < 5; i++)
        {
            arr[i] = random.nextInt(1000)+1;
        }

	printf("Creating heap from array");
        MaxHeap t = new MaxHeap(arr);
	printf("Printing heap:");
	printf(t.toString());

	printf("Deleting Elements:");
        printf("Deleting: " + t.deleteMax());
	printf("Printing heap:");
	printf(t.toString());
	printf("Deleting: " + t.deleteMax());
	printf("Printing heap:");
	printf(t.toString());
        printf("Deleting: " + t.deleteMax());
	printf("Printing heap:");
	printf(t.toString());
        printf("Deleting: " + t.deleteMax());
	printf("Printing heap:");
	printf(t.toString());
        printf("Deleting: " + t.deleteMax());
	printf("Printing heap:");
	printf(t.toString());
	
        // Error to be thrown
        //System.out.println(t.deleteMax());
	
	

	printf("Creating Array of random numbers:");
        Integer[] ary = {3134,1234,1234,23,41234,1234,342,34523,123};

	System.out.print("ary: ");
        for(int i = 0; i < ary.length; i++)
        {
            print(" " + ary[i] + ", ");
        }
	printf("\n----Sorting in-place via heapsort---");
        MaxHeap.heapsort(ary);
        printf("Same array sorted: ");
        System.out.print("ary: ");
        for(int i = 0; i < ary.length; i++)
        {
            print(" " + ary[i] + ", ");
        }
    }

    private static void printf(Object str)
    {
	System.out.println(str);
    }
    private static void print(Object str)
    {
	System.out.print(str);
    }
    
}
