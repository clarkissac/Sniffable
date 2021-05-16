package at.fhstp.sniffable;

import java.util.HashMap;
import java.util.StringJoiner;
import java.util.logging.*;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.io.IOException;
import java.net.*;
import java.net.http.*;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse.BodyHandlers;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.Connection;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class SniffableApplicationTests {
	//const
	static String DB_DRIVER = "org.h2.Driver";
    static String DB_CONNECTION = "jdbc:h2:mem:testdb";
    static String DB_USER = "sa";
    static String DB_PASSWORD = "";

	@Autowired
	ImageMetaRepository imageMetaRepository;

	Logger log = Logger.getLogger(SniffableApplication.class.getName());

    /**
     * Tear all things up
     */
    @BeforeAll
	//HTTP POST
	/**public static void httpPost(String address, HashMap<String,String> arguments) 
	throws IOException,InterruptedException {

	var sj = new StringJoiner("&");
    for(var entry : arguments.entrySet()) {
      sj.add(URLEncoder.encode(entry.getKey(), "UTF-8") + "="
              + URLEncoder.encode(entry.getValue(), "UTF-8"));
    }

    var out = sj.toString().getBytes(StandardCharsets.UTF_8);
    var request = HttpRequest.newBuilder()
            .uri(URI.create(address))
            .headers("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8")
            .POST(BodyPublishers.ofByteArray(out))
            .build();

	HttpResponse<String> response =HttpClient.newHttpClient().send(request, BodyHandlers.ofString());
	// print status code
	System.out.println(response.statusCode());

	// print response body
	System.out.println(response.body());
	}**/

    public static void setUp() throws IOException, InterruptedException {
		//Sniffer user1 = new Sniffer("user1","user1","user1","user1","user1");
		//Sniffer user2 = new Sniffer("user2","user2","user2","user2","user2");
		//adduser(user1);
		//adduser(user2);

		//add user1 and user2
		/**HashMap <String,String> temp = new HashMap<String,String>();
		temp.put("name", "user1");
		temp.put("name","user1");
		temp.put("firstname","user1");
		temp.put("lastname","user1");
		temp.put("dogname","user1");
		temp.put("password","user1");
		httpPost("http://127.0.0.1:8080/register",temp);

		temp.clear();
		temp.put("name", "user2");
		temp.put("name","user2");
		temp.put("firstname","user2");
		temp.put("lastname","user2");
		temp.put("dogname","user2");
		temp.put("password","user2");
		httpPost("http://127.0.0.1:8080/register",temp);**/

		//Sniffer user1 = new Sniffer("user1","user1","user1","user1","user1");
		//Sniffer user2 = new Sniffer("user2","user2","user2","user2","user2");	
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
	void testLike() {

		Tweet tweet = new Tweet("TestTweet");
		tweet.addLike("testUser");
		assertEquals(tweet.getLikes().size(), 1);
		assertEquals(tweet.getLikes().get(0).getUser(),"testUser");
	}

	@Test
	void testComment() {

		Tweet tweet = new Tweet("TestTweet");
		tweet.addComment("testUser", "testComment");
		assertEquals(tweet.getComments().size(), 1);
		assertEquals(tweet.getComments().get(0).getUser(),"testUser");
		assertEquals(tweet.getComments().get(0).getContent(),"testComment");
	}
	
	@Test
	void testImageMeta() {
				Path path = Paths.get("testPath");
		ImageMeta metaDate = new ImageMeta("testImage", 0, path, "testUser");

		imageMetaRepository.addMeta(metaDate);
		assertEquals(imageMetaRepository.getImageCount(), 1);
		assertEquals(imageMetaRepository.getMetaData().get(0).getName(), "testImage");
		assertEquals(imageMetaRepository.getMetaData().get(0).getSize(), 0);
		assertEquals(imageMetaRepository.getMetaData().get(0).getFilePath(), path);
		assertEquals(imageMetaRepository.getMetaData().get(0).getUser(), "testUser");
		
	}


	@Test
	void testSniffer() {

		Sniffer user = new Sniffer("test", "test", "test", "test", "test");
		user.addTweets("TestTweet");
		assertEquals(user.getName(), "test");
		assertEquals(user.getPassword(), "test");
		assertEquals(user.getLastname(), "test");
		assertEquals(user.getDogname(), "test");
		assertEquals(user.getFirstname(), "test");
		assertEquals(user.getTweets().get(0).getContent()[0], "TestTweet");


	}
}
