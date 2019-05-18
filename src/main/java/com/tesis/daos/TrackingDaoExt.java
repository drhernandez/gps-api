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
                .where(Trackings.TRACKINGS.DELETED_AT.isNull())
                .and(Trackings.TRACKINGS.DEVICE_ID.eq(deviceID))
                .fetch()
                .map(mapper());
    }
}
