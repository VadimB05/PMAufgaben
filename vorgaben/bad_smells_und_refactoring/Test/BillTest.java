import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.*;

public class BillTest {
    Bill bill;
    Customer customer;
    Address address;

    @org.junit.Before
    public void setUp() throws Exception {
        String oldstring = "2011-01-18 00:00:00.0";
        SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date date = dt.parse(oldstring);
        bill = new Bill();
        address = new Address("streetName","2",222,"cityName");
        customer = new Customer("Thomas","Thot",date, "email", address);

    }

    @Test
    public void detailsTest(){
        assertEquals(customer.getCustomerDetails()+bill.getDetails(),"Details for \"Thomas\"\n" +
            "streetName 2\n" +
            "222 cityName\n" +
            "Geburtstag: Tue Jan 18 00:00:00 CET 2011\n" +
            "Email: email\n" +
            "\n" +
            "Article: \n" +
            "\n" +
            "Total price:\t0.0"+ "\n");
    }


}
