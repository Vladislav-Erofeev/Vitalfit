package com.example.products.controllers;

import com.example.products.domain.dtos.ProductDto;
import com.example.products.mappers.ProductMapper;
import com.example.products.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
@PreAuthorize("permitAll()")
public class ProductController {
    private final ProductService productService;
    private final ProductMapper productMapper = ProductMapper.INSTANCE;

    @GetMapping
    public List<ProductDto> getAll() {
        return productService.getAll().stream().map(productMapper::toDto).toList();
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @PostMapping
    public ProductDto save(@RequestBody ProductDto productDto) {
        return productMapper.toDto(productService.save(productMapper.toEntity(productDto)));
    }

    @GetMapping("/{id}")
    public ProductDto getById(@PathVariable("id") Long id) {
        return productMapper.toDto(productService.getById(id));
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @PatchMapping("/{id}")
    public ProductDto patchById(@PathVariable("id") Long id, @RequestBody ProductDto productDto) {
        return productMapper.toDto(productService.patchById(id, productMapper.toEntity(productDto)));
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") Long id) {
        productService.deleteById(id);
    }
}
