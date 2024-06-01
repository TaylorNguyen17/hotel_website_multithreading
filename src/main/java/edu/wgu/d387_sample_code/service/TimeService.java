package edu.wgu.d387_sample_code.service;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class TimeService {

    public String getPresentationTimes() {

        LocalDateTime presentationTime = LocalDateTime.of(2024, 6, 30, 12, 30);

        ZoneId easternTime = ZoneId.of("America/New_York");
        ZoneId mountainTime = ZoneId.of("America/Denver");
        ZoneId utcTime = ZoneId.of("UTC");

        ZonedDateTime etTimeConvert = presentationTime.atZone(easternTime);
        ZonedDateTime mtTimeConvert = etTimeConvert.withZoneSameInstant(mountainTime);
        ZonedDateTime utcTimeConvert = etTimeConvert.withZoneSameInstant(utcTime);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm a z");

        String etFormat = etTimeConvert.format(formatter);
        String mtFormat = mtTimeConvert.format(formatter);
        String utcFormat = utcTimeConvert.format(formatter);

        return String.format(
                "Join us for an online live presentation held at the Landon Hotel on June 30, 2024 at %s | %s | %s",
                etFormat, mtFormat, utcFormat);

    }

}
