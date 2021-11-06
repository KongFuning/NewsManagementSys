package com.yy.dao;

import com.yy.pojo.Newspaper;

import java.util.List;

public interface NewspaperMapper {

    //查询所有报刊
    List<Newspaper> getAllNewspaper();

    //添加报刊
    Integer addNews(Newspaper newspaper);
}
