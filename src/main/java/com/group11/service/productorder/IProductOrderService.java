package com.group11.service.productorder;

import java.util.List;

import com.group11.dto.productorder.ProductOrderFormForCreating;
import com.group11.dto.productorder.ProductOrderFormForUpdating;
import com.group11.entity.ProductOrder;

public interface IProductOrderService {
	List<ProductOrder> getAllProductOrders();
	
	List<ProductOrder> geProductOrderByOrderId(short id);
	
	void createProductOrder(ProductOrderFormForCreating form);
	
	void updateProductOrder(short productId, short orderId, ProductOrderFormForUpdating form);

	void deleteProductOrder(short productId, short orderId);
	
	
}
