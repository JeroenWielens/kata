package tdd1;

public class StringCalc {

    int add(String numbers) {
        StringBuilder negativeNumbersSum = new StringBuilder();

        String customDelimiter = "";
        if (startsWithDoubleSlash(numbers)){
            customDelimiter = refactorDelimiter(numbers);
            String[] tempStr = splitTheStringByDelimiter(customDelimiter, "\n");
            customDelimiter = tempStr[0];
            numbers = tempStr[1];
        }
        StringBuilder regex = createRegexWithDelimiters(customDelimiter);
        String[] n1 = splitTheStringByDelimiter(numbers, regex.toString());
        int sum = getSum(negativeNumbersSum, n1);
        throwExceptionForNegativeNumbers(negativeNumbersSum);

        return sum;
    }

    private void throwExceptionForNegativeNumbers(final StringBuilder negativeNumbersSum) {
        if (negativeNumbersSum.length() != 0) {
            negativeNumbersSum.deleteCharAt(negativeNumbersSum.length() - 1);
            throw new RuntimeException("Negatives not allowed: " + negativeNumbersSum);
        }
    }

    private StringBuilder createRegexWithDelimiters(final String customDelimiter) {
        StringBuilder regex = new StringBuilder(",|\n");
        if (!customDelimiter.isEmpty()) {
            String[] tempDel = splitTheStringByDelimiter(customDelimiter, "]");
            for (String td : tempDel) {
                td = td.concat("]");
                regex.append("|").append(td);
            }
        }
        return regex;
    }

    private int getSum(final StringBuilder negativeNumbersSum, final String[] n1) {
        int sum = 0;
        for (String s : n1) {
            if (!s.isEmpty()) {
                if (itIsOverOnehousand(s)) {
                    break;
                }
                if (numberSmallerThanZero(s)) {
                    negativeNumbersSum.append(s).append(", ");
                }
                if (noNegativeNumbers(negativeNumbersSum)) {
                    sum = sum + Integer.valueOf(s);
                }
            }
        }
        return sum;
    }

    private boolean noNegativeNumbers(final StringBuilder negativeNumbersSum) {
        return negativeNumbersSum.length() == 0;
    }

    private boolean numberSmallerThanZero(final String s) {
        return Integer.valueOf(s) < 0;
    }

    private boolean itIsOverOnehousand(final String s) {
        return Integer.valueOf(s) > 1000;
    }

    private boolean startsWithDoubleSlash(final String numbers) {
        return numbers.startsWith("//");
    }

    private String[] splitTheStringByDelimiter(final String customDelimiter, final String s) {
        return customDelimiter.split(s);
    }

    private String refactorDelimiter(final String numbers) {
        return numbers.replaceFirst("//", "");
    }
}
