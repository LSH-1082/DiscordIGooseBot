package util;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ChangeTimeFormat {
    public String format(Timestamp time) {
        LocalDateTime now = time.toLocalDateTime();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd hh시 mm분 a");
        return String.valueOf(now.format(formatter));
    }
}
