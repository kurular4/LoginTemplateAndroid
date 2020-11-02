package com.omer.simplelogin.utils;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class Fragments {
    private FragmentManager mFragmentManager;

    public Fragments(@NonNull FragmentManager fragmentManager) {
        mFragmentManager = fragmentManager;
    }

    public void addFragment(int containerId, Fragment fragment, String tag) {
        addFragment(mFragmentManager, containerId, fragment, tag);
    }

    public void replaceFragment(int containerId, Fragment fragment, String tag) {
        replaceFragment(mFragmentManager, containerId, fragment, tag);
    }

    public void removeFragment(String tag) {
        removeFragment(mFragmentManager, tag);
    }

    public <T> T getFragmentByTag(Class<T> fragmentClass, String tag) {
        return getFragmentByTag(mFragmentManager, fragmentClass, tag);
    }

    public static void addFragment(FragmentManager manager, int containerId, Fragment fragment, String tag) {
        if (!manager.isStateSaved()) {
            manager.beginTransaction().add(containerId, fragment, tag).commit();
        }
    }

    public static void replaceFragment(FragmentManager manager, int containerId, Fragment fragment, String tag) {
        if (!manager.isStateSaved()) {
            manager.beginTransaction().setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE).replace(containerId, fragment, tag).addToBackStack(tag).commit();
        }
    }

    public static void removeFragment(FragmentManager manager, String tag) {
        Fragment fragment = manager.findFragmentByTag(tag);
        if (!manager.isStateSaved() && fragment != null) {
            manager.beginTransaction().remove(fragment).commit();
        }
    }

    public static <T> T getFragmentByTag(FragmentManager manager, Class<T> fragmentClass, String tag) {
        Fragment fragment = manager.findFragmentByTag(tag);

        if (fragment != null) {
            if (fragment.getClass().isAssignableFrom(fragmentClass)) {
                return fragmentClass.cast(fragment);
            } else if (fragment.getClass().getSuperclass().isAssignableFrom(fragmentClass)) {
                return (T) fragment;
            }
        }

        return null;
    }
}
