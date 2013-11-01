
/**
 * Zayna Shahzad
 * An event that will be stored in the calendar and scheduled for a specific date
 */
class Event implements Comparable<Event> {

    private int start;
    private int end;
    private String title;

    public Event(int start, int end, String title) {
        this.start = start;
        this.end = end;
        this.title = title;
    }

    public int getStart() {
        return this.start;
    }

    public int getEnd() {
        return this.end;
    }

    public String getTitle() {
        return this.title;
    }

    public int compareTo(Event other) {
        if (this.start == other.getStart()) {
            if (this.getEnd() == other.getEnd()) {
                return 0;
            } else if (this.getEnd() > other.getEnd()) {
                return 1;
            } else {
                return -1;
            }
        } else if (this.getStart() > other.getStart()) {
            return 1;
        } else {
            return -1;
        }
    }
}