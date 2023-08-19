package com.group11.dto.statistic;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TopProductDTO {
	short proId;
	String proName;
	int quantitySale;
	int revenue;
	
}
