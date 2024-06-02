using System.Text.Json.Serialization;

namespace Happy_Habits_App.Forms
{
    public class FoodStatistics
    {
        [JsonPropertyName("proteinPercentage")]
        public float ProteinPercentage { get; set; }
        [JsonPropertyName("carbsPercentage")]
        public float CarbsPercentage { get; set; }
        [JsonPropertyName("fiberPercentage")]
        public float FiberPercentage { get; set; }
        [JsonPropertyName("fatsPercentage")]
        public float FatsPercentage { get; set; }
    }
}
