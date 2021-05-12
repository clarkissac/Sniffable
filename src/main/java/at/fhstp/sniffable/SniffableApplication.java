package at.fhstp.sniffable;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.nio.file.Paths;
import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.PreparedStatement;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;



@SpringBootApplication
@Controller
public class SniffableApplication {
	static String DB_DRIVER = "org.h2.Driver";
    static String DB_CONNECTION = "jdbc:h2:mem:testdb";
    static String DB_USER = "sa";
    static String DB_PASSWORD = "";

	@Autowired
	ImageMetaRepository imageMetaRepository;

	public static void main(String[] args) {
		SpringApplication.run(SniffableApplication.class, args);
		//test
	}
	public Sniffer accountsearch(String name, Model model) {
		try {
			Sniffer user;
			Class.forName(DB_DRIVER);
			Connection dbConnection = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
			String selectQuery = "select obj from Sniffers WHERE username=?";
			PreparedStatement preparedStatement = dbConnection.prepareStatement(selectQuery);
			preparedStatement.setObject(1,name);
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) 
			{
				byte[] buf = rs.getBytes(1);
				ObjectInputStream object = null;
				if (buf != null)
					object = new ObjectInputStream(new ByteArrayInputStream(buf));

				user = (Sniffer) object.readObject();
					//Sniffer user = (Sniffer) rs.getObject("obj");
				model.addAttribute("user", user);
				//System.out.println(user.getName());
				dbConnection.close();
				return user;
			}
			
		}
		catch (Exception e)   {
			e.printStackTrace();
		}
		return null;
		
	}

	public void updateObjH2(Sniffer user)
	{
		try {
			Class.forName(DB_DRIVER);
			Connection dbConnection = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
			String selectQuery = "Update Sniffers SET obj = ? WHERE username=?; ";
			PreparedStatement preparedStatement = dbConnection.prepareStatement(selectQuery);
			preparedStatement.setObject(1,user);
			preparedStatement.setObject(2,user.getName());
			preparedStatement.executeUpdate();
			dbConnection.close();
		}
		catch (Exception e)   {
			e.printStackTrace();
		}
	}

	@PostMapping("/follow")
	public String follownow(@RequestParam("hidden_wanted_user") String wantedUser,HttpServletRequest request, Model model)
	{
		Sniffer wanted = accountsearch(wantedUser, model);
		Cookie[] cookies = request.getCookies();
		if (cookies != null){
			for (Cookie ck : cookies) {
			  if ("username".equals(ck.getName())) {
				Sniffer follower=accountsearch(ck.getValue(), model);
				wanted.addNewFollower(follower);
				follower.addNewFollowing(wanted);
				updateObjH2(wanted);
				updateObjH2(follower);
			  }
			}
		}
		model.addAttribute("user", wanted);
		return "other_account.html";	
	}
	@GetMapping("/")
	public String homepage(Model model,HttpServletRequest request)
	{
		Cookie[] cookies = request.getCookies();
		if (cookies != null){
			for (Cookie ck : cookies) {
			  if ("username".equals(ck.getName())) {
				accountsearch(ck.getValue(), model);
				return "own_account.html";
				}
				
				//return "welcome.html";
			  }
		  	}
			return "index.html";
	}

	@PostMapping("/")
	public String submit_search(@RequestParam("search_user") String name, Model model) {
		accountsearch(name, model);
		return "other_account.html";
	}

	@GetMapping("/register")
    public String showForm(Model model) {
        Sniffer user = new Sniffer();
        model.addAttribute("user", user);
        return "register_form.html";
    }

	@PostMapping("/register")
	public String submitForm(@ModelAttribute("user") Sniffer user, HttpServletResponse response) {
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
			Cookie cookie = new Cookie("username", user.getName());
    		response.addCookie(cookie);
		}
		catch (Exception e)   {
			e.printStackTrace();
		}
		
		return "register_success.html";
	}
	@GetMapping("/login")
	public String getForm(){
		return "login_form.html";

	}
	@PostMapping("/login")
    public String submitFormLogin(@RequestParam("name") String name, 
									@RequestParam("password") String password,
									HttpServletResponse response,
									RedirectAttributes redirectAttributes, Model model) {
		try {
			Class.forName(DB_DRIVER);
			Connection dbConnection = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
			String selectQuery = "select * from Sniffers WHERE username=? and passwort=? ";
			PreparedStatement preparedStatement = dbConnection.prepareStatement(selectQuery);
			preparedStatement.setObject(1,name);
			preparedStatement.setObject(2,password);
			ResultSet rs = preparedStatement.executeQuery();
			///System.out.println(rs.getString("first_name"));
			if (!rs.next()) {
				dbConnection.close();
				redirectAttributes.addAttribute("bad",1); //bad credentials
				return "redirect:/login";
			}
			else{
				dbConnection.close();
				Cookie cookie = new Cookie("username",name);
    			response.addCookie(cookie);
				return "redirect:/";
			}
		}
		catch (Exception e)   {
			e.printStackTrace();
			return "index.html"; //todo
		}
    }

	private static String UPLOADED_FOLDER = "src\\upload-dir";

    @GetMapping("/upload")
    public String uploadIndex(Model model, HttpServletRequest request, RedirectAttributes redirectAttributes) {

        Cookie[] cookies = request.getCookies();
		if (cookies != null){
			for (Cookie ck : cookies) {
			  if ("username".equals(ck.getName()) && accountsearch(ck.getValue(), model) != null) {
				return "upload";
				}
			  }
		  	}
        return "uploadstatus";
    }


    @PostMapping("/upload")
    public String singleFileUpload(@RequestParam("file") MultipartFile file, Model model, RedirectAttributes redirectAttributes, HttpServletRequest request) {

		Cookie[] cookies = request.getCookies();

        if (file.isEmpty() || !file.getContentType().startsWith("image")) {
            redirectAttributes.addFlashAttribute("message", "Invalid File");
            return "redirect:/";
        } 

        try {
 			byte[] bytes = file.getBytes();
			for (Cookie ck : cookies) {
				if ("username".equals(ck.getName())) {
					Path path = Paths.get(UPLOADED_FOLDER + "\\" + ck.getValue() + "\\" + System.currentTimeMillis() + "_" + file.getOriginalFilename());		
					Files.createDirectories(path.getParent());
					Files.write(path, bytes);
					ImageMeta metaDate = new ImageMeta(file.getOriginalFilename(), file.getSize(), path, ck.getValue());
					imageMetaRepository.addMeta(metaDate);
					redirectAttributes.addFlashAttribute("message",
                    "You successfully uploaded '" + file.getOriginalFilename() + "'");
				}
			}
        
        } catch (IOException e) {
            redirectAttributes.addFlashAttribute("message", "Upload failed");
            e.printStackTrace();
        }
        
        return "redirect:/";
    }

    @GetMapping("/uploadStatus")
    public String uploadStatus() {
        return "uploadStatus";
    }

}
