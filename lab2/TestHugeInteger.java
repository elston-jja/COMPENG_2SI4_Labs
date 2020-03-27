import java.math.*;
import java.util.Random;

public class TestHugeInteger
{
    public static void main(String args[])
    {
        HugeInteger h1;
        HugeInteger h2;
        HugeInteger h3;
        HugeInteger h4;
        HugeInteger h5;

        int MAXINTS = 100;
        int MAXRUN = 100;
        int n = 55;
           
	
        h1 = new HugeInteger("22");
        h2 = new HugeInteger("22");

        
        HugeInteger huge1, huge2, huge3;
        Random randNum = new Random();
        long startTime, endTime;

        double runTime = 0;

        for(int numInts = 0; numInts < MAXINTS; numInts++)
        {
            huge1 = new HugeInteger(n);
            huge2 = new HugeInteger(n);
            
            startTime = System.currentTimeMillis();

            for(int numRun = 0; numRun < MAXRUN; numRun++)
            {
                huge3 =  huge1.multiply(huge2);
            }
            endTime = System.currentTimeMillis();
            runTime += (double) (endTime - startTime)/((double) MAXRUN);
        }

        runTime = runTime/((double) MAXINTS);
        System.out.println(runTime);

        // Addition
        //0.0011600000000000004 //10
        //0.005100000000000002  //100
        //0.023119999999999988  //500
        //0.034339999999999954  //700
        //0.05649999999999999   //1000
        //0.19519999999999998   //2000
        //0.43782               //3000
        //0.7136999999999999    //4000
        //1.0905600000000002    //5000
        //4.4940799999999985    //10000
        

        
        //System.out.println(h1.toString());
        //System.out.println(h2.toString());
        
        //System.out.println(h1.compareTo(h2)); // Test compare
        //System.out.println(h1.subtract(h2).toString()); // Test subtraction

        //System.out.println(h1.multiply(h2));
        //h5 = new HugeInteger("");
        //h3 = new HugeInteger("&^*&3482346832764*&74983");
        //h4 = h1.add(h3);
        //System.out.println(h4.toString());
    }
}
