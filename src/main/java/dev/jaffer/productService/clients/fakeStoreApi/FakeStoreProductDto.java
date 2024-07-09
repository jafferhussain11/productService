package dev.jaffer.productService.clients.fakeStoreApi;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FakeStoreProductDto implements Serializable {

    private long id;
    private String title;
    private String description;
    private double price;
    private String image;
    private String category;
}
