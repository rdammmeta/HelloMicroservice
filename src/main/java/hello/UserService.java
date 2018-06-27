package hello;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hello.CommonDao.DaoException;

@Service
@Transactional
public class UserService {

	@Autowired
	private UserDao userDao;

	public User getUserByUuid(String uuid) throws DaoException {
		return userDao.getUserByUuid(uuid);
	}

	public List<User> getAllUsers() throws DaoException {
		return userDao.getAllUsers();
	}

	public String insertUser(User user) throws DaoException {
		return userDao.insertUser(user);
	}

	public void updateUser(User user) throws DaoException {
		userDao.updateUser(user);
	}

	public void deleteUser(String uuid) throws DaoException {
		userDao.deleteUser(uuid);
	}

}
