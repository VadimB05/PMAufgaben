import java.util.ArrayList;
import java.util.Date;

public class Customer {
    public String customerName;
    public String nickname;
    public Date birthday;
    public String email;
    Address address;

    public Customer(String customerName, String nickname, Date birthday, String email, Address address) {
        this.customerName = customerName;
        this.nickname = nickname;
        this.birthday = birthday;
        this.email = email;
        this.address = address;
    }

    public String getCustomerDetails(){
        String result = "Details for \"" + customerName + "\"\n";
        result += address.getStreet();
        result += address.getCity();
        result += "Geburtstag: " + birthday + "\n";
        result += "Email: " + email + "\n\n";
        return result;
    }
}
