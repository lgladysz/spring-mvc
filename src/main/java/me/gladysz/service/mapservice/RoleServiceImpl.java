package me.gladysz.service.mapservice;

import me.gladysz.model.security.Role;
import me.gladysz.service.RoleService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("map")
public class RoleServiceImpl extends AbstractMapService implements RoleService {
    @Override
    public Role getById(Long id) {
        return (Role) super.getById(id);
    }

    @Override
    public Role saveOrUpdate(Role domainObject) {
        return (Role) super.saveOrUpdate(domainObject);
    }
}
