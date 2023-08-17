package com.group11.service.productcart;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.group11.dto.product.ProductFormForCreating;
import com.group11.dto.product.ProductFormForUpdating;
import com.group11.dto.productcart.ProductCartFormForCreating;
import com.group11.dto.productcart.ProductCartFormForUpdating;
import com.group11.entity.Product;
import com.group11.entity.ProductCart;
import com.group11.entity.ProductCartId;
import com.group11.repository.IProductCartRepository;

@Service
public class ProductCartService implements IProductCartService{
	@Autowired 
	private IProductCartRepository repository;
	
	@Override
	public List<ProductCart> getAllProductCarts() {
		return repository.findAll();
	} 
	
	@Override
	public List<ProductCart> getProductCartsByCartId(short cartId) {
		return repository.findByCartId(cartId);
	}
	
	@Override
	public void createProductCart(ProductCartFormForCreating form) {
		repository.save(form.toEntity());
	}
	
	@Override
	public void updateProductCart(short productId, short cartId, ProductCartFormForUpdating form) {
		ProductCart entity = repository.findById(productId, cartId);
		ProductCartId id = new ProductCartId();
		id.setProductId(productId);
		id.setCartId(cartId);
		entity.setId(id);;
		entity.setQuantity(form.getQuantity());
		repository.save(entity);
	}
	
	@Transactional
	public void deleteProductCart(short productId, short cartId) {
		repository.deleteById(productId, cartId);
	}
	
//	@Override
//	public void deleteProductCart(short productId, short cartId) {
//	    int deletedRows = repository.deleteById(productId, cartId);
//	    if (deletedRows == 0) {
//	        throw new IllegalArgumentException("No product cart found with the specified IDs");
//	    }
//	}

}
