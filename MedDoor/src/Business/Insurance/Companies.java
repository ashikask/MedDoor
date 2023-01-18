/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Business.Insurance;

/**
 *
 * @author Dell
 */
public class Companies {
    private String Compname;
    private String Address;
    private String Email;
    private String Phone;

  

    public Companies() {
    }

    public Companies( String Compname, String Address,  String Email,String Phone) {

  
        this.Compname = Compname;
        this.Address = Address;
        this.Email = Email;
        this.Phone=Phone;
    }
    public String getCompname() {
        return Compname;
    }

    public void setCompname(String Compname) {
        this.Compname = Compname;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String Address) {
        this.Address = Address;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String Phone) {
        this.Phone = Phone;
    }
    
}
