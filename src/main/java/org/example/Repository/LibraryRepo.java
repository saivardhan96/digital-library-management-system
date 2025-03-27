package org.example.Repository;

import org.example.entity.Book;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LibraryRepo {

    private final Connection con;
    public LibraryRepo() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        this.con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sai","root","Kittu@96");
    }

    public int saveBook(Book book) throws SQLException {
        try{
            PreparedStatement ps = con.prepareStatement("insert into library values(?,?,?,?,?, ?);");
            ps.setString(1, book.getId());
            ps.setString(2, book.getTitle());
            ps.setString(3, book.getAuthor());
            ps.setString(4, "Available");
            ps.setString(5, String.valueOf(book.getQuantity()));
            ps.setString(6, book.getGenre());

            return ps.executeUpdate();

        } catch (SQLException e) {
            throw new SQLException("Book with this ID already exists." + e);
        }
    }

    public List<Book> allBooks() throws SQLException{
        try{
            PreparedStatement ps = con.prepareStatement("select * from library");
            ResultSet rs = ps.executeQuery();
            List<Book> books = new ArrayList<>();
            while(rs.next()){
                Book book = new Book();
                book.setId(rs.getString(1));
                book.setTitle(rs.getString(2));
                book.setAuthor(rs.getString(3));
                book.setAvailability_status(rs.getString(4));
                book.setQuantity(rs.getInt(5));
                book.setGenre(rs.getString(6));
                books.add(book);
            }
            return books;
        }catch (SQLException e){
            throw new SQLException(e);
        }
    }

    public Book getBook(String criteria, String criteriaValue) throws SQLException{
        try{
            PreparedStatement ps = con.prepareStatement("select * from library where "+criteria+" = ?");
            ps.setString(1,criteriaValue);
            ResultSet rs = ps.executeQuery();
            Book book = new Book();
            while(rs.next()){
                book.setId(rs.getString(1));
                book.setTitle(rs.getString(2));
                book.setAuthor(rs.getString(3));
                book.setAvailability_status(rs.getString(4));
                book.setQuantity(rs.getInt(5));
                book.setGenre(rs.getString(6));
            }
            if(book.getId() == null) throw new Exception("There is no book with the details provided.");
            return book;
        }catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }



    public boolean updateBook(String field, String newValue, String id) throws Exception{
        try {
            PreparedStatement ps = con.prepareStatement("update library set "+field + " = " + newValue + " where book_id = ?");
            ps.setString(1,id);
            return ps.executeUpdate()==1;
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    public boolean deleteBook(String id) throws Exception {
        try{
            Book book = getBook("book_id", id);
            PreparedStatement ps = con.prepareStatement("delete from library where book_id = ?");
            ps.setString(1, id);
            int rs = ps.executeUpdate();
            return rs==1;
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }
}
