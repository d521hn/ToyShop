package com.group11.service.order;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.group11.dto.brand.BrandFormForUpdating;
import com.group11.dto.order.OrderFormForCreating;
import com.group11.dto.order.OrderFormForUpdating;
import com.group11.entity.Order;
import com.group11.entity.ProductOrderId;

public interface IOrderService {
	Page<Order> getAllOrders(Pageable pageable, String search);
	
	void createOrder(OrderFormForCreating form);
	
	void updateOrder(ProductOrderId productOrderId, OrderFormForUpdating form);

	void deleteOrders(List<Short> ids);
}
