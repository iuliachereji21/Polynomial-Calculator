package org.ro.tuc.pt;
import org.junit.Test;
import org.ro.tuc.pt.model.Monomial;
import org.ro.tuc.pt.model.Polynomial;
import java.util.ArrayList;

public class PolynomialTest {
    @Test
    public void toStringTest(){
        ArrayList<Monomial> monomials = new ArrayList<Monomial>();
        monomials.add(new Monomial(7, 2));
        monomials.add(new Monomial(-10, 2));
        monomials.add(new Monomial(5, 4));
        monomials.add(new Monomial(6, 0));
        Polynomial pol1 = new Polynomial(monomials);
        assert (pol1.toString().compareTo("5x^4 - 3x^2 + 6")==0);
    }
    @Test
    public void addTest(){
        ArrayList<Monomial> monomials1 = new ArrayList<Monomial>();
        monomials1.add(new Monomial(7, 2));
        monomials1.add(new Monomial(-10, 2));
        monomials1.add(new Monomial(5, 4));
        monomials1.add(new Monomial(6, 0));
        Polynomial pol1 = new Polynomial(monomials1); // 5x^4 - 3x^2 + 6
        ArrayList<Monomial> monomials2 = new ArrayList<Monomial>();
        monomials2.add(new Monomial(7, 3));
        monomials2.add(new Monomial(10, 2));
        monomials2.add(new Monomial(5, -1));
        monomials2.add(new Monomial(6, 0));
        Polynomial pol2 = new Polynomial(monomials2); //7x^3 + 10x^2 + 6 + 5x^-1
        assert (pol1.add(pol2).toString().compareTo("5x^4 + 7x^3 + 7x^2 + 12 + 5x^-1")==0);
    }
    @Test
    public void integrateTest(){
        ArrayList<Monomial> monomials1 = new ArrayList<Monomial>();
        monomials1.add(new Monomial(7, 2));
        monomials1.add(new Monomial(5, 4));
        monomials1.add(new Monomial(6, 0));
        Polynomial pol1 = new Polynomial(monomials1); // 5x^4 + 7x^2 + 6
        assert (pol1.integrate().toString().compareTo("x^5 + 2.33x^3 + 6x")==0);
    }
    @Test
    public void divideTest(){
        ArrayList<Monomial> monomials1 = new ArrayList<Monomial>();
        monomials1.add(new Monomial(1, 2));
        monomials1.add(new Monomial(7, 1));
        Polynomial pol1 = new Polynomial(monomials1); // x^2 + 7x
        ArrayList<Monomial> monomials2 = new ArrayList<Monomial>();
        monomials2.add(new Monomial(3, 1));
        monomials2.add(new Monomial(1, 0));
        Polynomial pol2 = new Polynomial(monomials2); // 3x + 1
        Polynomial[] result = pol1.divide(pol2);
        assert (result[0].toString().compareTo("0.33x + 2.22")==0); //quotient
        assert (result[1].toString().compareTo("- 2.22")==0); //remainder
    }
}
