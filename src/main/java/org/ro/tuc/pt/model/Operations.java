package org.ro.tuc.pt.model;


import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Operations {

    public static Polynomial add(Polynomial pol1, Polynomial pol2) { return pol1.add(pol2);}
    public static Polynomial subtract(Polynomial pol1, Polynomial pol2) { return pol1.subtract(pol2);}
    public static Polynomial multiply(Polynomial pol1, Polynomial pol2) { return pol1.multiply(pol2);}
    public static Polynomial[] divide(Polynomial pol1, Polynomial pol2) { return pol1.divide(pol2);}
    public static Polynomial derivate(Polynomial pol1) {return pol1.derivate();}
    public static Polynomial integrate(Polynomial pol1) {return pol1.integrate();}

    public static Polynomial[] readPolynomials(String pol1, String pol2)
    {
        Polynomial[] result = new Polynomial[2];

        if((pol1!=null)&&(!pol1.isEmpty())) {
            result[0] = readPolynomial(pol1);
        } else {
            result[0]=null;
        }
        if((pol2!=null)&&(!pol2.isEmpty())) {
            result[1] = readPolynomial(pol2);
        } else {
            result[1]=null;
        }
        return result;
    }

    private static Polynomial readPolynomial(String input)
    {
        if (input==null) return null;
        Polynomial rezult;
        Monomial monomial;
        StringTokenizer tok = new StringTokenizer(input," ");
        ArrayList<Monomial> monomials = new ArrayList<Monomial>();
        int sign=1;
        String str;
        while(tok.hasMoreElements())
        {
            str=tok.nextToken();
            if(str.compareTo("-")==0) sign =-1;
            else if(str.compareTo("+")==0) sign =1;
                 else {
                     monomial=readMonomial(str);
                     if(monomial==null)
                         return null;
                     else if(sign==1) monomials.add(monomial);
                          else monomials.add(new Monomial(monomial.getCoefficient().doubleValue()*(-1),monomial.getDegree()));
                 }
        }
        rezult = new Polynomial(monomials);
        return rezult;
    }

    private static Monomial readMonomial(String input)
    {
        if (input==null) return null;
        int coeff, degree;
        int val=validateMonomial(input);
        if(val==-1)
            return null;
        switch (val)
        {
            case 0:
                coeff=Integer.parseInt(input);
                return new Monomial(coeff,0);
            case 1:
                coeff=Integer.parseInt(input.substring(0,input.length()-1));
                return new Monomial(coeff,1);
            case 2:
            case 3:
                int place=input.indexOf("x^");
                coeff=Integer.parseInt(input.substring(0,place));
                degree=Integer.parseInt(input.substring(place+2,input.length()));
                return new Monomial(coeff,degree);
            case 4: return new Monomial(1,1);
            case 5:
            case 6: return new Monomial(1,Integer.parseInt(input.substring(2,input.length())));
        }
        return null;
    }


    private static int validateMonomial(String input)
    {
        String[] formats = new String[7];
        formats[0] = "[0-9]{1,4}"; //just a number
        formats[1] = "[0-9]{1,4}x"; // number + x to power 1
        formats[2] = "[0-9]{1,4}x\\^[0-9]{1,4}"; //number + x to a power
        formats[3] = "[0-9]{1,4}x\\^-[0-9]{1,4}"; //number + x to a negative power
        formats[4] = "x"; //x to power 1
        formats[5] = "x\\^[0-9]{1,4}"; //x to a power
        formats[6] = "x\\^-[0-9]{1,4}"; //x to a negative power

        Pattern[] patterns = new Pattern[7];
        Matcher[] matchers = new Matcher[7];
        for(int i=0; i<7;i++)
        {
            patterns[i] = Pattern.compile(formats[i], Pattern.CASE_INSENSITIVE);
            matchers[i]=patterns[i].matcher(input);
            if(matchers[i].matches())
                return i;
        }
        return -1; //not found
    }
}
