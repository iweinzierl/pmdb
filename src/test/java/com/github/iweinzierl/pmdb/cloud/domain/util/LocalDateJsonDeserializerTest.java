package com.github.iweinzierl.pmdb.cloud.domain.util;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.hamcrest.Matchers;
import org.junit.Test;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class LocalDateJsonDeserializerTest {

    @Test
    public void testGoodCase() throws Exception {
        JsonParser parserMock = mock(JsonParser.class);
        when(parserMock.getValueAsString()).thenReturn("2016-12-02");

        DeserializationContext ctxMock = mock(DeserializationContext.class);

        LocalDate deserialize = new LocalDateJsonDeserializer().deserialize(parserMock, ctxMock);

        assertThat(deserialize,
                Matchers.allOf(
                        Matchers.hasProperty("year", Matchers.is(2016)),
                        Matchers.hasProperty("monthValue", Matchers.is(12)),
                        Matchers.hasProperty("dayOfMonth", Matchers.is(2))
                ));
    }

    @Test
    public void testNullValue() throws Exception {
        JsonParser parserMock = mock(JsonParser.class);
        when(parserMock.getValueAsString()).thenReturn(null);

        DeserializationContext ctxMock = mock(DeserializationContext.class);

        LocalDate deserialize = new LocalDateJsonDeserializer().deserialize(parserMock, ctxMock);
        assertThat(deserialize, Matchers.is(Matchers.nullValue()));
    }

    @Test
    public void testEmptyValue() throws Exception {
        JsonParser parserMock = mock(JsonParser.class);
        when(parserMock.getValueAsString()).thenReturn("");

        DeserializationContext ctxMock = mock(DeserializationContext.class);

        LocalDate deserialize = new LocalDateJsonDeserializer().deserialize(parserMock, ctxMock);
        assertThat(deserialize, Matchers.is(Matchers.nullValue()));
    }

    @Test(expected = DateTimeParseException.class)
    public void testInvalidDate() throws Exception {
        JsonParser parserMock = mock(JsonParser.class);
        when(parserMock.getValueAsString()).thenReturn("2016-25-11");

        DeserializationContext ctxMock = mock(DeserializationContext.class);

        new LocalDateJsonDeserializer().deserialize(parserMock, ctxMock);
    }
}