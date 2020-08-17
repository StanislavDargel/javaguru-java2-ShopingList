package com.javaguru.shoppinglist.console.actions;

import com.javaguru.shoppinglist.console.productparameters.ProductParameters;
import com.javaguru.shoppinglist.dto.ProductDTO;
import com.javaguru.shoppinglist.productutils.ProductDataNormalizer;
import com.javaguru.shoppinglist.productutils.ProductInfo;
import com.javaguru.shoppinglist.service.ProductService;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

@Component
@Order(1)
public class EditParameters implements ActionMenu {
    private final ProductService service;
    private final List<ProductParameters> productParameters;
    private final ProductInfo productInfo;
    private final ProductDataNormalizer normalizer;

    public EditParameters(ProductService service,
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
        Scanner scanner = new Scanner(System.in);
        System.out.println("Editors Menu. Enter product id: ");
        Long inputID = Long.parseLong(scanner.nextLine());
        ProductDTO foundedProductDTO = service.findById(inputID);
        normalizer.normalize(foundedProductDTO);
        String foundedProductInfo = productInfo.print(foundedProductDTO);
        System.out.println(foundedProductInfo);
        for (ProductParameters productParameter : productParameters) {
            System.out.print("Edit " + productParameter.parameterName() + " (Y/N)?");
            String inputAnswer = scanner.nextLine();
            if (isAgree(inputAnswer)) {
                productParameter.inputParameter(foundedProductDTO);
            }
        }
        service.productUpdate(inputID, foundedProductDTO);
        System.out.println("Product updated successfully");
    }

    @Override
    public String actionName() {
        return "Edit parameters";
    }
}
