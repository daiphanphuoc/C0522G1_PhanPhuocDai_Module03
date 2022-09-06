package service.impl;

import model.O;
import repository.IORepository;
import repository.impl.ORepository;
import service.IOService;

import java.util.Map;

public class OService implements IOService<O> {
    private static IORepository<O> repository = ORepository.getInstance();
    private static IOService<O> service;

    private OService() {
    }

    public synchronized static IOService<O> getInstance() {
        if (service == null) {
            service = new OService();
        }
        return service;
    }
    @Override
    public Map<Integer, O> findAll() {
        return ORepository.getInstance().findAll();
    }

    @Override
    public Map<Integer, O> find(String search) {
        return ORepository.getInstance().find(search);
    }

    @Override
    public Map<Integer, O> findNotID(int id) {
        return ORepository.getInstance().findNotID(id);
    }

    @Override
    public O findID(int id) {
        return ORepository.getInstance().findID(id);
    }

    @Override
    public boolean insert(O element) {
        /*Map<String, String> map = new HashMap<>();

if ("".equals(customer.getPhoneNumber())) {
    map.put("phoneNumber", "Số điện thoại không được để trống !!!");
} else if (!Validation.checkPhoneNumber(customer.getPhoneNumber())) {
    map.put("phoneNumber", "Số điện thoại phải có định dạng: 090xxxxxxx hoặc 091xxxxxxx hoặc (84)+90xxxxxxx hoặc (84)+91xxxxxxx.!!!");
}

if ("".equals(customer.getIdCard())) {
    map.put("idCard", "CMND không được để trống !!!");
} else if (!Validation.checkCard(customer.getIdCard())) {
    map.put("idCard", "CMND không đúng định dạng !!!");
}

if ("".equals(customer.getName())) {
    map.put("name", "Tên không được để trống !!!");
} else if (!Validation.checkName(customer.getName())) {
    map.put("name", "Tên không đúng định dạng !!!");
}

if ("".equals(customer.getEmail())) {
    map.put("email", "Email không được để trống !!!");
} else if (!Validation.checkEmail(customer.getEmail())) {
    map.put("email", "Email không đúng định dạng !!!");
}
if (map.isEmpty()) {
    iCustomerRepository.create(customer);
}
return map;*/
        return ORepository.getInstance().insert(element);
    }

    @Override
    public boolean update(O element) {
        return ORepository.getInstance().update(element);
    }

    @Override
    public boolean delete(int id) {
        return ORepository.getInstance().delete(id);
    }
}
