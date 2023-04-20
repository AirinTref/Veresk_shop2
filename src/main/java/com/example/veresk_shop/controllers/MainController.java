package com.example.veresk_shop.controllers;


import com.example.veresk_shop.enumm.Status;
import com.example.veresk_shop.models.Cart;
import com.example.veresk_shop.models.Order;
import com.example.veresk_shop.models.Product;
import com.example.veresk_shop.repositories.CartRepository;
import com.example.veresk_shop.repositories.OrderRepository;
import com.example.veresk_shop.repositories.ProductRepository;
import org.aspectj.weaver.ast.Or;
import org.springframework.stereotype.Controller;
import com.example.veresk_shop.models.Person;
import com.example.veresk_shop.security.PersonDetails;
import com.example.veresk_shop.services.PersonService;
import com.example.veresk_shop.services.ProductService;
import com.example.veresk_shop.util.PersonValidator;
import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
public class MainController {

    private final ProductRepository productRepository;
    private final PersonValidator personValidator;
    private final PersonService personService;

    private final ProductService productService;

    private final CartRepository cartRepository;

    private  final OrderRepository orderRepository;

    public MainController(ProductRepository productRepository, PersonValidator personValidator, PersonService personService, ProductService productService, CartRepository cartRepository, OrderRepository orderRepository) {
        this.productRepository = productRepository;
        this.personValidator = personValidator;
        this.personService = personService;
        this.productService = productService;
        this.cartRepository = cartRepository;
        this.orderRepository = orderRepository;
    }

    @GetMapping("/user/personAccount")
    public String index(Model model) {
        // Получаем объект аутентификации -> с помощью SpringContextHolder обращаемся к контексту и на нем вызываем метод аутентификации. Из сессии текущего пользователя получаем объект, который был положен в данную сессию после аутентификации пользователя
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PersonDetails personDetails = (PersonDetails) authentication.getPrincipal();
        String role = personDetails.getPerson().getRole();
        if (role.equals("ROLE_ADMIN")) {
            return "redirect:/admin";
        }
        model.addAttribute("product", productService.getAllProducts());
        return "/user/personAccount";
    }
//        System.out.println(personDetails.getPerson());
//        System.out.println("ID пользователя: " + personDetails.getPerson().getId());
//        System.out.println("Логин пользователя: " + personDetails.getPerson().getLogin());
//        System.out.println("Пароль пользователя: " + personDetails.getPerson().getPassword());
//        System.out.println(personDetails);


    @GetMapping("/registration")
    public String registration(@ModelAttribute("person") Person person) {
        return "registration";
    }

    @PostMapping("/registration")
    public String resultRegistration(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult) {
        personValidator.validate(person, bindingResult);
        if (bindingResult.hasErrors()) {
            return "registration";
        }
        personService.register(person);
        return "redirect:/user/personAccount";
    }


    @GetMapping("/user/personAccount/product/info/{id}")
    public String infoProduct(@PathVariable("id") int id, Model model) {
        model.addAttribute("product", productService.productById(id));
        return "redirect:/user/product_info";
    }

    //обработка кнопки нажатия на корзину
    @GetMapping("/cart/add/{id}")
    public String addProductInCart(@PathVariable("id") int id, Model model) {
        //ищем продукт добавления по id
        Product product = productService.productById(id);
        //смотрим авторизацию
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PersonDetails personDetails = (PersonDetails) authentication.getPrincipal();
//получаем id из объекта
        int id_person = personDetails.getPerson().getId();
//привязали
        Cart cart = new Cart(id_person, product.getId());
//объект корзины
        cartRepository.save(cart);
        return "redirect:/cart";
    }

    @GetMapping("/cart")
    public String cart(Model model) {
//кто ж в нашу корзину зашёл?
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PersonDetails personDetails = (PersonDetails) authentication.getPrincipal();

        int id_person = personDetails.getPerson().getId();
//конкретная корзина аутентифицированного пользователя
        List<Cart> cartList = cartRepository.findByPersonId(id_person);
//список товаров
        List<Product> productList = new ArrayList<>();
//то, что в корзине
        for (Cart cart : cartList) {
            productList.add(productService.productById(cart.getProductId()));
        }
//итог
        float price = 0;
        for (Product product : productList) {
            price += product.getPrice();
        }
        model.addAttribute("price", price);
        model.addAttribute("cart_product", productList);
        return "/user/cart";

    }

    @GetMapping("/cart/delete/{id}")
    public String deleteProductFromCart(Model model, @PathVariable("id") int id) {
//кто хочет всё удалить?!
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PersonDetails personDetails = (PersonDetails) authentication.getPrincipal();

        int id_person = personDetails.getPerson().getId();
        List<Cart> cartList = cartRepository.findByPersonId(id_person);
        List<Product> productList = new ArrayList<>();
        //то, что в корзине
        for (Cart cart : cartList) {
            productList.add(productService.productById(cart.getProductId()));
        }
        cartRepository.deleteCartByProductId(id);
        return "redirect:/cart";
    }

    @GetMapping("/order/create")
    public String order() {
//кто ж в нашу корзину зашёл?
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PersonDetails personDetails = (PersonDetails) authentication.getPrincipal();

        int id_person = personDetails.getPerson().getId();

        List<Cart> cartList = cartRepository.findByPersonId(id_person);
        List<Product> productList = new ArrayList<>();
        //то, что в корзине
        for (Cart cart : cartList) {
            productList.add(productService.productById(cart.getProductId()));
        }

//итог
        float price = 0;
        for (Product product : productList) {
            price += product.getPrice();
        }
//УНИКАЛЬНОСТЬ СТРОКИ для заказа(полного)
        String uuid = UUID.randomUUID().toString();
        for(Product product:productList){
            Order newOrder = new Order(uuid, product, personDetails.getPerson(), 1, product.getPrice(), Status.Получен);
            orderRepository.save(newOrder);
            cartRepository.deleteCartByProductId(product.getId());
        }
        return "redirect:/orders";
    }
@GetMapping("/orders")
    public  String  orderUser(Model model){
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    PersonDetails personDetails = (PersonDetails) authentication.getPrincipal();
    List<Order> orderList = orderRepository.findByPerson(personDetails.getPerson());
    model.addAttribute("orders", orderList);
    return "/user/orders";
}
}