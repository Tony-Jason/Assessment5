package Dao;

import java.util.List;

import model.Product;

public interface ProductDao {

	public default void create(Product p) {}
	
	public default List<Product> findAll(){
		return null;}
	
	public default Product findOne(String name) {
		return null;}
	
	public default void delete(Product p) {}
	
	public default void update(Product p) {}
}
