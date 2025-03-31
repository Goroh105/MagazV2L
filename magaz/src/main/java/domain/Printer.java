package domain;



public class Printer {

	/**
	 * Класс данных о принтерах
	 */

	    private Long id;
	    private Long model;
	    private Boolean color;
	    private String type;
	    private Double price;
	    private Integer count;

	    public Printer() {
	    }

	    public Printer(Long model, Boolean color, String type, Double price, Integer count) {
	        this.model = model;
	        this.color = color;
	        this.type = type;
	        this.price = price;
	        this.count = count;
	    }

	    public Printer(Long id, Long model, Boolean color, String type, Double price, Integer count) {
	        this.id = id;
	        this.model = model;
	        this.color = color;
	        this.type = type;
	        this.price = price;
	        this.count = count;
	    }

	    public Long getId() {
	        return id;
	    }
	    public void setId(Long id) {
	        this.id = id;
	    }

	    public Long getModel() {
	        return model;
	    }
	    public void setModel(Long model) {
	        this.model = model;
	    }

	    public Boolean isColor() {
	        return color;
	    }
	    public void setColor(Boolean color) {
	        this.color = color;
	    }

	    public String getType() {
	        return type;
	    }
	    public void setType(String type) {
	        this.type = type;
	    }

	    public Double getPrice() {
	        return price;
	    }
	    public void setPrice(Double price) {
	        this.price = price;
	    }

	    public Integer getCount() {
	        return count;
	    }
	    public void setCount(Integer count) {
	        this.count = count;
	    }

	    @Override
	    public String toString() {
	        return "Printer{" +
	                "id=" + id +
	                ", model='" + getModel() + '\'' +
	                ", color=" + color +
	                ", type='" + type + '\'' +
	                ", price=" + price +
	                ", count=" + count +
	                '}';
	 
	}

}
