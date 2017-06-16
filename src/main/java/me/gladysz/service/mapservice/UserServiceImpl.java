package me.gladysz.service.mapservice;

import me.gladysz.model.User;
import me.gladysz.service.UserService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("map")
public class UserServiceImpl extends AbstractMapService implements UserService {

    @Override
    public User getById(Long id) {
        return (User) super.getById(id);
    }

    @Override
    public User saveOrUpdate(User domainObject) {
        return (User) super.saveOrUpdate(domainObject);
    }
}
