package com.driver;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {
    OrderRepository repo = new OrderRepository();
    public boolean addOrders (Order data){
        repo.addOrders(data);
        return true;
    }
    public boolean addPartner (String data){

        repo.addPartner(new DeliveryPartner(data));
        return true;
    }

    public boolean addOrderPartnerPair (String orderId , String partnerId){

    repo.addOrderPartnerPair(orderId,partnerId);
    return true;
    }
    public Order getOrderById (String orderId){

       return repo.getOrderById(orderId);

    }

    public DeliveryPartner getPartnerById (String partnerId){

        return repo.getPartnerById(partnerId);

    }
    public int getOrderCountByPartnerId(String partnerId){
        if(repo.partnerRepo.containsKey(partnerId)){
            return repo.partnerRepo.get(partnerId).getNumberOfOrders();
        }
        return 0;
    }
    public List<String> getOrdersByPartnerId( String partnerId){
        if(repo.ordersRepo.containsKey(partnerId)){
            return repo.ordersRepo.get(partnerId);
        }
        return new ArrayList<>();
    }
    public List<String> getAllOrders( ){

        List<String> list = new ArrayList<>();
        for(String id : repo.orderRepo.keySet()){
            list.add(id);

        }
        System.out.print("list"+" "+ list);
        return list;
    }
//    getCountOfUnassignedOrders
    public int getCountOfUnassignedOrders( ){
    int count=Math.abs(repo.orderRepo.size()-repo.pairRepo.size());
    return count;
    }

    public int getOrdersLeftAfterGivenTimeByPartnerId(int T , String partnerId){
        int count=0;
        List li = repo.ordersRepo.get(partnerId);

        for(int i=0;i<li.size();i++){
            if(repo.orderRepo.containsKey(li.get(i))&&repo.orderRepo.get(li.get(i)).getDeliveryTime() > T){
                count++;
            }
        }
        return count;
    }
    public String getLastDeliveryTimeByPartnerId(String partnerId){
        int time = 0;
        List li= new ArrayList();

        if(repo.ordersRepo.containsKey(partnerId))
             li = repo.ordersRepo.get(partnerId);

        for(int i=0;i<li.size();i++){
            if(repo.orderRepo.containsKey(li.get(i))&&repo.orderRepo.get(li.get(i)).getDeliveryTime() > time){
                time =repo.orderRepo.get(li.get(i)).getDeliveryTime();
            }
        }

        System.out.println(time);
        String hh = String.valueOf(time/60);
        String mm = String.valueOf(time%60);
        String result=hh+":"+mm;

        if(time/60 < 10)
            result= "0"+result;
        if(time%60 <10)
            result =result+"0";

        return result;


    }
    public void deletePartnerById( String partnerId){
        List removeList = repo.ordersRepo.get(partnerId);

        for(int i=0;i<removeList.size();i++){
            repo.pairRepo.remove(removeList.get(i));
        }
        repo.ordersRepo.remove(partnerId);
        repo.partnerRepo.remove(partnerId);
        System.out.print(repo.orderRepo+" "+repo.ordersRepo+" "+repo.pairRepo+" "+repo.partnerRepo);
    }
    public void deleteOrderById(String orderId){
        repo.orderRepo.remove(orderId);
        if(repo.pairRepo.containsKey(orderId)){

        String partner= repo.pairRepo.get(orderId);
        repo.pairRepo.remove(orderId);

       List li = repo.ordersRepo.get(partner);

       for(int i=0;i<li.size();i++){
           if(li.get(i).equals(orderId)){
               li.remove(i);
               repo.ordersRepo.put(partner,li);
               break;
           }
       }
       System.out.println(li);

        }

    }
}
