package models;

import javax.persistence.*;

@Entity
public class Messages {
    private int id;
    private String sent;

    @Id
    @GeneratedValue
    public int getId() {
        return id;
    }

    @Basic
    @Column(name = "sent", nullable = true, length = 500)
    public String getSent() {
        return sent;
    }

    public void setSent(String sent) {
        this.sent = sent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Messages messages = (Messages) o;

        if (sent != null ? !sent.equals(messages.sent) : messages.sent != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return sent != null ? sent.hashCode() : 0;
    }
}
