package org.example;


class Product {
    private String name;
    private int product_id;
    private String category;
    private int year;
    private int price;
    public Product() {

    }
    public Product(int product_id, String name, String category, int year, int price ) {
        setId(product_id);
        setName(name);
        setCategory(category);
        setYear(year);
        setPrice(price);

    }
    public int getId() {
        return product_id;
    }
    public void setId(int productId) {
        this.product_id=productId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category){
        this.category = category;
    }
    public String getName() {
        return name;
    }

    public void setName(String name){
        this.name = name;
    }
    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
    public int getPrice() {
        return price;
    }
    public void setPrice(int price) {
        this.price=price;
    }

}
