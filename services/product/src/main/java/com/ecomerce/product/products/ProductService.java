package com.ecomerce.product.products;

import com.ecomerce.product.exception.ProductPurchaseException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper mapper;

    public Integer createProduct(ProductRequest request) {
        Product product = mapper.toProduct(request);
        return productRepository.save(product).getId();
    }

    public List<ProductPurchaseResponse> purchaseProducts(List<ProductPurchaseRequest> requestList) {
        List<Integer> productsIds = requestList
                .stream()
                .map(ProductPurchaseRequest::productId)
                .toList();
        List<Product> storedProducts = productRepository.findAllByIdInOrderById(productsIds);
        if(productsIds.size() != storedProducts.size()){
            throw  new ProductPurchaseException("One or more products doesn't exist");
        }
        List<ProductPurchaseRequest> storedPurchase = requestList
                .stream()
                .sorted(Comparator.comparing(ProductPurchaseRequest::productId))
                .toList();
        List<ProductPurchaseResponse> purchaseResponses = new ArrayList<ProductPurchaseResponse>();
        for (int i = 0; i < storedPurchase.size(); i++) {
            Product product = storedProducts.get(i);
            ProductPurchaseRequest productRequest = storedPurchase.get(i);
            if(product.getAvailableQuantity() < productRequest.quantity()){
                throw new ProductPurchaseException("Insufficient quantity for product ID " + productRequest.productId());
            }
             double newAvailableQuantity = product.getAvailableQuantity() - productRequest.quantity();
            product.setAvailableQuantity(newAvailableQuantity);
            productRepository.save(product);
            purchaseResponses.add(mapper.toProductPurchaseResponse(product,productRequest.quantity()));
        }
        return purchaseResponses;
    }

    public ProductResponse findById(Integer productId) {
        return productRepository.findById(productId).map(mapper::toProductResponse)
                .orElseThrow(() -> new EntityNotFoundException("Product not found ID:: " + productId));
    }

    public List<ProductResponse> findAll() {
        return productRepository.findAll()
                .stream()
                .map(mapper::toProductResponse)
                .toList();
    }
}
