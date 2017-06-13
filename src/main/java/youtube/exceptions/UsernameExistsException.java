package youtube.exceptions;

public class UsernameExistsException extends RuntimeException{
    private String username;
    private String errorMessage;

    public UsernameExistsException(String username) {
        this.username = username;
        this.errorMessage = "The username '" + username + "' already exists.";
    }

    public String getUsername() {
        return username;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
