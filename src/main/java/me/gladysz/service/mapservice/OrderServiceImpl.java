package me.gladysz.service.mapservice;

import me.gladysz.model.Order;
import me.gladysz.service.OrderService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("map")
public class OrderServiceImpl extends AbstractMapService implements OrderService {

    @Override
    public Order getById(Long id) {
        return (Order) super.getById(id);
    }

    @Override
    public Order saveOrUpdate(Order domainObject) {
        return (Order) super.saveOrUpdate(domainObject);
    }
}
