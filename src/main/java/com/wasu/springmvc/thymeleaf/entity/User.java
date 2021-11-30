package com.wasu.springmvc.thymeleaf.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author ZHANGLEI
 * @date 2021/11/27 12:27
 */
@Data
@AllArgsConstructor
public class User {
    private Long id;
    private String name;
    private int sex;
    private String email;
}
