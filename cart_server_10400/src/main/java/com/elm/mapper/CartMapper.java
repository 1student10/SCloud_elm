package com.elm.mapper;

import java.util.List;

import org.apache.ibatis.annotations.*;

import com.elm.po.Cart;

@Mapper
public interface CartMapper {

	@Select("select * from cart where userId=#{userId}")
	public List<Cart> listCartByAll(Cart cart);
	@Select("select * from cart where userId=#{userId} and businessId=#{businessId}")
	public List<Cart> listCartByBusiness(Cart cart);
	
	@Insert("insert into cart values(null,#{foodId},#{businessId},#{userId},1)")
	public int saveCart(Cart cart);
	
	@Update("update cart set quantity=#{quantity} where foodId=#{foodId} and businessId=#{businessId} and userId=#{userId}")
	public int updateCart(Cart cart);

	@Delete("delete from cart where foodId=#{foodId} and businessId=#{businessId} and userId=#{userId}")
	public int removeCart(Cart cart);
}
