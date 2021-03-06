package org.endercore.android.nmod.overrider;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public final class TextOverrider extends BaseOverrider {

    public static final int MODE_REPLACE = 1;
    public static final int MODE_APPEND = 2;
    public static final int MODE_PREPEND = 3;

    public TextOverrider(File overridePath) {
        super(overridePath);
    }

    @Override
    public void performOverride(File root, String name, String mode) throws IOException {
        File sourceFile = new File(root, name);
        FileInputStream inputStream = new FileInputStream(sourceFile);
        byte[] bufferAll = new byte[inputStream.available()];
        int sizeRead = inputStream.read(bufferAll);

        switch (mode) {
            case "replace": {
                FileOutputStream outputStream = new FileOutputStream(new File(overridePath, name), false);
                outputStream.write(bufferAll);
                break;
            }
            case "append": {
                FileOutputStream outputStream = new FileOutputStream(new File(overridePath, name), true);
                outputStream.write(bufferAll);
                break;
            }
            case "prepend": {
                FileInputStream inputOri = new FileInputStream(new File(overridePath, name));

                byte[] bufferOri = new byte[inputOri.available()];
                int bytesReadOri = inputOri.read(bufferOri);
                inputOri.close();
                FileOutputStream outputStream = new FileOutputStream(new File(overridePath, name));
                outputStream.write(bufferAll);
                outputStream.write(bufferOri);
                break;
            }
        }
    }
}
