package co.ankasec.campushub.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/view/verifications")
public class VerificationViewController {

    @GetMapping("/upload")
    public String showUploadPage() {
        return "upload";
    }
}