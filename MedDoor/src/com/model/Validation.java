
package com.model;

import java.util.regex.Pattern;
import java.math.BigInteger;


/**
 *
 * @author Dell
 */
public class Validation {
    public Validation() {
    }



   public boolean isUserExist() {



       return true;
    }



   public boolean isStringOnlyAlphabet(String str) {



       return ((str != null) && (!str.equals(""))
                && (str.matches("^[a-zA-Z ]+$")));
    }



   public boolean isAlphanumeric(String str) {
        String aptRegex = "^[a-zA-Z0-9]+$";



       Pattern pat = Pattern.compile(aptRegex);
        return pat.matcher(str).matches();
    }



   public boolean isNumeric(String strNum) {
        try {
            BigInteger number = new BigInteger(strNum);
            return true;
        } catch (NumberFormatException nfe) {
            return false;
        }



   }



   public boolean isAptValid(String aptNo) {
        try {
            
            String pattern = "^[a-zA-Z0-9]*$";
            if (aptNo.matches(pattern)) {
                System.out.println("imsidde---");
                return true;
            }
        } catch (NumberFormatException nfe) {
            return false;
        }
        //        return aptNo != null && aptNo.matches("^[a-zA-Z0-9]*$");
return false;
    }



   public boolean isDouble(String strNum) {
        try {
            Double number = Double.parseDouble(strNum);
            return true;
        } catch (NumberFormatException nfe) {
            return false;
        }



   }



   public boolean isLong(String strNum) {
        try {
            Long number = Long.parseLong(strNum);
            return true;
        } catch (NumberFormatException nfe) {
            return false;
        }



   }



   public boolean isValidLength(String value, int minLength, int maxLength) {
        int length = value.length();
        return length >= minLength && length <= maxLength;
    }



   public boolean isValidAge(String strAge) {
        int age = Integer.parseInt(strAge);
        if (age > 0 && age <= 150) {
            return true;
        } else {
            return false;
        }
    }



   public boolean isEmailValid(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."
                + "[a-zA-Z0-9_+&*-]+)*@"
                + "(?:[a-zA-Z0-9-]+\\.)+[a-z"
                + "A-Z]{2,7}$";



       Pattern pat = Pattern.compile(emailRegex);
        return pat.matcher(email).matches();
    }



}  

