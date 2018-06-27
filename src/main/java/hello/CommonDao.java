package hello;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import org.hibernate.Query;
import org.hibernate.Session;

public class CommonDao<T> {

	public static class DaoException extends Exception {
		private static final long serialVersionUID = 4678587740739335546L;

		public DaoException() {
			super();
		}

		public DaoException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
			super(arg0, arg1, arg2, arg3);
		}

		public DaoException(String arg0, Throwable arg1) {
			super(arg0, arg1);
		}

		public DaoException(String arg0) {
			super(arg0);
		}

		public DaoException(Throwable arg0) {
			super(arg0);
		}
	}

	public Session openSession() {
		TimeZone.setDefault(TimeZone.getTimeZone("Etc/UTC"));
		return HibernateUtil.getHibernateSession();
	}

	// Fetches.
	protected T get(Session session, String hql) throws DaoException {
		try {
			Query q = session.createQuery(hql);
			List<T> l = q.list();
			if ((l != null) && (l.size() > 0)) {
				return l.get(0);
			}
			return null;
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

	protected T get(Session session, String hql, Map<String, Object> params) throws DaoException {
		try {
			Query q = session.createQuery(hql);
			if ((params != null) && !params.isEmpty()) {
				for (String key : params.keySet()) {
					q.setParameter(key, params.get(key));
				}
			}
			List<T> l = q.list();
			if ((l != null) && (l.size() > 0)) {
				return l.get(0);
			}
			return null;
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

	protected List<T> find(Session session, String hql) throws DaoException {
		try {
			Query q = session.createQuery(hql);
			return q.list();
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

	protected List<T> find(Session session, String hql, Map<String, Object> params) throws DaoException {
		try {
			Query q = session.createQuery(hql);
			if ((params != null) && !params.isEmpty()) {
				for (String key : params.keySet()) {
					q.setParameter(key, params.get(key));
				}
			}
			return q.list();
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

	protected List<T> find(Session session, String hql, int page, int rows) throws DaoException {
		try {
			Query q = session.createQuery(hql);
			return q.setFirstResult((page - 1) * rows).setMaxResults(rows).list();
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

	protected List<T> find(Session session, String hql, Map<String, Object> params, int page, int rows) throws DaoException {
		try {
			Query q = session.createQuery(hql);
			if ((params != null) && !params.isEmpty()) {
				for (String key : params.keySet()) {
					q.setParameter(key, params.get(key));
				}
			}
			return q.setFirstResult((page - 1) * rows).setMaxResults(rows).list();
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

	// Insertions.
	protected Serializable save(Session session, T o) throws DaoException {
		try {
			if (o != null) {
				return session.save(o);
			}
			return null;
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

	// Updates.
	protected void update(Session session, T o) throws DaoException {
		try {
			if (o != null) {
				session.update(o);
			}
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

	// Deletions.
	protected void delete(Session session, T o) throws DaoException {
		try {
			if (o != null) {
				session.delete(o);
			}
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

}
