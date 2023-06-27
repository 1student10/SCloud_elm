package com.elm.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.elm.mapper.*;
import com.elm.po.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.elm.service.OrdersService;
import com.elm.util.CommonUtil;

@Service
public class OrdersServiceImpl implements OrdersService{
	
	@Autowired
	private CartMapper cartMapper;
	@Autowired
	private OrdersMapper ordersMapper;
	@Autowired
	private OrderDetailetMapper orderDetailetMapper;
	@Autowired
	private BusinessMapper businessMapper;
	@Autowired
	private FoodMapper foodMapper;

	@Override
	@Transactional
	public int createOrders(Orders orders) {
		//1、查询当前用户购物车中当前商家的所有食品
		Cart cart = new Cart();
		cart.setUserId(orders.getUserId());
		cart.setBusinessId(orders.getBusinessId());
		List<Cart> cartList = cartMapper.listCart(cart);
		
		//2、创建订单（返回生成的订单编号）
		orders.setOrderDate(CommonUtil.getCurrentDate());
		ordersMapper.saveOrders(orders);
		int orderId = orders.getOrderId();
		
		//3、批量添加订单明细
		List<OrderDetailet> list = new ArrayList<>();
		for(Cart c : cartList) {
			OrderDetailet od = new OrderDetailet();
			od.setOrderId(orderId);
			od.setFoodId(c.getFoodId());
			od.setQuantity(c.getQuantity());
			orderDetailetMapper.saveOrderDetailet(od);
			list.add(od);
		}
//		orderDetailetMapper.saveOrderDetailetBatch(list);
		
		//4、从购物车表中删除相关食品信息
		cartMapper.removeCart(cart);
		
		return orderId;
	}
	
	@Override
	public Orders getOrdersById(Integer orderId) {
		Orders orders = ordersMapper.getOrdersById(orderId);
		Business business = businessMapper.getBusinessById(orders.getBusinessId());
		orders.setBusiness(business);
		List<OrderDetailet> orderDetailetList = orderDetailetMapper.listOrderDetailetByOrderId(orders.getOrderId());
		for(OrderDetailet od : orderDetailetList) {
			Food food = foodMapper.getFoodById(od.getFoodId());
			od.setFood(food);
		}
		orders.setList(orderDetailetList);
		return orders;
	}
	
	@Override
	public List<Orders> listOrdersByUserId(String userId){
		List<Orders> ordersList = ordersMapper.listOrdersByUserId(userId);
		for(Orders o : ordersList) {
			Business business = new Business();
			business.setBusinessId(o.getBusinessId());
			o.setBusiness(business);
			List<OrderDetailet> orderDetailetList = orderDetailetMapper.listOrderDetailetByOrderId(o.getOrderId());
			for(OrderDetailet od : orderDetailetList) {
				Food food = foodMapper.getFoodById(od.getFoodId());
				od.setFood(food);
			}
			o.setList(orderDetailetList);
		}
		return ordersList;
	}
}
