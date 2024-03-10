package dev.ruzlab.bulk.data.user.util;

import dev.ruzlab.bulk.data.user.dao.UserDTO;

import java.util.ArrayList;
import java.util.List;

public class UserUtil {

    public static List<List<UserDTO>> get(List<UserDTO> userList, Integer subArraySize) {
        List<List<UserDTO>> userSubList = new ArrayList<>();
        for (int i = 0; i < userList.size(); i += subArraySize) {
            userSubList.add(userList.subList(i, Math.min(i + subArraySize, userList.size())));
        }
        return userSubList;
    }
}
