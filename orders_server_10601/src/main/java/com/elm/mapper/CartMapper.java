package com.elm.mapper;

import com.elm.po.Cart;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CartMapper {

	@Select("select * from cart where userId=#{userId} and businessId=#{businessId}")
	public List<Cart> listCart(Cart cart);
	
	@Insert("insert into cart values(null,#{foodId},#{businessId},#{userId},1)")
	public int saveCart(Cart cart);
	
	@Update("update cart set quantity=#{quantity} where foodId=#{foodId} and businessId=#{businessId} and userId=#{userId}")
	public int updateCart(Cart cart);

	@Delete("delete from cart where businessId=#{businessId} and userId=#{userId}")
	public int removeCart(Cart cart);
}
