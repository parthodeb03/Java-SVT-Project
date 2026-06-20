package org.example;

public class StudentAccount extends Student {

    private String password;
    private boolean isLoggedIn = false;
    private String username;
    public void accountCreation(String name, String username,String id, String dept,
                                int batch, String section,
                                String password, String confirmPass) throws Exception {

        if (!password.equals(confirmPass) || password.isEmpty()) {
            throw new IllegalAccessException("Password Mismatched!");
        }

        setName(name);
        setId(id);
        setDept(dept);
        setBatch(batch);
        setSection(section);
        this.username = username;
        this.password = password;
    }

    public boolean logIn(String username,String password) {
        if (this.password.equals(password) && this.username.equals(username)) {
            isLoggedIn = true;
            return true;
        }
        isLoggedIn = false;
        return false;
    }

    public boolean logout() {
        isLoggedIn = false;
        return false;
    }

    public boolean isLoggedIn() {
        return isLoggedIn;
    }

}