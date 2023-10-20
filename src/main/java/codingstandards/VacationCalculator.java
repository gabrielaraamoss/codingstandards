package codingstandards;

import java.util.Scanner;

public class VacationCalculator {


	    private static final int BASE_COST = 1000;
	    private static final int MAX_TRAVELERS = 80;
	     //CHECKSTYLE:OFF
	    public static void main(String[] args) {
	    	//CHECKSTYLE:ON
	        Scanner scanner = new Scanner(System.in);

	        System.out.print("Enter the destination of the vacation: ");
	        String destination = scanner.nextLine().trim();

	        System.out.print("Enter the number of travelers: ");
	        int numberOfTravelers;
	        try {
	            numberOfTravelers = Integer.parseInt(scanner.nextLine());
	        } catch (NumberFormatException e) {
	            System.out.println("Invalid number of travelers.");
	            return;
	        }

	        System.out.print("Enter the duration of the vacation in days: ");
	        int duration;
	        try {
	            duration = Integer.parseInt(scanner.nextLine());
	        } catch (NumberFormatException e) {
	            System.out.println("Invalid duration.");
	            return;
	        }

	        int result = calculateCost(destination, numberOfTravelers, duration);
	        System.out.println(result == -1 ? "Invalid input data." : "Total cost: $" + result);
	    }

	    public static int calculateCost(String destination, int numberOfTravelers, int duration) {
	        if (numberOfTravelers <= 0 || numberOfTravelers > MAX_TRAVELERS || duration <= 0) {
	            return -1;
	        }

	        int totalCost = BASE_COST;

	        switch (destination) {
	            case "Paris":
	                totalCost += 500;
	                break;
	            case "NewYorkCity":
	                totalCost += 600;
	                break;
	            default:
	                break;
	        }

	        if (numberOfTravelers > 4 && numberOfTravelers <= 10) {
	            totalCost = (int) (totalCost * 0.9);
	        } else if (numberOfTravelers > 10) {
	            totalCost = (int) (totalCost * 0.8);
	        }

	        if (duration < 7){
	            totalCost += 200;
	        }

	        if (duration > 30 || numberOfTravelers == 2) {
	            totalCost -= 200;
	        }

	        return totalCost;
	    }
}
