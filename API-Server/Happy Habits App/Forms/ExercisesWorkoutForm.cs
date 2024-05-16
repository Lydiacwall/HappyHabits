using Happy_Habits_App.Model;
using System.Text.Json.Serialization;

namespace Happy_Habits_App.Forms
{
    public class ExercisesWorkoutForm : HabitForm
    {
        [JsonPropertyName("type")]
        public string? Type { get; set; } = null;
        [JsonPropertyName("time")]
        public string? Time { get; set; } = null;
        [JsonPropertyName("duration")]
        public string? Duration { get; set; } = null;
        [JsonPropertyName("notes")]
        public string? Notes { get; set; } = null;
        [JsonPropertyName("simpleExercises")]
        public List<string> Exercises { get; set; } = new List<string>();

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
