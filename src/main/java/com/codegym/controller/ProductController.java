package com.codegym.controller;

import com.codegym.model.Product;
import com.codegym.model.ProductForm;
import com.codegym.service.IProductService;
import com.codegym.service.ProductService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
public class ProductController {
    private IProductService productService = new ProductService();

    @Value("${file-upload}")
    private String fileUpload;

    @GetMapping("")
    public String index(@RequestParam(name = "q", required = false) String q, Model model) {
        List<Product> productList;
        if (q == null) {
            productList = productService.getAll();
        } else {
            productList = productService.findByName(q);
        }
        model.addAttribute("productList", productList);
        return "/index";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("product", new Product());
        return "/create";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute ProductForm productForm) {
        MultipartFile multipartFile = productForm.getImage();
        String fileName = multipartFile.getOriginalFilename();
        try {
            FileCopyUtils.copy(productForm.getImage().getBytes(), new File(fileUpload + fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Product product = new Product(productForm.getId(), productForm.getName(), productForm.getPrice(), productForm.getColor(), fileName);
        product.setId((int) (Math.random() * 100));
        productService.save(product);
        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable int id) {
        ModelAndView modelAndView = new ModelAndView("/edit");
        modelAndView.addObject("product", productService.findById(id));
        return modelAndView;
    }

    @PostMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable int id, @ModelAttribute ProductForm productForm) {
        MultipartFile multipartFile = productForm.getImage();
        String fileName = multipartFile.getOriginalFilename();
        try {
            FileCopyUtils.copy(productForm.getImage().getBytes(), new File(fileUpload + fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Product product = new Product(id, productForm.getName(), productForm.getPrice(), productForm.getColor(), fileName);
        productService.update(id, product);
        ModelAndView modelAndView = new ModelAndView("redirect:/");
        return modelAndView;
    }

    @GetMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable int id) {
        ModelAndView modelAndView = new ModelAndView("/delete");
        modelAndView.addObject("product", productService.findById(id));
        return modelAndView;
    }

    @PostMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable int id, @ModelAttribute Product product) {
        ModelAndView modelAndView = new ModelAndView("redirect:/");
        productService.remove(id);
        return modelAndView;
    }

    @GetMapping("/view/{id}")
    public ModelAndView view(@PathVariable int id) {
        ModelAndView modelAndView = new ModelAndView("/view");
        modelAndView.addObject("product", productService.findById(id));
        return modelAndView;
    }
}
