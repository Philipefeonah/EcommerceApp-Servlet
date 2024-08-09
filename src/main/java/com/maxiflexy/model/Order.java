package com.maxiflexy.model;

import lombok.*;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Data
public class Order extends  Product{
    private int orderId;
    private int uId;
    private int quantity;
    private String date;
}
