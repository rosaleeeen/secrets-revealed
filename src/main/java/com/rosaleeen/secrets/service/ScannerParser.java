package com.rosaleeen.secrets.service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.rosaleeen.secrets.model.ScannerModel;
import lombok.RequiredArgsConstructor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class ScannerParser {
    // creates mapper that ignores unknown properties when deserialising
    private final ObjectMapper mapper = new ObjectMapper()
            .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

    /**
     * Traverses through given json string, adding each individual secret find to List.
     * @param jsonString NDJSON string containing scan finds.
     * @return List of find elements.
     */
    public List<ScannerModel> parseScan(String jsonString) throws IOException {
        List<ScannerModel> result = new ArrayList<>();

        for (String line : jsonString.split("\n")) {
            line = line.trim();
            if (line.isEmpty()) continue;

            result.add(mapper.readValue(line, ScannerModel.class));
        }
        return result;
    }
}
