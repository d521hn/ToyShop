package com.group11.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.group11.config.payment.VNPayConfig;
import com.group11.dto.payment.PaymentDTO;
import com.group11.service.payment.PaymentService;

@RestController
@RequestMapping(value = "api/v1/payment")

public class PaymentController {
	@Autowired
	private PaymentService paymentService;

	@GetMapping("/create")
	public ResponseEntity<?> createPayment(@RequestParam(name = "orderId") String orderId,
			@RequestParam(name = "totalPrice") long totalPrice, HttpServletRequest req)
			throws UnsupportedEncodingException {

		ResponseEntity<PaymentDTO> response = paymentService.createPayment(orderId, totalPrice, req);
		return response;
	}
}
