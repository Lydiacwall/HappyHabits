using MongoDB.Libmongocrypt;
using System.Text.Json.Serialization;

namespace Happy_Habits_App.Forms
{
    public class SleepForm : HabitForm
    {
        [JsonPropertyName("time")]
        public required string Time { get; set; }
        [JsonPropertyName("Quality")]
        public required string Quality { get; set; }
        public bool IsValid
        {
            get
            {
                return !string.IsNullOrEmpty(UserId) &&
                       !string.IsNullOrEmpty(Date) &&
                       !string.IsNullOrEmpty(Time) &&
                       (Quality != null);
            }
        }
    }
}
