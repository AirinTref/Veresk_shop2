package com.example.veresk_shop.controllers;

import com.example.veresk_shop.enumm.Status;
import com.example.veresk_shop.models.*;
import com.example.veresk_shop.repositories.CategoryRepository;
import com.example.veresk_shop.repositories.PersonRepository;
import com.example.veresk_shop.services.OrderRowService;
import com.example.veresk_shop.services.OrderService;
import com.example.veresk_shop.services.PersonService;
import com.example.veresk_shop.services.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

//@PreAuthorize("hasRole('ADMIN')")
@Controller
public class AdminController {

    private final ProductService productService;

    @Value("${upload.path}")
    private String uploadPath;

    private final CategoryRepository categoryRepository;
    private final PersonService personService;

    public AdminController(ProductService productService, CategoryRepository categoryRepository, PersonService personService, OrderService orderService, OrderRowService orderRowService) {
        this.productService = productService;
        this.categoryRepository = categoryRepository;
        this.personService = personService;
        this.orderService = orderService;
        this.orderRowService = orderRowService;
    }


    @GetMapping("/listPersons")
    public String getAllPersons(Model model) {
        model.addAttribute("persons", personService.getAllPersons());
        return "/admin";
    }
//
//    @GetMapping("/listPerson")
//    public String getAllPersons(Model model) {
//        model.addAttribute("person", personService.getAllPersons());
//        return "/admin";
//    }

    //    @GetMapping("")
//    public String getAllPersons(Model model) {
//        model.addAttribute("product", personService.getAllPersons());
//        return "/product/product";
//    }
//
    @GetMapping("/admin/product/add")
    public String addProduct(Model model) {
        model.addAttribute("product", new Product());
        model.addAttribute("category", categoryRepository.findAll());
        return "product/add_product";
    }

    @PostMapping("/admin/product/add")
    public String addProduct(@ModelAttribute("product") @Valid Product product, BindingResult bindingResult, @RequestParam("file_one") MultipartFile file_one, @RequestParam("file_two") MultipartFile file_two, @RequestParam("file_three") MultipartFile file_three, @RequestParam("file_four") MultipartFile file_four, @RequestParam("file_five") MultipartFile file_five, @RequestParam("category") int category, Model model) throws IOException {
        Category category_db = (Category) categoryRepository.findById(category).orElseThrow();
        System.out.println(category_db.getName());
        if (bindingResult.hasErrors()) {
            model.addAttribute("category", categoryRepository.findAll());
            return "product/add_product";
        }

        if (file_one != null) {
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }
            String uuidFile = UUID.randomUUID().toString();
            String resultFileName = uuidFile + "." + file_one.getOriginalFilename();
            file_one.transferTo(new File(uploadPath + "/" + resultFileName));
            Image image = new Image();
            image.setProduct(product);
            image.setFileName(resultFileName);
            product.addImageToProduct(image);

        }

        if (file_two != null) {
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }
            String uuidFile = UUID.randomUUID().toString();
            String resultFileName = uuidFile + "." + file_two.getOriginalFilename();
            file_two.transferTo(new File(uploadPath + "/" + resultFileName));
            Image image = new Image();
            image.setProduct(product);
            image.setFileName(resultFileName);
            product.addImageToProduct(image);
        }

        if (file_three != null) {
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }
            String uuidFile = UUID.randomUUID().toString();
            String resultFileName = uuidFile + "." + file_three.getOriginalFilename();
            file_three.transferTo(new File(uploadPath + "/" + resultFileName));
            Image image = new Image();
            image.setProduct(product);
            image.setFileName(resultFileName);
            product.addImageToProduct(image);
        }

        if (file_four != null) {
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }
            String uuidFile = UUID.randomUUID().toString();
            String resultFileName = uuidFile + "." + file_four.getOriginalFilename();
            file_four.transferTo(new File(uploadPath + "/" + resultFileName));
            Image image = new Image();
            image.setProduct(product);
            image.setFileName(resultFileName);
            product.addImageToProduct(image);
        }

        if (file_five != null) {
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }
            String uuidFile = UUID.randomUUID().toString();
            String resultFileName = uuidFile + "." + file_five.getOriginalFilename();
            file_five.transferTo(new File(uploadPath + "/" + resultFileName));
            Image image = new Image();
            image.setProduct(product);
            image.setFileName(resultFileName);
            product.addImageToProduct(image);
        }
        productService.saveProduct(product, category_db);
        return "redirect:/admin";
    }


    @GetMapping("/admin")
    public String admin(Model model) {
        model.addAttribute("product", productService.getAllProducts());
        return "admin";
    }

    @GetMapping("/admin/product/delete/{id}")
    public String deleteProduct(@PathVariable("id") int id) {
        productService.deleteProduct(id);
        return "redirect:/admin";
    }

    @GetMapping("/admin/product/edit/{id}")
    public String editProduct(Model model, @PathVariable("id") int id) {
        model.addAttribute("product", productService.productById(id));
        model.addAttribute("category", categoryRepository.findAll());
        return "product/edit_product";


    }

    @PostMapping("/admin/product/edit/{id}")
    public String editProduct(@ModelAttribute("product") @Valid Product product, BindingResult bindingResult, @PathVariable("id") int id, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("category", categoryRepository.findAll());
            return "product/edit_product";
        }
        productService.updateProduct(id, product);
        return "redirect:/admin";
    }


    private final OrderService orderService;
    private final OrderRowService orderRowService;

    //вывод всех заказов
    @GetMapping("/admin/orders/all")
    public String getPageAllOrders(Model model) {
        List<Order> allOrders = orderService.getAllOrders();
        model.addAttribute("orders", allOrders);
        model.addAttribute("typeOrder", "All");
        return "/order/orders";
    }

    //новые заказы
    @GetMapping("/admin/orders/new")
    public String getPageNewOrders(Model model) {
        List<Order> newOrders = orderService.getAllOrdersByOrderStatus(Status.NEW);
        model.addAttribute("orders", newOrders);
        model.addAttribute("isNewOrders", true);
        model.addAttribute("typeOrder", "New");
        return "/order/orders";
    }

    //в работе- принятые
    @GetMapping("/admin/orders/accepted")
    public String getPageAcceptedOrders(Model model) {
        List<Order> acceptedOrders = orderService.getAllOrdersByOrderStatus(Status.ACCEPTED);
        model.addAttribute("orders", acceptedOrders);
        model.addAttribute("isNewOrders", false);
        model.addAttribute("isAcceptedOrders", true);
        model.addAttribute("typeOrder", "Accepted");
        return "/order/orders";
    }

    //отклонённые(например, закончился товар)
    @GetMapping("/admin/orders/rejected")
    public String getPageRejectedOrders(Model model) {
        List<Order> rejectedOrders = orderService.getAllOrdersByOrderStatus(Status.REJECTED);
        model.addAttribute("orders", rejectedOrders);
        model.addAttribute("isNewOrders", false);
        model.addAttribute("typeOrder", "Rejected");
        return "/order/orders";
    }

    //завершенные
    @GetMapping("/admin/orders/finished")
    public String getPageFinishedOrders(Model model) {
        List<Order> finishedOrders = orderService.getAllOrdersByOrderStatus(Status.FINISHED);
        model.addAttribute("orders", finishedOrders);
        model.addAttribute("isNewOrders", false);
        model.addAttribute("typeOrder", "Finished");
        return "/order/orders";
    }

    //обновление статуса заказа -принят
    @GetMapping("/admin/orders/{orderId}/accept")
    public String acceptOrder(@PathVariable Integer orderId) {
        orderService.updateOrderStatusById(Status.ACCEPTED, orderId);
        return "redirect:/admin/orders/all";
    }

    //обновление статуса заказа -отказ
    @GetMapping("/admin/orders/{orderId}/reject")
    public String rejectOrder(@PathVariable Integer orderId) {
        orderService.updateOrderStatusById(Status.REJECTED, orderId);
        return "redirect:/admin/orders/all";
    }

    //обновление статуса заказа -завершен
    @GetMapping("/admin/orders/{orderId}/finished")
    public String finishOrder(@PathVariable Integer orderId) {
        orderService.updateOrderStatusById(Status.FINISHED, orderId);
        return "redirect:/admin/orders/all";
    }

    //просмотр заказа
    @GetMapping("/admin/orders/{orderId}/show")
    public String getPageOrder(@PathVariable Integer orderId, Model model) {
        System.out.println(orderId);
        Order order = orderService.getOrderById(orderId);
        List<OrderRow> orderRows = orderRowService.getRowsByOrderId(orderId);
        model.addAttribute("order", order);
        model.addAttribute("orderRows", orderRows);
        return "/order/order";
    }
}
