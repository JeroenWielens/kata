package tdd1;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class StringCalcTest {

    StringCalc stringCalc;

    @Before
    public void init() {
        stringCalc = new StringCalc();
    }

    @Test
    public void testAddMethodEmptyInput() {
        Assert.assertEquals(0, stringCalc.add(""));
    }

    @Test
    public void testAddMethodSingleInput() {
        Assert.assertEquals(2, stringCalc.add("2"));
    }

    @Test
    public void testAddMethodDoubleInput() {
        Assert.assertEquals(5 ,stringCalc.add("2,3"));
    }

    @Test
    public void testAddMethodUnknownInput() {
        Assert.assertEquals(15, stringCalc.add("1,2,3,4,5"));
    }

    @Test
    public void testAddMethodMultipleDelimiters() {
        Assert.assertEquals(6, stringCalc.add("1\n2,3"));
    }

    @Test
    public void testAddMethodDifferentDilimiter() {
        Assert.assertEquals(3, stringCalc.add("//[;]\n1;2"));
    }

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void testAddMethodNegativeNumber() {
        expectedException.expect(RuntimeException.class);
        expectedException.expectMessage("Negatives not allowed: -1");
        stringCalc.add("-1");
    }

    @Test
    public void testAddMethodMultipleNegativeNumbers() {
        expectedException.expect(RuntimeException.class);
        expectedException.expectMessage("Negatives not allowed: -1, -3, -5");
        stringCalc.add("-1,-3,-5");
    }

    @Test
    public void testAddMethodIgnoreBigInts() {
        Assert.assertEquals(2, stringCalc.add("2,1001"));
    }

    @Test
    public void testAddMethodExtendedDelimiters() {
        Assert.assertEquals(6, stringCalc.add("//[***]\n1***2***3"));
    }

    @Test
    public void testAddMethodExtendedDelimiters2() {
        Assert.assertEquals(6, stringCalc.add("//[%%]\n1%%2%%3"));
    }

    @Test
    public void testAddMethodMultipleExtendedDelimiters() {
        Assert.assertEquals(12, stringCalc.add("//[%][*]\n2%4*6"));
    }

    @Test
    public void testAddMethodMultipleExtendedDelimitersMoreChars() {
        Assert.assertEquals(12, stringCalc.add("//[%%][**][###]\n2%4*6"));
    }
    @Test
    public void testAddMethodStrangeChars() {
        Assert.assertEquals(14, stringCalc.add("//[%@!][&^*][###]\n2%@!4&^*8"));
    }
}