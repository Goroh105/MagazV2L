package domain;

/** 
 *   Класс данных о продуктах 
 */ 

public class Product {
	
	// Идентификатор  
		 private Long model; 
		 // Наименование производителя
		 private String maker; 
		// Тип товара
		private String type; 
		 
		 public Product() { 
		 } 
		  
		 public Product(String maker) { 
		  this.maker = maker; 
		 } 
		  
		 public Product(Long model, String maker, String type) { 
		  this.model = model; 
		  this.maker = maker; 
		  this.type = type;		 } 
		 
		 
		 public Long getmodel() { 
		  return model; 
		 } 
		 public void setmodel(Long model) { 
		  this.model = model; 
		 } 
		 
		 public String getmaker() { 
		  return maker; 
		 } 
		 public void setmaker(String maker) { 
		  this.maker = maker; 
		 } 
		 
		 public String gettype() { 
			  return type; 
			 } 
		 public void settype(String type) { 
			  this.type = type; 
			 } 
		  
		 @Override 
		 public String toString() { 
		  return "Product {" + "Model = " + model + ", Maker = " + maker + 
				  ", Type = " + type +"}"; 
		 } 

}
