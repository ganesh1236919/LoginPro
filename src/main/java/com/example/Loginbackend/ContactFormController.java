package com.example.Loginbackend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/contact")
@CrossOrigin(origins = "http://localhost:3000")
public class ContactFormController {

    @Autowired
    private ContactFormRepository contactFormRepository;
    @Autowired
    private  GoogleSheetService sheetService;

    // Client submits form
    @PostMapping("/submit")
    public ContactForm submitForm(@RequestBody ContactForm form) {
    	
    	
    	 try {
			sheetService.addRow(form.getName(), form.getEmail(), form.getMessage());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return contactFormRepository.save(form);
        
        
    }

    // Admin views all
    @GetMapping("/all")
    public List<ContactForm> getAllForms() {
        return contactFormRepository.findAll();
    }
}
