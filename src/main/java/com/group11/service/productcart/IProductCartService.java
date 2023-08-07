package com.group11.service.productcart;

import java.util.List;

import com.group11.dto.productcart.ProductCartFormForCreating;
import com.group11.dto.productcart.ProductCartFormForUpdating;
import com.group11.entity.ProductCart;
import com.group11.entity.ProductCartId;

public interface IProductCartService {
	List<ProductCart> getAllProductCarts();
	void createProductCart(ProductCartFormForCreating form);
	
	void updateProductCart(short productId, short cartId, ProductCartFormForUpdating form);

	void deleteProductCart(short productId, short cartId);
}
