package com.tesis.services;

import com.tesis.jooq.tables.pojos.Devices;
import com.tesis.models.ResponseDTO;

import java.util.List;

public interface UserService {

    ResponseDTO<List<Devices>> getUsers();
}
