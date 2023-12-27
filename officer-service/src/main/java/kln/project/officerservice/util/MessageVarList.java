package kln.project.officerservice.util;


import lombok.Data;

@Data
public class MessageVarList {

    public static final String COMMON_INVALID_REQUEST = "Invalid Request.";
    public static final String COMMON_INVALID_REQUEST_BODY = "Required request body is missing.";
    public static final String COMMON_INVALID_DATA = "Invalid Data.";
    public static final String COMMON_ERROR_ALRADY_USE = "Exception occured during the execution.";
    public static final String COMMON_ACCESS_DENIED = "Access denied.";
    public static final String COMMON_RESOURCE_NOT_FOUND = "The requested resource does not exist.";
    public static final String COMMON_UNAUTHORIZED_ACCESS = "Your account does not have access to this service. ";
    public static final String COMMON_INTERNAL_SERVER_ERROR = "Cannot process your request. Please contact administrator.";
    public static final String COMMON_ALREADY_REQUEST_EXIST = "Request already exist for the given ";
    public static final String COMMON_SUCC_CONFIRM = "confirmed successfully.";
    public static final String COMMON_SUCC_REJECT = "rejected successfully.";
    public static final String PASSWORD_MISMATCH = "password does not match.";

    //comm success fails
    public static final String SUCCESS_ADD = "Added Successfully.";
    public static final String SUCCESS_UPDATE = "Updated Successfully.";
    public static final String SUCCESS_DELETE = "Deleted Successfully.";
    public static final String SUCCESS_LOGIN = "Login Successfully.";

    public static final String COMMON_NOT_FOUND = " does not exist.";
    public static final String COMMON_ALREADY_EXIST = "  is alerady exists.";
    public static final String FAIL_ADD = "Adding Failed.";
    public static final String FAIL_UPDATE = "Updating Failed.";
    public static final String FAIL_DELETE = "Deleting Failed.";
    public static final String FAIL_LOGIN = "Login Failed.";

    public static final String USER_NAME_NULL = "User name is null.";
    public static final String PASSWORD_NULL = "Password is null.";
    public static final String USER_NAME_NOT_FOUND = "User name is not fount.";

    public static final String OFFICER_ID_NULL = "Officer id is null.";
    public static final String OFFICER_NIC_NULL = "Officer NIC is null.";
    public static final String OFFICER_NAME_NULL = "Officer name is null.";
    public static final String OFFICER_ADDRESS_NULL = "Officer address is null.";
    public static final String OFFICER_TELNO_NULL = "Officer tel no is null.";
    public static final String OFFICER_EMAIL_NULL = "Officer email is null.";
    public static final String OFFICER_ASSIGNED_AREA_NULL = "Officer assigned area code is null.";

    public static final String POST_ID_NULL = "Post Id is null.";
    public static final String COMMENT_NULL = "Comment is null.";
    public static final String REACT_NULL = "React is null.";
    public static final String REACT_INVALID = "React is invalid.";


    public static final String DISEASE_ID_NULL = "disease Id is null.";
    public static final String DISEASE_NAME_NULL = "disease is null.";
    public static final String ROOT_CAUSE_NULL = "root cause is null.";
    public static final String DISEASE_DISCRIPTION_NULL = "disease detail is null.";
    public static final String SPERADING_METHOD_NULL = "spreading method is null.";
    public static final String REMEDIES_NULL = "Remedy is null.";

    public static final String PLAGUE_NOTIFICATION="Watchout.. A new plague was reported. Named ";
    public static final String POST_COMMENTING_NOTIFICATION=" commented on your post subjected ";
    public static final String TIP_POST_NOTIFICATION="Checkout new tip on ";


    public static final String COMMON_FOUND = "Details found";
}
