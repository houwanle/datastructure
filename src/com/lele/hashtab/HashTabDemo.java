package com.lele.hashtab;

/**
 * author: hwl
 * date: 2020/10/26 21:33
 * version: 1.0.0
 * modified by:
 * description:
 */
public class HashTabDemo {
    public static void main(String[] args) {

    }
}

// 表示一个雇员
class Emp {
    public int id;
    public String name;
    public Emp next;// next默认为 null

    public Emp(int id, String name) {
        super();
        this.id = id;
        this.name = name;
    }
}
