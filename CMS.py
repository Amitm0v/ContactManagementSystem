def create_contact():
    name = input("Enter contact name: ")
    email = input("Enter contact email: ")
    phone = input("Enter contact phone number: ")

    with open('contacts.txt', 'a') as file:
        file.write(f"{name},{email},{phone}\n")

    print("Contact created successfully.")

def delete_contact():
    name = input("Enter contact name to delete: ")

    with open('contacts.txt', 'r') as file:
        contacts = file.read().splitlines()

    new_contacts = []
    for contact in contacts:
        if name not in contact:
            new_contacts.append(contact)

    with open('contacts.txt', 'w') as file:
        file.write('\n'.join(new_contacts))

    print("Contact deleted successfully.")

def search_contact():
    name = input("Enter contact name to search: ")

    with open('contacts.txt', 'r') as file:
        contacts = file.read().splitlines()

    found = False
    for contact in contacts:
        if name in contact:
            print(f"Contact found: {contact}")
            found = True

    if not found:
        print("Contact not found.")

def main():
    while True:
        print("Contact Management System")
        print("1. Create Contact")
        print("2. Delete Contact")
        print("3. Search Contact")
        print("4. Exit")
        choice = int(input("Enter your choice: "))

        if choice == 1:
            create_contact()
        elif choice == 2:
            delete_contact()
        elif choice == 3:
            search_contact()
        elif choice == 4:
            print("Exiting contact management system.")
            break
        else:
            print("Invalid choice. Please try again.")

if _name_ == "_main_":
    main()