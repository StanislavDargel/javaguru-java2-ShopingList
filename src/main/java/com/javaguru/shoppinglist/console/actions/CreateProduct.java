package com.javaguru.shoppinglist.console.actions;

import com.javaguru.shoppinglist.console.productparameters.ProductParameters;
import com.javaguru.shoppinglist.dto.ProductDTO;
import com.javaguru.shoppinglist.productutils.ProductDataNormalizer;
import com.javaguru.shoppinglist.productutils.ProductInfo;
import com.javaguru.shoppinglist.service.ProductService;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Order(0)
public class CreateProduct implements ActionMenu {
    private final ProductService service;
    private final List<ProductParameters> productParameters;
    private final ProductInfo productInfo;
    private final ProductDataNormalizer normalizer;

    public CreateProduct(ProductService service,
                         List<ProductParameters> productParameters,
                         ProductInfo productInfo,
                         ProductDataNormalizer normalizer) {
        this.service = service;
        this.productParameters = productParameters;
        this.productInfo = productInfo;
        this.normalizer = normalizer;
    }

    @Override
    public void action() {
        System.out.println("Product creation menu.");
        ProductDTO productDTO = new ProductDTO();
        productParameters.forEach(param -> param.inputParameter(productDTO));
        ProductDTO savedProduct = service.saveProduct(productDTO);
        normalizer.normalize(savedProduct);
        String savedProductInfo = productInfo.print(savedProduct);
        System.out.println(savedProductInfo);
    }

    @Override
    public String actionName() {
        return "Create product";
    }
}
