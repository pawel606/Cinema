import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number of rows:");
        int rows = sc.nextInt();
        System.out.println("Enter the number of seats in each row:");
        int seats = sc.nextInt();
        char[][] cinema = new char[rows][seats];

        for(int i = 0; i < cinema.length; i++) {
            for(int j = 0; j < cinema[i].length; j++) {
                cinema[i][j] = 'S';
            }
        }
        System.out.println();

        boolean menu = true;
        int x;
        while(menu) {
            System.out.println("1. Show the seats");
            System.out.println("2. Buy a ticket");
            System.out.println("3. Statistics");
            System.out.println("0. Exit");
            x = sc.nextInt();
            System.out.println();
            switch (x) {
                case 1:
                    showCinema(cinema);
                    System.out.println();
                    break;
                case 2:
                    buyTicket(cinema);
                    System.out.println();
                    break;
                case 3:
                    statistics(cinema);
                    System.out.println();
                    break;
                case 0:
                    menu = false;
                    break;
                default:
                    System.out.println("Niepoprawna wartosc");
            }
        }
    }
    public static void showCinema(char[][] cinema) {
        System.out.println("Cinema:");
        System.out.print(" ");
        for(int i = 0; i< cinema[0].length; i++){
            System.out.print(" " + (i+1));
        }
        System.out.println();

        for(int i = 0; i < cinema.length; i++) {
            for(int j = 0;j < cinema[i].length; j++) {
                if(j == 0) {
                    System.out.print(i+1 + " " + cinema[i][j]);
                }else {
                    System.out.print(" " + cinema[i][j]);
                }
            }
            System.out.println();
        }
    }
    public static void buyTicket(char[][] cinema) {
        boolean exit = true;
        Scanner sc = new Scanner(System.in);
        int income = 0;
        int half = cinema.length / 2;
        while(exit) {
            System.out.println("Enter a row number:");
            int rNumber = sc.nextInt();
            System.out.println("Enter a seat number in that row:");
            int sNumber = sc.nextInt();
            System.out.println();

            if(rNumber > cinema.length || rNumber <= 0 || sNumber > cinema[0].length || sNumber <= 0) {
                System.out.println("Wrong input!");
                System.out.println();
                continue;
            }

            if(cinema[rNumber - 1][sNumber - 1] == 'B') {
                System.out.println("That ticket has already been purchased!");
                System.out.println();
                continue;
            }
            if (cinema.length * cinema[0].length <= 60) {
                income = 10;
                cinema[rNumber - 1][sNumber - 1] = 'B';
                exit = false;
            } else {
                if (half >= rNumber) {
                    income = 10;
                    cinema[rNumber - 1][sNumber - 1] = 'B';
                    exit = false;
                } else {
                    income = 8;
                    cinema[rNumber - 1][sNumber - 1] = 'B';
                    exit = false;
                }
            }
        }
        System.out.println("Ticket price: $" + income);
    }
    public static void statistics(char[][] cinema) {
        int purchasedTickets = 0;
        int income = 0;
        int totalIncome = 0;
        int totalSeats = cinema.length * cinema[0].length;
        int half = cinema.length / 2;
        for(int i = 0; i < cinema.length; i++) {
            for(int j = 0; j < cinema[i].length; j++) {
                if(totalSeats <= 60) {
                    totalIncome += 10;
                }else  {
                    if(half >= i + 1) {
                        totalIncome += 10;
                    }else {
                        totalIncome +=8;
                    }
                }

                if(cinema[i][j] == 'B') {
                    purchasedTickets ++;
                    if(totalSeats <= 60) {
                        income += 10;
                    }else {
                        if(half >= i + 1) {
                            income += 10;
                        }else {
                            income += 8;
                        }
                    }
                }
            }
        }
        float percentage = (float) purchasedTickets / totalSeats * 100;
        String per = String.format("%.2f", percentage);
        System.out.println("Number of purchased tickets: " + purchasedTickets);
        System.out.println("Percentage: " + per + "%");
        System.out.println("Current income: $" + income);
        System.out.println("Total income: $" + totalIncome);
    }
}