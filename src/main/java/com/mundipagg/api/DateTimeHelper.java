package com.mundipagg.api;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class DateTimeHelper {

    private static final DateTimeFormatter RFC1123_DATE_TIME_FORMATTER = 
            DateTimeFormat.forPattern("EEE, dd MMM yyyy HH:mm:ss z")
            .withZone(DateTimeZone.forID("GMT"));

    /**
     * Parse a Unix Timestamp to a DateTime object
     * @param date The Unix Timestamp
     * @return The DateTime object
     */
    public static DateTime fromUnixTimestamp(Long date) {
        return new DateTime(date * 1000l);
    }

    /**
     * Parse a Unix Timestamp string to a DateTime object
     * @param date The Unix Timestamp as a String
     * @return The parsed DateTime object
     */
    public static DateTime fromUnixTimestamp(String date) {
        return new DateTime(Long.parseLong(date) * 1000l);
    }

    /**
     * Convert a DateTime object to a Unix Timestamp string
     * @param value The DateTime object to convert
     * @return The converted String
     */
    public static String toUnixTimestamp(DateTime value) {
        return Long.toString(value.getMillis() / 1000l);
    }

    /**
     * Convert a List of DateTime objects to Unix Timestamp strings
     * @param value The List of DateTime objects to convert
     * @return The list of converted Strings
     */
    public static List<String> toUnixTimestamp(List<DateTime> values) {
        if(values == null)
            return null;

        List<String> valuesAsString = new ArrayList<String>();
        for (DateTime value: values) {
            valuesAsString.add(toUnixTimestamp(value));
        }
        return valuesAsString;
    }

    /**
     * Parse a datetime string in Rfc1123 format to a DateTime object
     * @param date The datetime string in Rfc1123 format
     * @return The parsed DateTime object
     */
    public static DateTime fromRfc1123DateTime(String date) {
        return RFC1123_DATE_TIME_FORMATTER.parseDateTime(date);
    }

    /**
     * Convert a DateTime object to a Rfc1123 formatted string
     * @param value The DateTime object to convert
     * @return The converted String
     */
    public static String toRfc1123DateTime(DateTime value) {
        return RFC1123_DATE_TIME_FORMATTER.print(value);
    }

    /**
     * Convert a List of DateTime objects to Rfc1123 formatted strings
     * @param value The List of DateTime objects to convert
     * @return The List of converted Strings
     */
    public static List<String> toRfc1123DateTime(List<DateTime> values) {
        if(values == null)
            return null;

        List<String> valuesAsString = new ArrayList<String>();
        for (DateTime value: values) {
            valuesAsString.add(toRfc1123DateTime(value));
        }
        return valuesAsString;
    }

    /**
     * Parse a datetime string in Rfc8601(Rfc3339) format to a DateTime object
     * @param date The datetime string in Rfc8601(Rfc3339) format
     * @return The parsed DateTime object
     */
    public static DateTime fromRfc8601DateTime(String date) {
        try {
            return new DateTime(date, DateTimeZone.UTC);
        } catch  (IllegalArgumentException e) {
            throw new IllegalArgumentException("The value '" + date + "' was not a valid RFC3339 datetime string.", e);
        }
    }

    /**
     * Convert a DateTime object to a Rfc8601(Rfc3339) formatted string
     * @param value The DateTime object to convert
     * @return The converted String
     */
    public static String toRfc8601DateTime(DateTime value) {
        return value.toString();
    }

    /**
     * Convert a List of DateTime objects to Rfc8601(Rfc3339) formatted strings
     * @param value The List of DateTime objects to convert
     * @return The List of converted Strings
     */
    public static List<String> toRfc8601DateTime(List<DateTime> values) {
        if(values == null)
            return null;

        List<String> valuesAsString = new ArrayList<String>();
        for (DateTime value: values) {
            valuesAsString.add(toRfc8601DateTime(value));
        }
        return valuesAsString;
    }

    /**
     * Parse a simple date string to a LocalDate object
     * @param date The date string
     * @return The parsed LocalDate object
     */
    public static LocalDate fromSimpleDate(String date) {
        return new LocalDate(date);
    }
    
    /**
     * Convert a LocalDate object to a string
     * @param value The LocalDate object to convert
     * @return The converted Strings
     */
    public static String toSimpleDate(LocalDate value) {
        return value.toString();
    }

    /**
     * Convert a List of LocalDate objects to strings
     * @param value The List of LocalDate objects to convert
     * @return The List of converted Strings
     */
    public static List<String> toSimpleDate(List<LocalDate> values) {
        if(values == null)
            return null;
        
        List<String> valuesAsString = new ArrayList<String>();
        for (LocalDate value: values) {
            valuesAsString.add(toSimpleDate(value));
        }
        return valuesAsString;
    }

    /**
     * A class to handle deserialization of DateTime objects to Unix Timestamps
     */
    public static class UnixTimestampDeserializer extends JsonDeserializer<DateTime> {
        @Override
        public DateTime deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
            return fromUnixTimestamp(jp.getValueAsString());
        }
    }

    /**
     * A class to handle serialization of Unix Timestamps to DateTime objects
     */
    public static class UnixTimestampSerializer extends JsonSerializer<DateTime> {
        @Override
        public void serialize(DateTime value, JsonGenerator jgen, SerializerProvider provider)
                throws IOException, JsonProcessingException {
            jgen.writeNumber(toUnixTimestamp(value));
        }
    }

    /**
     * A class to handle deserialization of DateTime objects to Rfc1123 format strings
     */
    public static class Rfc1123DateTimeDeserializer extends JsonDeserializer<DateTime> {
        @Override
        public DateTime deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
            return fromRfc1123DateTime(jp.getValueAsString());
        }
    }

    /**
     * A class to handle serialization of Rfc1123 format strings to DateTime objects 
     */
    public static class Rfc1123DateTimeSerializer extends JsonSerializer<DateTime> {
        @Override
        public void serialize(DateTime value, JsonGenerator jgen, SerializerProvider provider)
                throws IOException, JsonProcessingException {
            jgen.writeString(toRfc1123DateTime(value));
        }
    }

    /**
     * A class to handle deserialization of DateTime objects to Rfc8601(Rfc3339) format strings
     */
    public static class Rfc8601DateTimeDeserializer extends JsonDeserializer<DateTime> {
        @Override
        public DateTime deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
            return fromRfc8601DateTime(jp.getValueAsString());
        }
    }

    /**
     * A class to handle serialization of Rfc8601(Rfc3339) format strings to DateTime objects 
     */
    public static class Rfc8601DateTimeSerializer extends JsonSerializer<DateTime> {
        @Override
        public void serialize(DateTime value, JsonGenerator jgen, SerializerProvider provider)
                throws IOException, JsonProcessingException {
            jgen.writeString(toRfc8601DateTime(value));
        }
    }
    
    /**
     * A class to handle deserialization of date strings to LocalDate objects 
     */
    public static class SimpleDateDeserializer extends JsonDeserializer<LocalDate> {
        @Override
        public LocalDate deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
            return fromSimpleDate(jp.getValueAsString());
        }
    }
    
    /**
     * A class to handle serialization of LocalDate objects to date strings  
     */
    public static class SimpleDateSerializer extends JsonSerializer<LocalDate> {
        @Override
        public void serialize(LocalDate value, JsonGenerator jgen, SerializerProvider provider)
                throws IOException, JsonProcessingException {
            jgen.writeString(toSimpleDate(value));
        }
    }
}