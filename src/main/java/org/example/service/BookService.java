package org.example.service;

import org.example.Repository.LibraryRepo;
import org.example.entity.Book;

import java.util.List;
import java.util.Scanner;

public class BookService {

    LibraryRepo libraryRepo;
    Scanner sc;

    public BookService()  {
        this.sc = new Scanner(System.in);
        try{
            this.libraryRepo = new LibraryRepo();
        }catch (Exception e){
            System.out.println("Error in connecting to db. Try again later.");
        }
    }

    public void addBook(){
        System.out.println("Book Id: ");
        String id = sc.next();
        System.out.println("Title: ");
        String title = sc.next();
        System.out.println("Author: ");
        String author = sc.next();
        System.out.println("Genre: ");
        String genre = sc.next();
        System.out.println("Quantity: ");
        String quantity = sc.next();
        try{
            int status = libraryRepo.saveBook(new Book(id, title,author,genre, Integer.parseInt(quantity)));
            if(status == 1) System.out.println("Book saved successfully!!");
        }catch (Exception e){
            System.out.println("Error occurred while saving the book..."+e.getMessage());
        }
    }

    public void deleteBook(){
        try{
            System.out.println("Enter ID: ");
            String id = sc.next();
            if(libraryRepo.deleteBook(id)) System.out.println("Book deleted successfully");
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void updateBook(){

        String[] arr = {"book_id", "book_title", "author", "Availability_status", "quantity", "genre"};
        System.out.println("Enter ID of the book : ");
        String id = sc.next();
        try{
            libraryRepo.getBook("book_id", id);
            System.out.println("""
                    What you wanna update today ?\s
                    1.ID\s
                    2.Title\s
                    3.Author\s
                    4.Availability Status\s
                    5.Quantity\s
                    6.Genre.""");

            int choice = Integer.parseInt(sc.next());
            System.out.println("Enter NEW "+arr[choice-1]+" : ");
            String newValue = sc.next();
                if(libraryRepo.updateBook(arr[choice-1],newValue, id)) System.out.println("Book updated successfully!!");
        }catch (Exception e){
            System.out.println("Error in updating: "+ e.getMessage());
        }
    }

    public void allBooks(){
        try{
            List<Book> books = libraryRepo.allBooks();
            for(Book book: books) System.out.println(book.toString());
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void searchBook(){
        System.out.println("""
                Search by ?\s
                1. Title\s
                2. ID\s
                """);
        String choice = sc.next();
        String criteria, criteriaValue;
        if(choice.equals("1")){
            criteria = "book_title";
            System.out.print("Enter Title: ");
            criteriaValue = sc.next();
        }
        else{
            criteria = "book_id";
            System.out.print("Enter ID: ");
            criteriaValue = sc.next();
        }
        try{
            System.out.println(libraryRepo.getBook(criteria, criteriaValue).toString());
        }catch (Exception e){
            System.out.println(e.getLocalizedMessage());
        }
    }

}
