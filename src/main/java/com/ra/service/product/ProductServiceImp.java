package com.ra.service.product;

import com.ra.model.dto.request.ProductDTO;
import com.ra.model.dto.response.ProductResponse;
import com.ra.model.entity.Catalog;
import com.ra.model.entity.Product;
import com.ra.repository.CatalogRepository;
import com.ra.repository.ProductRepository;
import com.ra.service.UploadFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImp implements ProductService {
    private final ProductRepository productRepository;
    private final CatalogRepository catalogRepository;
    private final UploadFileService uploadFileService;

    @Autowired
    public ProductServiceImp(ProductRepository productRepository, CatalogRepository catalogRepository, UploadFileService uploadFileService) {
        this.productRepository = productRepository;
        this.catalogRepository = catalogRepository;
        this.uploadFileService = uploadFileService;
    }

    @Override
    public List<ProductResponse> getAll() {
        List<Product> productList = productRepository.findAll();
        return productList.stream().map(product -> new ProductResponse(
                product.getProductId(),
                product.getProductName(),
                product.getPrice(),
                product.getDescription(),
                product.getImgURL(),
                product.isStatus(),
                product.getCatalog().getCatName()
        )).collect(Collectors.toList());
    }

    @Override
    public ProductResponse save(ProductDTO productDTO) {
        Catalog catalog = catalogRepository.findById(productDTO.getCatalogId()).orElse(null);
        String imgURL = uploadFileService.uploadFileToLocal(productDTO.getImage());
        Product product = Product.builder()
                .productName(productDTO.getProductName())
                .description(productDTO.getDescription())
                .imgURL(imgURL)
                .price(productDTO.getPrice())
                .catalog(catalog)
                .status(true)
                .build();
        Product productNew = productRepository.save(product);
        return ProductResponse.builder()
                .productId(productNew.getProductId())
                .productName(productNew.getProductName())
                .price(productNew.getPrice())
                .description(productNew.getDescription())
                .imgURL(productNew.getImgURL())
                .status(productNew.isStatus())
                .catalogName(productNew.getCatalog().getCatName())
                .build();
    }

    @Override
    public ProductResponse findById(long productId) {
        Product product = productRepository.findById(productId).orElse(null);
        if (product != null)
            return ProductResponse.builder()
                    .productId(product.getProductId())
                    .productName(product.getProductName())
                    .price(product.getPrice())
                    .description(product.getDescription())
                    .imgURL(product.getImgURL())
                    .status(product.isStatus())
                    .catalogName(product.getCatalog().getCatName())
                    .build();
        return null;
    }

    @Override
    public ProductResponse update(long productId, ProductDTO productDTO) {
        Product product = productRepository.findById(productId).orElse(null);
        if (product != null && product.getImgURL() != null)
            uploadFileService.deleteFileFromLocal(product.getImgURL());
        String imgURL = uploadFileService.uploadFileToLocal(productDTO.getImage());
        Catalog catalog = catalogRepository.findById(productDTO.getCatalogId()).orElse(null);
        product = Product.builder()
                .productId(productId)
                .productName(productDTO.getProductName())
                .description(productDTO.getDescription())
                .imgURL(imgURL)
                .price(productDTO.getPrice())
                .catalog(catalog)
                .build();
        Product productUpdate = productRepository.save(product);
        return ProductResponse.builder()
                .productId(productUpdate.getProductId())
                .productName(productUpdate.getProductName())
                .price(productUpdate.getPrice())
                .description(productUpdate.getDescription())
                .imgURL(productUpdate.getImgURL())
                .status(productUpdate.isStatus())
                .catalogName(productUpdate.getCatalog().getCatName())
                .build();
    }

    @Override
    public void delete(long productId) {
        productRepository.deleteById(productId);
    }

    @Override
    public List<ProductResponse> searchByName(String producName) {
        List<Product> productList = productRepository.findProductByProductNameContainingIgnoreCase(producName);
        if (!productList.isEmpty())
            return productList.stream()
                    .map(product -> new ProductResponse(
                            product.getProductId(),
                            product.getProductName(),
                            product.getPrice(),
                            product.getDescription(),
                            product.getImgURL(),
                            product.isStatus(),
                            product.getCatalog().getCatName()
                    ))
                    .collect(Collectors.toList());
        return null;
    }
}
