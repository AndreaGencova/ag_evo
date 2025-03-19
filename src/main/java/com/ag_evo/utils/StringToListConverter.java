package com.ag_evo.utils;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Converter
public class StringToListConverter implements AttributeConverter<List<String>, String> {
    private static final String SPLIT_CHAR = ";";

    @Override
    public String convertToDatabaseColumn(List<String> list) {
        if(list == null) return "";
        return String.join(SPLIT_CHAR, list);
    }

    @Override
    public List<String> convertToEntityAttribute(String joined) {
        if(joined == null) return new ArrayList<>();
        return new ArrayList<>(Arrays.asList(joined.split(SPLIT_CHAR)));
    }
}
