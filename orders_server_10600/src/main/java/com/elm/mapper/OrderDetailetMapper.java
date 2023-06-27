package com.elm.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import com.elm.po.OrderDetailet;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface OrderDetailetMapper {

	@Insert("insert into orderdetailet(orderId,foodId,quantity) values(#{orderId},#{foodId},#{quantity})")
//	@Options(useGeneratedKeys=true,keyProperty="orderId",keyColumn="orderId")
	public int saveOrderDetailetBatch(List<OrderDetailet> list);

	@Insert("insert into orderdetailet(orderId,foodId,quantity) values(#{orderId},#{foodId},#{quantity})")
	public int saveOrderDetailet(OrderDetailet orderDetailet);

	@Select("select * from orderdetailet where orderId=#{orderOd}")
	public List<OrderDetailet> listOrderDetailetByOrderId(Integer orderOd);
}
