
package utils;





import model.Userdata;

public class Session {

    private static Userdata loggedInUser = null;

    private Session() {}

    public static void login(Userdata user) {
        loggedInUser = user;
    }

    public static void logout() {
        loggedInUser = null;
    }

    public static boolean isLoggedIn() {
        return loggedInUser != null;
    }

    public static Userdata getUser() {
        return loggedInUser;
    }
    
    public static Userdata getCurrentUser() {
        return loggedInUser;
    }
    
    public static void setCurrentUser(Userdata user) {
        loggedInUser = user;
    }

    public static int getCustomerId() {
        return loggedInUser != null ? loggedInUser.getuser_id() : -1;
    }
    public static int getUserId() {
        return loggedInUser.getuser_id();
    }
    
    
}