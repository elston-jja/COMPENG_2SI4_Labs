public class HashTableQuad
{
    private int[] table;
    private int   size = 0;
    private int   ausage = 0;
    private int   keys = 0;
    private double    lf   = 0;

    public HashTableQuad(int maxNum, double load)
    {
        size = getPrime((int)(maxNum/load));
        table = new int[(int)(size)];
        lf = load;
    }
   
    public void insert(int n)
    {
        int probe = 0;
        
        if(!isIn(n))
        {
            if((keys + 1) > (int)(size*lf)){ rehash(); }
            
            int index = n % size;
            int hash  = index;
            
            while(table[index] != 0)
            {
                index = (hash + probe * probe) % size;
                probe++;
            }
            table[index] = n;
            keys++;
        }
    }
    
    public boolean isIn(int n)
    {
        int index = n % size;
        int probe = 0; 
        int hash  = index;
	    
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
                index = (hash + probe * probe) % size;
                probe++;
		
            }
            return false;
        }
    }


    public void rehash()
    {
        int hash = 0; int probe = 1;
        int newSize = getPrime(2*size + 1);
        int temp;
	
        int[] newTable = new int[newSize];
	
        for(int i = 0; i < size; i++)
        {
            if(table[i] != 0)
            {
                hash = table[i] % newSize;
                temp = hash;
                
                if(newTable[hash] == 0)
                {
                    newTable[hash] = table[i];
                }
                else
                {
                    while(newTable[hash] != 0)
                    {
                        hash = (temp + probe * probe) % newSize;
                        probe ++;
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
        int probe = 0;
    
        if(!isIn(n))
        {

            int index = n % size;
            int hash  = index;
	    
            while(table[index] != 0)
            {
                index = (hash + probe * probe) % size;
                probe++;
            }
            table[index] = n;
            keys++;
        }

        return probe;
        
    }
}
