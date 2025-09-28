package com.example.Loginbackend;

//package com.example.googlesheets.controller;

import org.springframework.web.bind.annotation.*;

//@RestController
///@RequestMapping("/api/sheet")
public class SheetController {

    private final GoogleSheetService sheetService;

    public SheetController(GoogleSheetService sheetService) {
        this.sheetService = sheetService;
    }

    @PostMapping("/add")
    public String addData(@RequestParam String name,
                          @RequestParam String email,
                          @RequestParam String phone) {
        try {
            sheetService.addRow(name, email, phone);
            return "Data added successfully!";
        } catch (Exception e) {
            e.printStackTrace();
            return "Error: " + e.getMessage();
        }
    }
}
