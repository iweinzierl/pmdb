package com.github.iweinzierl.pmdb.cloud.domain.util;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.google.common.base.Strings;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LocalDateJsonDeserializer extends JsonDeserializer<LocalDate> {

    @Override
    public LocalDate deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        return p == null || Strings.isNullOrEmpty(p.getValueAsString())
                ? null
                : LocalDate.parse(p.getValueAsString(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }
}
