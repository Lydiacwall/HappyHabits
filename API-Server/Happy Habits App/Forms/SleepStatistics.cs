using System.Text.Json.Serialization;

namespace Happy_Habits_App.Forms
{
    public class SleepStatistics(List<float> sleepDurations, int dailyAverageHours, int dailyAverageMinutes, int differenceInHours, int differenceInMinutes, string mostFrequentQuality)
    {
        [JsonPropertyName("sleepDurations")]
        public List<float> SleepDurations { get; set; } = sleepDurations;

        [JsonPropertyName("dailyAverageHours")]
        public int DailyAverageHours { get; set; } = dailyAverageHours;
        [JsonPropertyName("dailyAverageMinutes")]
        public int DailyAverageMinutes { get; set; } = dailyAverageMinutes;
        public int DifferenceInHours { get; set; } = differenceInHours;
        [JsonPropertyName("differenceInMinutes")]
        public int DifferenceInMinutes { get; set; } = differenceInMinutes;
        [JsonPropertyName("mostFrequentQuality")]
        public string MostFrequentQuality { get; set; } = mostFrequentQuality;
    }
}