package com.foxminded.division;

import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

class DivisionTest {

    protected ByteArrayOutputStream output;
    private PrintStream old;
    Division division;

    @BeforeEach
    void setUpStreams() {
        old = System.out;
        output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));
    }

    @BeforeEach
    void init() {
        division = new Division();
    }

    @AfterEach
    void cleanUpStreams() {
        System.setOut(old);
    }


    @Test
    void integerDivision_ShouldThrowExceptionWhenDivisorIsZero() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> division.integerDivision(10, 0));
    }

    @Test
    void integerDivision_ShouldDivideNumbersAndPrintExpectedStringWhenDividendIsZero() {
        division.integerDivision(0, 20);
        String expected =   "0|20\n" +
                            " |--\n" +
                            " |0\n" +
                            "\n";
        Assert.assertEquals(expected, output.toString());
    }

    @Test
    void integerDivision_ShouldDivideNumbersAndPrintExpectedStringWhenDividendAndDivisorAreSameSmallNumbers() {
        division.integerDivision(20, 20);
        String expected =   "_20|20\n" +
                            " 20|-\n" +
                            " --|1\n" +
                            "  0\n" +
                            "\n";
        Assert.assertEquals(expected, output.toString());
    }

    @Test
    void integerDivision_ShouldDivideNumbersAndPrintExpectedStringWhenDividendAndDivisorAreSameBigNumbers() {
        division.integerDivision(12560005, 12560005);
        String expected =   "_12560005|12560005\n" +
                            " 12560005|-\n" +
                            " --------|1\n" +
                            "        0\n" +
                            "\n";
        Assert.assertEquals(expected, output.toString());
    }

    @Test
    void integerDivision_ShouldDivideNumbersAndPrintExpectedStringWhenDividendIsMoreThanDivisor() {
        division.integerDivision(14, 9);
        String expected =   "_14|9\n" +
                            "  9|-\n" +
                            "  -|1\n" +
                            "  5\n" +
                            "\n";
        Assert.assertEquals(expected, output.toString());
    }

    @Test
    void integerDivision_ShouldDivideNumbersAndPrintExpectedStringWhenDividendIsMuchMoreThanDivisor() {
        division.integerDivision(14789, 20);
        String expected =   "_14789|20\n" +
                            " 140  |---\n" +
                            " ---  |739\n" +
                            "  _78\n" +
                            "   60\n" +
                            "   --\n" +
                            "  _189\n" +
                            "   180\n" +
                            "   ---\n" +
                            "     9\n" +
                            "\n";
        Assert.assertEquals(expected, output.toString());
    }

    @Test
    void integerDivision_ShouldDivideNumbersAndPrintExpectedStringWhenDividendIsLessThanDivisor() {
        division.integerDivision(45, 680);
        String expected =   "45|680\n" +
                            "  |---\n" +
                            "  |0\n" +
                            "\n";
        Assert.assertEquals(expected, output.toString());
    }

    @Test
    void integerDivision_ShouldDivideNumbersAndPrintExpectedStringWhenDividendIsMuchLessThanDivisor() {
        division.integerDivision(45, 68068712);
        String expected =   "45|68068712\n" +
                            "  |--------\n" +
                            "  |0\n" +
                            "\n";
        Assert.assertEquals(expected, output.toString());
    }

    @Test
    void integerDivision_ShouldDivideNumbersAndPrintExpectedStringWhenDividendIsMaxInteger() {
        division.integerDivision(2147483647, 95);
        String expected =   "_2147483647|95\n" +
                            " 190       |--------\n" +
                            " ---       |22605091\n" +
                            " _247\n" +
                            "  190\n" +
                            "  ---\n" +
                            "  _574\n" +
                            "   570\n" +
                            "   ---\n" +
                            "    _483\n" +
                            "     475\n" +
                            "     ---\n" +
                            "      _864\n" +
                            "       855\n" +
                            "       ---\n" +
                            "        _97\n" +
                            "         95\n" +
                            "         --\n" +
                            "          2\n" +
                            "\n";
        Assert.assertEquals(expected, output.toString());
    }

    @Test
    void integerDivision_ShouldDivideNumbersAndPrintExpectedStringWhenDivisorIsMaxInteger() {
        division.integerDivision(45, 2147483647);
        String expected =   "45|2147483647\n" +
                            "  |----------\n" +
                            "  |0\n" +
                            "\n";
        Assert.assertEquals(expected, output.toString());
    }

    @Test
    void integerDivision_ShouldDivideAbsoluteValuesOfNumbersAndPrintExpectedStringWhenDividendIsNegativeNumber() {
        division.integerDivision(-15244, 678);
        String expected =   "_15244|678\n" +
                            " 1356 |--\n" +
                            " ---- |22\n" +
                            " _1684\n" +
                            "  1356\n" +
                            "  ----\n" +
                            "   328\n" +
                            "\n";
        Assert.assertEquals(expected, output.toString());
    }

    @Test
    void integerDivision_ShouldDivideAbsoluteValuesOfNumbersAndPrintExpectedStringWhenDivisorIsNegativeNumber() {
        division.integerDivision(422, -8);
        String expected =   "_422|8\n" +
                            " 40 |--\n" +
                            " -- |52\n" +
                            " _22\n" +
                            "  16\n" +
                            "  --\n" +
                            "   6\n" +
                            "\n";
        Assert.assertEquals(expected, output.toString());
    }

    @Test
    void integerDivision_ShouldDivideAbsoluteValuesOfNumbersAndPrintExpectedStringWhenDivisorAndDividendAreNegativeNumbers() {
        division.integerDivision(-422, -8);
        String expected =   "_422|8\n" +
                            " 40 |--\n" +
                            " -- |52\n" +
                            " _22\n" +
                            "  16\n" +
                            "  --\n" +
                            "   6\n" +
                            "\n";
        Assert.assertEquals(expected, output.toString());
    }
}