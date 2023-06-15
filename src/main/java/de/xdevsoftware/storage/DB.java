package de.xdevsoftware.storage;

import one.microstream.storage.embedded.configuration.types.EmbeddedStorageConfiguration;
import one.microstream.storage.embedded.types.EmbeddedStorageManager;

public class DB {

    public static final EmbeddedStorageManager storageManager;

    public static final DataRoot root = new DataRoot();

    static
    {
        storageManager = EmbeddedStorageConfiguration.Builder()
                .setChannelCount(2)
                .setStorageDirectory("data")
                .createEmbeddedStorageFoundation()
                .createEmbeddedStorageManager(root)
                .start();
    }
}
