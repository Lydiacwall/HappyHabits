using System.Text.Json.Serialization;

namespace Happy_Habits_App.Forms
{
    public class SleepStatistics(Dictionary<string, string> timeSleepingOfMonth, int dailyAverageHours, int dailyAverageMinutes, int differenceInHours, int differenceInMinutes)
    {
        [JsonPropertyName("timeSleepingOfMonth")]
        public Dictionary<string, string> TimeSleepingOfMonth { get; set; } = timeSleepingOfMonth;

        [JsonPropertyName("dailyAverageHours")]
        public int DailyAverageHours { get; set; } = dailyAverageHours;
        [JsonPropertyName("dailyAverageMinutes")]
        public int DailyAverageMinutes { get; set; } = differenceInMinutes;
        [JsonPropertyName("differenceInHours")]
        public int DifferenceInHours { get; set; } = differenceInHours;
        [JsonPropertyName("differenceInMinutes")]
        public int DifferenceInMinutes { get; set; } = differenceInMinutes;
    }
}