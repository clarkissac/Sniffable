package at.fhstp.sniffable;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@SpringBootApplication
public class SniffableApplication {

	public static void main(String[] args) {
		SpringApplication.run(SniffableApplication.class, args);
		//test
	}

	@GetMapping("/register")
    public String showForm(Model model) {
        Sniffer user = new Sniffer();
        model.addAttribute("user", user);
         
        //List<String> listProfession = Arrays.asList("Developer", "Tester", "Architect");
        //model.addAttribute("listProfession", listProfession);
         
        return "register_form";
    }
}
