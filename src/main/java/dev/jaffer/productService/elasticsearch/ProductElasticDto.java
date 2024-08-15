//package dev.jaffer.productService.elasticsearch;
//
//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//
//@AllArgsConstructor
//@Getter
//@Setter
//@NoArgsConstructor
//public class ProductElasticDto {
//
//    private Long id;
//    private String title;
//    private String description;
//    private double price;
//    private Long categoryId;
//    private String imageUrl;
//
//    public ProductElastic toProductElastic(ProductElasticDto productElasticDto) {
//
//        ProductElastic productElastic = new ProductElastic();
//        productElastic.setId(productElasticDto.getId());
//        productElastic.setTitle(productElasticDto.getTitle());
//        productElastic.setDescription(productElasticDto.getDescription());
//        productElastic.setPrice(productElasticDto.getPrice());
//        productElastic.setImageUrl(productElasticDto.getImageUrl());
//        return productElastic;
//    }
//
//    public ProductElasticDto toProductElasticDto(ProductElastic productElastic) {
//
//        ProductElasticDto productElasticDto = new ProductElasticDto();
//        productElasticDto.setId(productElastic.getId());
//        productElasticDto.setTitle(productElastic.getTitle());
//        productElasticDto.setDescription(productElastic.getDescription());
//        productElasticDto.setPrice(productElastic.getPrice());
//        productElasticDto.setImageUrl(productElastic.getImageUrl());
//        return productElasticDto;
//    }
//
//
//}
