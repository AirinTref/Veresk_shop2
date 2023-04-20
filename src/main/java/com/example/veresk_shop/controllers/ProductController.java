package com.example.veresk_shop.controllers;

import com.example.veresk_shop.services.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import com.example.veresk_shop.repositories.ProductRepository;

@Controller
@RequestMapping("/product")
public class ProductController {

    private  final  ProductService productService;
    private  final  ProductRepository productRepository;

    public ProductController(ProductService productService, ProductRepository productRepository) {
        this.productService = productService;
        this.productRepository = productRepository;
    }


    //Метод, позвол. инф о всех товарах-список
    @GetMapping("")
    public String getAllProducts(Model model) {
        model.addAttribute("product", productService.getAllProducts());
        return "/product/product";
    }

    //метод перехода ссылок на каждый товар
    //@PathVariable-аннотация ,извлекающ. Id или др.переменную

    @GetMapping("/info/{id}")
    public String infoProduct(@PathVariable("id") int id, Model model) {
        model.addAttribute("product", productService.productById(id));
        return "/product/product_info";
    }

//поиск товара на страницу Product
// tea-1,grass-2, flower-4, salt-5
//
////проверяем пустоту категорий,иначе...
////ASC-по возрастанию
    @PostMapping("/search")
    public String productSearch(@RequestParam("search") String search, @RequestParam("ot") String ot, @RequestParam("do") String Do, @RequestParam(value = "price", required = false, defaultValue = "") String price, @RequestParam(value = "contract", required = false, defaultValue = "") String contract, Model model){
        model.addAttribute("product", productService.getAllProducts());


        if(!ot.isEmpty() & !Do.isEmpty()) {
            if (!price.isEmpty()) {
                if (price.equals("sorted_by_ascending_price")) {
                    if (!contract.isEmpty()) {
                        if (contract.equals("tea")) {
                            model.addAttribute("search_product", productRepository.findByTitleAndCategoryOrderByPriceAsc(search.toLowerCase(), Float.parseFloat(ot), Float.parseFloat(Do), 1));
                        } else if (contract.equals("grass")) {
                            model.addAttribute("search_product", productRepository.findByTitleAndCategoryOrderByPriceAsc(search.toLowerCase(), Float.parseFloat(ot), Float.parseFloat(Do), 2));
                        } else if (contract.equals("flower")) {
                            model.addAttribute("search_product", productRepository.findByTitleAndCategoryOrderByPriceAsc(search.toLowerCase(), Float.parseFloat(ot), Float.parseFloat(Do), 4));
                        } else if (contract.equals("salt")) {
                            model.addAttribute("search_product", productRepository.findByTitleAndCategoryOrderByPriceAsc(search.toLowerCase(), Float.parseFloat(ot), Float.parseFloat(Do), 5));
                        }
                    }else {
                        model.addAttribute("search_product", productRepository.findByTitleContainingIgnoreCase(search));
                    }}

                    //DESC-по убыванию

                    else if (price.equals("sorted_by_descending_price")) {
                        if (!contract.isEmpty()) {
                            System.out.println(contract);

                            if (contract.equals("tea")) {
                                model.addAttribute("search_product", productRepository.findByTitleAndCategoryOrderByPriceDesc(search.toLowerCase(), Float.parseFloat(ot), Float.parseFloat(Do), 1));
                            } else if (contract.equals("grass")) {
                                model.addAttribute("search_product", productRepository.findByTitleAndCategoryOrderByPriceDesc(search.toLowerCase(), Float.parseFloat(ot), Float.parseFloat(Do), 2));
                            } else if (contract.equals("flower")) {
                                model.addAttribute("search_product", productRepository.findByTitleAndCategoryOrderByPriceDesc(search.toLowerCase(), Float.parseFloat(ot), Float.parseFloat(Do), 4));
                            } else if (contract.equals("salt")) {
                                model.addAttribute("search_product", productRepository.findByTitleAndCategoryOrderByPriceDesc(search.toLowerCase(), Float.parseFloat(ot), Float.parseFloat(Do), 5));
                            }
                        }
                    } else {
                        model.addAttribute("search_product", productRepository.findByTitleAndPriceGreaterThanEqualAndPriceLessThanEqual(search.toLowerCase(), Float.parseFloat(ot), Float.parseFloat(Do)));
                    }
                }
                } else {
                    model.addAttribute("search_product", productRepository.findByTitleContainingIgnoreCase(search));
                }

                model.addAttribute("value_search", search);
                model.addAttribute("value_price_ot", ot);
                model.addAttribute("value_price_do", Do);
                return "/product/product";
            }






//Поиск для страницы personAccount

    @PostMapping("/user/personAccount/search2")
    public String productSearch2(@RequestParam("search") String search, @RequestParam("ot") String ot, @RequestParam("do") String Do, @RequestParam(value = "price", required = false, defaultValue = "") String price, @RequestParam(value = "contract", required = false, defaultValue = "") String contract, Model model){
        model.addAttribute("product", productService.getAllProducts());


        if(!ot.isEmpty() & !Do.isEmpty()){
            if (!price.isEmpty()){
                if (price.equals("sorted_by_ascending_price2")) {
                    if (!contract.isEmpty()) {
                        if (contract.equals("tea")) {
                            model.addAttribute("search_product2", productRepository.findByTitleAndCategoryOrderByPriceAsc(search.toLowerCase(), Float.parseFloat(ot), Float.parseFloat(Do), 1));
                        } else if (contract.equals("grass")) {
                            model.addAttribute("search_product2", productRepository.findByTitleAndCategoryOrderByPriceAsc(search.toLowerCase(), Float.parseFloat(ot), Float.parseFloat(Do), 2));
                        } else if (contract.equals("flower")) {
                            model.addAttribute("search_product2", productRepository.findByTitleAndCategoryOrderByPriceAsc(search.toLowerCase(), Float.parseFloat(ot), Float.parseFloat(Do), 4));
                        } else if (contract.equals("salt")) {
                            model.addAttribute("search_product2", productRepository.findByTitleAndCategoryOrderByPriceAsc(search.toLowerCase(), Float.parseFloat(ot), Float.parseFloat(Do), 5));
                        }
                    }else {
                        model.addAttribute("search_product2", productRepository.findByTitleOrderByPriceAsc(search.toLowerCase(), Float.parseFloat(ot), Float.parseFloat(Do)));
                    }
                //DESC-по убыванию

            }else if (price.equals("sorted_by_descending_price2")) {
                    if (!contract.isEmpty()) {
                        System.out.println(contract);

                        if (contract.equals("tea")) {
                            model.addAttribute("search_product2", productRepository.findByTitleAndCategoryOrderByPriceDesc(search.toLowerCase(), Float.parseFloat(ot), Float.parseFloat(Do), 1));
                        } else if (contract.equals("grass")) {
                            model.addAttribute("search_product2", productRepository.findByTitleAndCategoryOrderByPriceDesc(search.toLowerCase(), Float.parseFloat(ot), Float.parseFloat(Do), 2));
                        } else if (contract.equals("flower")) {
                            model.addAttribute("search_product2", productRepository.findByTitleAndCategoryOrderByPriceDesc(search.toLowerCase(), Float.parseFloat(ot), Float.parseFloat(Do), 4));
                        } else if (contract.equals("salt")) {
                            model.addAttribute("search_product2", productRepository.findByTitleAndCategoryOrderByPriceDesc(search.toLowerCase(), Float.parseFloat(ot), Float.parseFloat(Do), 5));
                        }

                }  else {
                        model.addAttribute("search_product2", productRepository.findByTitleOrderByPriceDesc(search.toLowerCase(), Float.parseFloat(ot), Float.parseFloat(Do)));}
            }
        } else {
                model.addAttribute("search_product2", productRepository.findByTitleAndPriceGreaterThanEqualAndPriceLessThanEqual(search.toLowerCase(), Float.parseFloat(ot), Float.parseFloat(Do)));
            }
        } else {
            model.addAttribute("search_product2", productRepository.findByTitleContainingIgnoreCase(search));
        }

        model.addAttribute("value_search2", search);
        model.addAttribute("value_price_ot2", ot);
        model.addAttribute("value_price_do2", Do);
        return "/user/personAccount";
    }


        }

