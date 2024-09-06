package util;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ChangeTimeFormat {
    public String format(Timestamp time) {
        LocalDateTime now = time.toLocalDateTime();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd a hh시 mm분");
        return String.valueOf(now.format(formatter));
    }
}
