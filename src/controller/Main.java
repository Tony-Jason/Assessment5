package controller;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

import Dao.ProductDaoImpl;
import model.Product;

public class Main {
	
	

	Scanner sc = new Scanner(System.in);
	ProductDaoImpl pDao = new ProductDaoImpl();
	
	List<Product> products = new LinkedList<>();
	
	class NameException extends Exception {
        public NameException(String message) {
               super(message);
        }
  }

  public boolean validateName(String name) {
        Pattern p = Pattern.compile("^[a-zA-Z][a-zA-Z\\s]+$");
        try {
               if (p.matcher(name).matches()) {
                     return false;
               }
               if (!(p.matcher(name).matches())) {
                     throw new NameException("Name Should not contain numbers or special characters or be null");
               } else {
                     return false;
               }
        } catch (NameException e) {
               System.err.println(e.getMessage());
               return true;
        }
  }

  class DescriptionException extends Exception {
        public DescriptionException(String message) {
               super(message);
        }
  }

  public boolean validateDesc(String desc) {
        try {
               if (desc == null || desc.length() == 0) {
                     throw new DescriptionException("Description should not be null");
               } else {
                     return false;
               }
        } catch (DescriptionException d) {
               System.out.println(d.getMessage());
               return true;
        }
  }

 

	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Main m = new Main();
		Scanner sc = new Scanner(System.in);
		

		
		int option = 0;
		do {
			System.out.println("1.Add Products");
			System.out.println("2.View Products");
			System.out.println("3.Delete a product");
			System.out.println("4.Update Product");
			System.out.println("0.Exit");
			
			System.out.println("Select an option");
			
			int choice = sc.nextInt();
			sc.nextLine();
			
			switch(choice) {
			case 1:
				m.add();
				break;
			case 2:
				m.view();
				break;
			case 3:
				m.delete();
				break;
			case 4:
				m.update();
				break;
			case 0:
				option = -1;
				break;
			}
			
		}while(option != -1);
	
	}
	
	public void add() {
		System.out.println("Enter Product Name");
		String pName = sc.next();
		while(validateName(pName)) {
			System.out.println("Enter Valid product Name");
			pName = sc.next();
		}
		System.out.println("Enter Product Description");
		String pDesc = sc.next();
		while(validateName(pDesc)) {
			System.out.println("Enter Valid Description");
			pDesc = sc.next();
		}
		System.out.println("Enter price");
		int price = sc.nextInt();
		sc.nextLine();
		System.out.println("Enter Quantity");
		int quan = sc.nextInt();
		
		Product p = new Product(pName,pDesc,price,quan);
		
		pDao.create(p);
		
	}
	
	public void view() {
		products = pDao.findAll();
		System.out.println("Products Available");
		for(Product p:products) {
			System.out.println("Product Id: " + p.getId() + "\n");
			System.out.println("Product Name: " + p.getName() + "\n");
			System.out.println("Description: " + p.getDescription() + "\n");
			System.out.println("Price: " + p.getPrice() + "\n");
			System.out.println("Quantity: " + p.getQuantity() + "\n");
		}
	}
	
	public void delete() {
		System.out.println("Enter name of product to delete");
		String dName = sc.next();
		Product p = pDao.findOne(dName);
		pDao.delete(p);
	}
	
	public void update() {
		System.out.println("Enter name of the product to update");
		String uName = sc.next();
		Product p = pDao.findOne(uName);
	
		System.out.println("Enter Product Name");
	     p.setName(sc.next()); 
		System.out.println("Enter Product Description");
		p.setDescription(sc.next());
		System.out.println("Enter price");
		p.setPrice(sc.nextInt());
		sc.nextLine();
		System.out.println("Enter Quantity");
		p.setQuantity(sc.nextInt());
		pDao.update(p);
		
		

		}
	
	

}
