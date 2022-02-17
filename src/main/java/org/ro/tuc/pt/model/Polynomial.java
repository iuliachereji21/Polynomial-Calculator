package org.ro.tuc.pt.model;

import java.util.ArrayList;
import java.util.Collections;

public class Polynomial {
    private ArrayList<Monomial> monomials= new ArrayList<Monomial>();

    public Polynomial()
    {
        super();
    }
    public Polynomial(ArrayList<Monomial> list)
    {
        monomials=list;
        this.simplify();
    }

    public Polynomial add(Polynomial pol)
    {
        if(pol==null) return null;

        ArrayList<Monomial>monomials2= pol.getMonomials();

        ArrayList<Monomial>newList = new ArrayList<Monomial>();
        for(Monomial mon:monomials)
            newList.add(mon);
        for(Monomial mon2: monomials2)
        {
            newList.add(mon2);
        }

        Polynomial rez= new Polynomial(newList);
        rez.simplify();
        return rez;
    }

    public Polynomial subtract(Polynomial pol)
    {
        if(pol==null) return null;

        ArrayList<Monomial>monomials2= pol.getMonomials();

        ArrayList<Monomial>newList = new ArrayList<Monomial>();
        for(Monomial mon:monomials)
            newList.add(mon);
        for(Monomial mon2: monomials2)
        {
            newList.add(new Monomial(mon2.getCoefficient().doubleValue()*(-1),mon2.getDegree()));
        }

        Polynomial rez= new Polynomial(newList);
        rez.simplify();
        return rez;
    }

    public Polynomial multiply(Polynomial pol)
    {
        if(pol==null) return null;

        ArrayList<Monomial>monomials2= pol.getMonomials();

        ArrayList<Monomial>newList = new ArrayList<Monomial>();

        for(Monomial mon:monomials)
        {
            for(Monomial mon2: monomials2)
            {
                newList.add(mon.multiply(mon2));
            }
        }
        Polynomial rez = new Polynomial(newList);
        rez.simplify();
        return rez;
    }

    public Polynomial derivate()
    {
        ArrayList<Monomial>newList = new ArrayList<Monomial>();
        Monomial newM;
        for(Monomial mon: monomials)
        {
            newM=mon.derivate();
            if(newM!=null)
                newList.add(newM);
        }
        return new Polynomial(newList);
    }
    public Polynomial integrate()
    {
        ArrayList<Monomial>newList = new ArrayList<Monomial>();
        Monomial newM;
        for(Monomial mon: monomials)
                newList.add(mon.integrate());
        return new Polynomial(newList);
    }

    public Polynomial[] divide(Polynomial pol)
    {
        Polynomial[] rezult= new Polynomial[2]; //[0] quotient, [1] remainder
        ArrayList<Monomial>quotient = new ArrayList<Monomial>();
        Polynomial deimpartit=new Polynomial(monomials);

        int deg1=deimpartit.getMonomials().get(0).getDegree();
        int deg2=pol.getMonomials().get(0).getDegree();
        if(deg2>deg1)
        {
            rezult[0]=new Polynomial();
            rezult[1]=this;
        }
        else{
            while(deg1>=deg2)
            {
                Monomial mon1=deimpartit.getMonomials().get(0);
                Monomial mon2=pol.getMonomials().get(0);
                Monomial q=mon1.divide(mon2);
                quotient.add(q);
                ArrayList<Monomial>toMultiply = new ArrayList<Monomial>();
                toMultiply.add(q);
                deimpartit=deimpartit.subtract(pol.multiply(new Polynomial(toMultiply)));
                if(deimpartit.getMonomials().size()>=1)
                    deg1=deimpartit.getMonomials().get(0).getDegree();
                else break;
            }
        }
        rezult[0]=new Polynomial(quotient);
        rezult[1]=deimpartit;
        return rezult;
    }

    private void simplify()
    {
        ArrayList<Monomial>newList = new ArrayList<Monomial>();
        boolean[] degreespozitive = new boolean[10000];
        boolean[] degreesnegative = new boolean[10000];
        for(int j=0; j<10000; j++) {
            degreespozitive[j]=false;
            degreesnegative[j]=false;
        }
        int i=0;
        for (Monomial mon: monomials) {
            double coeff = mon.getCoefficient().doubleValue();
            int deg= mon.getDegree();
            if((deg>=0 && !degreespozitive[deg]) || (deg<0 && !degreesnegative[-deg])){ //we haven't done this degree yet
                for(Monomial mon2: monomials.subList(i+1,monomials.size())){
                    if(deg == mon2.getDegree())
                        coeff+= mon2.getCoefficient().doubleValue();
                }
                if (deg>=0) degreespozitive[deg]=true;
                else degreesnegative[-deg]=true;

                if(coeff!=0){
                    if((int)coeff==coeff)
                        newList.add(new Monomial((int)coeff, deg));
                    else newList.add(new Monomial(coeff, deg));}
            }
            i++;
        }
        this.monomials=newList;
        this.arrange();
    }

    private void arrange()
    {
        Collections.sort(monomials,Collections.<Monomial>reverseOrder());
    }

    public String toString()
    {
        String result = "";
        if (monomials.size()==0)
            result=result+"0";
        else
        {
            boolean first=true;
            for(Monomial mon: monomials)
            {
                if(first)
                {
                    if(mon.getCoefficient().doubleValue()<0)
                        result=result+"- "+mon.toStringDouble();
                    else
                        result=result+mon.toStringDouble();
                    first=false;
                }
                else
                {
                    if(mon.getCoefficient().doubleValue()<0)
                        result=result+" - "+mon.toStringDouble();
                    else result=result+" + "+mon.toStringDouble();
                }
            }
        }
        return result;
    }

    public ArrayList<Monomial> getMonomials() {
        return monomials;
    }
}
