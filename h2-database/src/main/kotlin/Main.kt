package org.example

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


fun insertStudentRecord(connection: Connection) {
    var sql = "Create table students (ID int primary key, name varchar(50))";
    val statement = connection.createStatement();
    statement.execute(sql);
    println("Create table students.");

    sql = "Insert into students (ID, name) values (1, 'Nam Ha Minh')";
    val rows = statement.executeUpdate(sql);

    if (rows > 0) {
        println("Inserted a new row.");
    }
}

fun selectAllStudentRecords(connection: Connection) {
    var sql = "Select * from students;"
    val statement = connection.createStatement();
    val resultSet = statement.executeQuery(sql);

    var count = 0;
    while (resultSet.next()) {
        count++;

        val id = resultSet.getInt("ID");
        val name = resultSet.getString("name");
        println(name);
    }
}

fun main() {
    val jdbcUrl = "jdbc:h2:./test";
    val connection = DriverManager.getConnection(jdbcUrl);
    println("Connected to database");

    //insertStudentRecord(connection);
    selectAllStudentRecords(connection);

    connection.close();
}