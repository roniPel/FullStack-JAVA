package ExceptionsExercise;

import static java.lang.Character.isDigit;

public class SalesPerson extends Employee implements Comparable<SalesPerson> {
    private String certificationNumber;

    public String getCertificationNumber() {
        return certificationNumber;
    }

    public void setCertificationNumber(String certificationNumber) {
        this.certificationNumber = certificationNumber;
    }

    public SalesPerson(String name, int age, double salary, String certNum) throws InvalidEmployeeCertException {

        super(name, age, salary);
        this.certificationNumber = requiredValidCertificationNumber(certNum);
    }
    private static String requiredValidCertificationNumber (String certNum) throws InvalidEmployeeCertException {
        if(IsValid(certNum)){
            return certNum;
        }
        else {
            throw new InvalidEmployeeCertException("The certification entered doesn't match requirements!");
        }
    }
    private static boolean IsValid(String certNum) {
        // format: 3 digits-3 keys      ###-???

        if(certNum.length() != 7)
            return false;
        if( certNum.indexOf("-") != 3 )
            return false;
        String subStr1, subStr2;
        subStr1 = certNum.substring(0,3);
        subStr2 = certNum.substring(4,7);
        for (int i = 0; i < 3 ; i++) {
            if(!isDigit(subStr1.charAt(i)))
                return false;
            if(isDigit(subStr2.charAt(i)))
                return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "SalesPerson {" +
                super.toString()+
                ", certificationNumber='" + certificationNumber + '\'' +
                '}';
    }

    @Override
    public int compareTo(SalesPerson o) {
        return( (int) (this.getSalary()-o.getSalary()) );
    }
}
