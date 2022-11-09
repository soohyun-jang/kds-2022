package net.lotte.kds.domain;

import lombok.Getter;

import javax.persistence.Id;
import java.util.Date;

@Getter
public class Order {

    @Id
    private Long id;

    private Integer order_no;
    private String order_device;
    private Integer total_price;
    private Integer kind;
    private Integer status;
    private Date created_at;
    private Date served_at;
}
