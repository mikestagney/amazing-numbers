package numbers;

import java.util.Scanner;
import java.util.StringJoiner;

public class UserInterface {

    Scanner scanner;
    String[] input;
    NaturalNumber naturalNumber;

    UserInterface() {
       scanner = new Scanner(System.in);
    }

    public void start() {
        printInstructions();
        do {
            System.out.println("Enter a request: ");
            input = scanner.nextLine().split(" ");

            try {
                long number = Long.parseLong(input[0]);
                if (number == 0) {
                    System.out.println("Goodbye!");
                    break;
                }
                if (number < 0) {
                    throw new Exception();
                }
                switch (input.length) {
                    case 1: {
                        naturalNumber = new NaturalNumber(number);
                        naturalNumber.printPropertiesList();
                        break;
                    }
                    case 2: {
                        handleListRequest(number);
                        break;
                    }
                    default: {
                        propertiesSearch(number);
                        break;
                    }
                }
            } catch (Exception e) {
                System.out.println("The first parameter should be a natural number or zero");
                System.out.println();
            }
        } while (!input[0].equals("0"));
    }

    private void printInstructions() {
        System.out.println("Welcome to Amazing Numbers!");
        System.out.println();
        System.out.println("Supported requests:");
        System.out.println("- enter a natural number to know its properties;");
        System.out.println("- enter two natural numbers to obtain the properties of the list:");
        System.out.println("  * the first parameter represents a starting number;");
        System.out.println("  * the second parameter shows how many consecutive numbers are to be printed;");
        System.out.println("- two natural numbers and properties to search for;");
        System.out.println("- a property preceded by minus must not be present in numbers;");
        System.out.println("- separate the parameters with one space;");
        System.out.println("- enter 0 to exit.");
        System.out.println();
    }
    private void handleListRequest(long startingNumber) {
        try {
            long countList = Long.parseLong(input[1]);
            if (countList < 1) {
                throw new Exception();
            }
            for (int i = 0; i < countList; i++) {
                naturalNumber = new NaturalNumber(startingNumber + i);
                naturalNumber.printTruePropertiesLine();
            }
        } catch (Exception e) {
            System.out.println("The second parameter should be a natural number.");
            System.out.println();
        }
    }
    private void propertiesSearch(long number) {
        try {
            long howManyNumbers = Long.parseLong(input[1]);
            if (howManyNumbers < 1) {
                throw new Exception();
            }
            naturalNumber = new NaturalNumber(number);
            String[] properties = new String[input.length - 2];
            System.arraycopy(input, 2, properties, 0, input.length - 2);

            if (validProperties(properties) && areNotMutuallyExclusive(properties)) {
                long counter = 0;
                while (counter < howManyNumbers) {
                    if (allPropertiesTrue(properties, number)) {
                        naturalNumber = new NaturalNumber(number);
                        naturalNumber.printTruePropertiesLine();
                        counter++;
                    }
                    number++;
                }
            }
        } catch (Exception e) {
            System.out.println("The second parameter should be a natural number.");
            System.out.println();
        }
    }
    private boolean validProperties(String[] properties) {
        boolean areValid = true;
        StringJoiner invalidProperties = new StringJoiner(", ", "[", "]");
        int numberInvalid = 0;
        for (String property : properties) {
            property = property.charAt(0) == '-' ? property.substring(1) : property;
            if (!naturalNumber.isValidProperty(property.toLowerCase())) {
                areValid = false;
                invalidProperties.add(property.toUpperCase());
                numberInvalid++;
            }
        }
        if (!areValid) {
            if (numberInvalid < 2) {
                System.out.printf("The property %s is wrong.\n", invalidProperties);
            } else {
                System.out.printf("The properties %s are wrong.\n", invalidProperties);
            }
            System.out.println("Available properties: " + naturalNumber.getPropertiesList());
        }
        return areValid;
    }
    private boolean areNotMutuallyExclusive(String[] properties) {
        boolean areNotMutuallyExclusive = true;
        StringJoiner mutuallyExclusive = new StringJoiner(", ", "[", "]");
        for (String property : properties) {
            for (int j = 1; j < properties.length; j++) {
                String propertyOne = property.toLowerCase();
                String propertyTwo = properties[j].toLowerCase();
                if (propertyOne.charAt(0) == '-' && propertyTwo.charAt(0) == '-') {
                    propertyOne = propertyOne.substring(1);
                    propertyTwo = propertyTwo.substring(1);
                }
                if (naturalNumber.isNotMutuallyExclusive(propertyOne, propertyTwo)
                        || isSelfNegating(property.toLowerCase(), properties[j])) {
                    areNotMutuallyExclusive = false;
                    mutuallyExclusive.add(property.toUpperCase());
                    mutuallyExclusive.add(properties[j].toUpperCase());
                    break;
                }
            }
            if (!areNotMutuallyExclusive) {
                System.out.printf("The request contains mutually exclusive properties: %s\n", mutuallyExclusive);
                System.out.println("There are no numbers with these properties");
                break;
            }
        }
        return areNotMutuallyExclusive;
    }
    private boolean allPropertiesTrue(String[] properties, long number) {
        boolean allProperties = true;
        for (String property : properties) {
            boolean reverseBoolean = false;
            if (property.charAt(0) == '-') {
                property = property.substring(1);
                reverseBoolean = true;
            }
            if (PropertiesCheck.isTrue(property.toLowerCase(), number) == reverseBoolean) {
                allProperties = false;
                break;
            }
        }
        return allProperties;
    }
    private boolean isSelfNegating(String propertyOne, String propertyTwo) {
        return propertyOne.charAt(0) == '-' && propertyOne.substring(1).equals(propertyTwo)
                || propertyTwo.charAt(0) == '-' && propertyTwo.substring(1).equals(propertyOne);
    }
}
