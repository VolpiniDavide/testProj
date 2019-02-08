package it.onyx.test.serviceimpl;

import java.util.List;
import java.util.Optional;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import it.onyx.test.dao.UserDao;
import it.onyx.test.repository.UserRepository;
import it.onyx.test.service.UserService;

@Component("UserServiceImpl")
@Service
public class UserServiceImpl implements UserService {

	
	private UserRepository userRepo;
	
	@Override
	public UserDao create(UserDao user) {
		UserDao userCreated = user;
		return userRepo.save(userCreated);
	}

	@Override
	public UserDao delete(int id) throws Exception {
		Optional<UserDao> optionalUserDao = userRepo.findById((long) id);
		UserDao deletedUserDao = optionalUserDao.get();
		 
        if (deletedUserDao == null)
            System.out.println("non posso cancellare, non c'è!");
 
        userRepo.delete(deletedUserDao);
        return deletedUserDao;
	}

	@Override
	public List findAll() {
		return userRepo.findAll();
	}

	@Override
	public UserDao update(UserDao user) throws Exception {
		// TODO STUDIARE METODO UPDATE !!!
		return null;
	}

	@Override
	public UserDao findById(int id) {
		Optional<UserDao> optionalUserDao = userRepo.findById((long) id);
		UserDao foundUserDao = optionalUserDao.get();
		return foundUserDao;
	}

}
