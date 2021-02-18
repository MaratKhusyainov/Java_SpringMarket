package geekbrains.Service;

import geekbrains.Repository.OrderItemsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import geekbrains.Beans.Cart;
import geekbrains.Entity.OrderItem;


@Service
@RequiredArgsConstructor
public class OrderItemsService {
    private final OrderItemsRepository orderItemsRepository;

    public void save(Cart c) {
        for (OrderItem o : c.getItems()) {
            orderItemsRepository.save(o);
        }
    }
}