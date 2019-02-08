package it.onyx.test.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Component;

import it.onyx.test.dao.UserDao;
import it.onyx.test.repository.UserRepository;
import it.onyx.test.service.UserService;

@EnableJpaRepositories("it.onyx.test.repository") 
@Component
public class UserServiceImpl implements UserService{
	
	ApplicationContext context = new ClassPathXmlApplicationContext("ApplicationContext.xml");
	
	@Autowired
	UserRepository userRepo;

	@Override
	public UserDao create(UserDao user) {
		// TODO Auto-generated method stub
		return userRepo.save(user);
	}

	@Override
	public UserDao delete(int id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserDao update(UserDao shop) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserDao findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
