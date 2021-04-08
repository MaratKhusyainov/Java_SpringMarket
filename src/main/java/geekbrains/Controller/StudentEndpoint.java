package geekbrains.Controller;

import geekbrains.Service.ProductServiceImpl;
import geekbrains.Soap.GetAllProductsRequest;
import geekbrains.Soap.GetAllProductsResponse;
import geekbrains.Soap.GetProductByIdRequest;
import geekbrains.Soap.GetProductByIdResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
@RequiredArgsConstructor
public class StudentEndpoint {
    private static final String NAMESPACE_URI = "http://www.khusyainovmar.com/spring/ws/products";
    private final ProductServiceImpl productService;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getProductByIdRequest")
    @ResponsePayload
    public GetProductByIdResponse getProductById(@RequestPayload GetProductByIdRequest request) {
        GetProductByIdResponse response = new GetProductByIdResponse();
        response.setProducts(productService.getById(request.getId()));
        return response;
    }


    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAllProductsRequest")
    @ResponsePayload
    public GetAllProductsResponse getAllProducts(@RequestPayload GetAllProductsRequest request) {
        GetAllProductsResponse response = new GetAllProductsResponse();
        productService.getAllProductsSoap().forEach(response.getProducts()::add);
        return response;
    }
}