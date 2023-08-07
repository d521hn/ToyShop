package com.group11.dto.productcart;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProductCartFormForUpdating {
	short productId;
	short cartId;
	int quantity;
}
