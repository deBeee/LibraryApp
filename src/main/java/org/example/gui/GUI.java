package org.example.gui;

import org.example.database.BookDAO;
import org.example.model.*;

import java.sql.Date;
import java.sql.SQLOutput;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class GUI {
    private final Scanner scanner = new Scanner(System.in);
    final BookDAO bookDAO = BookDAO.getInstance();
    private static final GUI instance = new GUI();

    public String showMenu(){
        System.out.println("1. Search for a book");
        System.out.println("2. Borrow a book");
        System.out.println("3. Add a book");
        System.out.println("4. List borrowed books");
        System.out.println("5. List borrowed books not returned on time");
        System.out.println("6. List only available books");
        System.out.println("7. List all books");
        System.out.println("8. Show your borrowed books");
        System.out.println("9. Return your book");
        System.out.println("10. Exit");
        return scanner.nextLine();
    }
    public Book readBook(){
        List <Book> books = this.bookDAO.getAvailableBooks();
        int size = books.size();
        System.out.println("Currently available books: ");
        this.printBooks(books);
        int number = -1;
        boolean not_valid = true;
        do { //simple validation
            System.out.print("Select a book by its index number: ");
            if(scanner.hasNextInt())
                number = scanner.nextInt();
            else {
                System.out.println("Invalid input!");
                scanner.next();
                continue;
            }
            not_valid = false;
        } while (not_valid | number <= 0 | number > size);
        scanner.nextLine(); // clear buffer
        System.out.println("Selected: " + books.get(number - 1));
        return this.bookDAO.getAvailableBooks().get(number - 1);
    }
    public void showBorrowResult(boolean result){
        if(result) System.out.println("You have successfully borrowed a book for up to 2 weeks!");
        else System.out.println("Borrowing operation unsuccessful!");

    }
    public String readUserName(){
        System.out.print("Name: ");
        return this.scanner.nextLine();
    }
    public String readUserSurname(){
        System.out.print("Surname: ");
        return this.scanner.nextLine();
    }
    public User readLoginAndPassword() {
        User user = new User();
        System.out.print("Login: ");
        user.setLogin(this.scanner.nextLine());
        System.out.print("Password: ");
        user.setPassword(this.scanner.nextLine());
        return user;
    }
    public void showAddResult(boolean result){
        if(result) System.out.println("Book added successfully");
        else System.out.println("Adding operation unsuccessful");
    }
    public Book readNewBookData(){
        System.out.print("Author: ");
        String author = this.scanner.nextLine();
        System.out.print("Title: ");
        String title = this.scanner.nextLine();
        System.out.print("ISBN: ");
        String isbn = this.scanner.nextLine();
        return new Book(author, title, isbn);
    }
    public String readBookData() {
        System.out.print("Please enter either title, author or isbn: ");
        return scanner.nextLine();
    }

    public void printBooks(List<Book> books){
        for(int i = 1; i < books.size(); i++){
            System.out.println(i + ") " + books.get(i - 1));
        }
    }
    public void listBorrowedBooks(Map<Book, List<String>> bookMap){
        int i = 1;
        for(Book book : bookMap.keySet()){
            List<String> additionalData = bookMap.get(book);
            LocalDate date = LocalDate.parse(additionalData.get(2));
            LocalDate returnDate = date.plus(2, ChronoUnit.WEEKS);
            System.out.println(new StringBuilder()
                    .append(i++ + ") ")
                    .append(book)
                    .append(", borrowed by: ")
                    .append(additionalData.get(0))
                    .append(" ")
                    .append(additionalData.get(1))
                    .append(", borrow date: ")
                    .append(additionalData.get(2))
                    .append(", return date: ")
                    .append(returnDate));
        }
    }
    public void listNotReturnedBooks(Map<Book, List<String>> bookMap){
        int i = 1;
        for(Book book : bookMap.keySet()){
            List<String> additionalData = bookMap.get(book);
            LocalDate date = LocalDate.parse(additionalData.get(2));
            LocalDate returnDate = date.plus(2, ChronoUnit.WEEKS);
            if(returnDate.isBefore(LocalDate.now())) {
                System.out.println(new StringBuilder()
                        .append(i++ + ") ")
                        .append(book)
                        .append(", borrowed by: ")
                        .append(additionalData.get(0))
                        .append(" ")
                        .append(additionalData.get(1))
                        .append(", borrow date: ")
                        .append(additionalData.get(2))
                        .append(", return date: ")
                        .append(returnDate));
            }
        }
    }

    public static GUI getInstance() {
        return instance;
    }
}
