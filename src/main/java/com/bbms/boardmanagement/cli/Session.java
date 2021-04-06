package com.bbms.boardmanagement.cli;

import com.bbms.boardmanagement.cli.user.domain.User;

public class Session {

    private User userNow;




    public User getUserNow() {
        return userNow;
    }

    public void setUserNow(User userNow) {
        this.userNow = userNow;
    }
}
