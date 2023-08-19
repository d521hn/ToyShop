package com.group11.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.group11.dto.statistic.TopProductDTO;
import com.group11.repository.IStatisticRepository;
import com.group11.service.productorder.IProductOrderService;

@RestController
@RequestMapping(value = "api/v1/statistic")
public class StatisticController {
	@Autowired
	private IStatisticRepository statisticRepo;
	
	@GetMapping(value = "/totalQuantity")
	public ResponseEntity<?> getTotalQuantity(@RequestParam (name = "month") short month, @RequestParam (name = "year") short year) {
		return new ResponseEntity<>(statisticRepo.getTotalOrderQuantity(month, year), HttpStatus.OK);
	}
	
	@GetMapping(value = "/totalProduct")
	public ResponseEntity<?> getTotalProduct(@RequestParam (name = "month") short month, @RequestParam (name = "year") short year) {
		return new ResponseEntity<>(statisticRepo.getTotalProduct(month, year), HttpStatus.OK);
	}
	
	@GetMapping(value = "/revenue")
	public ResponseEntity<?> getRevenue(@RequestParam (name = "month") short month, @RequestParam (name = "year") short year) {
		return new ResponseEntity<>(statisticRepo.getRevenue(month, year), HttpStatus.OK);
	}
	
	@GetMapping(value = "/profit")
	public ResponseEntity<?> getProfit(@RequestParam (name = "month") short month, @RequestParam (name = "year") short year) {
		return new ResponseEntity<>(statisticRepo.getProfit(month, year), HttpStatus.OK);
	}
	
//	@GetMapping(value = "/topProduct")
//	public ResponseEntity<?> getTopProduct(@RequestParam (name = "month") short month, @RequestParam (name = "year") short year) {
//		List<TopProductDTO> entities = statisticRepo.getTopProduct(month, year);
//		return new ResponseEntity<>(entities, HttpStatus.OK);
//	}
	
    @GetMapping("/topProduct")
    public List<Object[]> getTopProduct(
        @RequestParam("month") short month,
        @RequestParam("year") short year) {
        return statisticRepo.getTopProduct(month, year);
    }
    
    @GetMapping("/revenueByMonth")
    public List<Object[]> getRevenueByMonth(@RequestParam("year") short year) {
        return statisticRepo.getRevenueByMonth( year);
    }
}
