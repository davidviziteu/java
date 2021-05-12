package models;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

public class FriendsPK implements Serializable {
    private int userId;
    private int friendWithId;

    @Column(name = "user_id", nullable = false)
    @Id
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Column(name = "friend_with_id", nullable = false)
    @Id
    public int getFriendWithId() {
        return friendWithId;
    }

    public void setFriendWithId(int friendWithId) {
        this.friendWithId = friendWithId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FriendsPK friendsPK = (FriendsPK) o;

        if (userId != friendsPK.userId) return false;
        if (friendWithId != friendsPK.friendWithId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = userId;
        result = 31 * result + friendWithId;
        return result;
    }
}
