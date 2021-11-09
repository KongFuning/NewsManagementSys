package com.yy.dao;

import com.yy.pojo.Newspaper;

import java.util.List;

public interface OrdersMapper {

    //在订单里根据user_id获取报刊
    List<Newspaper> getNewspaperByUserId(int user_id);

    //查询所有订阅报刊的用户
    List<Integer> getAllUsers();

}
