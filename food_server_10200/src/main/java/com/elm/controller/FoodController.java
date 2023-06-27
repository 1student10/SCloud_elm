package com.elm.controller;

import java.util.List;

import com.elm.po.CommonResult;
import com.elm.po.Food;
import com.elm.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

@RefreshScope //开启动态刷新
//@CrossOrigin("*")
@RestController
@RequestMapping("/FoodController")
public class FoodController {

	@Autowired
	private FoodService foodService;

	@Value("${eureka.instance.lease-renewal-interval-in-seconds}")
	private int renewal;
	@Value("${eureka.instance.lease-expiration-duration-in-seconds}")
	private int expiration;

	@GetMapping("/listFoodByBusinessId/{businessId}")
	public CommonResult<List> listFoodByBusinessId(@PathVariable("businessId") Integer businessId) throws Exception{
		System.out.println(renewal);
		System.out.println(expiration);
		List<Food> list = foodService.listFoodByBusinessId(businessId);
		return new CommonResult(200,"success（10200）",list);
	}

	@GetMapping("/getFoodById/{foodId}")
	public CommonResult<List> listFoodByFoodId(
			@PathVariable("foodId") Integer foodId
	) throws Exception{
		Food food = foodService.getFoodById(foodId);
		return new CommonResult(200,"success（10200）",food);
	}
}
