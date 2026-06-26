package org.yearup.service;

import org.springframework.stereotype.Service;
import org.yearup.models.Product;
import org.yearup.repository.ProductRepository;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ProductService
{
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository)
    {
        this.productRepository = productRepository;
    }

    public List<Product> search(Integer categoryId, Double minPrice, Double maxPrice, String subCategory)
    {
        List<Product> products = categoryId != null
                ? productRepository.findByCategoryId(categoryId)
                : productRepository.findAll();
        // changed return products.stream() to return productRepository.findAll()
        return productRepository.findAll().stream()
                .filter(p -> minPrice == null || p.getPrice().compareTo(BigDecimal.valueOf(minPrice)) >= 0)
                .filter(p -> maxPrice == null || p.getPrice().compareTo(BigDecimal.valueOf(maxPrice)) <= 0)
                .filter(p -> subCategory == null || subCategory.equalsIgnoreCase(p.getSubCategory()))
                .toList();
                      //removed this line .filter(Product::isFeatured)

    }

    public List<Product> listByCategoryId(int categoryId)
    {
        return productRepository.findByCategoryId(categoryId);
    }

    public Product getById(int productId)
    {
        return productRepository.findById(productId).orElse(null);
    }

    public Product create(Product product)
    {
        product.setProductId(0);
        return productRepository.save(product);
    }

    public Product update(int productId, Product product)
    {
        Product existing = productRepository.findById(productId).orElseThrow();
        existing.setName(product.getName());
        existing.setPrice(product.getPrice());
        existing.setCategoryId(product.getCategoryId());
        existing.setDescription(product.getDescription());
        existing.setSubCategory(product.getSubCategory());
        existing.setFeatured(product.isFeatured());
        existing.setImageUrl(product.getImageUrl());

        // this was missing bug
        existing.setStock(product.getStock());
        return productRepository.save(existing);
    }

    public void delete(int productId)
    {
        productRepository.deleteById(productId);
    }
}
