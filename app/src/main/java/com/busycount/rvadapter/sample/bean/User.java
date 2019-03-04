package com.busycount.rvadapter.sample.bean;


import com.busycount.rvadapter.diff.IDiffComparable;


/**
 * User bean
 * <p>
 * 2018/11/19 | Count.C | Created
 */
public class User implements IDiffComparable<User> {
    public int id;
    public String name;
    public int a;
    public int b;
    public int c;
    public int d;
    public int e;
    public int f;

    public User(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        return id == user.id;

    }

    @Override
    public int hashCode() {
        return id;
    }


    @Override
    public boolean areItemsTheSame(User other) {
        return id == other.id;
    }

    @Override
    public boolean areContentsTheSame(User other) {
        return name.equals(other.name);
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", name='" + name + '\'' + '}';
    }
}
