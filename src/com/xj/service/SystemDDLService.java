package com.xj.service;

import com.xj.domain.SystemDDL;

import java.util.List;

/**
 * Created by Asus on 2017/6/2.
 */
public interface SystemDDLService {
    List<Object> findKeyword();

    List<SystemDDL> setSystemDDLList(String keywordname, String[] itemname);

    void save(List<SystemDDL> systemDDLS);

    List<SystemDDL> findElecSystemDDLByKeyWord(String keyword);

    void updateElecSystemDDL(String keyword, String[] itemname);

    String findDdlname(String keyword, int ddlcode);
}
