package com.group11.service.order;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.group11.dto.order.OrderFilter;
import com.group11.dto.order.OrderFormForCreating;
import com.group11.dto.order.OrderFormForUpdating;
import com.group11.entity.Order;

public interface IOrderService {
	Page<Order> getAllOrders(Pageable pageable, OrderFilter filter, String search);
	
	boolean isOrderExistsById(short id);

	void createOrder(OrderFormForCreating form);

	Order getOrderByID(short id);
	
	List<Order> getOrderByShipId(short id);

	void updateOrder(short id, OrderFormForUpdating form);

	void deleteOrders(List<Short> ids);
}
