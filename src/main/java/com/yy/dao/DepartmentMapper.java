package com.yy.dao;

import com.yy.pojo.Department;

import java.util.List;

public interface DepartmentMapper {

    //根据id获取部门名
    String getDepartName(int id);

    //获取所有部门
    List<Department> getAllDepartment();
}
