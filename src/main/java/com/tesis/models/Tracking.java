package com.tesis.models;

import com.tesis.jooq.tables.pojos.Trakings;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class Tracking extends Trakings {

    public Tracking() {
        super();
    }

    public Tracking(Trakings value) {
        super(value);
    }

    public Tracking(Integer id, Long deviceId, Float lat, Float long_, Integer sat, Integer hdop, Timestamp time) {
        super(id, deviceId, lat, long_, sat, hdop, time);
    }

    public Tracking(String[] args) {
        super(
                null,
                Long.valueOf(args[0]),
                Float.valueOf(args[1]),
                Float.valueOf(args[2]),
                Integer.valueOf(args[3]),
                Integer.valueOf(args[4]),
                Timestamp.valueOf(LocalDateTime.now())
        );
    }
}
