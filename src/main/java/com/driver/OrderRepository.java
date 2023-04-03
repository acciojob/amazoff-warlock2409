package com.driver;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository
public class OrderRepository {

    HashMap<String,Order> orderRepo = new HashMap<>();
    HashMap<String,DeliveryPartner> partnerRepo = new HashMap<>();
    HashMap<String,String> pairRepo = new HashMap<>();
    HashMap<String, List<String>> ordersRepo = new HashMap<String, List<String>>();

    public boolean addOrders(Order order){
        orderRepo.put(order.getId(),order);
        System.out.print(orderRepo);
        return true;
    }
    public boolean addPartner(DeliveryPartner partner){
        partnerRepo.put(partner.getId(),partner);
        System.out.print(partnerRepo);
        return true;
    }

    public boolean addOrderPartnerPair(String orderId,String partnerId){
        pairRepo.put(orderId,partnerId);
        int orderCount=0;
        if(ordersRepo.containsKey(partnerId)){
            List<String> orderList= ordersRepo.get(partnerId);
            orderList.add(orderId);
            orderCount=orderList.size();
        }else{
            List <String> orderList = new ArrayList<>();
            orderList.add(orderId);
            ordersRepo.put(partnerId,orderList);
            orderCount=orderList.size();

        }
        partnerRepo.get(partnerId).setNumberOfOrders(orderCount);
        System.out.println(ordersRepo);

        return true;
    }

    public Order getOrderById(String orderId){
        if(orderRepo.containsKey(orderId)) {
            System.out.print(orderRepo.get(orderId).getId());

            return orderRepo.get(orderId);
        }
        return null;
    }
    public DeliveryPartner getPartnerById(String partnerId){
        if(partnerRepo.containsKey(partnerId)) {
            System.out.print(partnerRepo.get(partnerId).getId());

            return partnerRepo.get(partnerId);
        }
        return null;
    }
}
