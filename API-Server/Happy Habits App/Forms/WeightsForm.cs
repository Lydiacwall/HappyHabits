using Happy_Habits_App.Model;
using MongoDB.Bson.Serialization.Attributes;
using System.Text.Json.Serialization;

namespace Happy_Habits_App.Forms
{
    public class WeightsForm : HabitForm
    {
        [JsonPropertyName("type")]
        public string? Type { get; set; } = null;
        [JsonPropertyName("time")]
        public string? Time { get; set; } = null;
        [JsonPropertyName("duration")]
        public string? Duration { get; set; } = null;
        [JsonPropertyName("notes")]
        public string? Notes { get; set; } = null;
        [JsonPropertyName("exercises")]
        public List<Exercise> Exercises { get; set; } = new List<Exercise>();

        public bool IsValid
        {
            get
            {
                return !string.IsNullOrEmpty(UserId) &&
                       !string.IsNullOrEmpty(Date) &&
                       !string.IsNullOrEmpty(Time) &&
                       !string.IsNullOrEmpty(Duration) &&
                       Exercises.Count > 0;
            }
        }
    }
}
