package ru.study.mantisbt.model;

import com.google.common.collect.ForwardingSet;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Dreamer on 20.12.2016.
 */
public class Users extends ForwardingSet<UserData> {
    private Set<UserData> delegate;

    public Users(List<UserData> users) {
        this.delegate = new HashSet<UserData>(users);
    }

    @Override
    protected Set<UserData> delegate() {
        return delegate;
    }

}
