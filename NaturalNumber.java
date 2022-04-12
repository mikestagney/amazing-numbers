package numbers;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.StringJoiner;

public class NaturalNumber {

    private final HashMap<String, Boolean> properties;
    private final long number;
    private HashMap<String, String> mutuallyExclusiveProperties;

    NaturalNumber(long n) {
        this.number = n;
        properties = new LinkedHashMap<>();
        initializeMutuallyExclusiveProperties();
        setProperties();
    }
    private void initializeMutuallyExclusiveProperties() {
        mutuallyExclusiveProperties = new HashMap<>();
        mutuallyExclusiveProperties.put("even", "odd");
        mutuallyExclusiveProperties.put("odd", "even");
        mutuallyExclusiveProperties.put("sunny", "square");
        mutuallyExclusiveProperties.put("square", "sunny");
        mutuallyExclusiveProperties.put("spy", "duck");
        mutuallyExclusiveProperties.put("duck", "spy");
        mutuallyExclusiveProperties.put("happy", "sad");
        mutuallyExclusiveProperties.put("sad", "happy");
    }
    private void setProperties() {
        setNumberParity();
        setBuzzNumber();
        setDuckNumber();
        setPalindromic();
        setGapful();
        setSpy();
        setSquare();
        setSunny();
        setJumping();
        setHappyAndSad();
    }
    private void setNumberParity() {
        boolean isNumberEven = AmazingNums.isNumberEven(number);
        properties.put("even", isNumberEven);
        properties.put("odd", !isNumberEven);
    }
    private void setBuzzNumber() {
        boolean isBuzzNumber = AmazingNums.isBuzzNumber(number);
        properties.put("buzz", isBuzzNumber);
    }
    private void setDuckNumber() {
        boolean isDuckNumber = AmazingNums.isDuckNumber(number);
        properties.put("duck", isDuckNumber);
    }
    private void setPalindromic() {
        boolean isPalindrome = AmazingNums.isPalindrome(number);
        properties.put("palindromic", isPalindrome);
    }
    private void setGapful() {
        boolean isGapful = AmazingNums.isGapful(number);
        properties.put("gapful", isGapful);
    }
    private void setSpy() {
        boolean isSpy = AmazingNums.isSpy(number);
        properties.put("spy", isSpy);
    }
    private void setSquare() {
        boolean isSquare = AmazingNums.isSquare(number);
        properties.put("square", isSquare);
    }
    private void setSunny() {
        boolean isSunny = AmazingNums.isSunny(number);
        properties.put("sunny", isSunny);
    }
    private void setJumping() {
        boolean isJumping = AmazingNums.isJumping(number);
        properties.put("jumping", isJumping);
    }
    private void setHappyAndSad() {
        boolean isHappy= AmazingNums.isHappy(number);
        properties.put("happy", isHappy);
        properties.put("sad", !isHappy);
    }
    public void printPropertiesList() {
        System.out.printf("Properties of %d\n", number);
        for (var property : properties.entrySet()) {
            System.out.printf("\t\t%s: %s\n", property.getKey(), property.getValue());
        }
        System.out.println();
    }
    public void printTruePropertiesLine() {
        StringBuilder line = new StringBuilder();
        line.append("             ");
        line.append(number);
        line.append(" is ");
        int propertyCounter = 0;
        for (var property : properties.entrySet()) {
            if (property.getValue()) {
                if (propertyCounter > 0) {
                    line.append(", ");
                }
                line.append(property.getKey());
                propertyCounter++;
            }
        }
        System.out.println(line);
    }
    public boolean isValidProperty(String prop) {
        return properties.containsKey(prop);
    }
    public String getPropertiesList() {
        StringJoiner list = new StringJoiner(", ", "[", "]");
        for (var property : properties.entrySet()) {
            list.add(property.getKey().toUpperCase());
        }
        return list.toString();
    }
    public boolean isNotMutuallyExclusive(String firstProperty, String secondProperty) {
        boolean isExclusive = false;
        if (mutuallyExclusiveProperties.containsKey(firstProperty)) {
            if (mutuallyExclusiveProperties.get(firstProperty).equals(secondProperty)) {
                isExclusive = true;
            }
        }
        return isExclusive;
    }
  }
