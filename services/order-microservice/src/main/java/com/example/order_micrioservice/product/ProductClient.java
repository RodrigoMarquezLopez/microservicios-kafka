package com.example.order_micrioservice.product;

import com.example.order_micrioservice.exception.BussinesException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductClient {
   @Value("${application.config.product-url}")
   private String productUrl;
   private final RestTemplate restTemplate;

   public List<PurchaseResponse> purchaseProducts(List<PurchaseRequest> requests) {
      HttpHeaders headers = new HttpHeaders();
      headers.set(HttpHeaders.CONTENT_TYPE, "application/json");
      HttpEntity<List<PurchaseRequest>> request = new HttpEntity<>(requests, headers);
      ParameterizedTypeReference<List<PurchaseResponse>> responseType =
              new ParameterizedTypeReference<List<PurchaseResponse>>() {};
      ResponseEntity<List<PurchaseResponse>> responseEntity = restTemplate
              .exchange(productUrl+"/purchase",
                      HttpMethod.POST,
                      request,responseType);
      if(responseEntity.getStatusCode().isError()){
         throw new BussinesException("Ha ocurrido un error al comprar el producto"+responseEntity.getStatusCode());
      }
      return responseEntity.getBody();
   }
}
