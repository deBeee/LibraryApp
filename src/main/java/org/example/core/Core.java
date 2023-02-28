package org.example.core;

import org.example.database.BookDAO;
import org.example.gui.GUI;

public class Core {
    final BookDAO bookDAO = BookDAO.getInstance();
    final Authenticator authenticator = Authenticator.getInstance();
    final GUI gui = GUI.getInstance();
    private static final Core instance = new Core();


    private Core() {
    }

    public void start() {
        boolean isRunning = false;
        int counter = 0;

        while(!isRunning && counter < 3) {
            this.authenticator.authenticate(this.gui.readLoginAndPassword());
            isRunning = this.authenticator.getLoggedUser() != null;
            if(!isRunning) {
                System.out.println("Not authorized !!");
            }
            counter++;
        }

        while(isRunning) {
            switch(this.gui.showMenu()) {
                case "1":
                    this.bookDAO.searchBook(gui.readBookData()); //wyświetla wszystkie książki zarówno te wypożyczone jak i nie
                    break;
                case "2":
                    this.gui.showBorrowResult(bookDAO.borrowBook( //chcemy coś wypożyczyć więc wyświetla wyłącznie dostępne książki
                            this.gui.readBook(),
                            this.gui.readUserName(),
                            this.gui.readUserSurname()));
                    break;
                case "3":
                    this.gui.showAddResult(this.bookDAO.addBook(this.gui.readNewBookData()));
                    break;
                case "4":
                    this.gui.listBorrowedBooks(this.bookDAO.getBorrowedBooks());
                    break;
                case "5":
                    this.gui.listNotReturnedBooks(this.bookDAO.getBorrowedBooks());
                    break;
                case "6":
                    this.gui.printBooks(this.bookDAO.getAvailableBooks());
                    break;
                case "7":
                    this.gui.listAllBooks(); //wyświetla wszystkie książki zarówno te wypożyczone jak i nie
                    break;
                case "8" :
                    isRunning = false;
                    break;
                default:
                    System.out.println("Wrong choose !!");
                    break;
            }
        }
    }

    public static Core getInstance() {
        return instance;
    }
}
