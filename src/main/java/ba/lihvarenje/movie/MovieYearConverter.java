package ba.lihvarenje.movie;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.text.SimpleDateFormat;
import java.time.LocalDate;

@Converter(autoApply = true)
public class MovieYearConverter implements AttributeConverter<LocalDate, Short> {
    @Override
    public Short convertToDatabaseColumn(LocalDate date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");
        String year = dateFormat.format(date);
        System.out.println("CONVERSION from DATE: " + date +" u YEAR: " + year);
        return Short.parseShort(year);
    }

    @Override
    public LocalDate convertToEntityAttribute(Short year) {
        LocalDate date = LocalDate.of(year, 1, 1);
        System.out.println("Konverzija iz recorda u BAZU u OBJEKAT u programu u Javi");
        return date;
    }
}
