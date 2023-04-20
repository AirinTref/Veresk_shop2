package com.example.veresk_shop.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "product")
public class Product {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotEmpty(message = "Введите наименование товара")
    @Column(name = "title", length = 100, nullable = false, columnDefinition = "text")
    private String title;

    @Column(name = "description", length = 1000, nullable = false, columnDefinition = "text")
    @NotEmpty(message = "Введите описание товара")
    private String description;

    @NotNull(message = "Введите цену")
    @Min(value = 1, message = "Цена товара не может <=0")
    @Column(name = "price", nullable = false)
    private float price;

    @Min(value = 1, message = "Вес товара не может быть меньше 1 грамма")
    @Column(name = "weight", length = 10, nullable = false, columnDefinition = "float")
    private float weight;

    @NotEmpty(message = "Введите год изготовления")
    @Column(name = "actionDate", length = 20, nullable = false, columnDefinition = "text")
    private String actionDate;

    @NotEmpty(message = "Введите срок годности")
    @Column(name = "EXP_date", length = 20, nullable = false, columnDefinition = "text")
    private String EXP_date;

    @ManyToOne(optional = false)
    private Category category;

    private LocalDateTime dateTime;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "product")
    private List<Image> imageList = new ArrayList<>();

    // add photo в лист текущего продукта
    public void addImageToProduct(Image image) {
        image.setProduct(this);
        imageList.add(image);
    }

    // поле даты и времени при создании объекта класса
    @PrePersist
    private void init() {
        dateTime = LocalDateTime.now();
    }

    //2 промежуточная таблица-привязка продукта id к person id
    @ManyToMany()
    @JoinTable(name = "product_cart", joinColumns = @JoinColumn(name = "product_id"), inverseJoinColumns = @JoinColumn(name = "person_id"))
    private List<Person> personList;

    @OneToMany(mappedBy = "product", fetch = FetchType.EAGER)
    private List<Order> orderList;

    public Product(int id, String title, String description, float price, float weight, String actionDate, String EXP_date, Category category, LocalDateTime dateTime, List<Image> imageList, List<Person> personList, List<Order> orderList) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.price = price;
        this.weight = weight;
        this.actionDate = actionDate;
        this.EXP_date = EXP_date;
        this.category = category;
        this.dateTime = dateTime;
        this.imageList = imageList;
        this.personList = personList;
        this.orderList = orderList;
    }


    public Product() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public String getActionDate() {
        return actionDate;
    }

    public void setActionDate(String actionDate) {
        this.actionDate = actionDate;
    }

    public String getEXP_date() {
        return EXP_date;
    }

    public void setEXP_date(String EXP_date) {
        this.EXP_date = EXP_date;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public List<Image> getImageList() {
        return imageList;
    }

    public void setImageList(List<Image> imageList) {
        this.imageList = imageList;
    }

    public List<Person> getPersonList() {
        return personList;
    }

    public void setPersonList(List<Person> personList) {
        this.personList = personList;
    }

    public List<Order> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<Order> orderList) {
        this.orderList = orderList;
    }
}
