package com.group11.service.order;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.group11.dto.order.OrderFilter;
import com.group11.dto.order.OrderFormForCreating;
import com.group11.dto.order.OrderFormForUpdating;
import com.group11.entity.Order;
import com.group11.repository.IOrderRepository;
import com.group11.specification.order.OrderSpecificationBuilder;

@Service
public class OrderService implements IOrderService {
	@Autowired 
	private IOrderRepository repository;
	
	@Override
	public Page<Order> getAllOrders(Pageable pageable, OrderFilter filter,  String search) {
		OrderSpecificationBuilder specification = new OrderSpecificationBuilder(filter, search);

		return repository.findAll(specification.build(), pageable);
	}

	@Override
	public boolean isOrderExistsById(short id) {
		return repository.existsById(id);
	}

	@Override
	public void createOrder(OrderFormForCreating form) {
		repository.save(form.toEntity());
	}

	@Override
	public Order getOrderByID(short id) {
		return repository.findById(id).get();
	}
	
	@Override
	public List<Order> getOrderByShipId(short shipId) {
		return repository.getByShipId(shipId);
	}

	@Override
	public void updateOrder(short id, OrderFormForUpdating form) {
		Order entity = repository.findById(id).get();
//		entity.setName(form.getName());
		entity.setOrderStatus(form.getOrderStatus());
		entity.setUserId(form.getUserId());
		entity.setShipId(form.getShipId());
		entity.setCreatedTime(form.getCreatedTime());
		entity.setPaymentMethod(form.getPaymentMethod());
		repository.save(entity);
	}

	@Transactional
	public void deleteOrders(List<Short> ids) {
		repository.deleteByIdIn(ids);
	}
}
