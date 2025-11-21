package com.rosaleeen.secrets.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


@lombok.Data
@JsonPropertyOrder({
        "SourceMetadata",
        "DetectorName",
        "DetectorDescription",
        "Raw",
        "RawV2",
        "Redacted",
        "Verified"
})
public class ScannerModel {

    @JsonProperty("SourceMetadata")
    private SourceMetadata metadata;

    @JsonProperty("DetectorName")
    private String detectorName;

    @JsonProperty("DetectorDescription")
    private String detectorDescription;

    @JsonProperty("Verified")
    private boolean verified;

    @JsonProperty("Raw")
    private String raw;

    @JsonProperty("RawV2")
    private String rawV2;

    @JsonProperty("Redacted")
    private String redacted;
}
