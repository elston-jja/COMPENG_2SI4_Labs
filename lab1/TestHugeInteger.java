public class TestHugeInteger
{
    public static void main(String args[])
    {
	
        HugeInteger h1;
        HugeInteger h2;
        HugeInteger h3;
        HugeInteger h4;
        HugeInteger h5;
        try
        {
            h1 = new HugeInteger("-");
            h2 = new HugeInteger("11000");
            System.out.println(h1.toString());
            System.out.println(h2.toString());
            System.out.println(h1.add(h2).toString());
            System.out.println(h3.toString());
            //h5 = new HugeInteger("");
            //h3 = new HugeInteger("&^*&3482346832764*&74983");
            //h4 = h1.add(h3);
            //System.out.println(h4.toString());
        }
        catch(Exception e)
        {
            System.out.println("Error caught");
            System.out.println(e);
        }        
    }
}
