import java.util.ArrayList;
import java.util.Scanner;

public class Assignment2 {
    static class Book {
        String title;
        String author;
        int quantity;

        Book(String title, String author, int quantity) {
            this.title = title;
            this.author = author;
            this.quantity = quantity;
        }
    }

    private static final ArrayList<Book> books = new ArrayList<>();
     public static void main(String[] args) {
         try {
             boolean exitProgram = false;
             while (!exitProgram) {
                Scanner scanner = new Scanner(System.in);
                System.out.println("Please choose an option:");
                System.out.println("1. Add Book");
                System.out.println("2. Borrow Book");
                System.out.println("3. Return Book");
                System.out.println("4. Exit");
                System.out.println("Enter your choice (1-4): ");
                String userInput = scanner.nextLine();
                switch (userInput) {
                    case "1" -> addBook();
                    case "2" -> borrowBook();
                    case "3" -> returnBook();
                    case "4" -> exit();
                    default -> System.out.println("Invalid input, please enter a valid option");
                }
            }
        } catch (Exception e) {
            System.out.println("An error occurred, please try again: " + e.getMessage());
        } 
    }

    private static void addBook() {
        // ask user for title
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the title of the book: ");
        String title = scanner.nextLine();
        // ask user author
        System.out.println("Enter the author of the book: ");
        String author = scanner.nextLine();
        // ask user for quantity
        System.out.println("Enter the quantity of the book: ");
        int quantity = scanner.nextInt();
        scanner.nextLine(); // consume newline

        // search for book by title, case insensitive
        Book foundBook = null;
        for (Book b : books) {
            if (b.title.equalsIgnoreCase(title)) {
                foundBook = b;
                break;
            }
        }
        if (foundBook != null) {
            foundBook.quantity += quantity;
            System.out.println("Book already exists. Updated quantity to: " + foundBook.quantity);
        } else {
            books.add(new Book(title, author, quantity));
            System.out.println("Book added to library.");
        }
    }
    
    private static void borrowBook() {
        // ask user for title
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the title of the book: ");
        String title = scanner.nextLine();
        // search for book by title, case insensitive
        Book foundBook = null;
        for (Book b : books) {
            if (b.title.equalsIgnoreCase(title)) {
                foundBook = b;
                break;
            }
        }
        if (foundBook != null) {
            foundBook.quantity--;
            System.out.println("Book borrowed. Remaining quantity: " + foundBook.quantity);
        } else {
            System.out.println("Book not found in library.");
        }
    }
    
    private static void returnBook() {
        // ask user for title
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the title of the book: ");
        String title = scanner.nextLine();
        // search for book by title, case insensitive
        Book foundBook = null;
        for (Book b : books) {
            if (b.title.equalsIgnoreCase(title)) {
                foundBook = b;
                break;
            }
        }
        if (foundBook != null) {
            foundBook.quantity++;
            System.out.println("Book returned. Remaining quantity: " + foundBook.quantity);
        } else {
            System.out.println("Book not found in library.");
        }
    }
    
    private static void exit() {
        System.out.println("Exiting program...");
        System.exit(0);
    }
}