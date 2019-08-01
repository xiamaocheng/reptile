package com.moku.service.impl;


import com.moku.dao.ITaskDao;

import com.moku.model.Task;
import com.moku.service.ITaskService;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("userService")
public class UserServiceImpl implements ITaskService {

    @Resource
    private ITaskDao userDao;

    public Task selectUser(String userId) {
        return this.userDao.selectTask(userId);
    }

}