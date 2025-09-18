package com.du.baguniee0917.dao;

import com.du.baguniee0917.domain.CartItem;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CartMapper {

    @Select("select c.*, p.id as product_id, p.name, p.price from cart_items c join products p on c.product_id = p.id;")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "productId", column = "product_id"),
            @Result(property = "quantity", column = "quantity"),
            @Result(property = "product.id", column = "product_id"),
            @Result(property = "product.name", column = "name"),
            @Result(property = "product.price", column = "price")
    })
    List<CartItem> findAll();

    @Insert("insert into cart_items(product_id, quantity) values(#{productId}, #{quantity})")
    void insert(CartItem cartItem);

    @Insert("delete from cart_items where id = #{id}")
    void delete(Long id);
}
