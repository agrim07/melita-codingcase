package org.melita.delegator;

import org.melita.api.OrderApiDelegate;
import org.melita.domain.ModelApiResponse;
import org.melita.domain.Order;
import org.melita.exception.BadRequestException;
import org.melita.service.PlaceOrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class OrderApiDelegateImpl implements OrderApiDelegate {

    public static final Logger log = LoggerFactory.getLogger(OrderApiDelegateImpl.class);
    private PlaceOrderService placeOrderService;


    public OrderApiDelegateImpl(PlaceOrderService placeOrderService) {
        this.placeOrderService = placeOrderService;
    }

    @Override
    public ResponseEntity<ModelApiResponse> placeOrder(Order order) {
        log.info("Request Received {}", order);
        if(!isValidateProductPackage(order)) {
            throw new BadRequestException("Invalid Product and Package combination.");
        }
        this.placeOrderService.pushEventToQueue(order);
        return successfulResponse();
    }

    private boolean isValidateProductPackage(Order order) {
        if(Order.ProductsEnum.INTERNET.equals(order.getProducts())
            && !(Order.PackageEnum._1GBPS.equals(order.getPackage())
                || Order.PackageEnum._250MBPS.equals(order.getPackage()))) {
            return false;
        } else if(Order.ProductsEnum.TV.equals(order.getProducts())
                && !(Order.PackageEnum._90_CHANNELS.equals(order.getPackage())
                || Order.PackageEnum._140_CHANNELS.equals(order.getPackage()))) {
            return false;
        } else if(Order.ProductsEnum.MOBILE.equals(order.getProducts())
                && !(Order.PackageEnum.PREPAID.equals(order.getPackage())
                || Order.PackageEnum.POSTPAID.equals(order.getPackage()))) {
            return false;
        } else if(Order.ProductsEnum.TELEPHONY.equals(order.getProducts())
                && !(Order.PackageEnum.FREE_ON_NET_CALLS.equals(order.getPackage())
                || Order.PackageEnum.UNLIMITED_CALLS.equals(order.getPackage()))) {
            return false;
        }
        return true;
    }

    private ResponseEntity<ModelApiResponse> successfulResponse() {
        ModelApiResponse response = new ModelApiResponse();
        response.setCode(HttpStatus.OK.value());
        response.setType(HttpStatus.OK.name());
        response.setMessage("Order placed successfully.");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
