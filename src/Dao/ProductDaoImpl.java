package Dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


import model.Product;

public class ProductDaoImpl implements ProductDao {

	private EntityManagerFactory emf = Persistence.createEntityManagerFactory("myProducts");
	
	@Override
	public void create(Product p) {
	
		EntityManager em = emf.createEntityManager();
		
		em.getTransaction().begin();
		em.persist(p);
		em.getTransaction().commit();
		
	}
	
	@Override
	public List<Product> findAll() {
	
		EntityManager em = emf.createEntityManager();
		 List<Product> products = em.createQuery("select p from Product p", Product.class).getResultList();
		return products;

	}
	
	@Override
	public Product findOne(String name) {
	
		EntityManager em = emf.createEntityManager();
		
		Product p = em.createQuery("select p from Product p where p.name = :name",Product.class).setParameter("name",name).getSingleResult();
		
		return p;
	}
	
	@Override
	public void delete(Product p) {
	
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
	  
	    em.remove(em.contains(p) ? p : em.merge(p));
	    em.getTransaction().commit();
	}
	
	public void update(Product p) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.merge(p);
		 em.getTransaction().commit();
		
	}
}
