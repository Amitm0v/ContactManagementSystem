import json
import os
# File to store contacts
CONTACTS_FILE = 'contacts.json'
# Ensure the file exists
if not os.path.exists(CONTACTS_FILE):
    with open(CONTACTS_FILE, 'w') as file:
        json.dump([], file)
# Utility function to load contacts
def load_contacts():
    with open(CONTACTS_FILE, 'r') as file:
        return json.load(file)
# Utility function to save contacts
def save_contacts(contacts):
    with open(CONTACTS_FILE, 'w') as file:
        json.dump(contacts, file, indent=4)
# Create a new contact
def create_contact():
    name = input("Enter contact name: ").strip()
    email = input("Enter contact email: ").strip()
    phone = input("Enter contact phone number: ").strip()

    contacts = load_contacts()

    # Check for duplicate names
    if any(contact['name'].lower() == name.lower() for contact in contacts):
        print("A contact with this name already exists.")
        return

    contacts.append({"name": name, "email": email, "phone": phone})
    save_contacts(contacts)
    print("Contact created successfully.")

# Delete a contact
def delete_contact():
    name = input("Enter contact name to delete: ").strip()

    contacts = load_contacts()
    filtered_contacts = [contact for contact in contacts if contact['name'].lower() != name.lower()]

    if len(contacts) == len(filtered_contacts):
        print("Contact not found.")
    else:
        save_contacts(filtered_contacts)
        print("Contact deleted successfully.")

# Search for a contact
def search_contact():
    name = input("Enter contact name to search: ").strip()

    contacts = load_contacts()
    found_contacts = [contact for contact in contacts if name.lower() in contact['name'].lower()]

    if found_contacts:
        print("Contact(s) found:")
        for contact in found_contacts:
            print(f"- Name: {contact['name']}, Email: {contact['email']}, Phone: {contact['phone']}")
    else:
        print("No contacts found.")

# List all contacts
def list_contacts():
    contacts = load_contacts()
    if contacts:
        print("All contacts:")
        for contact in contacts:
            print(f"- Name: {contact['name']}, Email: {contact['email']}, Phone: {contact['phone']}")
    else:
        print("No contacts available.")

# Update a contact
def update_contact():
    name = input("Enter contact name to update: ").strip()

    contacts = load_contacts()
    for contact in contacts:
        if contact['name'].lower() == name.lower():
            print("Contact found. Leave fields blank to keep current values.")
            new_email = input(f"Enter new email (current: {contact['email']}): ").strip()
            new_phone = input(f"Enter new phone (current: {contact['phone']}): ").strip()

            if new_email:
                contact['email'] = new_email
            if new_phone:
                contact['phone'] = new_phone

            save_contacts(contacts)
            print("Contact updated successfully.")
            return

    print("Contact not found.")

def main():
    while True:
        print("\nContact Management System")
        print("1. Create Contact")
        print("2. Delete Contact")
        print("3. Search Contact")
        print("4. List All Contacts")
        print("5. Update Contact")
        print("6. Exit")

        try:
            choice = int(input("Enter your choice: ").strip())

            if choice == 1:
                create_contact()
            elif choice == 2:
                delete_contact()
            elif choice == 3:
                search_contact()
            elif choice == 4:
                list_contacts()
            elif choice == 5:
                update_contact()
            elif choice == 6:
                print("Exiting contact management system.")
                break
            else:
                print("Invalid choice. Please try again.")
        except ValueError:
            print("Please enter a valid number.")

if __name__ == "__main__":
    main()
