package com.rosaleeen.secrets.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonUnwrapped;


@lombok.Data
public class Data {
    @JsonUnwrapped
    @JsonProperty("Github")
    private Github github;
}
