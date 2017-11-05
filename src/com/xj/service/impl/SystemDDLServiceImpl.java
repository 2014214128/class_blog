package com.xj.service.impl;

import com.xj.dao.SystemDDLDao;
import com.xj.dao.impl.SystemDDLDaoImpl;
import com.xj.domain.SystemDDL;
import com.xj.exceptions.DaoException;
import com.xj.service.SystemDDLService;
import com.xj.util.LoggerTool;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Asus on 2017/6/2.
 */
public class SystemDDLServiceImpl implements SystemDDLService {
    private SystemDDLDao systemDDLDao = new SystemDDLDaoImpl();
    private LoggerTool logger = LoggerTool.newInstance(SystemDDLServiceImpl.class);

    @Override
    public List<Object> findKeyword() {
        return systemDDLDao.findKeyword();
    }

    public List<SystemDDL> setSystemDDLList(String keywordname, String[] itemname) {
        List<SystemDDL> systemDDLS = new ArrayList<>();
        SystemDDL systemDDL = null;
        for (int i = 0; i < itemname.length; i++) {
            systemDDL = new SystemDDL();
            systemDDL.setKeyword(keywordname);
            systemDDL.setDdlcode(i + 1);
            systemDDL.setDdlname(itemname[i]);
            systemDDLS.add(systemDDL);
        }
        return systemDDLS;
    }

    @Override
    public void save(List<SystemDDL> systemDDLS) {
        try {
            for (SystemDDL s : systemDDLS) {
                systemDDLDao.addSystemDDL(s);
            }
        } catch (DaoException e) {
            logger.warn(e.getMessage());
        }

    }

    @Override
    public List<SystemDDL> findElecSystemDDLByKeyWord(String keyword) {
        try {
            return systemDDLDao.findSystemDDLListByCondition(keyword);
        } catch (DaoException e) {
            logger.warn(e.getMessage());
        }
        return null;
    }

    @Override
    public void updateElecSystemDDL(String keyword, String[] itemname) {
        List<SystemDDL> systemDDLS = null;
        try {
            systemDDLS = systemDDLDao.findSystemDDLListByCondition(keyword);
        } catch (DaoException e) {
            logger.warn(e.getMessage());
        }
        systemDDLDao.deleteSystemDDLList(systemDDLS);
        List<SystemDDL> list = setSystemDDLList(keyword, itemname);
        save(list);
    }


    @Override
    public String findDdlname(String keyword, int ddlcode) {
        try {
            return systemDDLDao.findDdlname(keyword, ddlcode);
        } catch (DaoException e) {
            logger.warn(e.getMessage());
        }
        return null;
    }

    /*@Test
    public void testUpdateElecSystemDDL() {
        SystemDDLServiceImpl systemDDLServiceImpl = new SystemDDLServiceImpl();
        systemDDLServiceImpl.updateElecSystemDDL("性别",null);
    }*/
}
