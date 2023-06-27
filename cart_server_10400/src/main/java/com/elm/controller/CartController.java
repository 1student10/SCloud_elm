package com.elm.controller;

import java.util.List;

import com.elm.po.Business;
import com.elm.po.CommonResult;
import com.elm.po.Food;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

import com.elm.po.Cart;
import com.elm.service.CartService;
import org.springframework.web.client.RestTemplate;

@RefreshScope //开启动态刷新
//@CrossOrigin("*")
@RestController
@RequestMapping("/CartController")
public class CartController {
	@Autowired
	private CartService cartService;
	@Autowired
	private RestTemplate restTemplate;

	@GetMapping("/listCart/{userId}")
	public CommonResult<List> listCart(@PathVariable("userId") String userId) throws Exception{
		Cart param = new Cart();
		param.setUserId(userId);
		List<Cart> list = cartService.listCartByAll(param);
		return new CommonResult(200,"success(10400)",list);
	}

	@GetMapping("/listCart/{userId}/{businessId}")
	public CommonResult<List> listCart(
			@PathVariable("userId") String userId,
			@PathVariable("businessId") Integer businessId) throws Exception{
		Cart param = new Cart();
		param.setUserId(userId);
		param.setBusinessId(businessId);
		List<Cart> list = cartService.listCartByBusiness(param);
		for (Cart cart:list){
			//在购物车微服务中调用食品微服务
			CommonResult<Food> result =
					restTemplate.getForObject("http://localhost:10200/FoodController/getFoodById/"
							+cart.getFoodId(), CommonResult.class);
			if(result.getCode()==200) {
				//将LinkedHashMap对象转换为Food对象
				ObjectMapper objectMapper = new ObjectMapper();
				Food food = objectMapper.convertValue(result.getResult(), Food.class);
				cart.setFood(food);
			}
			//在购物车微服务中调用商家微服务
			CommonResult<Business> Result =
					restTemplate.getForObject("http://localhost:10300/BusinessController/getBusinessById/"
							+cart.getBusinessId(), CommonResult.class);
			if(Result.getCode()==200) {
				//将LinkedHashMap对象转换为Food对象
//				ObjectMapper objectMapper2 = new ObjectMapper();
//				Business business = objectMapper2.convertValue(Result.getResult(), Business.class);
				ObjectMapper objectMapper2 = new ObjectMapper();
				Business business = objectMapper2.convertValue(Result.getResult(), Business.class);
				cart.setBusiness(business);
			}
		}
		return new CommonResult(200,"success(10400)",list);
	}

	@PostMapping("/saveCart/{userId}/{businessId}/{foodId}")
	public CommonResult<Integer> saveCart(
			@PathVariable("userId") String userId,
			@PathVariable("businessId") Integer businessId,
			@PathVariable("foodId") Integer foodId) throws Exception{
		Cart param = new Cart();
		param.setUserId(userId);
		param.setBusinessId(businessId);
		param.setFoodId(foodId);
		int result = cartService.saveCart(param);
		return new CommonResult(200,"success(10400)",result);
	}

	@PutMapping("/updateCart/{userId}/{businessId}/{foodId}/{quantity}")
	public CommonResult<Integer> updateCart(
			@PathVariable("userId") String userId,
			@PathVariable("businessId") Integer businessId,
			@PathVariable("foodId") Integer foodId,
			@PathVariable("quantity") Integer quantity) throws Exception{
		Cart param = new Cart();
		param.setUserId(userId);
		param.setBusinessId(businessId);
		param.setFoodId(foodId);
		param.setQuantity(quantity);
		int result = cartService.updateCart(param);
		return new CommonResult(200,"success(10400)",result);
	}

	@DeleteMapping("/removeCart/{userId}/{businessId}/{foodId}")
	public CommonResult<Integer> removeCart(
			@PathVariable("userId") String userId,
			@PathVariable("businessId") Integer businessId,
			@PathVariable("foodId") Integer foodId) throws Exception{
		Cart param = new Cart();
		param.setUserId(userId);
		param.setBusinessId(businessId);

		param.setFoodId(foodId);
		int result = cartService.removeCart(param);
		return new CommonResult(200,"success(10400)",result);
	}
}
