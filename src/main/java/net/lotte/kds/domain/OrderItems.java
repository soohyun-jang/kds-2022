package net.lotte.kds.domain;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
public class OrderItems {

    @Id @GeneratedValue
    private Long id;

    private Long order_id;
    private Long menu_id;
    private Integer cnt;
    private String details;
}
