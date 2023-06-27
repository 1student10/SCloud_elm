package com.elm.service.impl;

import com.elm.mapper.CartMapper;
import com.elm.po.Cart;
import com.elm.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartServiceImpl implements CartService{
	
	@Autowired
	private CartMapper cartMapper;

	@Override
	public List<Cart> listCartByAll(Cart cart) {
		return cartMapper.listCartByAll(cart);
	}
	@Override
	public List<Cart> listCartByBusiness(Cart cart) {
		return cartMapper.listCartByBusiness(cart);
	}
	
	@Override
	public int saveCart(Cart cart) {
		return cartMapper.saveCart(cart);
	}
	
	@Override
	public int updateCart(Cart cart) {
		return cartMapper.updateCart(cart);
	}
	
	@Override
	public int removeCart(Cart cart) {
		return cartMapper.removeCart(cart);
	}
}
