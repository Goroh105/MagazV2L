
	/**
	 * Класс данных о ноутбуках
	 */

package domain;

public class Laptop {
	    private Long id;
	    private Long model;
	    private Float speed;
	    private Integer ram;
	    private Integer hd;
	    private Float screen;
	    private Double price;
	    private Integer count;

	    public Laptop() {
	    }

	    public Laptop(Long id, Long model, Float speed, Integer ram, Integer hd, Float screen, Double price, Integer count) {
	        this.id = id;
	        this.model = model;
	        this.speed = speed;
	        this.ram = ram;
	        this.hd = hd;
	        this.screen = screen;
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

	    public Float getSpeed() {
	        return speed;
	    }
	    public void setSpeed(Float speed) {
	        this.speed = speed;
	    }

	    public Integer getRam() {
	        return ram;
	    }
	    public void setRam(Integer ram) {
	        this.ram = ram;
	    }

	    public Integer getHd() {
	        return hd;
	    }
	    public void setHd(Integer hd) {
	        this.hd = hd;
	    }

	    public Float getScreen() {
	        return screen;
	    }
	    public void setScreen(Float screen) {
	        this.screen = screen;
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
	        return "Laptop{" +
	                "id=" + id +
	                ", model=" + getModel() +
	                ", speed=" + speed +
	                ", ram=" + ram +
	                ", hd=" + hd +
	                ", screen=" + screen +
	                ", price=" + price +
	                ", count=" + count +
	                '}';
	    }
	}
