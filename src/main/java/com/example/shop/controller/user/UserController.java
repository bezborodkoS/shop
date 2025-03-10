package com.example.shop.controller.user;

import com.example.shop.model.dto.HistoryDto;
import com.example.shop.model.dto.ProductDto;
import com.example.shop.security.JwtUtil;
import com.example.shop.service.UserMethodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private final UserMethodsService userMethodsService;


    public UserController(UserMethodsService userMethodsService) {
        this.userMethodsService = userMethodsService;
    }

    @GetMapping("/history")
    public ResponseEntity<List<HistoryDto>> getAllHistoryForUser(@RequestParam(name = "token") String token){
        return new ResponseEntity<>(userMethodsService.getAllHistoryForUser(token),HttpStatus.OK);
    }

    @PostMapping("/addMoney/{money}")
    public ResponseEntity<String> addMoney(@PathVariable BigDecimal money, @RequestParam(name = "token") String token){
        userMethodsService.addMoney(token,money);
        return new ResponseEntity<>("in beg", HttpStatus.OK);
    }

    @PostMapping("/addProductToBeg/{productCode}")
    public ResponseEntity<String> addProductToBeg(@PathVariable String productCode, @RequestParam(name = "token") String token){
        userMethodsService.addProductToBeg(token,productCode);
        return new ResponseEntity<>("to beg",HttpStatus.OK);
    }

    @GetMapping("/getProductInBeg")
    public ResponseEntity<List<ProductDto>> getAllProductsInBeg(@RequestParam(name = "token") String token){
        return new ResponseEntity<>(userMethodsService.getProductInBeg(token),HttpStatus.OK);
    }

    @PostMapping("/buyProducts")
    public ResponseEntity<String> buyProducts(@RequestParam(name = "token") String token){

        return userMethodsService.buyProduct(token)
                ?new ResponseEntity<>("buy all products",HttpStatus.OK)
                : new ResponseEntity<>("не хватает денег", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
