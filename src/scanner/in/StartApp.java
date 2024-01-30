package scanner.in;

import controllers.UserController;
import data.Operation;
import repositories.UserRepository;
import repositories.impl.UserRepositoryImpl;
import services.UserService;


import java.time.LocalDate;
import java.util.*;

public class StartApp {
    private int index = 0;
    public Scanner scan = new Scanner(System.in);

    UserRepository userRepository = new UserRepositoryImpl();
    UserService userService = new UserService(userRepository);
    UserController userController = new UserController(userService);

    public void run() {
        while (true) {
            try {
                System.out.print("""
                       Main menu:
                        ===> Registration
                        ===> Authorisation
                        ===> Admin
                        ===> Exit
                       Write it:\s""");
                Operation operation = Operation.valueOf(scan.nextLine().trim().toUpperCase());
                switch (operation) {
                    case REGISTRATION, REG -> registration();
                    case AUTHORISATION, AUTH -> authorisation();
                    //case ADMIN -> regAdmin();
                    case EXIT -> {
                        return;
                    }
                    default -> throw new IllegalStateException("Unexpected value: " + operation);
                }
            } catch (RuntimeException exception) {
                System.out.println("\n" + exception.getMessage());
            }
        }
    }

    public void registration() {
        System.out.print("\n=== Registration ===");
        try {
            System.out.print("\n-> Enter username: ");
            String enteredUsername = scan.nextLine();
            if (enteredUsername.length() < 4)
                throw new InputMismatchException("The password must have at least 4 characters.");
            if(!userController.isUserReged(enteredUsername)) {
                System.out.print("-> Enter password: ");
                String enteredPassword = scan.nextLine();
                if(enteredPassword.length() < 4)
                    throw new InputMismatchException("The password must have at least 4 characters.");
                if (isValidInput(enteredUsername, enteredPassword)) {
                    userController.createUser(enteredUsername, enteredPassword);
                    System.out.println("Registration successful.");
                } else throw new InputMismatchException("Invalid input. Please enter valid password.");
            }
            else  throw new InputMismatchException("User with name '" + enteredUsername + "' already exist.");
        } catch (RuntimeException exception) {
            System.out.println(exception.getMessage());
        }
    }

    private boolean isValidInput(String username, String password) {
        return !username.isEmpty() && !password.isEmpty()
                && !username.equals(" ") && !password.equals(" ")
                && isNonNegativeInteger(password);
    }
    private boolean isNonNegativeInteger(String value) {
        try {
            int intValue = Integer.parseInt(value);
            return intValue >= 0;
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }


    public void authorisation() {
        System.out.println("\n===Authorisation===");

        if (userController.getUserService().getAllUsers().isEmpty()) {
            System.out.println("No user has been added.");
            return;
        }
        try {
            System.out.print("-> Enter username: ");
            String enteredUsername = scan.nextLine();

            if (!userController.getUser(enteredUsername).getUsername().isEmpty()) {
                System.out.print("-> Enter password: ");
                String enteredPassword = scan.nextLine();

                if (isValidInput(enteredUsername, enteredPassword)) {
                    if (Objects.equals(userController.getUser(enteredUsername).getPassword(),
                            enteredPassword)) {
                        System.out.println("Authorisation successful.");
                        menuApp(enteredUsername);
                    } else {
                        System.out.println("Wrong password! Please try again.");
                    }
                } else {
                    System.out.println("Invalid password or username");
                }
            } else System.out.println("User with the provided username does not exist.");
        }catch (InputMismatchException exception) {
            System.out.println(exception.getMessage());
        }

    }



 /*   public  User checkUser() {
        if () {
            return null;
        }
        if (users.containsKey(username)) {
            return users.get(username);
        }
        System.out.println("AbstractUser " + username + " not found.");
        return null;
    }
    public void regAdmin() {
        System.out.print("""
               Choose:
                ===> Registration
                ===> Authorisation
               Write:\s""");
        Operation operation = Operation.valueOf(scan.nextLine().trim().toUpperCase());
        switch (operation) {
            case REGISTRATION -> {
                System.out.print("\n=== Admin registration ===");
                User admin = new User();
                System.out.print("-> Enter name: ");
                String enteredName = scan.nextLine();
                if (checkUser(admins, admin.getUsername()) == null) {
                    admin.setUsername(enteredName);
                    System.out.print("-> Enter password: ");
                    admin.setPassword(scan.nextLine());
                    admin.setAdmin(true);
                    admins.put(enteredName, admin);
                    System.out.println("Registration successful.");
                } else System.out.println("Admin with such username already exists.");
            }
            case AUTHORISATION -> {authorisation(); AuditLogService.displayAuditLog();}
            default -> throw new RuntimeException();
        }
    }*/
    public void menuApp(String name) {
        while (true) {
            try {
                System.out.print("""
                       Menu:
                       1 ===> Submit Readings
                       2 ===> View Current Readings
                       3 ===> View Reading History
                       0 ===> Exit
                       Write it:\s""");
                int operation;
                while (true) {
                    if (scan.hasNextInt()) {
                        operation = scan.nextInt();
                        break;
                    } else {
                        System.out.println("Invalid input. Please enter an integer.");
                        scan.next();
                    }
                }
                switch (operation) {
                    case 1 -> SubmitReadings(name);
                    case 2 -> ViewCurrentReadings(name);
                    case 3 -> ViewReadingHistory(name);
                    case 0 -> {return;}
                    default -> throw new IllegalStateException("Unexpected " +
                            "value: " + operation);
                }
            } catch (RuntimeException exception) {
                System.out.println("\n" + exception.getMessage());
            }
        }
    }
    private void ViewReadingHistory(String username) {
        if (userController.getUser(username).getUserMeterReadings().isEmpty())
            System.out.println("Data not found.");
        else {
            System.out.println("\nReading History for " + username + ":");
            userController.getMeterReadingsByUsername(username);
            System.out.println();
        }
    }
    private void ViewCurrentReadings(String username) {
        if (userController.getUser(username).getUserMeterReadings().isEmpty()) {
            System.out.println("Data not found.");
        } else {
            System.out.println("Current Readings:");
            userController.CurrentReadingsByUsername(username);
        }
    }


     private void SubmitReadings(String username) {
        try {
            if(userController.isUserReged(username)) {
                System.out.print("Write cold water reading: ");
                double coldWaterReading = scan.nextDouble();

                System.out.print("Write hot water reading: ");
                double hotWaterReading = scan.nextDouble();

                System.out.print("Write heating reading: ");
                double heatingReading = scan.nextDouble();
                if (heatingReading >= 0 && hotWaterReading >= 0 && coldWaterReading >= 0)
                userController.addMeterReading(username, index++, LocalDate.now(),
                            coldWaterReading, hotWaterReading, heatingReading);
                else throw new InputMismatchException("Error: Input cannot be negative.");
            }
        } catch (InputMismatchException exception) {
            System.out.println("\nInvalid input. Please enter a valid number.");
            scan.nextLine();
        }
    }


}
