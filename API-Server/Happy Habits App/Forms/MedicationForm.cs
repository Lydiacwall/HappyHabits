using System.Text.Json.Serialization;

namespace Happy_Habits_App.Forms
{
    public class MedicationForm
    {
        [JsonPropertyName("userId")]
        public string? UserId { get; set; }
        [JsonPropertyName("date")]
        public string? Date { get; set; }
        [JsonPropertyName("medicines")]
        public List<string> Medicines { get; set; } = new List<string>();

        public bool IsValid
        {
            get
            {
                return !string.IsNullOrEmpty(UserId) &&
                       !string.IsNullOrEmpty(Date);
            }
        }
    }
}
