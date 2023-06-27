package com.elm.controller;

import java.util.List;

import com.elm.po.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

import com.elm.service.OrdersService;
import org.springframework.web.client.RestTemplate;

@RefreshScope //开启动态刷新
//@CrossOrigin("*")
@RestController
@RequestMapping("/OrdersController")
public class OrdersController {
	@Autowired
	private OrdersService ordersService;
	@Autowired
	private RestTemplate restTemplate;

	@PostMapping("/createOrders/{userId}/{businessId}/{daId}/{orderTotal}")
	public CommonResult<Integer> createOrders(
			@PathVariable("userId") String userId,
			@PathVariable("businessId") Integer businessId,
			@PathVariable("daId") Integer daId,
			@PathVariable("orderTotal") Double orderTotal) throws Exception{
		Orders orders = new Orders();
		orders.setUserId(userId);
		orders.setBusinessId(businessId);
		orders.setDaId(daId);
		orders.setOrderTotal(orderTotal);
		int orderId = ordersService.createOrders(orders);
		return new CommonResult(200,"success（10600）",orderId);
	}

	@GetMapping("/getOrdersById/{orderId}")
	public CommonResult<Orders> getOrdersById(
			@PathVariable("orderId") Integer orderId) throws Exception{
		Orders orders = ordersService.getOrdersById(orderId);
		//在订单微服务中调用商家微服务
		CommonResult<Business> result =
				restTemplate.getForObject("http://localhost:10300/BusinessController/getBusinessById/"
						+orders.getBusinessId(), CommonResult.class);
		if(result.getCode()==200) {
			//将LinkedHashMap对象转换为Food对象
			ObjectMapper objectMapper = new ObjectMapper();
			Business business = objectMapper.convertValue(result.getResult(), Business.class);
			orders.setBusiness(business);
		}
		return new CommonResult(200,"success（10600）",orders);
	}

	@GetMapping("/listOrdersByUserId/{userId}")
	public CommonResult<List> listOrdersByUserId(
			@PathVariable("userId") String userId) throws Exception{
		List<Orders> list = ordersService.listOrdersByUserId(userId);
		for(Orders orders : list) {
			//在订单微服务中调用商家微服务
			CommonResult<Business> result =
					restTemplate.getForObject("http://localhost:10300/BusinessController/getBusinessById/"
							+orders.getBusinessId(), CommonResult.class);
			if(result.getCode()==200) {
				//将LinkedHashMap对象转换为Orders对象
				ObjectMapper objectMapper = new ObjectMapper();
				Business business = objectMapper.convertValue(result.getResult(), Business.class);
				orders.setBusiness(business);
			}
		}
		return new CommonResult(200,"success（10600）",list);
	}
}
