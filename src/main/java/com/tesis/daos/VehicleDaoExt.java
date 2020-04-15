package com.tesis.daos;

import javax.inject.Inject;

import com.tesis.enums.Status;
import com.tesis.jooq.tables.*;
import com.tesis.jooq.tables.records.VehiclesRecord;
import com.tesis.models.Pagination;
import com.tesis.utils.filters.VehicleFilters;
import org.jooq.Condition;
import org.jooq.Configuration;
import com.tesis.jooq.tables.daos.VehiclesDao;

import java.time.Clock;
import java.time.LocalDateTime;
import java.util.List;

import org.jooq.Result;
import org.jooq.impl.DSL;

import static com.tesis.config.Constants.DEFAULT_SPEED_ALERT;

public class VehicleDaoExt extends VehiclesDao {

    @Inject
    public VehicleDaoExt(Configuration configuration) {
        super(configuration);
    }

    public com.tesis.jooq.tables.pojos.Vehicles createVehicle(com.tesis.jooq.tables.pojos.Vehicles vehicle) {
        DSL.using(configuration()).transaction(t -> {
            Result<VehiclesRecord> result = t.dsl().insertInto(Vehicles.VEHICLES,
                    Vehicles.VEHICLES.STATUS,
                    Vehicles.VEHICLES.DELETED_AT,
                    Vehicles.VEHICLES.LAST_UPDATED,
                    Vehicles.VEHICLES.USER_ID,
                    Vehicles.VEHICLES.DEVICE_ID,
                    Vehicles.VEHICLES.PLATE,
                    Vehicles.VEHICLES.BRAND,
                    Vehicles.VEHICLES.BRAND_LINE)
                    .values(vehicle.getStatus(),
                            null,
                            null,
                            vehicle.getUserId(),
                            null,
                            vehicle.getPlate(),
                            vehicle.getBrand(),
                            vehicle.getBrandLine())
                    .returning(Vehicles.VEHICLES.ID)
                    .fetch();

            vehicle.setId(result.get(0).getId());
        });

        return vehicle;
    }

    public List<com.tesis.jooq.tables.pojos.Vehicles> findAllActives() {
        return DSL
                .using(configuration())
                .selectFrom(Vehicles.VEHICLES)
                .where(Vehicles.VEHICLES.DELETED_AT.isNull())
                .fetch()
                .map(mapper());
    }

    public void deleteVehicle(Long vehicleID) {
        DSL.using(configuration()).transaction(t -> {

            Long deviceID = t.dsl().selectFrom(Vehicles.VEHICLES)
                    .where(Vehicles.VEHICLES.ID.eq(vehicleID)).fetchAny(Vehicles.VEHICLES.DEVICE_ID);

            Long speedAlertID = t.dsl().selectFrom(SpeedAlerts.SPEED_ALERTS)
                    .where(SpeedAlerts.SPEED_ALERTS.DEVICE_ID.eq(deviceID))
                    .fetchAny(SpeedAlerts.SPEED_ALERTS.ID);

            t.dsl().delete(SpeedAlertsHistory.SPEED_ALERTS_HISTORY)
                    .where(SpeedAlertsHistory.SPEED_ALERTS_HISTORY.ALERT_ID.eq(speedAlertID))
                    .execute();

            Long movementAlertID = t.dsl().selectFrom(MovementAlerts.MOVEMENT_ALERTS)
                    .where(MovementAlerts.MOVEMENT_ALERTS.DEVICE_ID.eq(deviceID))
                    .fetchAny(MovementAlerts.MOVEMENT_ALERTS.ID);

            t.dsl().delete(MovementAlertsHistory.MOVEMENT_ALERTS_HISTORY)
                    .where(MovementAlertsHistory.MOVEMENT_ALERTS_HISTORY.ALERT_ID.eq(movementAlertID))
                    .execute();

            t.dsl().delete(Trackings.TRACKINGS).where(Trackings.TRACKINGS.DEVICE_ID.eq(deviceID)).execute();
            t.dsl().update(Devices.DEVICES)
                    .set(Devices.DEVICES.STATUS, Status.DELETED.toString())
                    .set(Devices.DEVICES.DELETED_AT, LocalDateTime.now(Clock.systemUTC()))
                    .where(Devices.DEVICES.ID.eq(deviceID))
                    .execute();

            t.dsl().update(SpeedAlerts.SPEED_ALERTS)
                    .set(SpeedAlerts.SPEED_ALERTS.ACTIVE, false)
                    .where(SpeedAlerts.SPEED_ALERTS.DEVICE_ID.eq(deviceID))
                    .execute();

            t.dsl().update(MovementAlerts.MOVEMENT_ALERTS)
                    .set(MovementAlerts.MOVEMENT_ALERTS.ACTIVE, false)
                    .where(MovementAlerts.MOVEMENT_ALERTS.DEVICE_ID.eq(deviceID))
                    .execute();

            t.dsl().update(Vehicles.VEHICLES)
                    .set(Vehicles.VEHICLES.DELETED_AT, LocalDateTime.now(Clock.systemUTC()))
                    .set(Vehicles.VEHICLES.DEVICE_ID, (Long) null)
                    .set(Vehicles.VEHICLES.STATUS, Status.DELETED.toString())
                    .where(Vehicles.VEHICLES.ID.eq(vehicleID))
                    .execute();
        });
    }

    public Long fetchByIDForUserID(Long vehicleID) {
        return DSL.using(configuration()).select(Vehicles.VEHICLES.USER_ID)
                .from(Vehicles.VEHICLES)
                .where(Vehicles.VEHICLES.ID.eq(vehicleID)).fetchOne(Vehicles.VEHICLES.USER_ID);
    }

    public List<com.tesis.jooq.tables.pojos.Vehicles> findByFilters(VehicleFilters filters, Pagination pagination) {
        Condition searchCondition = DSL.trueCondition();

        if (filters.getStatus() != null)
            searchCondition = searchCondition.and(Vehicles.VEHICLES.STATUS.eq(filters.getStatus()));
        if (filters.getUserId() != null)
            searchCondition = searchCondition.and(Vehicles.VEHICLES.USER_ID.eq(filters.getUserId()));
        if (filters.getDeviceId() != null)
            searchCondition = searchCondition.and(Vehicles.VEHICLES.DEVICE_ID.eq(filters.getDeviceId()));
        if (filters.getPlate() != null)
            searchCondition = searchCondition.and(Vehicles.VEHICLES.PLATE.eq(filters.getPlate()));
        if (filters.getBrand() != null)
            searchCondition = searchCondition.and(Vehicles.VEHICLES.BRAND.eq(filters.getBrand()));
        if (filters.getBrandLine() != null)
            searchCondition = searchCondition.and(Vehicles.VEHICLES.BRAND_LINE.eq(filters.getBrandLine()));
        searchCondition = searchCondition.and(Vehicles.VEHICLES.DELETED_AT.isNull());

        int count = DSL.using(configuration())
                .fetchCount(DSL.selectFrom(Vehicles.VEHICLES).where(searchCondition));
        pagination.setTotal(count);

        return DSL.using(configuration())
                .selectFrom(Vehicles.VEHICLES)
                .where(searchCondition)
                .limit(pagination.getLimit())
                .offset((pagination.getPage() - 1) * pagination.getLimit())
                .fetch()
                .map(mapper());
    }

}
