package lk.ijse.possystembackendwithspring.util;

import java.util.UUID;

public class AppUtil {
    public static String generateCustomerID(){
        return "C"+ UUID.randomUUID();
    }
    public static String generateItemCode(){
        return "Item"+ UUID.randomUUID();
    }
    public static String generateOrderId(){
        return "OR"+ UUID.randomUUID();
    }

}
