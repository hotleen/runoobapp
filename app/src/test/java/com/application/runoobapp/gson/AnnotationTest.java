package com.application.runoobapp.gson;

import com.application.runoobapp.bean.Job;
import com.application.runoobapp.bean.User;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.junit.Test;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class AnnotationTest {

    @Test
    public void testObject() {
        User user = new User("tom", "123", 18, false);
        Gson gson = new Gson();
        String jsonStr = gson.toJson(user);
        System.out.println("序列化: " + jsonStr);

        User userCopy = gson.fromJson(jsonStr, User.class);
        System.out.println("反序列化：" + userCopy);
    }

    /**
     * 测试嵌套
     */
    @Test
    public void testNestedObject() {
        User user = new User("tom", "123", 18, false);
        Job job = new Job("farmer", "3000");
        user.setJob(job);
        Gson gson = new Gson();
        String jsonStr = gson.toJson(user);
        System.out.println("序列化: " + jsonStr);

        User userCopy = gson.fromJson(jsonStr, User.class);
        System.out.println("反序列化：" + userCopy);
    }

    @Test
    public void testArrayToJson() {
        User[] users = new User[2];
        users[0] = new User("tom", "123", 18, false);
        users[1] = new User("jack", "456", 18, false);
        Gson gson = new Gson();
        String usersStr = gson.toJson(users);
        System.out.println("users array 序列化：" + usersStr);

        User[] usersCopy = gson.fromJson(usersStr, User[].class);
        System.out.println("usersCopy length: " + usersCopy.length);

    }

    @Test
    public void testListToJson() {
        List<User> userList = new ArrayList<>();
        User user = new User("tom", "123", 18, false);
        User user1 = new User("jack", "456", 18, false);
        userList.add(user);
        userList.add(user1);
        Gson gson = new Gson();
        String usersStr = gson.toJson(userList);
        System.out.println("user list 序列化：" + usersStr);

        Type type = new TypeToken<List<User>>() {
        }.getType();
        List<User> userListCopy = gson.fromJson(usersStr, type);
        System.out.println("userListCopy length: " + userListCopy.size());

    }

    @Test
    public void testMapToJson() {
        Map<String, User> userMap = new HashMap<>();
        userMap.put("user1", new User("tom", "123", 18, false));
        userMap.put("user2", new User("jack", "123", 18, false));
        userMap.put("user3", null);
        userMap.put(null, null);

        Gson gson = new Gson();
        String userMapStr = gson.toJson(userMap);
        System.out.println("user map 序列化: " + userMapStr);

        Type type = new TypeToken<Map<String, User>>() {
        }.getType();
        Map<String, User> userMapCopy = gson.fromJson(userMapStr, type);
        System.out.println("userMap size: " + userMapCopy.size());

    }

    @Test
    public void testSetToJson() {
        Set<User> userSet = new HashSet<>();
        userSet.add(new User("tom", "123", 18, false));
        userSet.add(new User("jack", "123", 18, false));
        userSet.add(null);

        Gson gson = new Gson();
        String userSetStr = gson.toJson(userSet);
        System.out.println("user set 序列化: " + userSetStr);
//      这里用set和list都可以
        Type type = new TypeToken<Set<User>>() {
        }.getType();
        Set<User> userSetCopy = gson.fromJson(userSetStr, type);
        System.out.println("userSet size: " + userSetCopy.size());

    }
}
