package io.github.batetolast1.junit5.healthycoderapp;

public class ActivityCalculator {

    // Test Driven Development Example
    // 1. write simple method
    // 2. start writing unit tests that will fail
    // public static String rateActivityLevel(int weeklyCardioMin, int weeklyWorkoutSessions) {
    //     if (weeklyCardioMin < 0) {
    //         throw new RuntimeException();
    //     }
    //     return "";
    // }

    // 3. implement method to satisfy requirements
    private static final int WORKOUT_DURATION_MIN = 45;

    public static String rateActivityLevel(int weeklyCardioMin, int weeklyWorkoutSessions) {
        if (weeklyCardioMin < 0 || weeklyWorkoutSessions < 0) {
            throw new RuntimeException("Input below 0");
        }

        int totalMinutes = weeklyCardioMin + weeklyWorkoutSessions * WORKOUT_DURATION_MIN;
        double avgDailyActivityMinutes = totalMinutes / 7.0;

        if (avgDailyActivityMinutes < 20) {
            return "bad";
        } else if (avgDailyActivityMinutes < 40) {
            return "average";
        }

        return "good";
    }
}
