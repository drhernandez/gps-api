package com.tesis.daos;

import com.tesis.jooq.tables.Trackings;
import com.tesis.jooq.tables.daos.TrackingsDao;
import org.jooq.Configuration;
import org.jooq.impl.DSL;

import javax.inject.Inject;
import java.util.List;

public class TrackingDaoExt extends TrackingsDao {

    @Inject
    public TrackingDaoExt(Configuration configuration){
        super(configuration);
    }

    public List<com.tesis.jooq.tables.pojos.Trackings> findAllActivesByDeviceID(Long deviceID){
        return DSL
                .using(configuration())
                .selectFrom(Trackings.TRACKINGS)
                .where(Trackings.TRACKINGS.DEVICE_ID.eq(deviceID))
                .fetch()
                .map(mapper());
    }


    public com.tesis.jooq.tables.pojos.Trackings findLocationByDeviceID(Long deviceID){
        try {
            return DSL
                    .using(configuration())
                    .selectFrom(Trackings.TRACKINGS)
                    .where(Trackings.TRACKINGS.DEVICE_ID.eq(deviceID))
                    .orderBy(Trackings.TRACKINGS.TIME.desc())
                    .fetchAny().map(record -> new com.tesis.jooq.tables.pojos.Trackings(
                                record.getValue(Trackings.TRACKINGS.ID),
                                record.getValue(Trackings.TRACKINGS.DEVICE_ID),
                                record.getValue(Trackings.TRACKINGS.LAT),
                                record.getValue(Trackings.TRACKINGS.LNG),
                                record.getValue(Trackings.TRACKINGS.SPEED),
                                record.getValue(Trackings.TRACKINGS.SAT),
                                record.getValue(Trackings.TRACKINGS.HDOP),
                                record.getValue(Trackings.TRACKINGS.TIME))
                    );
        }catch (Exception e){
            return null;
        }
    }
}
