package com.bakingapps.storage;

import io.realm.DynamicRealm;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmMigration;

/**
 * Created by Andi Insanudin on 30/10/2016.
 */

public class RealmUtils {

    private static RealmConfiguration config;

    public static Realm getRealmInstance() {
        if (config == null) {
            config = new RealmConfiguration
                    .Builder()
                    .schemaVersion(0)
                    .migration(new CustomMigration())
                    .build();
        }

        return Realm.getInstance(config);
    }

    private static class CustomMigration implements RealmMigration {

        @Override
        public void migrate(DynamicRealm realm, long oldVersion, long newVersion) {

        }
    }
}
