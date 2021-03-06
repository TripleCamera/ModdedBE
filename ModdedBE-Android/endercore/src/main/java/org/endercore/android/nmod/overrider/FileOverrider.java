package org.endercore.android.nmod.overrider;

import org.endercore.android.utils.FileUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public final class FileOverrider extends BaseOverrider {
    public FileOverrider(File overridePath) {
        super(overridePath);
    }

    @Override
    public void performOverride(File root, String name, String mode) throws IOException {
        File sourceFile = new File(root, name);
        FileInputStream inputStream = new FileInputStream(sourceFile);
        FileOutputStream outputStream = new FileOutputStream(new File(overridePath, name));
        FileUtils.copy(inputStream, outputStream);
    }
}
