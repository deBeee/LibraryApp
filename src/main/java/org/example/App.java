package org.example;

import org.apache.commons.codec.digest.DigestUtils;
import org.example.core.Core;

public class App {
    public static void main(String[] args) {
        //System.out.println(DigestUtils.md5Hex("dominik" + "c5H0t1^%%Xe4i6f5eKFkM4WQ&ZOwSXwmO1FvMZ5q"));
        //System.out.println(DigestUtils.md5Hex("mateusz" + "c5H0t1^%%Xe4i6f5eKFkM4WQ&ZOwSXwmO1FvMZ5q"));
        //System.out.println(DigestUtils.md5Hex("michal" + "c5H0t1^%%Xe4i6f5eKFkM4WQ&ZOwSXwmO1FvMZ5q"));
        //System.out.println(DigestUtils.md5Hex("maciek" + "c5H0t1^%%Xe4i6f5eKFkM4WQ&ZOwSXwmO1FvMZ5q"));
        //System.out.println(DigestUtils.md5Hex("natalia" + "c5H0t1^%%Xe4i6f5eKFkM4WQ&ZOwSXwmO1FvMZ5q"));
        //hasła użytkowników są takie same jak loginy
        Core.getInstance().start();
    }
}