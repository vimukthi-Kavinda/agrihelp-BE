package kln.project.mainservice.bean;

import lombok.Data;

@Data
public class ProductDataBean {
   private String productid;
    private String category;
    private String productname;
    private String  price;
    private String  manufacturedby;
    private String   importedby;
    private String  expdate;

    private String amount;
    private String usage;

    //img

 byte[] fileContent ;
 long fileSize ;
 String fileName ;

}
