package com.ecommerce.customer.controller;

import com.ecommerce.library.model.Customer;
import com.ecommerce.library.model.Product;
import com.ecommerce.library.model.ShoppingCart;
import com.ecommerce.library.service.CustomerService;
import com.ecommerce.library.service.ProductService;
import com.ecommerce.library.service.ShoppingCartService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.nio.file.attribute.UserPrincipalLookupService;
import java.security.Principal;

@Controller
public class CartController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private ShoppingCartService cartService;

    @Autowired
    private ProductService productService;

    @GetMapping("/cart")
    public String cart(Model model, Principal principal){
        if (principal==null){
            return "redirect:/login";
        }
        String username = principal.getName();
        Customer customer = customerService.findByUsername(principal.getName());
        ShoppingCart shoppingCart = customer.getShoppingCart();
        if (shoppingCart == null){
            model.addAttribute("check","No item in your cart");
        }
        model.addAttribute("shoppingCart",shoppingCart);
        return "cart";
    }

    @PostMapping("/add-to-cart")
    public String addItemToCart(
            @RequestParam("id") Long productId,
            @RequestParam(value="quantity",required = false,defaultValue = "1") int quantity,
            Principal principal,
            Model model,
            HttpServletRequest request){

        if (principal == null){
            return "redirect:/login";
        }
        Product product = productService.getproductById(productId);
        String username = principal.getName();
        Customer customer = customerService.findByUsername(username);

        ShoppingCart cart = cartService.addItemCart(product,quantity,customer);
        return "redirect:"+ request.getHeader("Referer");
    }

    @RequestMapping(value = "/update-cart",method = RequestMethod.POST,params = "action=update")
    public String updateCart(@RequestParam("quantity") int quantity,
                             @RequestParam("id") Long productId,
                             Model model,
                             Principal principal){

        if (principal == null){
            return "redirect:/login";
        }else {
            String username = principal.getName();
            Customer customer = customerService.findByUsername(username);
            Product product = productService.getproductById(productId);
            ShoppingCart cart = cartService.updateItemCart(product,quantity,customer);

            model.addAttribute("shoppingCart",cart);
            return "redirect:/cart";
        }
    }

    @RequestMapping(value = "/update-cart",method = RequestMethod.POST,params = "action=delete" )
    public String deleteItemFromCart(@RequestParam("id") Long productId,
                                     Model model,
                                     Principal principal){
        if (principal == null){
            return "redirect:/login";
        }else{
            String username = principal.getName();
            Customer customer = customerService.findByUsername(username);
            Product product = productService.getproductById(productId);
            ShoppingCart cart = cartService.deleteItemFromCart(product,customer);
            model.addAttribute("shoppingCart",cart);
            return "redirect:/cart";
        }
    }
}
