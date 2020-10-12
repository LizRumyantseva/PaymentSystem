package console;

import domain.Client;
import domain.Card;
import services.CardService;
import services.ClientService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class MenuProcessing {
    static void exitMainMenu() {
        System.out.println("");
        System.out.println("You are leaving the menu...");
        System.exit(0);
    }

    static void printMainMenu() {
        System.out.println("");
        System.out.println("Main menu:");
        System.out.println(" 0 - Exit menu");
        System.out.println(" 1 - Add a client");
        System.out.println(" 2 - Add a card to the client");
    }

    static int chooseMode(Scanner scan, String message, int minPossibleMode, int maxPossibleMode) {
        int chosenMode = -1;
        do {
            System.out.print(message);
            if (scan.hasNextInt()) {
                chosenMode = scan.nextInt();
                if ((chosenMode >= minPossibleMode) & (chosenMode <= maxPossibleMode) | (chosenMode == 0)) {
                    return chosenMode;
                }
            } else {
                scan.nextLine();
            }
        } while (true);
    }

    static void printPressEnterMessage() {
        System.out.println("");
        System.out.println("Press Enter to return to menu...");
        try {
            System.in.read();
        } catch (Exception e) {
        }
    }

    static void launchMainMenu(ClientService clients, CardService cards) {
        Scanner scan = new Scanner(System.in);
        int chosenMode = -1;
        while (chosenMode != 0) {
            printMainMenu();
            chosenMode = chooseMode(scan, "Input mode 0 - 2: ", 0, 2);
            switch (chosenMode) {
                case 0:
                    exitMainMenu();
                case 1: {
                    try {
                        System.out.println("");
                        System.out.println("Adding a new client...");
                        System.out.print(" Input client first name: ");
                        String fname = scan.next();
                        System.out.print(" Input client last name: ");
                        String lname = scan.next();
                        System.out.print(" Input birth date: ");
                        String bdate = scan.next();
                        SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
                        Date y = s.parse(bdate);

                        Client newClient = new Client(fname, lname, y);
                        clients.add(newClient);
                        System.out.println("");
                        break;
                    }
                    catch (ParseException e) {
                        //throw new Exception("Введите дату в правильном формате!");
                        System.err.println("Введите дату в правильном формате!");
                    }
                }
                case 2: {
                    try {
                        System.out.println("");
                        System.out.println("Adding a new card to the client...");
                        System.out.print(" Input client id: ");
                        int clientId = scan.nextInt();
                        Client foundedClient = clients.findClientById(clientId);
                        if (foundedClient != null) {
                            System.out.print(" Input card number: ");
                            int number = scan.nextInt();
                            System.out.print(" Input expiry date: ");
                            String expiryDate = scan.next();
                            SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
                            Date y = s.parse(expiryDate);

                            Card newCard = new Card(number, y, clientId);
                            cards.add(newCard);
                            System.out.println("");
                        }
                        break;
                    } catch (ParseException e) {
                        //throw new Exception("Введите дату в правильном формате!");
                        System.err.println("Введите дату в правильном формате!");
                    }
                }
            }
            printPressEnterMessage();
        }
    }
}
