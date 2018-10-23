package sadba.lab.com.realmmigrationtest.realmmigration;

import io.realm.DynamicRealm;
import io.realm.RealmMigration;
import io.realm.RealmObjectSchema;
import io.realm.RealmSchema;

public class RealmMigrations implements RealmMigration{

    @Override
    public void migrate(DynamicRealm realm, long oldVersion, long newVersion) {
        final RealmSchema schema = realm.getSchema();

        if (oldVersion == 1) {
            final RealmObjectSchema userSchema = schema.get("UserData");
            userSchema.addField("age", int.class);
            oldVersion++;
        }

        if (oldVersion == 2) {
            final RealmObjectSchema userSchema = schema.get("UserData");
            userSchema.addField("adresse", String.class);
            oldVersion++;
        }

        if (oldVersion == 1){
            final RealmObjectSchema personSchema = schema.get("PersonData");
            personSchema.addField("couleur", String.class);
            oldVersion++;
        }
    }
}
