package com.group11.service.productorder;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.group11.dto.productorder.ProductOrderFormForCreating;
import com.group11.dto.productorder.ProductOrderFormForUpdating;
import com.group11.entity.ProductOrder;
import com.group11.entity.ProductOrderId;
import com.group11.repository.IProductOrderRepository;

@Service
public class ProductOrderService implements IProductOrderService{
	@Autowired 
	private IProductOrderRepository repository;
	
	@Override
	public List<ProductOrder> getAllProductOrders() {
		return repository.findAll();
	}
	
	@Override
	public List<ProductOrder> geProductOrderByOrderId(short id) {
		return repository.findByOrderId(id);
	}
	
	@Override
	public void createProductOrder(ProductOrderFormForCreating form) {
		repository.save(form.toEntity());
	}
	
	@Override
	public void updateProductOrder(short productId, short orderId, ProductOrderFormForUpdating form) {
		ProductOrder entity = repository.findById(productId, orderId);
		ProductOrderId id = new ProductOrderId();
		id.setProductId(productId);
		id.setOrderId(orderId);
		entity.setId(id);;
		entity.setQuantity(form.getQuantity());
		entity.setPrice(form.getPrice());
		repository.save(entity);
	}
	
	@Transactional
	public void deleteProductOrder(short productId, short orderId) {
		repository.deleteById(productId, orderId);
	}

	

}
