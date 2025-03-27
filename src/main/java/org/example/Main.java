package org.example;

import org.example.service.BookService;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {

        Scanner sc = new Scanner(System.in);
        final BookService bookService = new BookService();
        questions();
        int choice = sc.nextInt();

        while(choice!=6) {
            switch (choice) {
                case 1 -> bookService.addBook();
                case 2 -> bookService.allBooks();
                case 3 -> bookService.updateBook();
                case 4 -> bookService.searchBook();
                case 5 -> bookService.deleteBook();
                default -> System.out.println("Enter correct choice and try again.");
            }
            System.out.println("\n");
            questions();
            choice = sc.nextInt();
        }

        System.out.println("Thank you. See you again!!!");

    }

    public static void questions(){
        System.out.println(
                """
                        Select a program:\s
                        1. Add a new Book\s
                        2. Show Available books\s
                        3. Update a Book\s
                        4. Search a Book\s
                        5. Delete a Book\s
                        6. EXIT\s
                        """);
    }
}