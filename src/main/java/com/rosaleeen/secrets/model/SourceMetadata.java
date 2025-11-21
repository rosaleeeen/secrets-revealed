package com.rosaleeen.secrets.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonUnwrapped;


@lombok.Data
public class SourceMetadata {
    @JsonUnwrapped
    @JsonProperty("Data")
    private Data data;
}
