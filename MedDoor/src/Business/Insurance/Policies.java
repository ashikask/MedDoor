/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Business.Insurance;

/**
 *
 * @author Dell
 */
public class Policies {
    private int Pid;
    private Companies Compname;
    private String Pname;
    private String Pdesc;
    private int Pamt;

    public Policies() {
    }

//    public policies(int Pid,  String Compname, String Pname, String Pdesc, int Pamt) {
//        this.Pid = Pid;
//        this.Compname = Compname;
//        this.Pname = Pname;
//        this.Pdesc = Pdesc;
//        this.Pamt=Pamt;
//    }
    public int getPid() {
        return Pid;
    }

    public void setPid(int Pid) {
        this.Pid = Pid;
    }

    public Companies getCompname() {
        return Compname;
    }

    public void setCompname(Companies Compname) {
        this.Compname = Compname;
    }

    public String getPname() {
        return Pname;
    }

    public void setPname(String Pname) {
        this.Pname = Pname;
    }

    public String getPdesc() {
        return Pdesc;
    }

    public void setPdesc(String Pdesc) {
        this.Pdesc = Pdesc;
    }

    public int getPamt() {
        return Pamt;
    }

    public void setPamt(int Pamt) {
        this.Pamt = Pamt;
    }
    
}
