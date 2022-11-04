package net.lotte.kds.domain;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;


@Entity
@Getter
public class Menu {

    @Id @GeneratedValue
    private Long id;

    private String category;
    private String name;
    private Integer price;
    private Date created_at;
}
