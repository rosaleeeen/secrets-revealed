package com.rosaleeen.secrets.client;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.List;


@Component
@RequiredArgsConstructor
public class ScannerClient {

    /**
     * Executes given command as a command-line process, returning its merged std and err
     * output as a String.
     * <p>
     * The method waits for the external process to finish, unless it exceeds the timeout.
     * If process times out, or fails to start, a ScannerClientException is thrown.
     *
     * @param cmd ordered comprehensive command and arguments to execute.
     * @return combined standard and error output produced by command-line process.
     */
    public String run(List<String> cmd) {
        if (cmd == null || cmd.isEmpty()) {
            throw new IllegalArgumentException("ERROR: cmd is null or empty :(");
        }

        ProcessBuilder pb = new ProcessBuilder(cmd);
        pb.redirectErrorStream(true);   // merge stderr w/ stdout

        Process p;

        try {
            p = pb.start();
        } catch (IOException e) {
            throw new RuntimeException("ERROR: failed to start external scanner process :(");
        }

        StringBuilder sb = new StringBuilder();

        try (BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()))) {
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line).append("\n");
            }
        } catch (Exception e) {
            throw new RuntimeException("ERROR: failed to read external scanner output :(");
        }
        return sb.toString();
    }
}