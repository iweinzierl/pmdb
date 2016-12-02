package com.github.iweinzierl.pmdb.cloud.domain.util;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;

import java.sql.Date;
import java.time.LocalDate;

public class LocalDateAttributeConverterTest {

    @Test
    public void testConvertValueToDatabase() {
        Date date = new LocalDateAttributeConverter().convertToDatabaseColumn(LocalDate.now());
        MatcherAssert.assertThat(date, Matchers.is(Matchers.notNullValue()));
    }

    @Test
    public void testConvertNullValueToDatabase() {
        Date date = new LocalDateAttributeConverter().convertToDatabaseColumn(null);
        MatcherAssert.assertThat(date, Matchers.is(Matchers.nullValue()));
    }

    @Test
    public void testConvertValueToEntityAttribute() {
        LocalDate date = new LocalDateAttributeConverter().convertToEntityAttribute(new Date(2016, 12, 15));
        MatcherAssert.assertThat(date, Matchers.is(Matchers.notNullValue()));
    }

    @Test
    public void testConvertNullValueToEntityAttribute() {
        LocalDate date = new LocalDateAttributeConverter().convertToEntityAttribute(null);
        MatcherAssert.assertThat(date, Matchers.is(Matchers.nullValue()));
    }
}