package org.dinuka.foodcatalogue.dto;

public record MenuDTO(Long id,
                      String name,
                      String description,
                      double price,
                      String category,
                      String type,
                      String image) {
    @Override
    public String toString() {
        return "MenuDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", category='" + category + '\'' +
                ", type='" + type + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
}
