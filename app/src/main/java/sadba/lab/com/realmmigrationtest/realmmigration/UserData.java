package sadba.lab.com.realmmigrationtest.realmmigration;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import sadba.lab.com.realmmigrationtest.model.User;

public class UserData extends RealmObject{

    @PrimaryKey
    private String email;

    private String name;

    private int age;

    private String adresse;

    public UserData() {
    }

    public void fill(final User user) {
        setEmail(user.getEmail());
        setName(user.getName());
        setAge(user.getAge());
        setAdresse(user.getAdresse());
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(final int age) {
        this.age = age;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }
}
