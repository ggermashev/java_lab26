package org.example;


import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedList;

public class App
{
    public static void main( String[] args ) throws SQLException {
        MyConnection my_connection = new MyConnection("jdbc:postgresql://localhost:5432/java_lab26", "postgres", "postgres");
        Connection connection = my_connection.getConnection();
        University university = new University(connection);
        LinkedList<String> teachers_work = university.getTeachersWork("MONDAY", 1);
        for (String t: teachers_work) {
            System.out.println(t);
        }
        System.out.println("---------------------------");
        LinkedList<String> teachers_dont_work = university.getTeachersDontWork("MONDAY");
        for (String t: teachers_dont_work) {
            System.out.println(t);
        }
        System.out.println("---------------------------");
        LinkedList<String> days = university.getDays(5);
        for (String d: days) {
            System.out.println(d);
        }
    }
}
