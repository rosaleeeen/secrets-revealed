package com.rosaleeen.secrets.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;


/**
 * Binds Trufflehog related settings from application properties.
 * Expected prefix: "trufflehog".
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "trufflehog")
public class ScannerProperties {
    private String binary;
    private Flags flags = new Flags();


    @Data
    public static class Flags {
        private String version;
        private ScanRepository scanRepository = new ScanRepository();

        @Data
        public static class ScanRepository {

            /**
             * source flag used by Trufflehog when scanning repository.
             */
            private String source;
            private List<String> defaultFlags = new ArrayList<>();

            /**
             * flag attached to Github repository URL.
             */
            private String repository;
        }
    }
}
