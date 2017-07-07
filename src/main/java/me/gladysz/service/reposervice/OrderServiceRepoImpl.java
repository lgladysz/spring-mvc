package me.gladysz.service.reposervice;

import me.gladysz.model.Order;
import me.gladysz.repository.OrderRepository;
import me.gladysz.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Profile("springdatajpa")
public class OrderServiceRepoImpl implements OrderService {

    private final OrderRepository orderRepository;

    @Autowired
    public OrderServiceRepoImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }


    @Override
    public List<Order> listAll() {
        List<Order> orders = new ArrayList<>();
        orderRepository.findAll().forEach(orders::add);
        return orders;
    }

    @Override
    public Order getById(Long id) {
        return orderRepository.findOne(id);
    }

    @Override
    public Order saveOrUpdate(Order domainObject) {
        return orderRepository.save(domainObject);
    }

    @Override
    public void delete(Long id) {
        orderRepository.delete(id);
    }
}
