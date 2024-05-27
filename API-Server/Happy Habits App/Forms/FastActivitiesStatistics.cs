using System.Text.Json.Serialization;

namespace Happy_Habits_App.Forms
{
    public class FastActivitiesStatistics
    {
        [JsonPropertyName("averageDuration")]
        public double AverageDuration { get; set; }
        [JsonPropertyName("averageElevation")]
        public double AverageElevation { get; set; }
        [JsonPropertyName("averageQuantity")]
        public double AverageQuantity { get; set; }
        [JsonPropertyName("totalQuantity")]
        public float TotalQuantity { get; set; }
        [JsonPropertyName("totalWorkouts")]
        public int TotalWorkouts { get; set; }
    }
}
