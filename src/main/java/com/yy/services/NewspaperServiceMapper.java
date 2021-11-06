package com.yy.services;

import com.yy.pojo.Newspaper;

import java.util.List;

public interface NewspaperServiceMapper {
    //查询所有报刊
    List<Newspaper> getAllNewspaper();

    //添加报刊
    Integer addNews(Newspaper newspaper);
}
