import java.io.*;
import java.util.*;

public class ContactManagement {
    private static final String FILE_NAME = "contacts.txt";

    public static void createContact() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter contact name: ");
        String name = scanner.nextLine();
        System.out.print("Enter contact email: ");
        String email = scanner.nextLine();
        System.out.print("Enter contact phone number: ");
        String phone = scanner.nextLine();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            writer.write(name + "," + email + "," + phone);
            writer.newLine();
            System.out.println("Contact created successfully.");
        } catch (IOException e) {
            System.out.println("An error occurred while creating the contact.");
        }
    }

    public static void deleteContact() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter contact name to delete: ");
        String name = scanner.nextLine();

        List<String> contacts = new ArrayList<>();
        boolean found = false;

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.startsWith(name + ",")) {
                    contacts.add(line);
                } else {
                    found = true;
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading contacts.");
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (String contact : contacts) {
                writer.write(contact);
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("An error occurred while updating contacts.");
        }

        if (found) {
            System.out.println("Contact deleted successfully.");
        } else {
            System.out.println("Contact not found.");
        }
    }

    public static void searchContact() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter contact name to search: ");
        String name = scanner.nextLine();
        boolean found = false;

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith(name + ",")) {
                    System.out.println("Contact found: " + line);
                    found = true;
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred while searching for contacts.");
        }

        if (!found) {
            System.out.println("Contact not found.");
        }
    }

    public static int reverse(int x) {
        int rev = 0;
        while (x != 0) {
            int digit = x % 10;
            x /= 10;
            
            if (rev > Integer.MAX_VALUE / 10 || (rev == Integer.MAX_VALUE / 10 && digit > 7)) return 0;
            if (rev < Integer.MIN_VALUE / 10 || (rev == Integer.MIN_VALUE / 10 && digit < -8)) return 0;
            
            rev = rev * 10 + digit;
        }
        return rev;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Contact Management System");
            System.out.println("1. Create Contact");
            System.out.println("2. Delete Contact");
            System.out.println("3. Search Contact");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    createContact();
                    break;
                case 2:
                    deleteContact();
                    break;
                case 3:
                    searchContact();
                    break;
                case 4:
                    System.out.println("Exiting contact management system.");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
