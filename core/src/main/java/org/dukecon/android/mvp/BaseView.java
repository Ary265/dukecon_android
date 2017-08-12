package org.dukecon.android.mvp;

public interface BaseView<T> {

    void setPresenter(T presenter);

    boolean isActive();
}
