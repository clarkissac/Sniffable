package at.fhstp.sniffable;

import java.util.logging.*;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import static org.junit.jupiter.api.Assertions.assertEquals;
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
    public static void setUp() throws IOException, InterruptedException {
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
		metaDate.addComment("testUser", "testComment");
		imageMetaRepository.addMeta(metaDate);
		assertEquals(imageMetaRepository.getImageCount(), 1);
		assertEquals(imageMetaRepository.getMetaData().get(0).getName(), "testImage");
		assertEquals(imageMetaRepository.getMetaData().get(0).getSize(), 0);
		assertEquals(imageMetaRepository.getMetaData().get(0).getFilePath(), path);
		assertEquals(imageMetaRepository.getMetaData().get(0).getUser(), "testUser");
		assertEquals(imageMetaRepository.getMetaData().get(0).getComments().get(0).getUser(),"testUser");
		assertEquals(imageMetaRepository.getMetaData().get(0).getComments().get(0).getContent(),"testComment");
		assertEquals(imageMetaRepository.getMetaData().get(0).getComments().size(), 1);
		
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
