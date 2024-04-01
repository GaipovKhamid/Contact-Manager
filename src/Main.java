import java.util.Scanner;

public class Main {
    static Contact[] contactArray = new Contact[2];
    static int currentIndex = 0;

    public static void main(String[] args) {
        boolean b = true;
        while (b) {
            menu();
            int n = getMenuNumber();
            switch (n) {
                case 1:
                    Contact contact = addContact();
                    addToArray(contact);
                    break;
                case 2:
                    printContactList();
                    break;
                case 3:
                    String query = getQuery();
                    search(query);
                    break;
                case 4:
                    String phone = deleteContact();
                    deleteContactFromArray(phone);
                    break;
                case 0:
                    b = false;
                    break;
                default:
                    System.out.println("Пожалуйста введите правильный номер");
            }
        }
        System.out.println("Все");
    }

    public static Contact addContact() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите имя: ");
        String name = scanner.next();

        System.out.print("Введите фамилию: ");
        String surname = scanner.next();

        System.out.print("Введите номер: ");
        String phone = scanner.next();

        Contact contact = new Contact();
        contact.name = name;
        contact.surname = surname;
        contact.phone = phone;

        return contact;
    }


    public static boolean isValidContact(Contact contact) {
        if (contact.name == null || contact.name.trim().length() < 3) {
            System.out.println("Имя контакта не корректна");
            return false;
        }
        if (contact.surname == null || contact.surname.trim().length() < 3) {
            System.out.println("Фамилия контакта не корректна");
            return false;
        }
        if (contact.phone == null || contact.phone.trim().length() != 12 ||
                !contact.phone.startsWith("998")) {
            System.out.println("Номер контакта не корректна");
            return false;
        }

        char[] phoneArr = contact.phone.toCharArray();
        for (char c : phoneArr) {
            if (!Character.isDigit(c)) {
                System.out.println("Номер контакта не корректна");
                return false;
            }
        }
        return true;
    }


    public static void addToArray(Contact contact) {
        if (!isValidContact(contact)) {
            return;
        }
        if (isPhoneExist(contact.phone)) {
            System.out.println("Такой номер уже есть");
            return;
        }
        if (currentIndex == contactArray.length) {
            Contact[] newArr = new Contact[contactArray.length * 2];

            for (int i = 0; i < contactArray.length; i++) {
                newArr[i] = contactArray[i];
            }
            contactArray = newArr;
            System.out.println("Создается новая таблица");
        }
        contactArray[currentIndex] = contact;
        currentIndex++;
        System.out.println("Контакт добавился");

    }

    public static void printContactList() {
        for (Contact c : contactArray) {
            if (c != null) {
                System.out.println(c.name + " " + c.surname + " " + c.phone);
            }
        }
    }

    public static String getQuery() {
        System.out.print("Enter query: ");
        Scanner scanner = new Scanner(System.in);
        return scanner.next();
    }

    public static void search(String query) {
        for (Contact contact : contactArray) {
            if (contact == null) {
                continue;
            }
            if (contact.name.toLowerCase().contains(query) || contact.surname.toLowerCase().contains(query)
                    || contact.phone.toLowerCase().contains(query)) {   //contains()
                System.out.println(contact.name + " " + contact.surname + " " + contact.phone);
            }
        }
    }


    public static boolean isPhoneExist(String phone) {
        for (Contact contact : contactArray) {
            if (contact != null) {
                if (contact.phone.equals(phone)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static String deleteContact() {
        System.out.print("Введите номер: ");
        Scanner scanner = new Scanner(System.in);
        return scanner.next();
    }

    public static void deleteContactFromArray(String phone) {
        System.out.println("Вы хотите удалить контакт?");
        Scanner scanner = new Scanner(System.in);
        String accept = scanner.next();

        if (accept.equals("Yes")) {
            for (int i = 0; i < contactArray.length; i++) {
                Contact contact = contactArray[i];
                if (contact != null && contact.phone.equals(phone)) {
                    contactArray[i] = null;
                    System.out.print("Контакт удален.");
                }
                break;
            }
        }
    }

    public static void menu() {
        System.out.println("------------------");
        System.out.println("** Меню **        ");
        System.out.println("1. Добавить контакт");
        System.out.println("2. Показать контакты");
        System.out.println("3. Искать");
        System.out.println("4. Удалить контакт");
        System.out.println("0. Выйти");
    }

    public static int getMenuNumber() {
        System.out.println("------------------");
        System.out.print("Нажмите на нужное меню: ");
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        return n;
    }
}
