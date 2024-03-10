package dev.ruzlab.bulk.data.user.assembler;

import dev.ruzlab.bulk.data.user.dao.UserDTO;
import dev.ruzlab.bulk.data.user.entity.UserEntity;

public class UserAssembler {

    public static UserEntity userDtoToEntity(UserDTO user) {
        UserEntity userEntity = new UserEntity();
        userEntity.setName(user.getName());
        userEntity.setAge(user.getAge());
        userEntity.setGender(user.getGender());
        userEntity.setEmail(user.getEmail());
        userEntity.setIsActive(user.getIsActive());
        userEntity.setCompany(user.getCompany());
        userEntity.setPhone(user.getPhone());
        userEntity.setAddress(user.getAddress());
        return userEntity;
    }

    public static UserDTO userEntityToDto(UserEntity userEntity) {
        UserDTO user = new UserDTO();
        user.setName(userEntity.getName());
        user.setAge(userEntity.getAge());
        user.setGender(userEntity.getGender());
        user.setEmail(userEntity.getEmail());
        user.setIsActive(userEntity.getIsActive());
        user.setCompany(userEntity.getCompany());
        user.setPhone(userEntity.getPhone());
        user.setAddress(userEntity.getAddress());
        return user;
    }
}
