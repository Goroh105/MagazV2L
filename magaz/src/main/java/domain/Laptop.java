
	/**
	 * Класс данных о ноутбуках
	 */

package domain;

public class Laptop {
    private Long id;
    private String model;
    private Integer speed;
    private Integer ram;
    private Integer hd;
    private Double screen;
    private Double price;
    private Integer count;

    public Laptop() {
    }

    public Laptop(Long id, String model, Integer speed, Integer ram, Integer hd, Double screen, Double price, Integer count) {
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

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Integer getSpeed() {
        return speed;
    }

    public void setSpeed(Integer speed) {
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

    public Double getScreen() {
        return screen;
    }

    public void setScreen(Double screen) {
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
                ", model='" + model + '\'' +
                ", speed=" + speed +
                ", ram=" + ram +
                ", hd=" + hd +
                ", screen=" + screen +
                ", price=" + price +
                ", count=" + count +
                '}';
    }
}
