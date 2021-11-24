package com.yy.dao;

import com.yy.pojo.Newspaper;

import java.util.List;
import java.util.Map;

public interface NewspaperMapper {

    //查询所有报刊
    List<Newspaper> getAllNewspaper();

    //添加报刊
    Integer addNews(Newspaper newspaper);

    //更新报刊
    Integer updateNews(Map map);

    //根据报刊id删除报刊
    Integer deleteNewsById(int id);
}
