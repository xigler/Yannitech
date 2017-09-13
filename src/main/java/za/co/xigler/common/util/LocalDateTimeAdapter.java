package za.co.xigler.common.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class LocalDateTimeAdapter extends XmlAdapter<String, LocalDateTime> {

    @Override
    public LocalDateTime unmarshal(String dateInput) throws Exception {
        return LocalDateTime.parse(dateInput, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    }

    @Override
    public String marshal(LocalDateTime localDate) throws Exception {
        return DateTimeFormatter.ISO_LOCAL_DATE_TIME.format(localDate);
    }
}