package sadba.lab.com.realmmigrationtest.realmmigration;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import sadba.lab.com.realmmigrationtest.model.Person;

public class PersonData extends RealmObject{

    @PrimaryKey
    private String numero;
    private String tel;
    private String couleur;

    public PersonData() {
    }

    public void fill(final Person person){
        setNumero(person.getNumero());
        setTel(person.getTel());
        setCouleur(person.getCouleur());

    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getCouleur() {
        return couleur;
    }

    public void setCouleur(String couleur) {
        this.couleur = couleur;
    }
}
