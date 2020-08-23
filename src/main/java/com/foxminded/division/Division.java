package com.foxminded.division;

public class Division {

    private StringBuilder outputString = new StringBuilder();
    private StringBuilder quotientString = new StringBuilder();
    private StringBuilder incompleteDividendString= new StringBuilder();

    public void integerDivision(int dividend, int divisor) {

        if (divisor == 0) {
            throw new IllegalArgumentException("Division by zero!");
        }

        dividend = Math.abs(dividend);
        divisor = Math.abs(divisor);

        String[] dividendDigits = String.valueOf(dividend).split("");
        Integer incompleteDividendNumber;
        Integer nearestMultiplicationNumber;
        Integer remainder;
        Integer incompleteQuotient;

        for (int i = 0; i < dividendDigits.length; i++) {
            incompleteDividendString.append(dividendDigits[i]);
            incompleteDividendNumber = Integer.parseInt(incompleteDividendString.toString());

            if (incompleteDividendNumber >= divisor) {

                incompleteQuotient = incompleteDividendNumber / divisor;
                remainder = incompleteDividendNumber % divisor;
                nearestMultiplicationNumber = incompleteDividendNumber / divisor * divisor;

                String lastRemainder = String.format("%" + (i + 2) + "s", "_" + incompleteDividendNumber.toString());
                outputString.append(lastRemainder).append("\n");

                String nearestMultiplicationString = String.format("%" + (i + 2) + "d", nearestMultiplicationNumber);
                outputString.append(nearestMultiplicationString).append("\n");

                Integer numberOfTabs = lastRemainder.length() - calculateDigit(nearestMultiplicationNumber);
                outputString.append(assemblySymbolString(numberOfTabs, ' ') + assemblySymbolString(calculateDigit(nearestMultiplicationNumber), '-')).append("\n");

                quotientString.append(incompleteQuotient);

                /*
                Transformation incompleteDividend for next cycle
                */
                incompleteDividendString.replace(0, incompleteDividendString.length(), remainder.toString());
                incompleteDividendNumber = Integer.parseInt(incompleteDividendString.toString());

            } else {

            	/*
            	The condition to exclude the appearance of 0 at the first position in the quotient
            	*/
                if (i >= calculateDigit(divisor))
                    quotientString.append(0);
            }

            /*
            The condition for the last line
            */
            if (i == dividendDigits.length - 1) {

                /*
                If the last line is zero it shouldn't move to the right
                */
                if (incompleteDividendNumber == 0 && dividend != 0) {
                    int numberOfZeroInEndOfQuotion = 0;
                    int quotientDividedByTen = Integer.parseInt(quotientString.toString());
                    while (quotientDividedByTen % 10 == 0) {
                        numberOfZeroInEndOfQuotion++;
                        quotientDividedByTen /= 10;
                    }
                    outputString.append(String.format("%" + (i + 2 - numberOfZeroInEndOfQuotion) + "s", incompleteDividendNumber.toString())).append("\n");
                } else

                    outputString.append(String.format("%" + (i + 2) + "s", incompleteDividendNumber.toString())).append("\n");
            }
        }

        assemblyThreeTopLines(dividend, divisor);

        System.out.print(finalAssemblyOutputString(dividend, divisor) + "\n");

        /*
        Clearing strings for next method call
        */
        outputString.delete(0, outputString.length());
        quotientString.delete(0, quotientString.length());
        incompleteDividendString.delete(0, incompleteDividendString.length());
    }



    private int calculateDigit(int i) {
        if (i == 0) return 1;
        return (int) Math.log10(i) + 1;
    }



    private String assemblySymbolString(int numberOfSymbols, char symbol) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < numberOfSymbols; i++) {
            sb.append(symbol);
        }
        return sb.toString();
    }



    private void assemblyThreeTopLines(Integer dividend, Integer divisor) {
        int[] index = new int[3];
        for (int i = 0, j = 0; i < outputString.length(); i++) {
            if (outputString.charAt(i) == '\n') {
                index[j] = i;
                j++;
            }

            if (j == 3) {
                break;
            }
        }

        int tabs = calculateDigit(dividend) + 1 - index[0];
        outputString.insert(index[2], assemblySymbolString(tabs, ' ') + "|" + quotientString.toString());
        outputString.insert(index[1], assemblySymbolString(tabs, ' ') + "|" + assemblySymbolString(quotientString.length(), '-'));
        outputString.insert(index[0], "|" + divisor);
        outputString.replace(1, index[0], dividend.toString());
    }



    private String finalAssemblyOutputString(int dividend, int divisor) {
        if (dividend < divisor) {
            int numbersOfDigitsOfTheDividend = calculateDigit(dividend);
            int numbersOfDigitsOfTheDivisor = calculateDigit(divisor);
            outputString.replace(0, outputString.length(), dividend + "|" + divisor + "\n" + assemblySymbolString(numbersOfDigitsOfTheDividend, ' ') + "|" + assemblySymbolString(numbersOfDigitsOfTheDivisor, '-') + "\n" + assemblySymbolString(numbersOfDigitsOfTheDividend, ' ') + "|" + 0 + "\n");
            return outputString.toString();

        } else
            return outputString.toString();

    }
}
