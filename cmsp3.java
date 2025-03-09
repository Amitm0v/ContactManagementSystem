import java.io.*;
import java.util.*;

public class ContactManager {
    private static final String CONTACTS_FILE = "contacts.txt";
    private static final Scanner scanner = new Scanner(System.in);
    // Create a new contact
    private static void createContact() {
        System.out.print("Enter contact name: ");
        String name = scanner.nextLine().trim();
        System.out.print("Enter contact email: ");
        String email = scanner.nextLine().trim();
        System.out.print("Enter contact phone number: ");
        String phone = scanner.nextLine().trim();

        
        try (FileWriter writer = new FileWriter(CONTACTS_FILE, true)) {
            writer.write(name + "," + email + "," + phone + "\n");
            System.out.println("Contact created successfully.");
        } catch (IOException e) {
            System.out.println("Error writing to file.");
        }
    }

    // Delete a contact
    private static void deleteContact() {
        System.out.print("Enter contact name to delete: ");
        String name = scanner.nextLine().trim();

        List<String> contacts = new ArrayList<>();
        boolean found = false;

        try (BufferedReader reader = new BufferedReader(new FileReader(CONTACTS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.toLowerCase().startsWith(name.toLowerCase() + ",")) {
                    contacts.add(line);
                } else {
                    found = true;
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file.");
            return;
        }

        if (found) {
            try (FileWriter writer = new FileWriter(CONTACTS_FILE)) {
                for (String contact : contacts) {
                    writer.write(contact + "\n");
                }
            } catch (IOException e) {
                System.out.println("Error writing to file.");
            }
            System.out.println("Contact deleted successfully.");
        } else {
            System.out.println("Contact not found.");
        }
    }

    // Search for a contact
    private static void searchContact() {
        System.out.print("Enter contact name to search: ");
        String name = scanner.nextLine().trim();
        boolean found = false;

        try (BufferedReader reader = new BufferedReader(new FileReader(CONTACTS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.toLowerCase().startsWith(name.toLowerCase() + ",")) {
                    System.out.println("Contact found: " + line);
                    found = true;
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file.");
        }

        if (!found) {
            System.out.println("Contact not found.");
        }
    }

    // Main menu
    private static void mainMenu() {
        while (true) {
            System.out.println("\nContact Management System");
            System.out.println("1. Create Contact");
            System.out.println("2. Delete Contact");
            System.out.println("3. Search Contact");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    createContact();
                    break;
                case "2":
                    deleteContact();
                    break;
                case "3":
                    searchContact();
                    break;
                case "4":
                    System.out.println("Exiting contact management system.");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    public static void main(String[] args) {
        mainMenu();
    }
}
