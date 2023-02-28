package org.example.database;

import com.mysql.cj.conf.ConnectionUrlParser;
import org.example.model.Book;
import org.example.core.Authenticator;

import java.sql.*;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

public class BookDAO {
    private static final BookDAO instance = new BookDAO();
    final Authenticator authenticator = Authenticator.getInstance();
    private final Connection connection;

    private BookDAO() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/library",
                    "root",
                    "");
        } catch(SQLException | ClassNotFoundException e){
            throw new RuntimeException(e);
        }
    }

    public List<Book> getAllBooks(){
        ArrayList<Book> books = new ArrayList<>();
        try{
            String sql = "SELECT * FROM tbook";
            PreparedStatement ps = this.connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                books.add(new Book(
                        rs.getString("author"),
                        rs.getString("title"),
                        rs.getString("ISBN")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return books;
    }
    public void searchBook(String data){
        String regex = "(?i).*" + data + ".*"; //(?i) - case insensitive
        this.getAllBooks().stream().filter(book -> book.getISBN().matches(regex) |
                        book.getAuthor().matches(regex) |
                        book.getTitle().matches(regex))
                .toList()
                .forEach(System.out::println);
    }

    public List<Book> getAvailableBooks() {
        ArrayList<Book> books = new ArrayList<>();
        try{
            String sql = "SELECT * FROM tbook " +
                    "WHERE bookid NOT IN (SELECT bookid FROM tborrows WHERE returned = 0)"; //subquery, returned = 1 in database means book was returned
            PreparedStatement ps = this.connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                books.add(new Book(
                        rs.getString("author"),
                        rs.getString("title"),
                        rs.getString("ISBN")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return books;
    }
    public Map<Book, List<String>> getBorrowedBooks(){
        Map<Book, List<String>> result = new HashMap<>();
        try{
            String sql =
                    "SELECT author, title, ISBN, name, surname, borrowdate " +
                            "FROM tbook LEFT JOIN tborrows " +
                            "ON tbook.bookid = tborrows.bookid " +
                            "WHERE tborrows.returned = 0";
            PreparedStatement ps = this.connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                result.put(new Book(
                                rs.getString("author"),
                                rs.getString("title"),
                                rs.getString("ISBN")),
                        new ArrayList<>(Arrays.asList(
                                rs.getString("name"),
                                rs.getString("surname"),
                                rs.getString("borrowdate"))));
            }
            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public boolean borrowBook(Book book, String name, String surname){
        //tabela tborrows służy jako historia wypożyczeń, znajdują się tam również wiersze w których książki zostały oddane (returned = 1)
        try{
            String sql = "INSERT INTO tborrows " +
                    "(userid, bookid, name, surname, borrowdate, returned) VALUES (?,?,?,?,?,?)";
            PreparedStatement ps = this.connection.prepareStatement(sql);
            ps.setInt(1, getUserId());
            ps.setInt(2, getBookId(book));
            ps.setString(3,name);
            ps.setString(4,surname);
            ps.setDate(5, new java.sql.Date(System.currentTimeMillis())); //data wypożyczenia to aktualna data
            ps.setBoolean(6,false);

            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException();
        }
        return true;
    }
    private int getBookId (Book book){
        String isbn = book.getISBN();
        try{
            String sql = "SELECT bookid FROM tbook WHERE ISBN = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, isbn);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                return rs.getInt("bookid");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 1;
    }
    private int getUserId (){
        String login = authenticator.getLoggedUser().getLogin();
        try{
            String sql = "SELECT userid FROM tuser WHERE login = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, login);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                return rs.getInt("userid");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 1;
    }
    public boolean addBook(Book book){
        try{
            String sql = "INSERT INTO tbook " +
                    "(author, title, ISBN) VALUES (?,?,?)";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, book.getAuthor());
            ps.setString(2,book.getTitle());
            ps.setString(3,book.getISBN());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return true;
    }
    public static BookDAO getInstance() {return instance;}
}


