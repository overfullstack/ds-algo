package icake.Arrays.MergeMeetings;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Created by gakshintala on 6/22/16.
 */
public class MergeMeetings {
    public static void main(String[] args) {
        var meetings = new Meeting[5];
        meetings[0] = new Meeting(0, 1);
        meetings[1] = new Meeting(3, 5);
        meetings[2] = new Meeting(4, 8);
        meetings[3] = new Meeting(10, 12);
        meetings[4] = new Meeting(9, 10);

        System.out.println(Arrays.toString(meetings));
        System.out.println(Arrays.toString(mergeMeetings(meetings)));
    }

    private static Meeting[] mergeMeetings(Meeting[] meetings) {
        var mergedMeetings = new Meeting[meetings.length];
        Arrays.sort(meetings, Comparator.comparingInt(m -> m.startTime));
        mergedMeetings[0] = meetings[0];
        var mergedMeetingsIndex = 0;
        for (var i = 1; i < meetings.length; i++) {
            var lastMergedMeeting = mergedMeetings[mergedMeetingsIndex];
            var curMeeting = meetings[i];

            if (lastMergedMeeting.endTime >= curMeeting.startTime) { // Overlapped meeting, Swallow or merge curMeeting into lastMergedMeeting
                lastMergedMeeting.endTime = Math.max(lastMergedMeeting.endTime, curMeeting.endTime);
            } else { // Non-overlapped meeting, push the first one and start another bubble.
                mergedMeetingsIndex++;
                mergedMeetings[mergedMeetingsIndex] = curMeeting;
                // This is independent of previous last merged meeting, but if it's merged with next meetings,
                // it shall be taken care in subsequent iteration
            }
        }
        return mergedMeetings;
    }
}

class Meeting {
    public int startTime;
    public int endTime;

    public Meeting(int startTime, int endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "(" + startTime + "," + endTime + ")";
    }
}
