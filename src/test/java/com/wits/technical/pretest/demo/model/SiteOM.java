package com.wits.technical.pretest.demo.model;

public class SiteOM {
    public static Site newSite(Long id, String location) {
        Site newSite = new Site();
        newSite.setId(id);
        newSite.setLocation(location);
        return newSite;
    }
}
