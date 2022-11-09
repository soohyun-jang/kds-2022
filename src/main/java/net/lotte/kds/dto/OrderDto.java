package net.lotte.kds.dto;

import lombok.Data;
import net.lotte.kds.domain.Menu;

import java.util.Date;
import java.util.List;

@Data
public class OrderDto {
    private Integer no;
    private String datetime;
    private List<MenuDto> menus;
}
