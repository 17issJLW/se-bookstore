package web.com.pojo;

public class Users {

  private long id;
  private String logname;
  private String password;
  private String email;
  private long gender;
  private String selfinfo;
  private long admin;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public String getLogname() {
    return logname;
  }

  public void setLogname(String logname) {
    this.logname = logname;
  }


  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }


  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }


  public long getGender() {
    return gender;
  }

  public void setGender(long gender) {
    this.gender = gender;
  }


  public String getSelfinfo() {
    return selfinfo;
  }

  public void setSelfinfo(String selfinfo) {
    this.selfinfo = selfinfo;
  }


  public long getAdmin() {
    return admin;
  }

  public void setAdmin(long admin) {
    this.admin = admin;
  }

}
