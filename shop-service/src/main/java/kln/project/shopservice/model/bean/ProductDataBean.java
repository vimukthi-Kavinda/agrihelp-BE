package kln.project.shopservice.model.bean;

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
    private String usage;
 private String amount;

    //img

 byte[] fileContent ;
 long fileSize ;
 String fileName ;

 //imge

 private String   imageName;
 private String  imageData;
}
