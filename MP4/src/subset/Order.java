package subset;

import objects.ObjectPlus4;


public class Order extends ObjectPlus4
{
    private String orderNumber;

    public Order(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getOrderNumber()
    {
        return orderNumber;
    }

    public static final String isPrepared = "is prepared by";
    public static final String isProceeded = "is proceeded by";

    @Override
    public String toString()
    {
        return getOrderNumber();
    }

}
