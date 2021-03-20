package com.example.demo.controllers;

import com.example.demo.model.Category;
import com.example.demo.model.LoginUser;
import com.example.demo.model.Product;
import com.example.demo.service.LoginUser.ILoginUserService;
import com.example.demo.service.category.ICategoryService;
import com.example.demo.service.product.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import sun.rmi.runtime.Log;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/")
@Validated
public class ProductControllers {

    @Autowired
    private IProductService productService;

    @Autowired
    private ICategoryService categoryService;

    @Autowired
    private ILoginUserService loginUserService;

    @ModelAttribute("categoryList")
    private List<Category> sendCategoryList(){
        return categoryService.findALl();
    }

    @ModelAttribute("category")
    private Category sendCategory(){
        return new Category();
    }

    @ModelAttribute("currentUser")
    private LoginUser getCurrentUser(){
        return loginUserService.getCurrentUser();
    }


    @GetMapping("/list")
    public ModelAndView getListProduct(@PageableDefault(size = 2)Pageable pageable){

        ModelAndView modelAndView = new ModelAndView("list","productList",productService.findALl(pageable));
        return modelAndView;
    }
//    @GetMapping("/list")
//    public ResponseEntity<List<Product>> getListProduct(){
//
//         List<Product> productList = productService.findALl();
//         return new ResponseEntity<>(productList, HttpStatus.OK);
//    }
@GetMapping("/create")
public ModelAndView showCreateForm() {
    ModelAndView modelAndView = new ModelAndView("create");
    modelAndView.addObject("product", new Product());
    return modelAndView;
}

    @PostMapping("/create")
    public ModelAndView createProduct(@Validated @ModelAttribute Product product, BindingResult bindingResult) {
//add dateTime to product
//        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        if (bindingResult.hasErrors()) {
            return new ModelAndView("create");
        } else {
            Date date = new Date();
            product.setDate_time(date);

            productService.save(product);
            return new ModelAndView("redirect:/list");

        }

    }

    @GetMapping("/update/{id}")
    public ModelAndView showUpdateForm(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("create");

        Product product = productService.findById(id);

        modelAndView.addObject("product", product);
        return modelAndView;


    }

    @PostMapping("/update/{id}")
    public ModelAndView updateProduct(@Validated @ModelAttribute Product product, BindingResult bindingResult) {

        if (bindingResult.hasFieldErrors()) {
            return new ModelAndView("create");
        } else {
            Date date = new Date();
            product.setDate_time(date);

            productService.save(product);
            return new ModelAndView("redirect:/list");
        }

    }

    @GetMapping("/delete/{id}")
    public ModelAndView showDelete(@PathVariable Long id)  {
        ModelAndView modelAndView = new ModelAndView("delete");
        return modelAndView.addObject("product", productService.findById(id));
    }

    @PostMapping("/delete/{id}")
    public ModelAndView deleteProduct(@PathVariable Long id) {
        productService.deleteById(id);
        return new ModelAndView("redirect:/list");
    }

    @PostMapping("/findByName")
    public ModelAndView findByName(@PageableDefault(size = 2)Pageable pageable,@RequestParam String name) {
        String keyWord = "%" + name + "%";
        ModelAndView modelAndView = new ModelAndView("list");
        modelAndView.addObject("productList", productService.findByName(keyWord,pageable));
        return modelAndView;
    }

    @PostMapping("/findByCategory")
    public ModelAndView findByCategory(@PageableDefault(size = 2)Pageable pageable, @ModelAttribute Category category) {
        ModelAndView modelAndView = new ModelAndView("list");

        //find by id
        //Long id = category.getId();
        // modelAndView.addObject("productList",  productService1.findAllByCategory(id));

        //find by object using repository default method
        modelAndView.addObject("productList", productService.findByCategory(category,pageable));

        return modelAndView;
    }


    @GetMapping("/admin")
    public ModelAndView admin(){
        ModelAndView modelAndView = new ModelAndView("admin");
        return modelAndView;
    }
    @GetMapping("/user")
    public ModelAndView pUser(){
        ModelAndView modelAndView = new ModelAndView("user");
        return modelAndView;
    }
    @PostMapping("/khongcoquyen")
    public ModelAndView p403(){
        ModelAndView modelAndView =  new ModelAndView("khongcoquyen");
        return modelAndView;
    }
//
//    @GetMapping("/get5Latest")
//    public ModelAndView get5LatestProduct() {
//        ModelAndView modelAndView = new ModelAndView("listWithoutPageable");
//        modelAndView.addObject("productList", productService1.get5LatestProduct());
//        return modelAndView;
//    }
//
//    @GetMapping("/getTop5expensive")
//    public ModelAndView getTop5expensive() {
//        ModelAndView modelAndView = new ModelAndView("listWithoutPageable");
//        modelAndView.addObject("productList", productService1.getTop5expensive());
//        return modelAndView;
//    }
//
//    @GetMapping("/getTotalMoneyRemainInWareHouse")
//    public ModelAndView getTotalMoneyRemainInWareHouse() {
//        ModelAndView modelAndView = new ModelAndView("totalMoney");
//        List<Product> productList = productService.findALl();
//        Long totalMoney = productService1.getTotalMoneyRemainInWareHouse();
//        modelAndView.addObject("productList",productList);
//        modelAndView.addObject("totalMoney",totalMoney);
//        return modelAndView;
//    }
//
//    @GetMapping("/*")
//    public String returnFailURL() throws PageNotFoundException {
//        throw new PageNotFoundException();
//
//    }
//
//    @GetMapping("/*/*")
//    public String returnFailURL1() throws PageNotFoundException {
//        throw new PageNotFoundException();
//
//    }

}
