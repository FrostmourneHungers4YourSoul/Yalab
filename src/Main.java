import data.*;
import data.Operation;
import users.User;

import java.time.LocalDate;
import java.util.*;

public class Main {
    public static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {
        run();
    }

    public static void run() {
        Map<String, User> users = new HashMap<>();
        Map<String, User> admins = new HashMap<>();
        Map<String, MeterReadings> readings = new HashMap<>();

        while (true) {
            try {
                System.out.print("""

                        Main menu:
                         ===> Registration (reg)
                         ===> Authorisation (auth)
                         ===> Admin
                         ===> Exit
                        Write it:\s""");

                Operation operation = Operation.valueOf(scan.nextLine().trim().toUpperCase());

                switch (operation) {
                    case REGISTRATION, REG -> registration(users);
                    case AUTHORISATION, AUTH -> authorisation(users, readings);
                    case ADMIN -> regAdmin(admins, readings);
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

    public static void registration(Map<String, User> users) {
        System.out.print("\n=== Registration ===");
        User user = new User();
        System.out.print("\n-> Enter username: ");
        String enteredUsername = scan.nextLine();

        if (checkUser(users, user.getUsername()) == null) {
            user.setUsername(enteredUsername);
            System.out.print("-> Enter password: ");
            user.setPassword(scan.nextLine());
            users.put(enteredUsername, user);
            System.out.println("Registration successful.");
        } else System.out.println("User with such username already exists.");
    }

    public static void authorisation(Map<String, User> users,
                                     Map<String, MeterReadings> readings) {
        System.out.println("\n===Authorisation===");
        if (users.isEmpty()) {
            System.out.println("No user has been added.");
            return;
        }
        System.out.print("-> Enter username: ");
        String enteredValue = scan.nextLine();

        if (users.containsKey(enteredValue)) {
            User user = users.get(enteredValue);
            System.out.print("-> Enter password: ");
            enteredValue = scan.nextLine();
            if (Objects.equals(user.getPassword(), enteredValue)) {
                System.out.println("Authorisation successful.");
                menuApp(users, user, readings);
            } else System.out.println("Wrong password!");
        }
    }

    public static User checkUser(Map<String, User> users, String username) {
        if (users.isEmpty()) {
            return null;
        }
        if (users.containsKey(username)) {
            return users.get(username);
        }
        System.out.println("User " + username + " not found.");
        return null;
    }

    public static void regAdmin(Map<String, User> admins,
                                Map<String, MeterReadings> readings) {
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
            case AUTHORISATION -> authorisation(admins, readings);
            default -> throw new RuntimeException();
        }
    }

    public static void menuApp(Map<String, User> users, User user,
                               Map<String, MeterReadings> readingsMap) {
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
                        scan.next(); // consume the invalid input
                    }
                }


                switch (operation) {
                    case 1-> SubmitReadings(user, readingsMap);
                    case 2-> ViewCurrentReadings(readingsMap, user.getUsername());
                    case 3-> ViewReadingHistory(readingsMap, user.getUsername());
                    case 0 -> {return;}
                    default -> throw new IllegalStateException("Unexpected " +
                            "value: " + operation);
                }

            } catch (RuntimeException exception) {
                System.out.println("\n" + exception.getMessage());
            }
        }
    }

    private static void ViewReadingHistory(Map<String, MeterReadings> readingsMap, String username) {
        MeterReadings userReadings = readingsMap.get(username);

        if (userReadings != null) {
            System.out.println("Reading History for " + username + ":");
            userReadings.printHistory();
        } else {
            System.out.println("Data not found.");
        }
    }


    private static void ViewCurrentReadings(Map<String, MeterReadings> readingsMap, String username)
    {
        MeterReadings userReadings = readingsMap.get(username);

        if (userReadings != null) {
            System.out.println("Current Readings:");
            userReadings.printSquare();
        } else {
            System.out.println("Data not found.");
        }
    }

    private static void SubmitReadings(User user, Map<String, MeterReadings> readingsMap) {
        try {
            MeterReadings readings = new MeterReadings();
            readings.setUsername(user.getUsername());

            System.out.print("Write cold water reading: ");
            double coldWaterReading = scan.nextDouble();
            readings.setMeterReading(LocalDate.now(), coldWaterReading, "Cold");

            System.out.print("Write hot water reading: ");
            double hotWaterReading = scan.nextDouble();
            readings.setMeterReading(LocalDate.now(), hotWaterReading, "Hot");

            System.out.print("Write heating reading: ");
            double heatingReading = scan.nextDouble();
            readings.setMeterReading(LocalDate.now(), heatingReading, "Heating");

            readingsMap.put(user.getUsername(), readings);
        } catch (InputMismatchException exception) {
            System.out.println("\nInvalid input. Please enter a valid number.");
            scan.nextLine();
        }
    }


//    private static void SubmitReadings(User user, Map<String, MeterReadings> readingsMap) {
//        while (true) {
//            try {
//                System.out.print("""
//
//                        Readings:
//                        1 ===> Cold Water
//                        2 ===> Hot Water
//                        3 ===> Heating
//                        0 ===> Exit
//                        Write it:\s""");
//
//                int operation = scan.nextInt();
//                MeterReadings reading = new MeterReadings();
//                reading.setUsername(user.getUsername());
//
//                switch (operation) {
//                    case 1 -> {
//
//                        System.out.print("Write cold water reading: ");
//                        double waterReading = scan.nextDouble();
//                        reading.setCWM(waterReading);
//                        readingsMap.put(user.getUsername(), reading);
//                    }
//                    case 2 -> {
//                        System.out.print("Write hot water reading: ");
//                        double waterReading = scan.nextDouble();
//                        reading.setHWM(waterReading);
//                        readingsMap.put(user.getUsername(), reading);
//                    }
//                    case 3 -> {
//                        System.out.print("Write heating reading: ");
//                        double heatingReading = scan.nextDouble();
//                        reading.setHM(heatingReading);
//                        readingsMap.put(user.getUsername(), reading);
//                    }
//
//                    case 0 -> { return;}
//                    default -> throw new IllegalStateException("Unexpected " +
//                            "value: " + operation);
//                }
//
//            } catch (RuntimeException exception) {
//                System.out.println("\n" + exception.getMessage());
//            }
//        }
//    }

}