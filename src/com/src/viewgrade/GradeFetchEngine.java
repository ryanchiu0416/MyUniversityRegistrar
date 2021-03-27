package com.src.viewgrade;

/*
    GradeFetchEngine class handles tasks related to fetching grade(s) for students.

    Pattern used: Singleton
*/
public class GradeFetchEngine {
    private static GradeFetchEngine instance = null;
    public static GradeFetchEngine getInstance() {
        if (instance == null) {
            instance = new GradeFetchEngine();
        }
        return instance;
    }
    private GradeFetchEngine() {}

    public String fetchGrade(GradeFetchStrategy fetchType) {
        return fetchType.fetchGrade();
    }
}