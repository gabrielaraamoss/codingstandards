package codingstandards;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * The VacationCalculator class allows users to calculate 
 * the total cost of a vacation package
 * with optional add-ons, based on user input.
 */
public final class VacationCalculator {

    /**
     * BASE COST
     */
    private static final int BASE_COST = 1000;
    /**
     * MAX TRAVELERS
     */
    private static final int MAX_TRAVELERS = 80;
    /**
     * ALL PACKAGE COST
     */
    private static final int ALL_PACKAGE_COST = 200;
    /**
     * ADVENTURE PACKAGE COST
     */
    private static final int ADVENTURE_PACKAGE = 150;
    /**
     * SPAW AND WELLNESS PACKAGE COST
     */
    private static final int SPAW_PACKAGE = 100;
    /**
     * SHORT DURATION VALUE
     */
    private static final int SHORT_DURATION = 7;
    /**
     * LONG DURATION VALUE
     */
    private static final int LONG_DURATION = 30;
    /**
     * PARIS NAME
     */
    private static final String D_PARIS = "Paris";
    /**
     * NEW YORK NAME
     */
    private static final String D_NEWYORK = "NewYorkCity";
    /**
     *  PARIS COST
     */
    private static final int COST_PARIS = 500;
    /**
     * NEW YORK COST
     */
    private static final int COST_NEW_YORK = 600;
    /**
     * MIN TRAVELERS FOR DISCOUNT
     */
    private static final int MIN_TRAVELERS_D = 4;
    /**
     * MAX TRAVELERS FOR DISCOUNT
     */
    private static final int MAX_TRAVELERS_D = 10;

    // Private constructor to prevent instantiation
    private VacationCalculator() {
        // This constructor is private to prevent the instantiation of this utility class.
    }
    
    /**
     * Main method for input and calculation of vacation package cost.
     * @param args Command line arguments (not used in this application).
     */
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            final Logger log = Logger.getLogger(VacationCalculator.class.getName());
            
            log.fine("Enter the destination of the vacation: ");
            final String destination = scanner.nextLine().trim();

            log.fine("Enter the number of travelers: ");
            int numberOfTravelers;
            try {
                numberOfTravelers = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                log.fine("Invalid number of travelers.");
                numberOfTravelers = -1;  
            }

            log.fine("Enter the duration of the vacation in days: ");
            int duration;
            try {
                duration = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                log.fine("Invalid duration.");
                return;
            }

            log.fine("Do you want to add any package (All-Inclusive/Adventure Activities/Spa and Wellness)? Enter the number (0 for none): ");
            final int selectedPackage = Integer.parseInt(scanner.nextLine());

            if (log.isLoggable(Level.FINE)) {
                final int result = calculateCost(destination, numberOfTravelers, duration, selectedPackage);
                log.fine(result == -1 ? "Invalid input data." : "Total cost: $" + result);
            }
        }
    }

    /**
     * Calculates the total cost of a vacation package including optional add-ons.
     * @param destination The destination of the vacation.
     * @param numberOfTravelers The number of travelers.
     * @param duration The duration of the vacation in days.
     * @param selectedPackage The selected add-on package number (0 for none).
     * @return The total cost of the vacation package.
     */
    public static int calculateCost(final String destination, final int numberOfTravelers, final int duration, final int selectedPackage) {
        int totalCost = -1;  

        if (isInputValid(numberOfTravelers, duration)) {
            totalCost = calculateBaseCost(destination);
            totalCost = applyTravelerDiscount(numberOfTravelers, totalCost);
            totalCost = applyDurationCost(duration, totalCost);
            totalCost += calculatePackageCost(numberOfTravelers, selectedPackage);
        }

        return totalCost; 
    }

    private static boolean isInputValid(final int numberOfTravelers, final int duration) {
        return numberOfTravelers > 0 && numberOfTravelers <= MAX_TRAVELERS && duration > 0;
    }

    private static int calculateBaseCost(final String destination) {
        int baseCost = BASE_COST;

        if (D_PARIS.equals(destination)) {
            baseCost += COST_PARIS;
        } else if (D_NEWYORK.equals(destination)) {
            baseCost += COST_NEW_YORK;
        }

        return baseCost;
    }

    private static int applyTravelerDiscount(final int numberOfTravelers, final int totalCost) {
        int discountedCost = totalCost;

        if (numberOfTravelers > MIN_TRAVELERS_D && numberOfTravelers <= MAX_TRAVELERS_D) {
            discountedCost = (int) (totalCost * 0.9);
        } else if (numberOfTravelers > MAX_TRAVELERS_D) {
            discountedCost = (int) (totalCost * 0.8);
        }

        return discountedCost;
    }



    private static int applyDurationCost(final int duration, final int totalCost) {
        int costModifier = 0;
        final int shortDurationCost = 200; // Constante descriptiva
        final int longDurationCost = -200; // Constante descriptiva

        if (duration < SHORT_DURATION) {
            costModifier = shortDurationCost;
        } else if (duration > LONG_DURATION) {
            costModifier = longDurationCost;
        }

        return totalCost + costModifier;
    }



    private static int calculatePackageCost(final int numberOfTravelers,final int selectedPackage) {
        int packageCost = 0;

        switch (selectedPackage) {
            case 1:
                packageCost = numberOfTravelers * ALL_PACKAGE_COST;
                break;
            case 2:
                packageCost = numberOfTravelers * ADVENTURE_PACKAGE;
                break;
            case 3:
                packageCost = numberOfTravelers * SPAW_PACKAGE;
                break;
            default:
                break;
        }

        return packageCost;
    }


}
