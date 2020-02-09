package com.tesis.models;

import java.util.List;

public class UserDTO {
    private Long id;
    private String status;
    private RolDTO rol;
    private String email;
    private String name;
    private String last_name;
    private String dni;
    private String address;
    private String phone;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public RolDTO getRol() {
        return rol;
    }

    public void setRol(RolDTO rol) {
        this.rol = rol;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}


/*
{
    "created_at": [
        2020,
        2,
        3,
        19,
        51,
        14,
        848000000
    ],
    "updated_at": [
        2020,
        2,
        3,
        19,
        51,
        14,
        848000000
    ],
    "id": 13,
    "status": "INACTIVE",
    "roles": [
        {
            "id": 2,
            "name": "ADMIN",
                "privileges": [
                {
                    "id": 1,
                    "name": "GET_CLIENT"
                },
                {
                    "id": 2,
                    "name": "CREATE_CLIENT"
                }
            ]
        }
    ],
    "email": "ddrhernandez92@gmail.com",
    "name": "Diego",
    "last_name": "Hernández",
    "dni": "36354805",
    "address": "Tomás de Irobi 165",
    "phone": "3515495416"
}
 */