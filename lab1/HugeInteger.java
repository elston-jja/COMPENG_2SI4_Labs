import java.util.Random;

public class HugeInteger
{
	public String val;
    private int stop;
    private int digits;
    public boolean negSign = false;
    private int position = 0;

    private Random rand = new Random();
	
	public HugeInteger(String val)
        {
            this.val = val;

            if (this.val.length() == 0)
            {
                throw new NumberFormatException();
            }
        
            // check if we have a negative sign
            if (val.startsWith("-"))
            {
                negSign = true;
                position++;
            }

            // leading zeros check
            for( int i = position ; i < val.length(); i++ )
            {
                if(val.charAt(i) - '0' != 0)
                {
                    break;
                }
                position++;
                if(position == val.length()){position = 1;}
            }

            for(int i = position ; i < this.val.length(); i++)
            {
                if(this.val.charAt(i) - '0'>= 10 || this.val.charAt(i) - '0' < 0) 
                {
                    throw new NumberFormatException();
                }
            }
            
            // store metadata about val
            stop  = val.length();
            digits = stop - position;
        }

    public HugeInteger(int n)
        {

            if(n <= 0)
            {
                throw new NumberFormatException();
            }
            
            this.val = "";

            this.val = this.val + Integer.toString(rand.nextInt(9) + 1) ;
            
            for(int i = 0; i < n-1; i++)
            {
                this.val = this.val + Integer.toString(rand.nextInt(10)) ;
            }

            this.position = 0;
            this.stop = val.length();
            digits = this.stop - position;

        }

	public HugeInteger add(HugeInteger h)
        {
            int pCarry = 0;
            int nCarry = 0;
            boolean isNegative = false;

            int currCalc = 0; 

            String compliment = "";
            String nStr = "";
            String aStr = "";

            if (h.val.substring(h.position) == "" && this.val.substring(this.position) == "")
            {
                return new HugeInteger("0");
            }
            else if (h.val.substring(h.position) == "")
            {
                return new HugeInteger(this.val);
            }
            else if (this.val.substring(this.position) == "")
            {
                return new HugeInteger(h.val);
            }
            
        
            // if one of the numbers is negative use their 9s compliment + 1
            if( (h.negSign && !this.negSign) || (!h.negSign && this.negSign))
            {
                if(h.negSign)
                {
                    int currentPos = h.position;

                    if(Integer.parseInt(h.val.substring(h.position)) > Integer.parseInt(this.val.substring(this.position)))
                    {
                        isNegative = true;
                    }
                
                    while(currentPos < h.stop)
                    {
                        compliment = compliment + Integer.toString('9'-h.val.charAt(currentPos));
                        currentPos++;
                    }
                    
                    aStr = add_positive(compliment, 0, compliment.length(), this.val, this.position, this.digits, false, isNegative);

                }
                else
                {
                    if(Integer.parseInt(h.val.substring(h.position)) < Integer.parseInt(this.val.substring(this.position)))
                    {
                        isNegative = true;
                    }
                    int currentPos = this.position;

                    while(currentPos < this.stop)
                    {
                        compliment = compliment + Integer.toString('9'-this.val.charAt(currentPos));
                        currentPos++;
                    }
                    
                    aStr = add_positive(compliment, 0, compliment.length(), h.val, h.position, h.digits, false, isNegative);
                }
                

            }
            else if (!h.negSign && !this.negSign)
            {
                aStr = add_positive(h.val, h.position, h.digits, this.val, this.position, this.digits, true, false);
            }


            if(isNegative)
            {
                for(int i  = aStr.length()-1; i >= 0 ; i--)
                {
                    nStr = Integer.toString('9' - aStr.charAt(i)) + nStr;
                }

                aStr = nStr;
            }
            
        
            HugeInteger retHugeInt = new HugeInteger(aStr);
            
            if(this.negSign && h.negSign){retHugeInt.negSign = true;}
            
            if(isNegative){retHugeInt.negSign = true;}
            
            return retHugeInt;
        }


    public String add_positive(String s1, int p1, int d1, String s2, int p2, int d2, boolean endCarry, boolean isNegative)
        {

            //printf(s1);
            //printf(s2);
            
            int pCarry = 0;
            int nCarry = 0;
            
            int currCalc; 
            String aStr = "";

            int len = d1 < d2 ? d1 : d2;

            p1 = d1 - 1;
            p2 = d2 - 1;

            if (!endCarry && !isNegative){nCarry = 1;}
            
            for(int i = 0; i < len; i++)
            {
                pCarry = nCarry;
			
                currCalc = pCarry + s1.charAt(p1) + s2.charAt(p2) - 2*'0';
			
                if( currCalc >= 10 ){nCarry = 1;}
                else{nCarry = 0;}
                aStr = Integer.toString(currCalc%10) + aStr;
                p1--;
                p2--;
            }

            
            
            
            for(int j = 0; j < Math.abs(d1-d2); j++)
            {

                pCarry = nCarry;
                
                if(d1 - d2 > 0)
                {
                    currCalc = pCarry + s1.charAt(p1) - '0';
                }
                else
                {
                    currCalc = pCarry + s2.charAt(p2) - '0';
                }

                if(currCalc  >= 10){nCarry = 1;}
                else{nCarry = 0;}
                aStr = Integer.toString(currCalc % 10) + aStr;
                p1--;
                p2--;
            }
        
            if(nCarry == 1 && endCarry)
            {
                aStr = "1" + aStr;
            }

            //case where d1 or d2 is bigger than the other
            return aStr;
        }

    public String toString()
        {
            String retString = "";

            if(this.negSign)
            {
                 retString = "-";
            }
            retString = retString + this.val.substring(position);

            return retString;
        }

    public void printf(Object v)
        {
            System.out.println(v);
        }
}
