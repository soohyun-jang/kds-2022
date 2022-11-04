package net.lotte.kds.domain;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Getter
public class Order {

    @Id @GeneratedValue
    private Long id;

    private String order_id;
    private String order_device;
    private Integer total_price;
    private Integer kind;
    private Integer status;
    private Date created_at;
    private Date served_at;
}
