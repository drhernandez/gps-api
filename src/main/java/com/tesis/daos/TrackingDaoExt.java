package com.tesis.daos;

import com.tesis.enums.Status;
import com.tesis.jooq.tables.Devices;
import com.tesis.jooq.tables.Trackings;
import com.tesis.jooq.tables.daos.TrackingsDao;
import com.tesis.models.Pagination;
import com.tesis.utils.filters.TrackingFilters;
import org.jooq.Condition;
import org.jooq.Configuration;
import org.jooq.impl.DSL;

import javax.inject.Inject;
import java.util.List;

public class TrackingDaoExt extends TrackingsDao {

    @Inject
    public TrackingDaoExt(Configuration configuration){
        super(configuration);
    }

    public void saveTracking(com.tesis.jooq.tables.pojos.Trackings tracking){
        Long deviceID = getDeviceIDFromPhysicalID(tracking.getDeviceId());

        DSL.using(configuration()).insertInto(Trackings.TRACKINGS,
                    Trackings.TRACKINGS.DEVICE_ID,
                    Trackings.TRACKINGS.LAT,
                    Trackings.TRACKINGS.LNG,
                    Trackings.TRACKINGS.SAT,
                    Trackings.TRACKINGS.HDOP,
                    Trackings.TRACKINGS.TIME,
                    Trackings.TRACKINGS.SPEED)
                .values(deviceID,
                        tracking.getLat(),
                        tracking.getLng(),
                        tracking.getSat(),
                        tracking.getHdop(),
                        tracking.getTime(),
                        tracking.getSpeed())
                .execute();
    }

    public Long getDeviceIDFromPhysicalID(Long physicalID){
        return DSL.using(configuration())
                .selectFrom(Devices.DEVICES)
                .where(Devices.DEVICES.PHYSICAL_ID.eq(physicalID))
                .and(Devices.DEVICES.STATUS.eq(Status.ACTIVE.toString()))
                .and(Devices.DEVICES.DELETED_AT.isNull())
                .fetchOne().getId();
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

    public List<com.tesis.jooq.tables.pojos.Trackings> findByFilters(TrackingFilters filters, Pagination pagination){

        Condition searchCondition = DSL.trueCondition();

        if (filters.getDeviceId() != null)
            searchCondition = searchCondition.and(Trackings.TRACKINGS.DEVICE_ID.eq(filters.getDeviceId()));
        if (filters.getSpeed() != null)
            searchCondition = searchCondition.and(Trackings.TRACKINGS.SPEED.eq(filters.getSpeed()));
        if (filters.getSat() != null)
            searchCondition = searchCondition.and(Trackings.TRACKINGS.SAT.eq(filters.getSat()));
        if (filters.getHdop() != null)
            searchCondition = searchCondition.and(Trackings.TRACKINGS.HDOP.eq(filters.getHdop()));
        if (filters.getTimeStart() != null)
            searchCondition = searchCondition.and(Trackings.TRACKINGS.TIME.ge(filters.getTimeStart()));
        if (filters.getTimeEnd() != null)
            searchCondition = searchCondition.and(Trackings.TRACKINGS.TIME.le(filters.getTimeEnd()));

        int count = DSL.using(configuration())
                .fetchCount(DSL.selectFrom(Trackings.TRACKINGS).where(searchCondition));

        pagination.setTotal(count);

        return DSL.using(configuration())
                .selectFrom(Trackings.TRACKINGS)
                .where(searchCondition)
                .limit(pagination.getLimit())
                .offset((pagination.getPage()-1)*pagination.getLimit())
                .fetch()
                .map(mapper());
    }
}
