package com.lti.controller;

import com.lti.dao.ProductOrderDAO;
import com.lti.dto.ProductOrderDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
@CrossOrigin(origins = "*")
public class ProductOrderController {

    @Autowired
    private ProductOrderDAO productOrderDAO;

    @PostMapping("/create")
    public String orderProduct(@RequestBody ProductOrderDTO productOrderDTO){
        return productOrderDAO.insertOrder(productOrderDTO);
    }

    @GetMapping("/status/{orderId}")
    public int fetchOrderStatus(@PathVariable int orderId){
        return productOrderDAO.fetchStatus(orderId);
    }

}
