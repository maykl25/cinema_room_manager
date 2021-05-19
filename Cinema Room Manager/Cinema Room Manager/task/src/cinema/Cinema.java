package cinema;
import java.util.Scanner;

/*Simple program that simulates work of ticket office in cinema.
User can buy tickets, choose the seat in cinema room, and even watch statistics about income.
 */

public class Cinema {
    public static Scanner scanner = new Scanner(System.in);
    public static int best_half;
    public static int worst_half;
    public static int ticket_Price;
    public static int amount;
    public static int rows;
    public static int seats;
    public static int purchasedTickets;
    public static double percentage;
    public static int totalIncome;
    public static int currentIncome;
    public static String option = "";
    public static String[][] cinema;

    /*
    method start() allows to set amount of seats and rows in cinema room and predicts total income.
     */

    public static void start() {
        System.out.println("Enter the number of rows:"); // Write your code here
        rows = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        seats = scanner.nextInt();
        amount = rows * seats;
        cinema = new String[rows + 1][seats + 1];
        for(int i = 0; i < cinema.length; i++) {
            for(int j = 0; j < cinema[i].length; j++) {
                if(i == 0 && j == 0) {
                    cinema[i][j] = " ";
                } else if(i == 0 && j != 0) {
                    cinema[i][j] = Integer.toString(j);
                } else if(i != 0 && j == 0) {
                    cinema[i][j] = Integer.toString(i);
                } else {
                    cinema[i][j] = "S";
                }
            }
        }

        if(amount <= 60) {
            totalIncome = (rows * seats) * 10;
        } else {
            best_half = rows / 2;
            worst_half = rows - best_half;
            int bestTotalIncome = (best_half * seats) * 10;
            int worstTotalIncome = (worst_half * seats) * 8;
            totalIncome = bestTotalIncome + worstTotalIncome;
        }
    }

    /*
    method show() shows the scheme of cinema room and allows to see free and occupied seats.
     */

    public static void show() {
        System.out.println("Cinema:");
        for(int i = 0; i < cinema.length; i++) {
            for(int j = 0; j < cinema[i].length; j++) {
                System.out.print(cinema[i][j] + " ");
            }
            System.out.println();
        }
    }

    /*
    method statistics() shows the number of purchased tickets, total percentage of occupied seats,
        current and total income.
     */

    public static void statistics() {
        System.out.println("Number of purchased tickets: " + purchasedTickets);
        System.out.println(String.format("Percentage: %.2f", percentage) + "%");
        System.out.println("Current income: $" + currentIncome);
        System.out.println("Total income: $" + totalIncome);
    }

    /*
    method buy() allows to buy tickets and checks either seat is occupied or not.
     */

    public static void buy() {
        boolean check = true;
        int row = 0;
        int seat = 0;
        while (check) {
            System.out.println("Enter a row number:");
             row = scanner.nextInt();
            System.out.println("Enter a seat number in that row:");
             seat = scanner.nextInt();

             if (row <= 0 || row > rows || seat <= 0 || seat > seats) {
                 System.out.println("Wrong input!");

             } else if (cinema[row][seat].equals("B")) {
                 System.out.println("That ticket has already been purchased!");

             } else {
                 check = false;
             }
        }

        cinema[row][seat] = "B";

        if (amount <= 60) {
            ticket_Price = 10;
            System.out.println("Ticket price: $" + ticket_Price);
        } else {
            best_half = rows / 2;
            if(row <= best_half) {
                ticket_Price = 10;
            } else {
                ticket_Price = 8;
            }
            System.out.println("Ticket price: $" + ticket_Price);
        }
        purchasedTickets++;
        currentIncome += ticket_Price;
        percentage = ((double)purchasedTickets  / amount) * 100;
        check = true;
    }

    /*
    method manager() provides a menu and allows to buy tickets, to see the scheme of cinema room etc.
     */

    public static void manager() {
        while (true) {
            System.out.println("1. Show the seats");
            System.out.println("2. Buy a ticket");
            System.out.println("3. Statistics");
            System.out.println("0. Exit");
            option = scanner.nextLine();

            switch(option) {
                case "1":
                    show();
                    break;
                case "2":
                    buy();
                    break;
                case "3":
                    statistics();
                    break;
                case "0":
                    return;
            }
        }
    }


    public static void main(String[] args) {
        start();
        manager();
    }
}