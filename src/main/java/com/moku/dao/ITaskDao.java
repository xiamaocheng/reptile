package com.moku.dao;




import com.moku.model.Task;

public interface ITaskDao {

    Task selectTask(String  id);


}