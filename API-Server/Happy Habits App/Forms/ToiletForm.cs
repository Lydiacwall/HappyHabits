using Happy_Habits_App.Model;
using System.Text.Json.Serialization;
using static System.Runtime.InteropServices.JavaScript.JSType;

namespace Happy_Habits_App.Forms
{
    public class ToiletForm : HabitForm
    {
        [JsonPropertyName("type")]
        public string? Type { get; set; } = null;
        [JsonPropertyName("time")]
        public string? Hour { get; set; } = null;
        [JsonPropertyName("notes")]
        public string? Note { get; set; } = null;

        public bool IsValid
        {
            get
            {
                return !string.IsNullOrEmpty(UserId) &&
                       !string.IsNullOrEmpty(Date) &&
                       !string.IsNullOrEmpty(Type) &&
                       !string.IsNullOrEmpty(Hour) &&
                       (Note != null);
            }
        }
    }
}
