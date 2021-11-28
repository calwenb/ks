package huang.pojo;

public class Cart {
    private int cartId;
    private String goodsName;
    private double price;
    private int buyNum;
    private int stock;
    private int pId;
    private int uId;

    public Cart() {
    }

    public Cart(int cartId, String goodsName, double price, int buyNum, int stock, int pId, int uId) {
        this.cartId = cartId;
        this.goodsName = goodsName;
        this.price = price;
        this.buyNum = buyNum;
        this.stock = stock;
        this.pId = pId;
        this.uId = uId;
    }

    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getBuyNum() {
        return buyNum;
    }

    public void setBuyNum(int buyNum) {
        this.buyNum = buyNum;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getpId() {
        return pId;
    }

    public void setPId(int pId) {
        this.pId = pId;
    }

    public int getUId() {
        return uId;
    }

    public void setUId(int uId) {
        this.uId = uId;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "cartId=" + cartId +
                ", goodsName='" + goodsName + '\'' +
                ", price=" + price +
                ", buyNum=" + buyNum +
                ", stock=" + stock +
                ", pId=" + pId +
                ", uId='" + uId + '\'' +
                '}';
    }
}




