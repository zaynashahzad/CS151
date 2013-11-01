
class EventDate implements Comparable<EventDate> {

    private int curYear;
    private int curMonth;
    private int curDay;
    public final static String[] months = {
        "January", "February", "March", "April", "May", "June",
        "July", "August", "September", "October", "November", "December"
    };

    public EventDate(int year, int month, int day) {
        curYear = year;
        curMonth = month;
        curDay = day;
    }

    /**
     * Gets the year of this eventDate
     *
     * @return the current year
     */
    public int getYear() {
        return curYear;
    }

    /**
     * Gets the month of this eventDate
     *
     * @return the month day
     */
    public int getMonth() {
        return curMonth;
    }

    /**
     * Gets the day of this eventDate
     *
     * @return the current day
     */
    public int getDay() {
        return curDay;
    }

    /**
     * Turns this eventDate into a string that can be printed out
     *
     * @return a string with the events details
     */
    public String toString() {
        return months[curMonth] + " " + curDay + ", " + curYear;
    }

    /**
     * Determines if two objects are the same
     *
     * @param otherObject the other object to compare to
     * @return true if they are equal, false otherwise
     */
    public boolean equals(Object otherObject) {
        if (otherObject == null) {
            return false;
        }
        if (getClass() != otherObject.getClass()) {
            return false;
        }
        EventDate other = (EventDate) otherObject;
        if (curYear == other.getYear() && curMonth == other.getMonth() && curDay == other.getDay()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Determines the order of two event dates
     *
     * @param other the other eventDate to compare to
     * @return 1 if this one comes before in order, 0 if they are the same, -1
     * if this one comes before
     */
    public int compareTo(EventDate other) {
        if (curYear == other.getYear()) {
            if (curMonth == other.getMonth()) {
                if (curDay == other.getDay()) {
                    return 0;
                } else if (curDay > other.getDay()) {
                    return 1;
                } else {
                    return -1;
                }
            } else if (curMonth > other.getMonth()) {
                return 1;
            } else {
                return -1;
            }
        } else if (curYear > other.getYear()) {
            return 1;
        } else {
            return -1;
        }
    }

    /**
     * calculates a hashcode for each EventDate. Needed for a hashmap, but
     * changed to treemap
     *
     * @return
     */
    public int hashCode() {
        int code = 0;
        String builder = curYear + "" + curMonth + curDay;
        code = Integer.parseInt(builder);
        return code;
    }
}