package domain;

public class Printer {
    private Long id;
    private String model;
    private Boolean color;
    private String type;
    private Double price;
    private Integer count;

    public Printer() {
    }

    public Printer(Long id, String model, Boolean color, String type, Double price, Integer count) {
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

    public String getModel() {
        return model;
    }
    public void setModel(String model) {
        this.model = model;
    }

    public Boolean getColor() {
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
                ", model='" + model + '\'' +
                ", color=" + color +
                ", type='" + type + '\'' +
                ", price=" + price +
                ", count=" + count +
                '}';
    }
}
