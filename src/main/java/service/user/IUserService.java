package service.user;

import model.User;
import service.IGeneralService;

import java.util.List;

public interface IUserService extends IGeneralService<User> {
    List<User> findByName(String name);

    List<User> findByCountry(String country);
}
