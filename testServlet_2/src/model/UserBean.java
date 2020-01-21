package model;


public class UserBean {

    private int userId;

    private String userPhone;

    private String userName;

    private String userPwd;



    public String getUserPhone() {
         return userPhone;
    }



    public void setUserPhone(String userPhone) {
         this.userPhone = userPhone;
    }



    public String getUserName() {
         return userName;
    }



    public void setUserName(String userName) {
         this.userName = userName;
    }



    public String getUserPwd() {
         return userPwd;
    }



    public void setUserPwd(String userPwd) {
         this.userPwd = userPwd;
    }



    public int getUserId() {
         return userId;
    }



    public void setUserId(int userId) {
         this.userId = userId;
    }
}