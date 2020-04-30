package com.example.devopscasestudy.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.devopscasestudy.model.Product;
import com.example.devopscasestudy.repo.ProductRepository;

@RestController   //exposes to restapi
public class ProductApi {
	
	@Autowired  //dependency injection no need to create object container will create
	private ProductRepository productRepository;
	
	
	@GetMapping("/products")
	public ResponseEntity<List<Product>> findAll(){
		List<Product> products=productRepository.findAll();
		return new ResponseEntity<List<Product>>(products,HttpStatus.OK);  
	}
	
	@PostMapping("/products")
	public ResponseEntity<Product> save(@RequestBody Product product){
		productRepository.save(product);
		return new ResponseEntity<Product>(product,HttpStatus.CREATED);  
	}
	
	@GetMapping("/products/{price}")
	public ResponseEntity<List<Product>> findByPrice(@PathVariable("price") double price){
		List<Product> products=productRepository.findByPriceGreaterThan(price);
		return new ResponseEntity<List<Product>>(products,HttpStatus.OK);  
	}
	
	
	@DeleteMapping("/products/{id}")
	public ResponseEntity delete(@PathVariable("id")int productId) {
	productRepository.deleteById(productId);
	return new ResponseEntity(HttpStatus.OK);

	}
	
	@PostMapping("/products/bulk")
	public ResponseEntity<List<Product>> saveAll(@RequestBody List <Product> product){
		productRepository.saveAll(product);
		return new ResponseEntity<List<Product>>(product,HttpStatus.CREATED);
	}
	
	
	
/*	@PutMapping("products/{id}")
	public ResponseEntity<List<Product>> update(@PathVariable("id")int productId) {

	List<Product> products=productRepository.insertByID(productId);
	return new ResponseEntity<List<Product>>(HttpStatus.OK);

	}*/
	
	
	
	

}
