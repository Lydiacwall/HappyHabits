using MongoDB.Libmongocrypt;
using System.Text.Json.Serialization;

namespace Happy_Habits_App.Forms
{
    public class FastActivityForm : HabitForm
    {
        [JsonPropertyName("type")]
        public string? Type { get; set; } = null;
        [JsonPropertyName("time")]
        public string? Time { get; set; } = null;
        [JsonPropertyName("duration")]
        public string? Duration { get; set; } = null;
        [JsonPropertyName("notes")]
        public string? Notes { get; set; } = null;
        [JsonPropertyName("quantity")]
        public float Quantity { get; set; }
        [JsonPropertyName("elevation")]
        public float Elevation { get; set; }

        public bool IsValid
        {
            get
            {
                return !string.IsNullOrEmpty(UserId) &&
                       !string.IsNullOrEmpty(Date) &&
                       !string.IsNullOrEmpty(Time) &&
                       !string.IsNullOrEmpty(Duration);
            }
        }
    }
}
