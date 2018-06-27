package hello;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

@Repository
public class UserDao extends CommonDao<User> {

	public User getUserByUuid(String uuid) throws DaoException {
		String hql = "FROM User u WHERE u.uuid=:uuid";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("uuid", uuid);
		return this.get(hql, map);
	}

	public List<User> getAllUsers() throws DaoException {
		String hql = "FROM User u";
		return this.find(hql);
	}

	public String insertUser(User user) throws DaoException {
		return (String) super.save(user);
	}

	public void updateUser(User user) throws DaoException {
		super.update(user);
	}

	public void deleteUser(String uuid) throws DaoException {
		User user = this.getUserByUuid(uuid);
		if (user != null) {
			super.delete(user);
		}
	}

}
