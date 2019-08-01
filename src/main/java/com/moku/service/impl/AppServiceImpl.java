package com.moku.service.impl;


import com.moku.dao.IAppDao;
import com.moku.model.App;
import com.moku.service.IAppService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("userService1")
public class AppServiceImpl implements IAppService {

    @Resource
    private IAppDao userDao;

    public List<App> selectUser1(String userId) {

        return this.userDao.selectUser1(userId);
    }

}