package com.yy.dao;

import com.yy.pojo.Newspaper;
import com.yy.pojo.User;

import java.util.List;
import java.util.Map;

public interface OrdersMapper {

    //在订单里根据user_id获取报刊
    List<Newspaper> getNewspaperByUserId(int user_id);

    //查询所有订阅报刊的用户
    List<Integer> getAllUsers();

    //查询订阅的所有报刊id
    List<Integer> getAllNewsId();

    //查询订阅某本报刊的所有用户
    List<User> getAllUsersOrderOneNew(int id);

    //根据报刊id获取报刊
    Newspaper getNewspaperByNewsId(int id);

    //查询某个用户是否订阅了某本书
    Integer checkOrder(Map map);

    //某用户根据报刊id订阅报刊
    Integer orderNew(Map map);

    //删除某个用户订阅的某个报刊
    Integer cancelOrder(Map map);
}
