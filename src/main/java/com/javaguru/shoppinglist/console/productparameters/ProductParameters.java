package com.javaguru.shoppinglist.console.productparameters;

import com.javaguru.shoppinglist.dto.ProductDTO;

public interface ProductParameters {
    void inputParameter(ProductDTO productDTO);

    String parameterName();
}
