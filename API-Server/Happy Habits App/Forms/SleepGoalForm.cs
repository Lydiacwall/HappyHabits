using Happy_Habits_App.Model;
using static System.Runtime.InteropServices.JavaScript.JSType;
using System.Text.Json.Serialization;

namespace Happy_Habits_App.Forms
{
    public class SleepGoalForm
    {
        [JsonPropertyName("userId")]
        public string? UserId { get; set; } = null;
        [JsonPropertyName("sleepGoal")]
        public int? SleepGoal { get; set; } = null;

        public bool IsValid
        {
            get
            {
                return !string.IsNullOrEmpty(UserId) &&
                       !(SleepGoal == null);
            }
        }
    }
}
