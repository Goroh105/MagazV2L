package domain;

public class PC {


	/**
	 * Класс данных о настольных компьютерах
	 */

	    private Long id;
	    private Long model;
	    private Float speed;
	    private Integer ram;
	    private Integer hd;
	    private String cd;
	    private Double price;
	    private Integer count;

	    public PC() {
	    }

	    public PC(Long model, Float speed, Integer ram, Integer hd, String cd, Double price, Integer count) {
	        this.model = model;
	        this.speed = speed;
	        this.ram = ram;
	        this.hd = hd;
	        this.cd = cd;
	        this.price = price;
	        this.count = count;
	    }

	    public PC(Long id, Long model, Float speed, Integer ram, Integer hd, String cd, Double price, Integer count) {
	        this.id = id;
	        this.model = model;
	        this.speed = speed;
	        this.ram = ram;
	        this.hd = hd;
	        this.cd = cd;
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

	    public String getCd() {
	        return cd;
	    }
	    public void setCd(String cd) {
	        this.cd = cd;
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
	        return "PC{" +
	                "id=" + id +
	                ", model='" + getModel() + '\'' +
	                ", speed=" + speed +
	                ", ram=" + ram +
	                ", hd=" + hd +
	                ", cd='" + cd + '\'' +
	                ", price=" + price +
	                ", count=" + count +
	                '}';
	    }
}
