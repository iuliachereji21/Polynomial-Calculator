package org.ro.tuc.pt;
import org.junit.Test;
import org.ro.tuc.pt.model.Monomial;

public class MonomialTest {
    @Test
    public void compareToTest() {
        Monomial mon1 = new Monomial(17, 2);
        Monomial mon2 = new Monomial(10, 3);
        assert(mon1.compareTo(mon2)==-1);
    }
    @Test
    public void toStringDoubleTest(){
        Monomial mon1 = new Monomial(17, 2);
        Monomial mon2 = new Monomial(-10, 0);
        //the minus sign shouldn't appear, only the absolute value of the coefficient,
        //because the signs are handled in the Polynomial class
        Monomial mon3 = new Monomial(2.15, -2);
        assert(mon1.toStringDouble().compareTo("17x^2")==0);
        assert(mon2.toStringDouble().compareTo("10")==0);
        assert(mon3.toStringDouble().compareTo("2.15x^-2")==0);
    }
    @Test
    public void addTest(){
        Monomial mon1 = new Monomial(17, 2);
        Monomial mon2 = new Monomial(-10, 2);
        assert(mon1.add(mon2).toStringDouble().compareTo("7x^2")==0);
    }
    @Test
    public void multiplyTest(){
        Monomial mon1 = new Monomial(17, 2);
        Monomial mon2 = new Monomial(10, -3);
        assert(mon1.multiply(mon2).toStringDouble().compareTo("170x^-1")==0);
    }
    @Test
    public void derivateTest(){
        Monomial mon1 = new Monomial(7, 2);
        assert(mon1.derivate().toStringDouble().compareTo("14x")==0);
    }
}
