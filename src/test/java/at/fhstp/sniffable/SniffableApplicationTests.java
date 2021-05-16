package at.fhstp.sniffable;

import java.util.logging.*;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Connection;

import org.apache.tomcat.jni.User;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import at.fhstp.sniffable.*;

@SpringBootTest
public class SniffableApplicationTests {
	//const
	static String DB_DRIVER = "org.h2.Driver";
    static String DB_CONNECTION = "jdbc:h2:mem:testdb";
    static String DB_USER = "sa";
    static String DB_PASSWORD = "";

	Logger log = Logger.getLogger(SniffableApplication.class.getName());

	public static void adduser(Sniffer user) {
		try {

			Class.forName(DB_DRIVER);
			Connection dbConnection = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
			String selectQuery = "INSERT INTO Sniffers (username, passwort, first_name, last_name, dog_name, obj) VALUES (?, ?, ?, ?, ?, ?); ";
			PreparedStatement preparedStatement = dbConnection.prepareStatement(selectQuery);
			preparedStatement.setObject(1,user.getName());
			preparedStatement.setObject(2,user.getPassword());
			preparedStatement.setObject(3,user.getFirstname());
			preparedStatement.setObject(4,user.getLastname());
			preparedStatement.setObject(5,user.getDogname());
			preparedStatement.setObject(6,user);
			preparedStatement.execute();
			dbConnection.commit();
			dbConnection.close();
		} catch (Exception e) {
			//TODO: handle exception
		}
		
	}
    /**
     * Tear all things up
     */
    @BeforeAll
    public static void setUp() {
		Sniffer user1 = new Sniffer("user1","user1","user1","user1","user1");
		Sniffer user2 = new Sniffer("user2","user2","user2","user2","user2");
		adduser(user1);
		adduser(user2);
		
        System.out.println("@BeforeAll - executes once before all test methods in this class");
    }

    /**
     * Tear all things up - before each test
     */

    @BeforeEach
    void init() {
        log.info("@BeforeEach - executes before each test method in this class");
    }

    /**
     * Rigorous Test :-)
     */
	@Test
	void contextLoads() {
	}

}
