package com.elm.service;

import com.elm.po.Food;

import java.util.List;

public interface FoodService {

	public List<Food> listFoodByBusinessId(Integer businessId);

	public Food getFoodById(Integer foodId);
}
