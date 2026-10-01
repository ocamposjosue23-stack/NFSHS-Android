package com.psxports.nfshs;

import android.app.Activity;
import android.content.res.AssetManager;
import android.os.Bundle;
import java.io.*;

public class MainActivity extends Activity {

    static {
        System.loadLibrary("SDL2");
        System.loadLibrary("main");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        copyAssetsToInternalStorage();
    }

    private void copyAssetsToInternalStorage() {
        File internalDir = getFilesDir();
        if (internalDir != null) {
            copyAssetFolder(getAssets(), "", internalDir.getAbsolutePath());
        }
    }

    private static boolean copyAssetFolder(AssetManager assetManager, String fromAssetPath, String toPath) {
        try {
            String[] files = assetManager.list(fromAssetPath);
            if (files == null) return false;
            new File(toPath).mkdirs();
            boolean success = true;

            for (String file : files) {
                String subFrom = fromAssetPath.isEmpty() ? file : fromAssetPath + "/" + file;
                String subTo = toPath + "/" + file;

                if (file.contains(".")) {
                    success &= copyAssetFile(assetManager, subFrom, subTo);
                } else {
                    success &= copyAssetFolder(assetManager, subFrom, subTo);
                }
            }
            return success;
        } catch (Exception e) {
            return false;
        }
    }

    private static boolean copyAssetFile(AssetManager assetManager, String fromAssetPath, String toPath) {
        File outFile = new File(toPath);
        if (outFile.exists() && outFile.length() > 0) return true;

        try (InputStream in = assetManager.open(fromAssetPath);
             OutputStream out = new FileOutputStream(outFile)) {
            
            byte[] buffer = new byte[1024 * 64];
            int read;
            while ((read = in.read(buffer)) != -1) {
                out.write(buffer, 0, read);
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
