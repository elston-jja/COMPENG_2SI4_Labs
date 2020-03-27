import java.util.Random;

public class HugeInteger
{    
    public boolean _debug_ = false;
    
    private  String  val; 
    private String nVal;

    private boolean negSign = false;
    
    private int position = 0;	

    public HugeInteger(String val)
    {
        this.val = val;

        if (this.val.length() == 0)
            throw new NumberFormatException("Improper declaration of Object: Null String");
                    
        // check if we have a negative sign
        if (this.val.startsWith("-"))
        {
            negSign = true;
            position++;
                
            // Ensures we do not have '-' as an input
            if (this.val.length() == 1)
            {
                throw new NumberFormatException("Found no number after negative sign");
            }        
        }
            
        // Removes leading zeros 
        for( int i = position ; i < val.length(); i++ )
        {
            if(val.charAt(i) - '0' != 0)
            {
                break;
            } 
            position++;
                
            // Deals with '-0' and "000000" and saves data as "0"
            if(position == val.length()){position = val.length()-1; negSign = false;}

        }

            
        // Check to see if the characters in the string are within the ASCII range of numbers
        for(int i = position ; i < this.val.length(); i++)
        {
            if(this.val.charAt(i) - '0'>= 10 || this.val.charAt(i) - '0' < 0) 
            {
                throw new NumberFormatException();
            }
        }

        // Stores the data without leading zeros or negative sign
        nVal = getValue(this);

        if(_debug_)
        {
            printf("--------------------");
            printf("Variable Created:");
            printf("nVal: " + nVal);
            printf("position:" + position);
            printf("val: " + val);
            printf("--------------------");
        }
    }


    public HugeInteger(int n)
    {
        Random rand = new Random();
        
        if(n <= 0)
        {
            throw new NumberFormatException("Improper declataion of Object: Random number cannot have zero digits or less");
        }

        // Generates the numbers in the string
        this.val = "";

        // Ensure no leading zero
        this.val = this.val + Integer.toString(rand.nextInt(9) + 1) ;

        for(int i = 0; i < n-1; i++)
        {
            this.val = this.val + Integer.toString(rand.nextInt(10)) ;
        }

        this.nVal = val;
        this.position = 0;
    }

    public HugeInteger subtract(HugeInteger h)
    {
        if(h.negSign)
        {
            String temp = h.nVal;
            HugeInteger t = new HugeInteger(temp);
            return this.add(t);
        }
        else
        {
            String temp = h.nVal;
            temp = "-" + temp;
            HugeInteger t = new HugeInteger(temp);
            return this.add(t);
        }
        
    }
    

    public HugeInteger add(HugeInteger h)
    {
        boolean isNegative = false;
            

        String compliment = "";
        String aStr = "";
        
        /* Handles special cases such as adding zeros */
            
        if ( getDigit(h, 0) == 0 && getDigit(this, 0) == 0 )
        {
            return new HugeInteger("0");
        }
        else if (getDigit(h, 0) == 0)
        {
            HugeInteger retVal = new HugeInteger(this.nVal);
            retVal.negSign = this.negSign;
            return retVal;
        }
        else if (getDigit(this, 0) == 0)
        {
            HugeInteger retVal = new HugeInteger(h.nVal);
            retVal.negSign = h.negSign;
            return retVal;
        }


        /* Handles the case when one number is negative and the other is positive*/

        // Ensures only one number is negative
        if( (h.negSign && !this.negSign) || (!h.negSign && this.negSign))
        {
            if(h.negSign)
            {
                if(this.compareAbs(h) == -1){isNegative = true;}
                compliment = getCompliment(h,this);
                aStr = sumPositive(compliment, this.val, false);
            }
            else
            {
                if(this.compareAbs(h) == 1){isNegative = true;}
                compliment = getCompliment(this,h);
                aStr = sumPositive(compliment, h.val, false);
            }
            
            if(this._debug_)
            {
                //System.out.println("aStr: " + aStr);
                System.out.println("compliment: " + compliment);
            }
        }
        
        // both positive numbers
        else if (!h.negSign && !this.negSign){aStr = sumPositive(h.val, this.val, true);}

        if(isNegative){aStr = getCompliment(aStr);}

        if(this.negSign && h.negSign){aStr = sumPositive(this.nVal, h.nVal, true);}
        
        HugeInteger retHugeInt = new HugeInteger(aStr);
            
        if(this.negSign && h.negSign){retHugeInt.negSign = true;}    
        if(isNegative){retHugeInt.negSign = true;}
        return retHugeInt;
    }


    public HugeInteger multiply(HugeInteger h)
    {

        String s1 = this.nVal;
        String s2 = h.nVal;
        int zeros = 0;
        int zerosOffset = 0;
        int iMulti = 0;
        String sMulti = "";
        String ans = "0";

        // check signs off this and h to get proper sign when returning

        // s1 is always the bigger or equal string
        for(int i = s1.length()-1; i >= 0; i--)
        {
            for(int j = s2.length()-1; j >= 0; j--)
            {
                
                iMulti = getDigit(this, i) * getDigit(h, j);

                sMulti = Integer.toString(iMulti);
                
                for(int k = 0; k < zeros; k++)
                {
                    sMulti = sMulti + "0";
                }

                ans = sumPositive(ans, sMulti, true);
                zeros++;

                if(_debug_)
                {
                    printf("iMulti: " + iMulti);
                    printf("sMulti: " + sMulti);
                    printf("Ans: " + ans);
                }
            }
            sMulti = "0";
            zerosOffset++;
            zeros = zerosOffset;
        }

        HugeInteger multiplied = new HugeInteger(ans);

        if((this.negSign && !h.negSign) || (!this.negSign && h.negSign))
        {
            multiplied.negSign = true;
        }
        
        return multiplied;
    }
    
    

    /*
      Add two positive strings s1 and s2. 
      Allow us to control if we would like an end carry for compliments using boolean endCarry
     */
    private String sumPositive(String s1, String s2, boolean endCarry)
    {

        int pCarry = 0;
        int nCarry = 0;
            
        int currCalc; 
        String aStr = "";

        boolean s1follower = s1.length() > s2.length() ? true : false; 

        int p1 = s1.length()-1;
        int p2 = s2.length()-1;
    
        while(p1 != -1 && p2 != -1)
        {
            pCarry = nCarry;
            currCalc = pCarry + s1.charAt(p1) + s2.charAt(p2) - 2*'0';
            if( currCalc >= 10 ){nCarry = 1;}
            else{nCarry = 0;}
            aStr = Integer.toString(currCalc%10) + aStr;
            p1--;
            p2--;
        }

        
        for(int j = 0; j < Math.abs(s1.length()-s2.length()); j++)
        {

            pCarry = nCarry;
                
            if(s1follower)
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
        
        if(nCarry == 1 && endCarry){ aStr = "1" + aStr;  }

        return aStr;
    }



    /* Helper Functions */



    private String getCompliment(HugeInteger h, HugeInteger wrt)
    {
        String compliment = "";
        String nValCopy = h.nVal;
        int position = 0;

        for(int i = 0; i <  wrt.nVal.length() - h.nVal.length(); i++)
        {
            nValCopy = '0' + nValCopy ;
        }

        if (_debug_)
        {
            printf(nValCopy);
        }
        
        while(position < nValCopy.length())
        {
            compliment =  compliment + Integer.toString('9'- nValCopy.charAt(position));
            position++;
        }
        
        compliment = sumPositive(compliment, "1", false);

        return compliment;
    }

    private String getCompliment(String s)
    {
        String compliment = "";
        int position = 0;
        
        while(position < s.length())
        {
            compliment =  compliment + Integer.toString('9'- s.charAt(position));
            position++;
        }
        
        compliment = sumPositive(compliment, "1", false);
        
        return compliment;
    }
        
    
    /*
      Compares this HugeInteger to h 
      Returns 1 if this is bigger than h
      Returns -1 if h is bigger than this
      Returns 0 if h is equal to this
     */
    public int compareTo(HugeInteger h)
    {
            
        if ( this.negSign && !h.negSign){return -1;}
        if (!this.negSign &&  h.negSign){return  1;}

        // Both numbers negative
        if (this.negSign && h.negSign)
        {
            if     (h.nVal.length() > this.nVal.length()) {return  1;}
            else if(h.nVal.length() < this.nVal.length()) {return -1;}

            // The lengths of the two numbers are equal
            else
            {
                for(int i = 0; i < this.nVal.length(); i++)
                {
                    if(getDigit(h, i) > getDigit(this, i))
                    {
                        return 1;
                    }
                    else if(getDigit(h, i) < getDigit(this, i))
                    {
                        return -1;
                    }
                }
            }
        }          

        // Both Numbers Positive
        if(!this.negSign && !h.negSign)
        {
            if     (h.nVal.length() < this.nVal.length()) {return  1;}
            else if(h.nVal.length() > this.nVal.length()) {return -1;}

            // The lengths of the two numbers are equal
            else
            {
                for(int i = 0; i < this.nVal.length(); i++)
                {
                    if(getDigit(h, i) < getDigit(this, i))
                    {
                        return 1;
                    }
                    else if(getDigit(h, i) > getDigit(this, i))
                    {
                        return -1;
                    }
                }
            }
        }

        return 0;
            
    }

    public int compareAbs(HugeInteger h)
    {
        if(this.nVal.length() > h.nVal.length()){return 1;}
        else if(this.nVal.length() < h.nVal.length()){return -1;}
        
        for(int i = 0; i < this.nVal.length(); i++)
        {
            if(getDigit(this, i) > getDigit(h,i)){return 1;}
            else if(getDigit(this, i) < getDigit(h,i)){return -1;}
        }
        return 0;
    }
    

    private int getDigit(HugeInteger h, int index)
    {
        return h.nVal.charAt(index) - '0';
    }
    
    
    private String getValue(HugeInteger h)
    {
        return h.val.substring(h.position);
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
