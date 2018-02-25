package shopping.dao;

import org.hibernate.Session;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface IBaseDAO {

	Session getCurrentSession();
	Serializable save(Object obj);
	void saveOrUpdate(Object obj);
	Object[] getObjectArrayFromNativeQuery(String queryString);
	Object[] getObjectArrayFromNativeQuery(String queryString, Map<String, Object> queryParameters);
	List getListFromNativeQuery(String queryString);
	List getListFromNativeQuery(String queryString, Map<String, Object> queryParameters);
}
