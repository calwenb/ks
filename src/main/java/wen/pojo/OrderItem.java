package wen.pojo;

/**
 * 订单商品详细 pojo
 */
public class OrderItem {
    private int orderId;//订单
    private String goodsName;
    private String size;
    private int buyNum;

    public OrderItem() {
    }

    public OrderItem(int orderId, String goodsName, String size, int buyNum) {
        this.orderId = orderId;
        this.goodsName = goodsName;
        this.size = size;
        this.buyNum = buyNum;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public int getBuyNum() {
        return buyNum;
    }

    public void setBuyNum(int buyNum) {
        this.buyNum = buyNum;
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "orderId=" + orderId +
                ", goodsName='" + goodsName + '\'' +
                ", size='" + size + '\'' +
                ", buyNum=" + buyNum +
                '}';
    }
}
