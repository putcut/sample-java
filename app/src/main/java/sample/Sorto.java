package sample;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import javax.xml.datatype.DatatypeConstants;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import sample.model.SortoP;

public class Sorto {
    public static void main(String[] args) {

        List<SortoP> list = new ArrayList<>();
        try {
            list.add(new SortoP(1, "Rikako", createDatetime(LocalDateTime.of(2021, 4, 18, 0, 0, 0))));
            list.add(new SortoP(2, "Kyoko", null));
            list.add(new SortoP(2, "Himeko", createDatetime(LocalDateTime.of(2021, 4, 15, 0, 0, 0))));
            
            // Collections.sort(list, (x, y) -> convertDatetime(x.getDatetime()).compareTo(convertDatetime(y.getDatetime())));

            // List<P> sortlist = list.stream()
            //     .sorted((x, y) -> convertDatetime(x.getDatetime()).compareTo(convertDatetime(y.getDatetime())))
            //     .collect(Collectors.toList());

            List<SortoP> sortlist = list.stream()
                .sorted(Comparator.comparing(x -> convertDatetime(x.getDatetime()), Comparator.nullsLast(Comparator.naturalOrder())))
                .collect(Collectors.toList());
            
            System.out.println(sortlist);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static XMLGregorianCalendar createDatetime(LocalDateTime ldt) throws Exception {
        DatatypeFactory factory = DatatypeFactory.newInstance();
        XMLGregorianCalendar datetime  = factory.newXMLGregorianCalendar();
        datetime.setYear(ldt.getYear());
        datetime.setMonth(ldt.getMonthValue());
        datetime.setDay(ldt.getDayOfMonth());
        datetime.setHour(ldt.getHour());
        datetime.setMinute(ldt.getMinute());
        datetime.setSecond(ldt.getSecond());
        datetime.setMillisecond(DatatypeConstants.FIELD_UNDEFINED);
        datetime.setTimezone(DatatypeConstants.FIELD_UNDEFINED);

        return datetime;
    }

    private static LocalDateTime convertDatetime(XMLGregorianCalendar datetime) {
        if (datetime == null) {
            return null;
        }
        return LocalDateTime.of(
            datetime.getYear()
            , datetime.getMonth()
            , datetime.getDay()
            , datetime.getHour()
            , datetime.getMinute()
            , datetime.getSecond()
        );
    }
}
