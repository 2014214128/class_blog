package com.xj.dao;

import com.xj.domain.SystemDDL;
import com.xj.exceptions.DaoException;

import java.util.List;

/**
 * Created by Asus on 2017/6/2.
 */
public interface SystemDDLDao extends BaseDao<SystemDDL>{

    void  addSystemDDL(SystemDDL systemDDL) throws DaoException;

    List<Object> findKeyword();

    List<SystemDDL> findSystemDDLListByCondition(String keyword) throws DaoException;

    void deleteSystemDDLList(List<SystemDDL> systemDDLS);

    String findDdlname(String keyword, int ddlcode) throws DaoException;

}
