package numbers;

public class PropertiesCheck {

    public static boolean isTrue(String property, long number) {
        boolean isTrue = false;

        switch (property) {
            case "even": {
                isTrue = AmazingNums.isNumberEven(number);
                break;
            }
            case "odd": {
                isTrue = !AmazingNums.isNumberEven(number);
                break;
            }
            case "buzz": {
                isTrue = AmazingNums.isBuzzNumber(number);
                break;
            }
            case "duck": {
                isTrue = AmazingNums.isDuckNumber(number);
                break;
            }
            case "palindromic": {
                isTrue = AmazingNums.isPalindrome(number);
                break;
            }
            case "gapful": {
                isTrue = AmazingNums.isGapful(number);
                break;
            }
            case "spy": {
                isTrue = AmazingNums.isSpy(number);
                break;
            }
            case "square": {
                isTrue = AmazingNums.isSquare(number);
                break;
            }
            case "sunny": {
                isTrue = AmazingNums.isSunny(number);
                break;
            }
            case "jumping": {
                isTrue = AmazingNums.isJumping(number);
                break;
            }
            case "happy": {
                isTrue = AmazingNums.isHappy(number);
                break;
            }
            case "sad": {
                isTrue = !AmazingNums.isHappy(number);
                break;
            }
        }
        return isTrue;
    }

}
