package com.rosaleeen.secrets.controller;

import com.rosaleeen.secrets.model.ScannerModel;
import com.rosaleeen.secrets.service.ScannerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;


@RestController
@RequiredArgsConstructor
public class ScannerController {
    private final ScannerService service;

    /**
     * Runs configured Trufflehog binary with the version flag and returns raw CLI output as a string.
     * @return raw output produced by the Trufflehog version command.
     */
    @GetMapping("/version")
    public String version() throws IOException { return service.retrieveVersion(); }

    /**
     * Runs Trufflehog repository scan against given repository URL, returning resulting JSON as a string.
     * @return resulting JSON as a string.
     */
    @GetMapping("/scan")
    public List<ScannerModel> scan(@RequestParam String link) throws IOException {
        return service.scanRepositoryModel(link); }
}