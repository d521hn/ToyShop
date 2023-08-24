package com.group11.dto.order;

import java.util.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class OrderFilter {
	Date minDate;
	Date maxDate;
}
