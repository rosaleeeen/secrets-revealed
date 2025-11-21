package com.rosaleeen.secrets.service;

import com.rosaleeen.secrets.client.ScannerClient;
import com.rosaleeen.secrets.config.ScannerProperties;
import com.rosaleeen.secrets.model.ScannerModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class ScannerService {
    private final ScannerClient client;
    private final ScannerParser parser;
    private final ScannerProperties props;

    /**
     * Initialises string List with binary path as sole element.
     * @return string List containing binary path.
     */
    public List<String> baseCommand() {
        return new ArrayList<>(List.of(props.getBinary()));
    }

    /**
     * Executes external command for Trufflehog version, and returns process output.
     * @return string output containing Trufflehog version.
     */
    public String retrieveVersion() {
        var cmd = baseCommand();
        cmd.add(props.getFlags().getVersion());
        return client.run(cmd);
    }

    /**
     * Executes external repository scan, and returns readable summary of found secrets.
     * @param link URL of Github repository to be scanned.
     * @return string readable summary of found secrets.
     */
    public List<ScannerModel> scanRepositoryModel(String link) throws IOException {
        var cmd = baseCommand();
        cmd.add(props.getFlags().getScanRepository().getSource());
        cmd.addAll(props.getFlags().getScanRepository().getDefaultFlags());
        cmd.add(props.getFlags().getScanRepository().getRepository() + link);

        return parser.parseScan(client.run(cmd));
    }
}