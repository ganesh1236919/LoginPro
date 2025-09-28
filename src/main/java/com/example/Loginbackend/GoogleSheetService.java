package com.example.Loginbackend;

import com.google.auth.oauth2.GoogleCredentials;
//package com.example.googlesheets.service;
import com.google.auth.oauth2.ServiceAccountCredentials;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.SheetsScopes;
import com.google.api.services.sheets.v4.model.*;
import com.google.auth.http.HttpCredentialsAdapter;
import com.google.auth.oauth2.ServiceAccountCredentials;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

@Service
public class GoogleSheetService {

    private static final String APPLICATION_NAME = "Spring Boot Google Sheets Demo";
    private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();

    // Replace with your spreadsheet ID
    private static final String SPREADSHEET_ID = "15voHeBfsjy0fcIdghEE-gEX7_egJ9zhNyxP2BT1aMIk";

    private Sheets getSheetsService() throws Exception {
        InputStream in = getClass().getResourceAsStream("/credentials.json");

        GoogleCredentials credentials = ServiceAccountCredentials.fromStream(in)
                .createScoped(List.of(SheetsScopes.SPREADSHEETS));

        return new Sheets.Builder(
                GoogleNetHttpTransport.newTrustedTransport(),
                JSON_FACTORY,
                new HttpCredentialsAdapter(credentials))
                .setApplicationName(APPLICATION_NAME)
                .build();
    }

    public void addRow(String name, String email, String phone) throws Exception {
        Sheets service = getSheetsService();

        List<Object> row = Arrays.asList(name, email, phone);
        ValueRange requestBody = new ValueRange().setValues(List.of(row));

        service.spreadsheets().values()
                .append(SPREADSHEET_ID, "Sheet1!A:C", requestBody)
                .setValueInputOption("RAW")
                .execute();
    }
}
