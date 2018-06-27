package hello;

import java.util.Date;
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
		User user = null;
		try {
			user = userDao.getUserByUuid(uuid);
		} catch (DaoException e) {
			throw e;
		}
		return user;
	}

	public List<User> getAllUsers() throws DaoException {
		List<User> users = null;
		try {
			users = userDao.getAllUsers();
		} catch (DaoException e) {
			throw e;
		}
		return users;
	}

	public String insertUser(User user) throws DaoException {
		user.setUuid(Long.toString(new Date().getTime()));
		String uuid = null;
		try {
			uuid = userDao.insertUser(user);
		} catch (DaoException e) {
			throw e;
		}
		return uuid;
	}

	public void updateUser(User user) throws DaoException {
		try {
			userDao.updateUser(user);
		} catch (DaoException e) {
			throw e;
		}
	}

	public void deleteUser(String uuid) throws DaoException {
		try {
			userDao.deleteUser(uuid);
		} catch (DaoException e) {
			throw e;
		}
	}

}
