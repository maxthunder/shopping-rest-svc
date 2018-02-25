package shopping.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class BaseDAO extends AbstractBaseDAO implements IBaseDAO {

	@Override
	public Session getCurrentSession() {
		return super.getCurrentSession();
	}

	@Override
	public Object[] getObjectArrayFromNativeQuery(String queryString) {
		return getObjectArrayFromNativeQuery(queryString, null);
	}

	@Override
	public Object[] getObjectArrayFromNativeQuery(String queryString, Map<String, Object> queryParameters) {
		Query query = getCurrentSession().createSQLQuery(queryString);

		addQueryParameters(query, queryParameters);
		return (Object[]) query.uniqueResult();
	}

	@Override
	public List getListFromNativeQuery(String queryString) {
		return getListFromNativeQuery(queryString, null);
	}

	@Override
	public List<?> getListFromNativeQuery(String queryString, Map<String, Object> queryParameters) {
		Query query = getCurrentSession().createSQLQuery(queryString);

		addQueryParameters(query, queryParameters);

		return query.list();
	}

	private Query addQueryParameters(Query query, Map<String, Object> queryParameters) {
		if (queryParameters != null && !queryParameters.isEmpty()) {
			queryParameters.forEach((key, value) -> {
				if (value instanceof List) {
					query.setParameterList(key, (List) value);
				} else {
					query.setParameter(key, value);
				}
			});
		}
		return query;
	}
}
