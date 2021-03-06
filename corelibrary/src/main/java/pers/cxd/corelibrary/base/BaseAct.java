package pers.cxd.corelibrary.base;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public abstract class BaseAct extends AppCompatActivity implements UiComponent, FragmentFinder {

    protected final String TAG = "DEBUG_CXD_"+ this.getClass().getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        setUp(savedInstanceState);
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Nullable
    @Override
    public <T extends Fragment> T findFragment(FragmentManager manager, Class<T> clazz, int position) {
        return UiComponentPlugins.sSimpleFinder.findFragment(manager, clazz, position);
    }

    @Override
    public <T extends Fragment> T findOrCreateFmt(Class<T> clazz, int position) {
        return UiComponentPlugins.findOrCreateFmt(getSupportFragmentManager(), clazz, position, this);
    }

}
