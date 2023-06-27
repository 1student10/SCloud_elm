package com.elm.controller;

import com.elm.po.CommonResult;
import com.elm.po.Food;
import com.elm.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RefreshScope //开启动态刷新
//@CrossOrigin("*")
@RestController
@RequestMapping("/FoodController")
public class FoodController {

	@Autowired
	private FoodService foodService;
	@GetMapping("/listFoodByBusinessId/{businessId}")
	public CommonResult<List> listFoodByBusinessId(@PathVariable("businessId") Integer businessId) throws Exception{
		List<Food> list = foodService.listFoodByBusinessId(businessId);
		return new CommonResult(200,"success（10201）",list);
	}

	@GetMapping("/getFoodById/{foodId}")
	public CommonResult<List> listFoodByFoodId(
			@PathVariable("foodId") Integer foodId
	) throws Exception{
		Food food = foodService.getFoodById(foodId);
		return new CommonResult(200,"success（10201）",food);
	}
}
