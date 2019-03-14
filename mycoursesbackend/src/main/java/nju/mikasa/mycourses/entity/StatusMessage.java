package nju.mikasa.mycourses.entity;

public class StatusMessage {
    public static ResponseMessage redirect=new ResponseMessage(302,"Redirect","http://localhost:8087/login");

    public static ResponseMessage loginSuccess = new ResponseMessage(0, "Login success");
    public static ResponseMessage unknownUsername = new ResponseMessage(1001, "Unknown username");
    public static ResponseMessage incorrectPassword = new ResponseMessage(1002, "Incorrect password");
    public static ResponseMessage notAuthenticated = new ResponseMessage(1003, "Not authenticated");

    public static ResponseMessage logoutSuccess = new ResponseMessage(0, "Logout success");

    public static ResponseMessage signUpSuccess = new ResponseMessage(0, "Sign up success");
    public static ResponseMessage verifySuccess = new ResponseMessage(0, "Verify success");
    public static ResponseMessage usernameNotUsed = new ResponseMessage(0, "Username not used");
    public static ResponseMessage emailFormatError = new ResponseMessage(1004, "Email format error");
    public static ResponseMessage userAlreadyExist = new ResponseMessage(1005, "User already exists");
    public static ResponseMessage userNotActivated = new ResponseMessage(1006, "User is not activated");
    public static ResponseMessage verifyFailed = new ResponseMessage(1007, "Verify failed");

    public static ResponseMessage deleteUserSuccess = new ResponseMessage(0, "Delete user success");
    public static ResponseMessage userIsDeleted = new ResponseMessage(2001, "User is deleted");

    public static ResponseMessage updatePasswordSuccess = new ResponseMessage(0, "Update new password success");
    public static ResponseMessage modifyInfoSuccess = new ResponseMessage(0, "Modify info success");
    public static ResponseMessage usernameNotMatch = new ResponseMessage(3001, "Username does not match current session");

    public static ResponseMessage isLoggedIn = new ResponseMessage(0, "You are now logged in");
    public static ResponseMessage needToLogin = new ResponseMessage(1, "Need to login first");

    public static ResponseMessage IOExceptionOccurs = new ResponseMessage(5001, "IOException occurs");
    public static ResponseMessage fileIsNull = new ResponseMessage(5002, "File do not exist");
    public static ResponseMessage fileFormatWrong=new ResponseMessage(5003,"File is no csv");
    public static ResponseMessage withdrawSuccess=new ResponseMessage(0,"Quit course success");


    public static ResponseMessage getSuccess=new ResponseMessage(0,"Get stuff success");
    public static ResponseMessage createSuccess=new ResponseMessage(0,"Create entity success");
    public static ResponseMessage groupEmailSuccess=new ResponseMessage(0,"Send group email success");
    public static ResponseMessage notExist=new ResponseMessage(6001,"Entity not exist");
    public static ResponseMessage fileNotExist=new ResponseMessage(6001,"File not exist");

    public static ResponseMessage cutOffSuccess=new ResponseMessage(0,"Publish cut off success");
    public static ResponseMessage approveSuccess=new ResponseMessage(0,"Approve success");

    public static ResponseMessage electiveSuccess =new ResponseMessage(0,"elective in the class directly");
    public static ResponseMessage electiveSuccessWait =new ResponseMessage(1,"wait to cut off");
    public static ResponseMessage reachLimit=new ResponseMessage(7001,"reach the upper limit");
    public static ResponseMessage alreadyElectived =new ResponseMessage(7002,"already Electived");

    public static ResponseMessage electived=new ResponseMessage(0,"electived");
    public static ResponseMessage notElectived=new ResponseMessage(5005,"Not elective");
    public static ResponseMessage notCutOffed=new ResponseMessage(5006,"not cut offed");

    public static ResponseMessage fail=new ResponseMessage(9999,"something goes wrong");
}
