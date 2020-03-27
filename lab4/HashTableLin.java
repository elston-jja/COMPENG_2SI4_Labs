public class HashTableLin
{
    
    private int[] table;
    private int   size = 0;
    private int   ausage = 0;
    private int   keys = 0;
    private double    lf   = 0;

    
    public HashTableLin(int maxNum, double load)
    {

        size = getPrime((int)(maxNum/load));
        table = new int[(int)(size)];
        lf = load;
    }
    
    
    public void insert(int n)
    {
        if(!isIn(n))
        {
            if((keys + 1) > (int)(size*lf)){ rehash(); }
            
            int index = n % size;
            
            while(table[index] != 0)
            {
                index = (++index) % size;
            }
            table[index] = n;
            keys++;
        }
    }
    
    public boolean isIn(int n)
    {
        int index = n % size;
        if(table[index] == 0)
            return false;
	else
	{
	    while(table[index] != 0)
	    {
            if(table[index] == n)
            {
                return true;
		}
            index = (++index) % size;
            
	    }
	    return false;
	}
    }
    

    public void rehash()
    {
        int hash = 0;
        int newSize = getPrime(2*size + 1);
        
        int[] newTable = new int[newSize];
        
        for(int i = 0; i < size; i++)
        {
            if(table[i] != 0)
            {
                hash = table[i] % size;
                
                if(newTable[hash] == 0)
                {
                    newTable[hash] = table[i];
                }
                else
                {
                    while(newTable[hash] != 0)
                    {
                        hash = (++hash) % newSize;
                    }
                    newTable[hash] = table[i];
                }
            }

        }
        size = newSize;
        table = newTable;
    }


    public void printKeys()
    {
	for( int i  = 0; i < size; i++)
	{
	    if(table[i] != 0)
	    {
		System.out.println(table[i]);
	    }
	}
	
    }

    public int getPrime(int p)
    {
	while(true)
	{
	    for(int i = (int) Math.sqrt(p) + 1; i > 0; i--)
	    {
		if( i == 1 ){return p;}
		if(p % i == 0){break;}
	    }
	    p++;  
	}
    }

    public void printKeysAndIndexes()
    {
	for(int i = 0; i < size; i++)
	{
	    System.out.println("Index: " + i + " Key:" + table[i]);
	}
    }
    
    public double getMaxLoadFactor() {return lf;}
    public int getKeys(){return keys;}
    public int getSize(){return size;}
    public double getCurrLoadFactor(){return ((double)keys)/size;}

    public int insertCount(int n)
    {
        int inserts = 0;
        if(!isIn(n))
        {
            if((keys + 1) > (int)(size*lf)){ rehash(); }
            
            int index = n % size;
            
            while(table[index] != 0)
            {
                index = (++index) % size;
                inserts++;
            }
            table[index] = n;
            keys++;
        }
        return inserts;
    }
}
