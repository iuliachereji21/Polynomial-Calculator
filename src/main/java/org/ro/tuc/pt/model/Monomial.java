package org.ro.tuc.pt.model;

public class Monomial implements Comparable<Monomial> {
    private Number coefficient;
    private int degree;

    public Monomial(Number coefficient,int degree)
    {
        this.coefficient=coefficient;
        this.degree=degree;
    }

    public Number getCoefficient() {
        return coefficient;
    }

    public int getDegree() {
        return degree;
    }

    public Monomial add(Monomial mon)
    {
        if(mon==null) return null;
        if(mon.getDegree()!= degree)
            return null;
        Monomial rez=new Monomial(coefficient.doubleValue() + mon.getCoefficient().doubleValue(), degree);
        return rez;
    }
    public Monomial subtract(Monomial mon)
    {
        if(mon==null) return null;
        if(mon.getDegree()!= degree)
            return null;
        Monomial rez=new Monomial(coefficient.doubleValue() - mon.getCoefficient().doubleValue(), degree);
        return rez;
    }
    public Monomial multiply(Monomial mon)
    {
        if(mon==null) return null;
        Monomial rez=new Monomial(coefficient.doubleValue() * mon.getCoefficient().doubleValue(), degree + mon.getDegree());
        return rez;
    }

    public Monomial divide(Monomial mon)
    {
        if(mon==null) return null;
        double coeff=coefficient.doubleValue()/mon.getCoefficient().doubleValue();
        return new Monomial(coeff,degree- mon.getDegree());
    }

    public Monomial derivate()
    {
        if (degree==0) return null;
        return new Monomial(coefficient.intValue()*degree,degree-1);
    }

    public Monomial integrate()
    {
        return new Monomial(coefficient.doubleValue()/(degree+1),degree+1);
    }

    public String toStringDouble()
    {
        if(coefficient.intValue()== coefficient.doubleValue())
            return this.toStringInteger();

        String result;
        double newcoef=coefficient.doubleValue();
        if(newcoef<0)  newcoef= -newcoef;

        String coef=""+newcoef;
        int point=coef.indexOf('.');
        if(coef.length()>point+3)
            coef=coef.substring(0,point+3);
        if(degree==0)
            result=""+coef;
        else if(degree!=1)
            result=coef+ "x^"+degree;
        else result=coef+"x";
        return result;
    }

    private String toStringInteger()
    {
        String result;
        int coeff=coefficient.intValue();
        if(coeff<0) coeff= -coeff;

        if(coeff!=1)
        {
            if(degree==1)
                result=""+coeff+"x";
            else if(degree==0)
                result=""+coeff;
                 else
                     result=""+coeff+"x^"+degree;
        }
        else if(degree==0) result=""+coeff;
             else if (degree==1)  result="x";
                  else result="x^"+degree;
        return result;
    }


    @Override
    public int compareTo(Monomial o) {
        if(this.degree>o.getDegree()) return 1;
        if(this.degree<o.getDegree()) return -1;
        return 0;
    }
}
