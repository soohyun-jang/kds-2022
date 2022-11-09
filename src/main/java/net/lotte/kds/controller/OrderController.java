package net.lotte.kds.controller;

import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.firebase.cloud.FirestoreClient;
import lombok.SneakyThrows;
import net.lotte.kds.domain.Menu;
import net.lotte.kds.domain.Order;
import net.lotte.kds.domain.OrderItem;
import net.lotte.kds.dto.MenuDto;
import net.lotte.kds.dto.OrderDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.*;

@RestController
public class OrderController {


    /**
     * 주문 리스트 조회
     * @return orders
     */
    @SneakyThrows
    @GetMapping("/orders")
    public ResponseEntity getOrders() {

        // db 조회
        Firestore db = FirestoreClient.getFirestore();
        List<QueryDocumentSnapshot> ordersDS = db.collection("order").get().get().getDocuments();
        List<QueryDocumentSnapshot> menusDS = db.collection("menu").get().get().getDocuments();
        List<QueryDocumentSnapshot> orderItemsDS = db.collection("order_items").get().get().getDocuments();

        // 주문 리스트 생성
        List<OrderDto> orders = new ArrayList<>();
        for (QueryDocumentSnapshot orderDS : ordersDS) {
            OrderDto orderDto = new OrderDto();
            List<MenuDto> menus = new ArrayList<>();

            Order order = orderDS.toObject(Order.class);
            orderDto.setNo(order.getOrder_no());
            SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss", Locale.KOREA);
            orderDto.setDatetime(dateFormat.format(order.getCreated_at()));

            for (QueryDocumentSnapshot orderItemDS : orderItemsDS) {
                OrderItem orderItem = orderItemDS.toObject(OrderItem.class);

                for (QueryDocumentSnapshot menuDS : menusDS) {
                    Menu menu = menuDS.toObject(Menu.class);
                    MenuDto menuDto = new MenuDto();

                    // join
                    if (orderItem.getOrder_id() == order.getId() && orderItem.getMenu_id() == menu.getId()) {
                        menuDto.setName(menu.getName());
                        menuDto.setCnt(orderItem.getCnt());
                        menuDto.setDetails(orderItem.getDetails());
                        menuDto.setPrintable(true);
                        menus.add(menuDto);
                    }
                }
            }

            orderDto.setMenus(menus);
            orders.add(orderDto);
        }

        Map<String, Object> response = new HashMap<>();
        response.put("orders", orders);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
