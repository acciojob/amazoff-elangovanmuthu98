package com.driver;

import java.util.*;

import org.springframework.stereotype.Repository;

@Repository
public class OrderRepository {

    private HashMap<String, Order> orderMap;
    private HashMap<String, DeliveryPartner> partnerMap;
    private HashMap<String, HashSet<String>> partnerToOrderMap;
    private HashMap<String, String> orderToPartnerMap;

    public OrderRepository(){
        this.orderMap = new HashMap<String, Order>();
        this.partnerMap = new HashMap<String, DeliveryPartner>();
        this.partnerToOrderMap = new HashMap<String, HashSet<String>>();
        this.orderToPartnerMap = new HashMap<String, String>();
    }

    public void saveOrder(Order order){
        // your code here
        String key = order.getId();
            if(key != null)
              orderMap.put(key, order);
       // orderMap.put((order.getId()), order);
    }

    public void savePartner(String partnerId){
        // your code here
        // create a new partner with given partnerId and save it
         DeliveryPartner deliveryPartner = new DeliveryPartner((partnerId));
        String key = deliveryPartner.getId();
        partnerMap.put(key,deliveryPartner);
       // partnerMap.put(partnerId, new DeliveryPartner(partnerId));
    }

    public void saveOrderPartnerMap(String orderId, String partnerId){
        if(orderMap.containsKey(orderId) && partnerMap.containsKey(partnerId)){

            HashSet<String> currentOrders = new HashSet<>();

            if(partnerToOrderMap.containsKey(partnerId)){
                currentOrders = partnerToOrderMap.get(partnerId);
            }
            currentOrders.add(orderId);
            partnerToOrderMap.put(partnerId,currentOrders);

            DeliveryPartner partner = partnerMap.get(partnerId);
            partner.setNumberOfOrders(currentOrders.size());

            orderToPartnerMap.put(orderId, partnerId);
        }
    }

    public Order findOrderById(String orderId){
        // your code here
        Order order = orderMap.get(orderId);
        return order;
    }

    public DeliveryPartner findPartnerById(String partnerId){
          DeliveryPartner deliveryPartner = null;

        if(partnerMap.containsKey(partnerId))
           deliveryPartner = partnerMap.get(partnerId);

        return deliveryPartner;
        // your code here
    }

    public Integer findOrderCountByPartnerId(String partnerId){
        int orderCount = 0;

        if(partnerToOrderMap.containsKey(partnerId))
        {
            orderCount = partnerToOrderMap.get(partnerId).size();
        }

        return orderCount;
        // your code here
    }

    public List<String> findOrdersByPartnerId(String partnerId){
         HashSet<String> orderList = null;

        if(partnerToOrderMap.containsKey(partnerId))
        {orderList = partnerToOrderMap.get(partnerId);}

        return new ArrayList<>(orderList);
        // your code here
    }

    public List<String> findAllOrders(){
        // your code here
        // return list of all orders
        List<String> orders = new ArrayList<>(orderMap.keySet());
        return orders;
    }

    public void deletePartner(String partnerId){
        // your code here
        // delete partner by ID
        HashSet<String> list = new HashSet<>();

        if(partnerToOrderMap.containsKey(partnerId))
        {
            list = partnerToOrderMap.get(partnerId);

            for (String st : list) {
//                orderMap.remove(st);

                if (orderToPartnerMap.containsKey(st))
                {
                    orderToPartnerMap.remove(st);
            }
            }

            partnerToOrderMap.remove(partnerId);
        }

        if(partnerMap.containsKey(partnerId)) {
            partnerMap.remove(partnerId);
        }
    
    }

    public void deleteOrder(String orderId){
        // your code here
        // delete order by ID
         if(orderToPartnerMap.containsKey(orderId))
        {
            String partnerId = orderToPartnerMap.get(orderId);

            HashSet<String> list = partnerToOrderMap.get(partnerId);
            list.remove(orderId);
            partnerToOrderMap.put(partnerId,list);

            DeliveryPartner deliveryPartner = partnerMap.get(partnerId);
            deliveryPartner.setNumberOfOrders(list.size());
        }

        if(orderMap.containsKey(orderId)) {
            orderMap.remove(orderId);
        }
    }
    

    public Integer findCountOfUnassignedOrders(){
        // your code here
 //return orderMap.size() - orderPartnerMap.size();
 Integer countOfOrders = 0;

        List<String> list = new ArrayList<>(orderMap.keySet());

        for(String st : list){
            if(!orderToPartnerMap.containsKey(st))
            {
                countOfOrders += 1;
            }
        }

        return countOfOrders;
 

    }

    public Integer findOrdersLeftAfterGivenTimeByPartnerId(String timeString, String partnerId){

        // your code here
          int countOfOrders = 0;
        int hours = Integer.valueOf(timeString.substring(0,2));
        int minutes = Integer.valueOf(timeString.substring(3));
        int total = hours*60 + minutes;

        if(partnerToOrderMap.containsKey(partnerId))
        {
            HashSet<String> set = partnerToOrderMap.get(partnerId);

            for(String st : set)
            {
                if(orderMap.containsKey(st))
                {
                    Order order = orderMap.get(st);

                    if(total < order.getDeliveryTime())
                        countOfOrders++;
                }
            }
        }

        return countOfOrders;
        

    }

    public String findLastDeliveryTimeByPartnerId(String partnerId){
        // your code here
        // code should return string in format HH:MM
        String time = null;
        int delivery_time = 0;

        if(partnerMap.containsKey(partnerId))
        {
            HashSet<String> list = partnerToOrderMap.get(partnerId);

            for(String st : list)
            {
                if(orderMap.containsKey(st))
                {
                    Order order = orderMap.get(st);

                    if(delivery_time < order.getDeliveryTime())
                        delivery_time = order.getDeliveryTime();
                }
            }
        }
        StringBuilder str = new StringBuilder();

        int hr = delivery_time / 60;                 // calculate hour
        if(hr < 10)
            str.append(0).append(hr);
        else
            str.append(hr);

        str.append(":");

        int min = delivery_time - (hr*60);          // calculate minutes
        if(min < 10)
            str.append(0).append(min);
        else
            str.append(min);

//        str.append(min);

        return str.toString();
    }
}
