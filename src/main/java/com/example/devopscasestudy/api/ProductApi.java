package com.example.devopscasestudy.api;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.devopscasestudy.model.Product;
import com.example.devopscasestudy.repo.ProductRepository;

@RestController   //exposes to restapi
public class ProductApi {
	private final Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired  //dependency injection no need to create object container will create
	private ProductRepository productRepository;
	
	
	@GetMapping("/products")
	public ResponseEntity<List<Product>> findAll(){
		logger.info("find by all");
		List<Product> products=productRepository.findAll();
		return new ResponseEntity<>(products,HttpStatus.OK);  
	}
	
	@PostMapping("/products")
	public ResponseEntity<Product> save(@RequestBody Product product){
		productRepository.save(product);
		return new ResponseEntity<>(product,HttpStatus.CREATED);  
	}
	
	@GetMapping("/products/{price}")
	public ResponseEntity<List<Product>> findByPrice(@PathVariable("price") double price){
		List<Product> products=productRepository.findByPriceGreaterThan(price);
		return new ResponseEntity<>(products,HttpStatus.OK);  
	}
	
	@GetMapping("/products/find/{name}")
	public ResponseEntity<List<Product>> findByName(@PathVariable("name") String name){
		List<Product> products=productRepository.findByProductNameIgnoreCase(name);
		return new ResponseEntity<>(products,HttpStatus.OK);  
	}
	
	@DeleteMapping("/products/{id}")
	public ResponseEntity delete(@PathVariable("id")int productId) {
	productRepository.deleteById(productId);
	return new ResponseEntity(HttpStatus.OK);

	}
	
	@PostMapping("/products/bulk")
	public ResponseEntity<List<Product>> saveAll(@RequestBody List <Product> product){
		productRepository.saveAll(product);
		return new ResponseEntity<>(product,HttpStatus.CREATED);
	}

