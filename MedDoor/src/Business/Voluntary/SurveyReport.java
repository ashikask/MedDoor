/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Business.Voluntary;

/**
 *
 * @author ashikakalmady
 */
public class SurveyReport {
     private boolean needHospitalization;
     public boolean isNeedHospitalization() {
        return needHospitalization;
    }

    public void setNeedHospitalization(boolean needHospitalization) {
        this.needHospitalization = needHospitalization;
    }

    public String getSicknessType() {
        return sicknessType;
    }

    public void setSicknessType(String sicknessType) {
        this.sicknessType = sicknessType;
    }
   private String sicknessType;
   public SurveyReport() {
       
   }
}
