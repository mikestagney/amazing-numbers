package numbers;

import java.util.HashSet;
import java.util.Set;

public class AmazingNums {

    public static boolean isNumberEven(long number) {
        return number % 2 == 0;
    }
    public static boolean isBuzzNumber(long number) {
        boolean isDivisibleBySeven = number % 7 == 0;
        boolean endsWithSeven = number % 10 == 7;

        return isDivisibleBySeven || endsWithSeven;
    }
    public static boolean isDuckNumber(long number) {
        boolean isDuckNumber = false;
        long temp = number;
        while (temp > 0) {
            if (temp % 10 == 0) {
                isDuckNumber = true;
                break;
            }
            temp /= 10;
        }
        return isDuckNumber;
    }
    public static boolean isPalindrome(long number) {
        boolean isPalindrome = true;
        String numberText = Long.toString(number);
        int numberLength = numberText.length();
        if (numberLength > 1) {
            int counter = 0;
            for (int i = numberLength - 1; i >= numberLength / 2; i--) {
                if (numberText.charAt(counter) != numberText.charAt(i)) {
                    isPalindrome = false;
                    break;
                }
                counter++;
            }
        }
        return isPalindrome;
    }
    public static boolean isGapful(long number) {
        boolean isGapful = false;
        if (number >= 100) {
            long secondDigit = number % 10;
            long firstDigit = number;
            while (firstDigit >= 10) {
                firstDigit /= 10;
            }
            long divisor = firstDigit * 10 + secondDigit;
            if (number % divisor == 0) {
                isGapful = true;
            }
        }
        return isGapful;
    }
    public static boolean isSpy(long number) {
        long temp = number;
        long sumDigits = 0;
        long productDigits = 1;
        while (temp > 0) {
            sumDigits += temp % 10;
            productDigits *= temp % 10;
            temp /= 10;
        }
        return sumDigits == productDigits;
    }
    public static boolean isSquare(long number) {
        return isPerfectSquare(number);
    }
    public static boolean isSunny(long number) {
        return isPerfectSquare(number + 1);
    }
    public static boolean isJumping(long number) {
        boolean isJumping = true;
        long temp = number;

        long firstDigit = temp % 10;
        temp /= 10;
        while (temp >= 1) {
            long secondDigit = temp % 10;
            if (Math.abs(firstDigit - secondDigit) != 1) {
                isJumping = false;
                break;
            }
            firstDigit = secondDigit;
            temp /= 10;
        }
        return isJumping;
    }
    public static boolean isHappy(long number) {
        Set<Long> numberChain = new HashSet<>();
        while (number > 1) {
            long digit;
            long sumDigitSquares = 0;

            while (number >=1) {
                digit = number % 10;
                sumDigitSquares += (long) Math.pow(digit, 2);
                number /= 10;
            }
            number = sumDigitSquares;
            if (numberChain.contains(number)) {
                break;
            }
            numberChain.add(number);
        }
        return number < 2;
    }
    private static boolean isPerfectSquare(long n) {
        double square = Math.sqrt(n);
        return square == (long)square;
    }
}
