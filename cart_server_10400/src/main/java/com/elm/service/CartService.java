package com.elm.service;

import java.util.List;

import com.elm.po.Cart;

public interface CartService {

	public List<Cart> listCartByAll(Cart cart);
	public List<Cart> listCartByBusiness(Cart cart);
	public int saveCart(Cart cart);
	public int updateCart(Cart cart);
	public int removeCart(Cart cart);
}
