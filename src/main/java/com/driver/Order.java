// package com.driver;

// <<<<<<< HEAD



// public class Order {

//     public void setId(String id) {
//         this.id = id;
//     }

//     public void setDeliveryTime(int deliveryTime) {
//         this.deliveryTime = deliveryTime;
//     }

//     private String id;
//     private int deliveryTime;
    
//    // @PostMapping("/orders/add-order")
//     public Order(String id, String deliveryTime) {

//         // The deliveryTime has to converted from string to int and then stored in the attribute
//         //deliveryTime  = HH*60 + MM
//         this.id=id;
//         //int temp=Integer.parseInt(deliveryTime);
//          int hours = Integer.valueOf(deliveryTime.substring(0,2));
//         int minutes = Integer.valueOf(deliveryTime.substring(3));
//         int total = hours*60 + minutes;
        
//         this.deliveryTime=total;
// =======
// public class Order {

//     private String id;
//     private int deliveryTime;

//     public Order(String id, String deliveryTime) {

//         // The deliveryTime has to converted from string to int and then stored in the attribute
//         //deliveryTime  = HH*60 + MM
// >>>>>>> origin/master
//     }

//     public String getId() {
//         return id;
//     }

//     public int getDeliveryTime() {return deliveryTime;}
// }
package com.driver;





public class Order {

    public void setId(String id) {
        this.id = id;
    }

    public void setDeliveryTime(int deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    private String id;

    public String getId() {
        return id;
    }

    public int getDeliveryTime() {
        return deliveryTime;
    }
    private int deliveryTime;
    
   // @PostMapping("/orders/add-order")
    public Order(String id, String deliveryTime) {

        // The deliveryTime has to converted from string to int and then stored in the attribute
        //deliveryTime  = HH*60 + MM
        this.id=id;
        //int temp=Integer.parseInt(deliveryTime);
         int hours = Integer.valueOf(deliveryTime.substring(0,2));
        int minutes = Integer.valueOf(deliveryTime.substring(3));
        int total = hours*60 + minutes;
        
        this.deliveryTime=total;


}
}
