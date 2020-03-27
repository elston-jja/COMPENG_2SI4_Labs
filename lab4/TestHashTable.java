import java.util.Random;


public class TestHashTable{

    public static void main(String[] args)
    {
        // HashTableLin A  = new HashTableLin(5,0.4);
        // A.printKeys();
        // A.insert(1);
        // A.insert(2);
        // A.insert(3);
        // A.insert(4);
        // A.insert(5);
        // A.insert(6);
        // A.insert(7);
        // A.insert(8);
        // A.insert(9);
        // A.insert(10);
        // A.insert(29);
        // A.insert(31);
        // A.insert(34);
        // A.insert(344);
        // System.out.println(A.isIn(31));
        // System.out.println(A.getSize());
        // A.printKeysAndIndexes();
        // System.out.println(A.getCurrLoadFactor());

        HashTableQuad B = new HashTableQuad(5,0.5);


        //System.out.print("old size ");
        //System.out.println(B.getSize());
                

        B.insert(3);

        B.insert(1);


        B.insert(4);




        

        B.insert(5);


        B.insert(6);
        
        System.out.print("OLD Load Factor: ");
        System.out.println(B.getCurrLoadFactor());
        B.insert(7);
        
        System.out.print("NEW Load Factor: ");
        System.out.println(B.getCurrLoadFactor());

        B.insert(8);
        
        B.insert(9);
        B.insert(10);
        B.insert(11);
        B.insert(12);
        B.insert(13);
        B.insert(208);
        B.insert(2);
        //System.out.print("new size ");
        //System.out.println(B.getSize());


                
        B.printKeysAndIndexes();


        
        //testQuad();
        //testLinear();
    }

    
    static public void testQuad()
    {
        int MAX_REPEATS = 10;
        int MAX_INSERTS = 100;
        int probes = 0;

        Random randInt = new Random(1);

        System.out.println("Testing Quadratic:");
        
        for(double j = 0.9; j > 0.1; j = j-0.1)
        {
            HashTableQuad C = new HashTableQuad(10000,j);
            
            for(int i = 0 ; i < MAX_REPEATS; i++)
            {
                
                for(int k  = 0; k < MAX_INSERTS; k++)
                {
                    probes += C.insertCount(randInt.nextInt(1000000)+1);
                        
                }
                   
            }
            
            System.out.println("Load Factor: "+j+" Probes " +(float)(probes)/(MAX_INSERTS*MAX_REPEATS));
            probes = 0;

        }
    }


    static public void testLinear()
    {
        int MAX_REPEATS = 100;
        int MAX_INSERTS = 100000;
        int probes = 0;
        double[] tests = new double[MAX_REPEATS];
        double avg = 0;
        
        Random randInt = new Random(1);
        
        System.out.println("Testing Linear");
        


        for(double j = 0.9; j > 0.1; j = j-0.1)
        {

            for(int i = 0; i < MAX_REPEATS; i++)
            {
            
                HashTableLin C = new HashTableLin(100000,j);
                
                for(int k  = 0; k < MAX_INSERTS; k++)
                {
                    probes += C.insertCount(randInt.nextInt(Integer.MAX_VALUE)+1);
                
                }

                tests[i] = ((double)probes)/(double)MAX_INSERTS;
                //System.out.println(tests[i]);

                probes = 0;
            }


            for(int i = 0; i < MAX_REPEATS; i++)
            {
                avg += tests[i] + 1;
            }




            System.out.println("Theory Load Factor: " + j + " is " + ((float)1)/2*(1+1/(1-j)));
            System.out.println("Actual Load Factor: " + j + " is " + ((float)avg)/MAX_REPEATS);

                        
            
            tests = new double[MAX_REPEATS];
            avg = 0;
        }
        

        

        

        
        
    }    

    
    
}
