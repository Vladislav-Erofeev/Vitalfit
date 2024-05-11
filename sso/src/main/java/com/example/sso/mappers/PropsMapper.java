package com.example.sso.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Named;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Mapper
public interface PropsMapper {
    ThreadLocal<SimpleDateFormat> dateFormat = ThreadLocal.withInitial(() -> new SimpleDateFormat("dd.MM.yyyy"));

    @Named("getDateFromString")
    static Date getDateFromString(String string) throws ParseException {
        return dateFormat.get().parse(string);
    }

    @Named("getStringFromDate")
    static String getStringFromDate(Date date) {
        return dateFormat.get().format(date);
    }
}
