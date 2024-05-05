using Happy_Habits_App.Model;
using System.Text.Json.Serialization;
using static System.Runtime.InteropServices.JavaScript.JSType;

namespace Happy_Habits_App.Forms
{
    public class ToiletForm : HabitForm
    {
        [JsonPropertyName("type")]
        public string? Type { get; set; } = null;
        [JsonPropertyName("hour")]
        public string? Hour { get; set; } = null;
        [JsonPropertyName("note")]
        public string? Note { get; set; } = null;

        public bool IsValid
        {
            get
            {
                return !string.IsNullOrEmpty(UserId) &&
                       Date.Equals(null) &&
                       !string.IsNullOrEmpty(Type) &&
                       !string.IsNullOrEmpty(Hour) &&
                       !string.IsNullOrEmpty(Note);
            }
        }
    }
}
