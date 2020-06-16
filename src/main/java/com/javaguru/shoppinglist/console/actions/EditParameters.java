package com.javaguru.shoppinglist.console.actions;

import com.javaguru.shoppinglist.console.productparameters.ProductParameters;
import com.javaguru.shoppinglist.dto.ProductDTO;
import com.javaguru.shoppinglist.service.ValidationService;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

@Component
@Order(1)
public class EditParameters implements ActionMenu {
    private final ValidationService service;
    private final List<ProductParameters> productParameters;

    public EditParameters(ValidationService service, List<ProductParameters> productParameters) {
        this.service = service;
        this.productParameters = productParameters;
    }

    @Override
    public void action() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Editors Menu. Enter product id: ");
        Long inputID = Long.parseLong(scanner.nextLine());
        ProductDTO foundedProductDTO = service.findById(inputID);
        String foundedProductInfo = service.printProductInfo(foundedProductDTO);
        System.out.println(foundedProductInfo);
        for (ProductParameters productParameter : productParameters) {
            System.out.print("Edit " + productParameter + " (Y/N)?");
            String inputAnswer = scanner.nextLine();
            if (isAgree(inputAnswer)) {
                productParameter.inputParameter(foundedProductDTO);
            }
        }
        ProductDTO changedProduct = service.changeParameters(inputID, foundedProductDTO);
        String changedProductInfo = service.printProductInfo(changedProduct);
        System.out.println(changedProductInfo);
    }

    @Override
    public String toString() {
        return "Edit parameters";
    }
}
