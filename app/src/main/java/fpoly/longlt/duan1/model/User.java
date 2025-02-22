package fpoly.longlt.duan1.model;

public class User {
    int id_user, role, moneyOnl;
    String userName, passWord, nameUser ,phoneNumber, address;

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
