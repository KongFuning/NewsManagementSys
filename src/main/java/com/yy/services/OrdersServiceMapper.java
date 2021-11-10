package com.yy.services;

import com.yy.pojo.Newspaper;
import com.yy.pojo.User;

import java.util.List;

public interface OrdersServiceMapper {

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

}
