package it.onyx.test.service;

import java.util.List;

import it.onyx.test.dao.UserDao;

public interface UserService {
	
	public UserDao create(UserDao shop);
    public UserDao delete(int id) throws Exception;
    public List findAll();
    public UserDao update(UserDao shop) throws Exception;
    public UserDao findById(int id);

}
