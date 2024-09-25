package com.dev.tssvett.integrations.product;

import com.dev.tssvett.dto.DeliverDto;
import com.dev.tssvett.dto.StatusDto;
import com.dev.tssvett.enums.OrderStatus;
import com.dev.tssvett.integrations.product.request.OrderDeliveryRequest;
import com.dev.tssvett.integrations.product.request.OrderStatusRequest;

import java.time.LocalDateTime;

public interface OrderServiceClient {

    StatusDto changeOrderStatus(OrderStatusRequest orderStatusRequest);

    DeliverDto changeDeliveryDate(OrderDeliveryRequest orderDeliveryRequest);
}
