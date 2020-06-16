package com.javaguru.shoppinglist.console.actions;

import com.javaguru.shoppinglist.console.productparameters.ProductParameters;
import com.javaguru.shoppinglist.dto.ProductDTO;
import com.javaguru.shoppinglist.service.ValidationService;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Order(0)
public class CreateProduct implements ActionMenu {
    private final ValidationService service;
    private final List<ProductParameters> productParameters;

    public CreateProduct(ValidationService service, List<ProductParameters> productParameters) {
        this.service = service;
        this.productParameters = productParameters;
    }

    @Override
    public void action() {
        System.out.println("Product creation menu.");
        ProductDTO productDTO = new ProductDTO();
        productParameters.forEach(param -> param.inputParameter(productDTO));
        ProductDTO savedProduct = service.saveProduct(productDTO);
        String savedProductInfo = service.printProductInfo(savedProduct);
        System.out.println(savedProductInfo);
    }

    @Override
    public String toString() {
        return "Create product";
    }
}
