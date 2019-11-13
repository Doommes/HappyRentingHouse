package com.ldq.happyrentinghouse;

import android.app.Application;
import android.content.res.AssetManager;
import android.system.ErrnoException;
import android.system.Os;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.corpus.io.IIOAdapter;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class MyApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        initHanLP();
    }

    private void initHanLP()
    {
        try
        {
            Os.setenv("HANLP_ROOT", "", true);
        }
        catch (ErrnoException e)
        {
            throw new RuntimeException(e);
        }
        final AssetManager assetManager = getAssets();
        HanLP.Config.IOAdapter = new IIOAdapter()
        {
            @Override
            public InputStream open(String path) throws IOException
            {
                return assetManager.open(path);
            }

            @Override
            public OutputStream create(String path) throws IOException
            {
                throw new IllegalAccessError("不支持写入" + path + "！请在编译前将需要的数据放入app/src/main/assets/data");
            }
        };
    }
}
