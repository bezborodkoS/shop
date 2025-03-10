package com.example.shop.service;

import com.example.shop.converter.HistoryConverter;
import com.example.shop.converter.ProductConverter;
import com.example.shop.model.dto.HistoryDto;
import com.example.shop.model.dto.ProductDto;
import com.example.shop.model.enity.History;
import com.example.shop.model.enity.Product;
import com.example.shop.model.enity.User;
import com.example.shop.repository.HistoryRepository;
import com.example.shop.repository.ProductRepository;
import com.example.shop.repository.UserRepository;
import com.example.shop.security.JwtUtil;
import com.example.shop.service.crudMethodsForWorkModelDb.ProductService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserMethodsService {
    private final UserRepository userRepository;
    private final ProductService productService;
    private final ProductRepository productRepository;
    private final JwtUtil jwtUtil;
    private final HistoryConverter historyConverter;
    private final ProductConverter productConverter;
    private final HistoryRepository historyRepository;

    public UserMethodsService(UserRepository userRepository, ProductService productService,
                              ProductRepository productRepository, JwtUtil jwtUtil,
                              HistoryRepository historyRepository, HistoryConverter historyConverter, ProductConverter productConverter) {
        this.userRepository = userRepository;
        this.productService = productService;
        this.productRepository = productRepository;
        this.jwtUtil = jwtUtil;
        this.historyRepository = historyRepository;
        this.historyConverter = historyConverter;
        this.productConverter = productConverter;
    }

    public List<ProductDto> getProductInBeg(String token) {
        User user = userRepository.findByUsername(jwtUtil.extractUserName(token)).get();
        return productConverter.toDTOList(user.getProducts());
    }

    public List<HistoryDto> getAllHistoryForUser(String token) {
        return historyConverter.toDTOList(historyRepository.findAllByUser_Username(jwtUtil.extractUserName(token)));
    }

    public boolean addMoney(String token, BigDecimal money) {
        User user = userRepository.findByUsername(jwtUtil.extractUserName(token)).get();
        user.setWallet(user.getWallet().add(money));
        userRepository.save(user);
        return true;
    }

    //    TODO переделать
    public boolean buyProduct(String token) {
        User user = userRepository.findByUsername(jwtUtil.extractUserName(token)).get();
        double d = 0;
        for (Product product : user.getProducts()) {
            d = d + product.getPrice().doubleValue();
        }

        if (user.getWallet().compareTo(BigDecimal.valueOf(d)) != -1) {
            user.setWallet(user.getWallet().subtract(BigDecimal.valueOf(d)));
            createHistory(user.getProducts(), user);
            user.setProducts(new ArrayList<>());
            userRepository.save(user);
            return true;
        }
        return false;
    }

    public boolean addProductToBeg(String token, String productCode) {
        User user = userRepository.findByUsername(jwtUtil.extractUserName(token)).get();
        Product product = productRepository.findByProductCode(productCode).get();
        List<Product> productSet = user.getProducts();
        productSet.add(product);
        user.setProducts(productSet);
        userRepository.save(user);
        return true;
    }

    private void createHistory(List<Product> productList, User user) {
        for (Product product : productList) {
            History history = new History();
            history.setCategory(product.getCategory().getName());
            history.setNameProduct(product.getName());
            history.setFabricator(product.getFabricator());
            history.setProductCode(product.getProductCode());
            history.setUser(user);
            historyRepository.save(history);
        }
    }
}
