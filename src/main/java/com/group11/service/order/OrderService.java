package com.group11.service.order;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.group11.dto.order.OrderFormForCreating;
import com.group11.dto.order.OrderFormForUpdating;
import com.group11.entity.Order;
import com.group11.entity.Product;
import com.group11.entity.ProductOrder;
import com.group11.entity.ProductOrderId;
import com.group11.repository.IOrderRepository;
import com.group11.repository.IProductOrderRepository;
import com.group11.repository.IProductRepository;
import com.group11.specification.order.OrderSpecificationBuilder;

@Service
public class OrderService implements IOrderService {
	@Autowired
	private final IOrderRepository repository;
	private final IProductOrderRepository productOrderRepository;
	private final IProductRepository productRepository;

	@Override
	public Page<Order> getAllOrders(Pageable pageable, String search) {
		OrderSpecificationBuilder specification = new OrderSpecificationBuilder(search);

		return repository.findAll(specification.build(), pageable);
	}

	public OrderService(IOrderRepository repository, IProductOrderRepository productOrderRepository,
			IProductRepository productRepository) {
		this.repository = repository;
		this.productOrderRepository = productOrderRepository;
		this.productRepository = productRepository;
	}

	@Override
	@Transactional
	public void createOrder(OrderFormForCreating form) {
		// Save the Order entity to the database
		Order order = repository.save(form.toEntity());

		// Save productOrder entities to the database
		for (ProductOrder productOrder : form.getItems()) {
			// Find the Product entity based on the productId in productOrder
			Product product = productRepository.findById(productOrder.getProduct().getId()).orElseThrow(
					() -> new RuntimeException("Product not found with ID: " + productOrder.getProduct().getId()));

			// Create a new ProductOrderId instance
			ProductOrderId productOrderId = new ProductOrderId();
			productOrderId.setProductId(product.getId());
			productOrderId.setOrderId(order.getId());

			// Create a new ProductOrder instance
			ProductOrder newProductOrder = new ProductOrder();
			newProductOrder.setId(productOrderId);
			newProductOrder.setProduct(product);
			newProductOrder.setOrder(order);
			newProductOrder.setPrice(productOrder.getPrice());
			newProductOrder.setQuantity(productOrder.getQuantity());

			// Save the new ProductOrder entity to the database
			productOrderRepository.save(newProductOrder);
		}
		repository.save(order);
	}

	@Override
    @Transactional
    public void updateOrder(ProductOrderId productOrderId, OrderFormForUpdating form) {
        // Check if the ProductOrder with the given composite ID exists in the database
        ProductOrder existingProductOrder = productOrderRepository.findById(productOrderId)
                .orElseThrow(() -> new NotFoundException("ProductOrder not found with ID: " + productOrderId));

        // Update the existing ProductOrder's properties with the new data from form
        existingProductOrder.getProduct().setPrice(form.getPrice());
        existingProductOrder.setQuantity(form.getQuantity());

        // Save the updated ProductOrder entity to the database
        productOrderRepository.save(existingProductOrder);
    }
	
    @Override
    @Transactional
    public void deleteOrders(List<Short> ids) {
        // Delete all orders with the given ids
        for (Short id : ids) {
            // Check if the order with the given id exists in the database
            Order existingOrder = repository.findById(id)
                    .orElseThrow();

            // Delete all ProductOrder entities related to this order
            productOrderRepository.deleteById(id);

            // Delete the Order entity from the database
            repository.delete(existingOrder);
        }
    }

}
