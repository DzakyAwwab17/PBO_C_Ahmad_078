class Admin {
    String username = "admin078";
    String password = "password078";

    public boolean login(String inputUsername, String inputPassword) {
        return inputUsername.equals(username) && inputPassword.equals(password);
    }
    public void displayInfo(){
 }
}