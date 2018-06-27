package hello;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao extends CommonDao<User> {

	public User getUserByUuid(Session session, String uuid) throws DaoException {
		String hql = "FROM User u WHERE u.uuid=:uuid";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("uuid", uuid);
		return this.get(session, hql, map);
	}

	public List<User> getAllUsers(Session session) throws DaoException {
		String hql = "FROM User u";
		return this.find(session, hql);
	}

	public String insertUser(Session session, User user) throws DaoException {
		return (String) super.save(session, user);
	}

	public void updateUser(Session session, User user) throws DaoException {
		super.update(session, user);
	}

	public void deleteUser(Session session, String uuid) throws DaoException {
		User user = this.getUserByUuid(session, uuid);
		if (user != null) {
			super.delete(session, user);
		}
	}

}
