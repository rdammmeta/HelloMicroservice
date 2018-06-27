package hello;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hello.CommonDao.DaoException;

@Service
public class UserService {

	@Autowired
	private UserDao userDao;

	public User getUserByUuid(String uuid) throws DaoException {
		Session session = userDao.openSession();
		User user = null;
		try {
			user = userDao.getUserByUuid(session, uuid);
		} catch (DaoException e) {
			session.close();
			throw e;
		}
		session.close();
		return user;
	}

	public List<User> getAllUsers() throws DaoException {
		Session session = userDao.openSession();
		List<User> users = null;
		try {
			users = userDao.getAllUsers(session);
		} catch (DaoException e) {
			session.close();
			throw e;
		}
		session.close();
		return users;
	}

	public String insertUser(User user) throws DaoException {
		Session session = userDao.openSession();
		String uuid = null;
		Transaction transaction = session.beginTransaction();
		try {
			uuid = userDao.insertUser(session, user);
		} catch (DaoException e) {
			transaction.rollback();
			session.close();
			throw e;
		}
		transaction.commit();
		session.close();
		return uuid;
	}

	public void updateUser(User user) throws DaoException {
		Session session = userDao.openSession();
		Transaction transaction = session.beginTransaction();
		try {
			userDao.updateUser(session, user);
		} catch (DaoException e) {
			transaction.rollback();
			session.close();
			throw e;
		}
		transaction.commit();
		session.close();
	}

	public void deleteUser(String uuid) throws DaoException {
		Session session = userDao.openSession();
		Transaction transaction = session.beginTransaction();
		try {
			userDao.deleteUser(session, uuid);
		} catch (DaoException e) {
			transaction.rollback();
			session.close();
			throw e;
		}
		transaction.commit();
		session.close();
	}

}
