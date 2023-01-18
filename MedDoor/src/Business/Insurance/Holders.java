/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Business.Insurance;

/**
 *
 * @author Dell
 */
public class Holders {
    private int HolderId;
    private String Hname;
    private String Haddress;
//    private String Hcommunity;
    private String Hemail;
    private String Hmobile;
    private int Pid;
    
    public Holders()
    {
        
    }

    public int getHolderId() {
        return HolderId;
    }

    public void setHolderId(int HolderId) {
        this.HolderId = HolderId;
    }

    public String getHname() {
        return Hname;
    }

    public void setHname(String Hname) {
        this.Hname = Hname;
    }

    public String getHaddress() {
        return Haddress;
    }

    public void setHaddress(String Haddress) {
        this.Haddress = Haddress;
    }

//    public String getHcommunity() {
//        return Hcommunity;
//    }
//
//    public void setHcommunity(String Hcommunity) {
//        this.Hcommunity = Hcommunity;
//    }

    public String getHemail() {
        return Hemail;
    }

    public void setHemail(String Hemail) {
        this.Hemail = Hemail;
    }

    public String getHmobile() {
        return Hmobile;
    }

    public void setHmobile(String Hmobile) {
        this.Hmobile = Hmobile;
    }

    public int getPid() {
        return Pid;
    }

    public void setPid(int Pid) {
        this.Pid = Pid;
    }
    
}
