package com.elm.service;

import com.elm.po.Cart;

import java.util.List;

public interface CartService {

	public List<Cart> listCartByAll(Cart cart);
	public List<Cart> listCartByBusiness(Cart cart);
	public int saveCart(Cart cart);
	public int updateCart(Cart cart);
	public int removeCart(Cart cart);
}
