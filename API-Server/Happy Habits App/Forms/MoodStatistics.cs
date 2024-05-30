using System.Text.Json.Serialization;

namespace Happy_Habits_App.Forms
{
    public class MoodStatistics(Dictionary<string, string> moodMap)
    {
        [JsonPropertyName("moodMap")]
        public Dictionary<string, string> moodMap { get; set; } = moodMap;
    }
}
