public class Account {
    //propriete
    private String email;
    private String password;

    //constructeur
    public Account() {}
    public Account(String email, String password) {
        this.email = email;
        this.password = password;
    }

    //get-set
    public String getEmail() { return this.email; }
    public void setEmail(String email) { this.email = email; }
    public String getPassword() { return this.password; }
    public void setPassword(String password) { this.password = password; }

    //method

}
