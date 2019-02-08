package it.onyx.test.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import it.onyx.test.dao.UserDao;

public interface UserRepository extends JpaRepository<UserDao, Long>{

	@Override
	default List<UserDao> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
