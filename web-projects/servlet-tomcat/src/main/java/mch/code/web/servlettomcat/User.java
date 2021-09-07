package mch.code.web.servlettomcat;

import java.util.Date;

public class User {
    private Integer idCounter; // количество созданных пользователей
    private String name;
    private Date birthday;

    public Integer getId() {
        return idCounter;
    }

    public String getName() {
        return name;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public void setId(Integer id) {
        this.idCounter = id;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + idCounter +
                ", name='" + name + '\'' +
                ", birthday=" + birthday +
                '}';
    }
}
