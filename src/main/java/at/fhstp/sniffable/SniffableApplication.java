package at.fhstp.sniffable;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.PreparedStatement;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@SpringBootApplication
@Controller
public class SniffableApplication {
	static String DB_DRIVER = "org.h2.Driver";
    static String DB_CONNECTION = "jdbc:h2:mem:testdb";
    static String DB_USER = "sa";
    static String DB_PASSWORD = "";
	public static void main(String[] args) {
		SpringApplication.run(SniffableApplication.class, args);
		//test
	}

	@GetMapping("/")
	public String homepage(HttpServletRequest request)
	{
		Cookie[] cookies = request.getCookies();
		if (cookies != null){
			for (Cookie ck : cookies) {
			  if ("username".equals(ck.getName())) {
				try {
					Class.forName(DB_DRIVER);
					Connection dbConnection = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
					String selectQuery = "select * from Sniffers WHERE first_name=?";
					PreparedStatement preparedStatement = dbConnection.prepareStatement(selectQuery);
					preparedStatement.setObject(1,ck.getValue());
					ResultSet rs = preparedStatement.executeQuery();
					///System.out.println(rs.getString("first_name"));
					while(rs.next())    {
						//Sniffer user = (Sniffer) rs.getObject("ser_user");
						//model.addAttribute("user", user);
						System.out.println(rs.getString("first_name"));
					}
					dbConnection.close();
					return "welcome.html";
				}
				catch (Exception e)   {
					e.printStackTrace();
				}
				
				//return "welcome.html";
			  }
		  	}
		}
			return "index.html";
	}
	@GetMapping("/register")
    public String showForm(Model model) {
        Sniffer user = new Sniffer();
        model.addAttribute("user", user);
         
        //List<String> listProfession = Arrays.asList("Developer", "Tester", "Architect");
        //model.addAttribute("listProfession", listProfession);
    
        return "register_form";
    }

	@PostMapping("/register")
	public String submitForm(@ModelAttribute("user") Sniffer user, HttpServletResponse response) {
		//System.out.println(user);
		Cookie cookie = new Cookie("username", user.getName());

    	//add cookie to response
    	response.addCookie(cookie);
		return "register_success.html";
	}
	@GetMapping("/login")
	public String getForm(){
		return "login_form.html";

	}
	@PostMapping("/login")
    public String submitFormLo(@RequestParam("name") String name, @RequestParam("password") String password) {

		if (name.compareTo("test") == 1 && password.compareTo("test") == 1) {
			return "welcome.html";
			
		}
		return "index.html";
    }
}
