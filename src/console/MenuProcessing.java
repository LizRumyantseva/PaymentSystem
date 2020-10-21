package console;

import domain.Client;
import domain.Card;
import services.CardService;
import services.ClientService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
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
        System.out.println(" 3 - Delete a card by its number");
        System.out.println(" 4 - Delete a client by its id");
        System.out.println(" 5 - Show all cards");
        System.out.println(" 6 - Show all clients");
        System.out.println(" 7 - Get client cards");
        System.out.println(" 8 - Make purchase/receive money to a card");
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
            chosenMode = chooseMode(scan, "Input mode 0 - 8: ", 0, 8);

            switch (chosenMode) {
                case 0:
                    scan.close();
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
                    } catch (ParseException e) {
                        System.err.println("Введите дату в правильном формате!");
                    }
                    break;
                }
                case 2: {
                    try {
                        System.out.println("");
                        System.out.println("Adding a new card to the client...");
                        System.out.print(" Input client id: ");
                        int clientId = 0;
                        if (scan.hasNextInt()) {
                            clientId = scan.nextInt();
                        } else {
                            System.out.println("введите корректный ид");
                            scan.next();
                            break;
                        }
                        Client foundedClient = clients.findClientById(clientId);
                        if (foundedClient != null) {
                            System.out.print(" Input card number: ");
                            String number = scan.next();
                            boolean isNumeric = number.chars().allMatch(Character::isDigit);
                            if (isNumeric) {
                                System.out.print(" Input expiry date: ");
                                String expiryDate = scan.next();
                                SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
                                Date y = s.parse(expiryDate);

                                Card newCard = new Card(number, y, clientId);
                                cards.add(newCard);
                                System.out.println("");
                            } else {
                                System.out.println("Номер карты должен состоять из цифр");
                                break;
                            }
                        }
                    } catch (ParseException e) {
                        System.err.println("Введите дату в правильном формате!");
                    }
                    break;
                }
                case 3: {
                    System.out.println("");
                    System.out.println("Deleting a card by its number...");
                    System.out.print(" Input card number: ");
                    String card_number = scan.next();
                    boolean isNumeric = card_number.chars().allMatch(Character::isDigit);
                    if (isNumeric) {
                        cards.delete(card_number);
                        System.out.println("");
                    } else {
                        System.out.println("введите корректный ид");
                        //scan.next();
                        break;
                    }
                    break;
                }
                case 4: {
                    System.out.println("");
                    System.out.println("Deleting a client by its id...");
                    System.out.print(" Input client id: ");
                    int client_id = 0;
                    if (scan.hasNextInt()) {
                        client_id = scan.nextInt();
                    } else {
                        System.out.println("введите корректный ид клиента");
                        scan.next();
                        break;
                    }
                    clients.delete(client_id);
                    System.out.println("");
                    break;
                }
                case 5: {
                    System.out.println("");
                    System.out.println("Show all cards...");
                    List<Card> foundedCards = cards.getAll();
                    foundedCards.forEach(System.out::println);
                    break;
                }
                case 6: {
                    System.out.println("");
                    System.out.println("Show all clients...");
                    List<Client> foundedClients = clients.getAll();
                    foundedClients.forEach(System.out::println);
                    break;
                }
                case 7: {
                    System.out.println("");
                    System.out.println("Display cards by client id...");
                    System.out.print(" Input client id: ");
                    int client_id = 0;
                    if (scan.hasNextInt()) {
                        client_id = scan.nextInt();
                    } else {
                        System.out.println("введите корректный ид клиента");
                        scan.next();
                        break;
                    }
                    List<Card> foundedCards = clients.getClientCards(client_id);
                    foundedCards.forEach(System.out::println);
                    break;
                }
                case 8: {
                    System.out.println("");
                    System.out.println("Make purchase/receive money to a card...");
                    System.out.print(" Input card id: ");
                    int card_id = 0;
                    if (scan.hasNextInt()) {
                        card_id = scan.nextInt();
                    } else {
                        System.out.println("введите корректный ид карты");
                        scan.next();
                        break;
                    }
                    System.out.print(" Input sum: ");
                    double sum = 0;
                    if (scan.hasNextDouble()) {
                        sum = scan.nextDouble();
                    } else {
                        System.out.println("введите корректную сумму операции");
                        scan.next();
                        break;
                    }
                    cards.receiveMoney(card_id, sum);
                    break;
                }
            }
            printPressEnterMessage();
        }
    }
}
