package com.elm.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;

import com.elm.po.Orders;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface OrdersMapper {

	@Insert("insert into orders(userId,businessId,orderDate,orderTotal,daId,orderState) values(#{userId},#{businessId},#{orderDate},#{orderTotal},#{daId},0)")
	@Options(useGeneratedKeys=true,keyProperty="orderId",keyColumn="orderId")
	public int saveOrders(Orders orders);

	@Select("select * from orders where orderId=#{orderId}")
	public Orders getOrdersById(Integer orderId);

	@Select("select * from orders where userId=#{userId} order by orderId")
	public List<Orders> listOrdersByUserId(String userId);
}
