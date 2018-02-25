package shopping.dao;

import java.io.Serializable;

public interface IAbstractBaseDAO {

	Serializable getByPrimaryKey(Class<?> clazz, Integer primaryKeyId);

}
