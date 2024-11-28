import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

public class DatabaseConnection {
    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost:3306/testdb";
        String user = "root";
        String password = "nurissql123$";

        String createTable = "CREATE TABLE IF NOT EXISTS Employees (" +
                "id INT AUTO_INCREMENT PRIMARY KEY, " +
                "first VARCHAR(50), " +
                "last VARCHAR(50), " +
                "age INT)";

        String insertData = "INSERT INTO Employees (first, last, age) VALUES " +
                "('Alice', 'Smith', 25), " +
                "('Bob', 'Johnson', 30), " +
                "('Charlie', 'Williams', 28)";

        String selectData = "SELECT * FROM Employees";

        String updateData = "UPDATE Employees SET age = 35 WHERE first = 'Alice'";

        String deleteData = "DELETE FROM Employees WHERE first = 'Bob'";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             Statement stmt = conn.createStatement()) {

            stmt.executeUpdate(createTable);
            System.out.println("Table created successfully!");

            stmt.executeUpdate(insertData);
            System.out.println("Data inserted successfully!");

            ResultSet rs = stmt.executeQuery(selectData);
            System.out.println("Contents of the Employees table:");
            while (rs.next()) {
                int id = rs.getInt("id");
                String first = rs.getString("first");
                String last = rs.getString("last");
                int age = rs.getInt("age");
                System.out.println("ID: " + id + ", First: " + first + ", Last: " + last + ", Age: " + age);
            }

            stmt.executeUpdate(updateData);
            System.out.println("Data updated successfully!");

            rs = stmt.executeQuery(selectData);
            System.out.println("\nContents of the Employees table after update:");
            while (rs.next()) {
                int id = rs.getInt("id");
                String first = rs.getString("first");
                String last = rs.getString("last");
                int age = rs.getInt("age");
                System.out.println("ID: " + id + ", First: " + first + ", Last: " + last + ", Age: " + age);
            }

            stmt.executeUpdate(deleteData);
            System.out.println("Data deleted successfully!");

            rs = stmt.executeQuery(selectData);
            System.out.println("\nContents of the Employees table after delete:");
            while (rs.next()) {
                int id = rs.getInt("id");
                String first = rs.getString("first");
                String last = rs.getString("last");
                int age = rs.getInt("age");
                System.out.println("ID: " + id + ", First: " + first + ", Last: " + last + ", Age: " + age);
            }

        } catch (SQLException e) {
            System.err.println("Error!!: " + e.getMessage());
        }
    }
}

