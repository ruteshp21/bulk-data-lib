package dev.ruzlab.bulk.data.user.dao;

import lombok.Data;

@Data
public class UserDTO {

    private Boolean isActive;
    private Integer age;
    private String name;
    private String gender;
    private String company;
    private String email;
    private String phone;
    private String address;

}
