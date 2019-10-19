package shopping.dao;

import shopping.model.PhoneRef;

public interface IPhoneRefDAO {
    PhoneRef getPhoneRefByName(String name);
    PhoneRef savePhoneRef(String name);

    PhoneRef getPhoneItemMapByName(String name);
    PhoneRef savePhoneItemMap(String name);
}
