package geekbrains.Dto;

import geekbrains.Entity.Order;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class OrderDto {
    private Long id;
    private String username;
    private int totalPrice;
    private String creationDateTime;
    private String address;

    public OrderDto(Order order) {
        this.id = order.getId();
        this.username = order.getOwner().getUsername();
        this.totalPrice = order.getPrice();
        this.creationDateTime = order.getCreatedAt().toString();
        this.address= order.getAddress();
    }
}
