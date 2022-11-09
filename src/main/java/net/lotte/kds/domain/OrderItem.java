package net.lotte.kds.domain;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
public class OrderItem {

    @Id
    private Long id;

    private Long order_id;
    private Long menu_id;
    private String cnt;
    private String details;
}
