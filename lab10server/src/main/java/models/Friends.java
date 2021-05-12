package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

@Entity
@IdClass(FriendsPK.class)
public class Friends {
    private int userId;
    private int friendWithId;

    @Id
    @Column(name = "user_id", nullable = false)
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Id
    @Column(name = "friend_with_id", nullable = false)
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

        Friends friends = (Friends) o;

        if (userId != friends.userId) return false;
        if (friendWithId != friends.friendWithId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = userId;
        result = 31 * result + friendWithId;
        return result;
    }
}
