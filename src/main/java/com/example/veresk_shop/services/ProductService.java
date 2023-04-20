package com.example.veresk_shop.services;

import com.example.veresk_shop.models.Category;
import com.example.veresk_shop.models.Product;
import com.example.veresk_shop.repositories.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class ProductService {
    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {

        this.productRepository = productRepository;
    }


    //Получение всех продуктов
public List<Product> getAllProducts(){
    return  productRepository.findAll();
}

//поиск по id
//обертка для значениц:нацден или нет продукт
public  Product productById(int id){
    Optional<Product>optionalProduct = productRepository.findById(id);
    return optionalProduct.orElse(null);
}
//Добавление продукт
@Transactional
    public void saveProduct(Product product,Category category){
        product.setCategory(category);
        productRepository.save(product);
}

//Обновление данных
@Transactional
public void updateProduct(int id, Product product){
        product.setId(id);
        productRepository.save(product);
}
//удаление
    @Transactional
    public  void deleteProduct(int id){
        productRepository.deleteById(id);
    }
}
