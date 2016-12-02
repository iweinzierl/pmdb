package com.github.iweinzierl.pmdb.cloud.domain.util;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.junit.Test;
import org.mockito.Mockito;

import java.time.LocalDate;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class LocalDateJsonSerializerTest {

    @Test
    public void testGoodCase() throws Exception {
        JsonGenerator generatorMock = mock(JsonGenerator.class);
        SerializerProvider providerMock = mock(SerializerProvider.class);

        LocalDate date = LocalDate.of(2016, 11, 15);

        new LocalDateJsonSerializer().serialize(date, generatorMock, providerMock);

        verify(generatorMock, Mockito.times(1)).writeString("2016-11-15");
    }

    @Test
    public void testNullValue() throws Exception {
        JsonGenerator generatorMock = mock(JsonGenerator.class);
        SerializerProvider providerMock = mock(SerializerProvider.class);

        new LocalDateJsonSerializer().serialize(null, generatorMock, providerMock);

        verify(generatorMock, Mockito.times(1)).writeString("");
    }
}