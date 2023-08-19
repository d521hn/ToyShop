package com.group11.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.group11.dto.statistic.TopProductDTO;
import com.group11.entity.Order;

public interface IStatisticRepository extends JpaRepository<Order, Short>, JpaSpecificationExecutor<Order>{
	@Query(value = 
	        "SELECT COUNT(*) FROM `Order` o "
	        + "WHERE MONTH(o.createdTime) = :month AND YEAR(o.createdTime) = :year"
	        , nativeQuery = true)
	public int getTotalOrderQuantity(short month, short year);
	
	@Query(value = 
	        "SELECT SUM(po.quantity) "
	        + "FROM `Order` o INNER JOIN PRODUCT_ORDER po "
	        + "ON o.id = po.orderId "
	        + "WHERE MONTH(o.createdTime) = :month AND YEAR(o.createdTime) = :year"
	        , nativeQuery = true)
	public int getTotalProduct(short month, short year);
	
	@Query(value = 
	        "SELECT SUM(po.price * po.quantity) "
	        + "FROM `Order` o INNER JOIN PRODUCT_ORDER po "
	        + "ON o.id = po.orderId "
	        + "WHERE MONTH(o.createdTime) = :month AND YEAR(o.createdTime) = :year"
	        , nativeQuery = true)
	public int getRevenue(short month, short year);
	
	@Query(value = 
	        "SELECT SUM(po.quantity * po.price) - SUM(po.quantity * pr.price) "
	        + "FROM Product pr INNER JOIN PRODUCT_ORDER po "
	        + "ON pr.id = po.productId "
	        + "INNER JOIN `Order` o "
	        + "ON o.id = po.orderId "
	        + "WHERE MONTH(o.createdTime) = :month AND YEAR(o.createdTime) = :year"
	        , nativeQuery = true)
	public int getProfit(short month, short year);
	
	@Query(value = 
	        "SELECT pr.id, pr.name, br.name as brandName, ca.name as cateName, pr.ageGroup, SUM(po.quantity) so_luong_ban, sum(po.quantity * po.price) "
	        + "FROM Product pr INNER JOIN PRODUCT_ORDER po "
	        + "ON pr.id = po.productId "
	        + "INNER JOIN `Order` o "
	        + "ON o.id = po.orderId "
	        + "INNER JOIN Brand br "
	        + "ON br.id = pr.brandId "
	        + "INNER JOIN Category ca "
	        + "ON ca.id = pr.cateId "
	        + "WHERE MONTH(o.createdTime) = :month AND YEAR(o.createdTime) = :year "
	        + "GROUP BY pr.id, pr.name "
	        + "ORDER BY so_luong_ban DESC"
	        , nativeQuery = true)
	public List<Object[]> getTopProduct(short month, short year);
//	public List<TopProductDTO> getTopProduct(short month, short year);
	
	@Query(value = 
	        "SELECT MONTH(o.createdTime), SUM(po.quantity * po.price) "
	        + "FROM `Order` o INNER JOIN PRODUCT_ORDER po "
	        + "ON o.id = po.orderId "
	        + "WHERE YEAR(o.createdTime) = :year "
	        + "GROUP BY MONTH(o.createdTime)"
	        + "ORDER BY MONTH(o.createdTime)"
	        , nativeQuery = true)
	public List<Object[]> getRevenueByMonth(short year);
	
}
