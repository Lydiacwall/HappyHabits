using System.Text.Json.Serialization;

namespace Happy_Habits_App.Forms
{
    public abstract class HabitForm
    {
        [JsonPropertyName("habitId")]
        public string? HabitId { get; set; } = null;
        [JsonPropertyName("userId")]
        public required string UserId { get; set; }
        [JsonPropertyName("date")]
        public required string Date { get; set; }
    }
}
