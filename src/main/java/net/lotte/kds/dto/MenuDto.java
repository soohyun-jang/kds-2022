package net.lotte.kds.dto;

import lombok.Data;

@Data
public class MenuDto {
    private String name;
    private String cnt;
    private String details;
    private Boolean printable;
}
