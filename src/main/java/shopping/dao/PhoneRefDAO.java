package shopping.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import shopping.model.PhoneRef;

import java.util.HashMap;
import java.util.Map;


@Repository
@Transactional
public class PhoneRefDAO implements IPhoneRefDAO {

    private final IBaseDAO baseDAO;

    private final String GET_PHONE_REF_QUERY
            =   "SELECT phone_ref_id, name, price, description, phone_cart_order_id " +
                "FROM shopping.phone_ref pr\n";
    private final String GET_PHONE_ITEM_MAP_QUERY
            =   "SELECT phone_item_map_id, phone_cart_order_id, phone_ref_id " +
                "FROM shopping.phone_item_map_ref phone_map\n";

    @Autowired
    public PhoneRefDAO(IBaseDAO baseDAO) {
        this.baseDAO = baseDAO;
    }

    @Override
    public PhoneRef getPhoneRefByName(String name) {
        final String query = GET_PHONE_REF_QUERY + " WHERE pr.name = :name";
        Map<String, Object> queryParameters = new HashMap<>();
        queryParameters.put("name", name);
        PhoneRef phoneRef;
        Object[] obj = baseDAO.getObjectArrayFromNativeQuery(query, queryParameters);
        if (obj == null)
            phoneRef = null;
        else
            phoneRef = mapObjectToPhoneRef(obj);
        return phoneRef;
    }

    @Override
    public PhoneRef savePhoneRef(String name) {
        return (PhoneRef) baseDAO.save(new PhoneRef(name));
    }

    @Override
    public PhoneRef getPhoneItemMapByName(String name) {
        final String query = GET_PHONE_ITEM_MAP_QUERY
                + " ";
        // TODO: 2019-10-11 FIX ME!!! ^ ^ ^
        Map<String, Object> queryParameters = new HashMap<>();
        queryParameters.put("name", name);
        PhoneRef phoneRef;
        Object[] obj = baseDAO.getObjectArrayFromNativeQuery(query, queryParameters);
        if (obj == null)
            phoneRef = null;
        else
            phoneRef = mapObjectToPhoneRef(obj);
        return phoneRef;
    }

    @Override
    public PhoneRef savePhoneItemMap(String name) {
        return null;
    }

    private PhoneRef mapObjectToPhoneRef(Object[] obj) {
        Integer phoneRefId = (Integer) obj[0];
        String name = (String) obj[1];
        Double price = (Double) obj[2];
        String description = (String) obj[3];
        Integer CartOrderId = (Integer) obj[4];
        return new PhoneRef(phoneRefId, name , price, description, CartOrderId);
    }

}
