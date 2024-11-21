package fpoly.longlt.duan1.model;

public class User {
    int id_user;
    int role;
    int moneyOnl;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public User(int id_user, int role, int moneyOnl, int status, String userName, String passWord, String nameUser, String phoneNumber, String address) {
        this.id_user = id_user;
        this.role = role;
        this.moneyOnl = moneyOnl;
        this.status = status;
        this.userName = userName;
        this.passWord = passWord;
        this.nameUser = nameUser;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    int status;
    String userName;
    String passWord;
    String nameUser;
    String phoneNumber;
    String address;

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public User(int id_user, int role, int moneyOnl, int status, String userName, String passWord, String nameUser, String phoneNumber, String address, String img) {
        this.id_user = id_user;
        this.role = role;
        this.moneyOnl = moneyOnl;
        this.status = status;
        this.userName = userName;
        this.passWord = passWord;
        this.nameUser = nameUser;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.img = img;
    }

    String img;

    public User(int role, int moneyOnl, String userName, String passWord, String nameUser, String phoneNumber, String address) {
        this.role = role;
        this.moneyOnl = moneyOnl;
        this.userName = userName;
        this.passWord = passWord;
        this.nameUser = nameUser;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    public User() {
        this.id_user = id_user;
        this.role = role;
        this.moneyOnl = moneyOnl;
        this.userName = userName;
        this.passWord = passWord;
        this.nameUser = nameUser;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public int getMoneyOnl() {
        return moneyOnl;
    }

    public void setMoneyOnl(int moneyOnl) {
        this.moneyOnl = moneyOnl;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getNameUser() {
        return nameUser;
    }

    public void setNameUser(String nameUser) {
        this.nameUser = nameUser;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
