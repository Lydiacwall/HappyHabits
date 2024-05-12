using Happy_Habits_App.Model;
using System.Text.Json.Serialization;
using static System.Runtime.InteropServices.JavaScript.JSType;

namespace Happy_Habits_App.Forms
{
    public class MoodForm : HabitForm
    {
        [JsonPropertyName("diary")]
        public string? Diary { get; set; } = null;
        [JsonPropertyName("scale")]
        public string? Scale { get; set; } = null;

        public bool IsValid
        {
            get
            {
                return !string.IsNullOrEmpty(UserId) &&
                       !string.IsNullOrEmpty(Date) &&
                       !string.IsNullOrEmpty(Scale) &&
                       (Diary != null);
            }
        }
    }
}
