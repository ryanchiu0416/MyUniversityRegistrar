package com.src.schedule;

import java.util.*;
/*
     TimesheetFormatter is a concrete instantiation of FormatStrategy interface.
     Responsible for formatting a schedule into time-sheet format.

     Pattern: Strategy (concrete class)
 */
public class TimesheetFormatter implements FormatStrategy {
    @Override
    public String formatSchedule(List<Course> courses) {
        Map<String, List<Course>> map = new HashMap<>();
        String[] daysArr = new String[] {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday"};
        for (String s : daysArr) {
            map.put(s, new ArrayList<>());
        }

        int maxCount = 0;
        for (Course c : courses) {
            map.get(c.getDayStr()).add(c);
            maxCount = Math.max(map.get(c.getDayStr()).size(), maxCount);
        }
        String singleLine = "*****************************************";

        // sort by time in each day of week
        for (List<Course> lst : map.values()) {
            Collections.sort(lst);
        }

        String isoLine = "";
        String res = "(Report):\n";
        for (int i = 0; i < maxCount; i++) {
            res += singleLine;
            isoLine += singleLine;
        }
        res += "\n";
        res += dayFormatter(map.get("Monday"), "Monday", isoLine);
        res += dayFormatter(map.get("Tuesday"), "Tuesday", isoLine);
        res += dayFormatter(map.get("Wednesday"), "Wednesday", isoLine);
        res += dayFormatter(map.get("Thursday"), "Thursday", isoLine);
        res += dayFormatter(map.get("Friday"), "Friday", isoLine);
        return res;
    }

    private String dayFormatter(List<Course> lst, String day, String line) {
        StringBuilder res = new StringBuilder(day + " : ");
        for (Course c : lst) {
            res.append(" ").append(c.abbrevToString()).append(",");
        }
        res = new StringBuilder(res.substring(0, res.length() - 1));
        res.append("\n").append(line).append("\n");
        return res.toString();
    }
}
