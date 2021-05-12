/* package at.fhstp.sniffable;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class UploadController {

    
    private static String UPLOADED_FOLDER = "src\\upload-dir";

    @GetMapping("/upload")
    public String uploadIndex(Model model,HttpServletRequest request) {

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
        return "upload";
    }

    @PostMapping("/upload")
    public String singleFileUpload(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {

        if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
            return "redirect:uploadStatus";
        }

        try {
            byte[] bytes = file.getBytes();
            Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
            Files.write(path, bytes);

            redirectAttributes.addFlashAttribute("message",
                    "You successfully uploaded '" + file.getOriginalFilename() + "'");

        } catch (IOException e) {
            redirectAttributes.addFlashAttribute("message", "Upload failed");
            e.printStackTrace();
        }
        
        return "redirect:/uploadStatus";
    }

    @GetMapping("/uploadStatus")
    public String uploadStatus() {
        return "uploadStatus";
    }

} */