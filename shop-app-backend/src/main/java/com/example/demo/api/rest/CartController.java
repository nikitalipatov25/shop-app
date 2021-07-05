package com.example.demo.api.rest;

import com.example.demo.core.supportingClasses.CartDTO;
import com.example.demo.core.models.CartEntity;
import com.example.demo.core.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@CrossOrigin(allowedHeaders = {
        "Access-Control-Allow-Origin",
        "Access-Control-Allow-Headers",
        "Access-Control-Allow-Methods",
        "Content-Type"
        },
        methods = { RequestMethod.POST,RequestMethod.OPTIONS, RequestMethod.GET, RequestMethod.DELETE, RequestMethod.PUT })
@RestController
@Transactional
@RequestMapping(value = "/cart")
public class CartController {

    private final CartService cartService;

    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping()
    public ResponseEntity<CartEntity> createCart(@RequestBody CartEntity cartEntity,
                                                 @RequestParam(name = "productId")UUID productId) {
        CartEntity createdCartEntity = cartService.createCart(cartEntity, productId);
        return ResponseEntity.ok(createdCartEntity);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<CartEntity> modifyItemInCart(@PathVariable(name = "id")UUID id, @RequestBody CartEntity cartEntity) {
        Optional<CartEntity> result = cartService.modifyItem(id, cartEntity);
        return result
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping()
    public ResponseEntity<?> getAllCart(@RequestParam(name = "filter", required = false)String filter, Pageable pageable) {
        CartDTO cartDTO = new CartDTO();
        cartDTO.setCatalogPage(cartService.getAllCart(filter, pageable));
        cartDTO.setCartSummary(cartService.getCartSummary());
        return new ResponseEntity<>(cartDTO, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CartEntity> getCartItem(@PathVariable(name = "id") UUID id) {
        Optional<CartEntity> result = cartService.getById(id);
        return result
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCartItem (@PathVariable(name = "id")UUID id) {
        Optional<Boolean> deletedItem = cartService.deleteCartItem(id);
        return deletedItem
                .map(e -> ResponseEntity.noContent().build())
                .orElseGet(() -> ResponseEntity.notFound().build());
    }




}
