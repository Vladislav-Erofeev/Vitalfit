package com.example.products.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Named;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Mapper
public interface PropsMapper {
    ThreadLocal<SimpleDateFormat> simpleDateFormat = ThreadLocal.withInitial(() -> new SimpleDateFormat("dd.MM.yyyy"));

    @Named("dateToString")
    static String dateToString(Date date) {
        return simpleDateFormat.get().format(date);
    }

    @Named("stringToDate")
    static Date stringToDate(String string) throws ParseException {
        return simpleDateFormat.get().parse(string);
    }
}
