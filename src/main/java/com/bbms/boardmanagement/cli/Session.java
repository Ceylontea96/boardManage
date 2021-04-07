package com.bbms.boardmanagement.cli;

import com.bbms.boardmanagement.cli.board.domain.Post;
import com.bbms.boardmanagement.cli.user.domain.User;

public class Session {

    private User userNow;
    private Post postNow;




    public User getUserNow() {
        return userNow;
    }

    public void setUserNow(User userNow) {
        this.userNow = userNow;
    }

    public Post getPostNow() {
        return postNow;
    }

    public void setPostNow(Post postNow) {
        this.postNow = postNow;
    }
}
