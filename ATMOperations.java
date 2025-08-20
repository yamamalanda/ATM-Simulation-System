import java.util.HashMap;

public class ATMOperations {
    private HashMap<String, User> users = new HashMap<>();

    public ATMOperations() {
        // Demo accounts
        users.put("12345", new User("12345", "12345", 5000));
        users.put("67890", new User("67890", "67890", 10000));
    }

    public User login(String acc, String pin) {
        if (users.containsKey(acc)) {
            User user = users.get(acc);
            if (user.getPin().equals(pin)) {
                return user;
            }
        }
        return null;
    }
}
