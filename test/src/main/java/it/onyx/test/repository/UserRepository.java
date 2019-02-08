package it.onyx.test.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.onyx.test.dao.UserDao;


@Repository
public interface UserRepository extends JpaRepository<UserDao, Long>{

}
