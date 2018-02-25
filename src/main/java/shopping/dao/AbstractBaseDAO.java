package shopping.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;

public abstract class AbstractBaseDAO implements IAbstractBaseDAO {

	@Autowired
	protected SessionFactory sessionFactory;

	protected Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public Serializable getByPrimaryKey(Class<?> clazz, Integer primaryKeyId) {
		return (Serializable) getCurrentSession().get(clazz, primaryKeyId);
	}

}
