package com.wasu.springmvc.thymeleaf.dao;

import com.wasu.springmvc.thymeleaf.entity.User;
import org.springframework.stereotype.Repository;

import java.util.*;

/**
 * @author ZHANGLEI
 * @date 2021/11/27 12:30
 */
@Repository
public class UserDao {
    private static Map<Long, User> users;

    static {
        users = new HashMap<Long, User>();
        users.put(1001l, new User(1001l, "Test1001", 1,"test1001@163.com"));
        users.put(1002l, new User(1002l, "Test1002", 0,"test1002@163.com"));
        users.put(1003l, new User(1003l, "Test1003", 1,"test1003@163.com"));
    }

    private static Long userId = 1004l;

    public Collection<User> selectAllUsers() {
        return users.values();
    }

    public int insertUser(User user) {
        if(user.getId() == null) {
            user.setId(userId++);
            users.put(user.getId(), user);
            return 1;
        } else
            return 0;
    }

    public int updateUser(User user) {
        if(user.getId() != null) {
            users.put(user.getId(), user);
            return 1;
        } else
            return 0;
    }

    public User selectUser(Long id) {
        return users.get(id);
    }

    public int deleteUser(Long id) {
        users.remove(id);
        return 1;
    }
}
