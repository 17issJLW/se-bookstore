package web.com.pojo;


public class Orders {

  private long id;
  private String userName;
  private String userAddress;
  private String userTel;
  private long userCid;
  private long bookId;
  private long bookNumber;
  private long status;
  private double goodsPrice;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }


  public String getUserAddress() {
    return userAddress;
  }

  public void setUserAddress(String userAddress) {
    this.userAddress = userAddress;
  }


  public String getUserTel() {
    return userTel;
  }

  public void setUserTel(String userTel) {
    this.userTel = userTel;
  }


  public long getUserCid() {
    return userCid;
  }

  public void setUserCid(long userCid) {
    this.userCid = userCid;
  }


  public long getBookId() {
    return bookId;
  }

  public void setBookId(long bookId) {
    this.bookId = bookId;
  }


  public long getBookNumber() {
    return bookNumber;
  }

  public void setBookNumber(long bookNumber) {
    this.bookNumber = bookNumber;
  }


  public long getStatus() {
    return status;
  }

  public void setStatus(long status) {
    this.status = status;
  }


  public double getGoodsPrice() {
    return goodsPrice;
  }

  public void setGoodsPrice(double goodsPrice) {
    this.goodsPrice = goodsPrice;
  }

}
