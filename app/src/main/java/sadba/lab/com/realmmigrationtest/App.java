package sadba.lab.com.realmmigrationtest;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import sadba.lab.com.realmmigrationtest.realmmigration.RealmMigrations;

public class App extends Application {

    @Override
    public void onCreate() {

        super.onCreate();
        Realm.init(this);

        final RealmConfiguration configuration = new RealmConfiguration.Builder()
                .name("sample.realm")
                .schemaVersion(3)
                .migration(new RealmMigrations()).build();
        Realm.setDefaultConfiguration(configuration);
        Realm.getInstance(configuration);

        final RealmConfiguration conf = new RealmConfiguration.Builder()
                .name("sample1.realm")
                .schemaVersion(1)
                .migration(new RealmMigrations()).build();
        Realm.setDefaultConfiguration(conf);
        Realm.getInstance(conf);
    }

    @Override
    public void onTerminate() {
        Realm.getDefaultInstance().close();
        super.onTerminate();
    }
}
