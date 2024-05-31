using System.Text.Json.Serialization;

namespace Happy_Habits_App.Forms
{
    public class SymptomStatistics(List<string> symptoms)
    {
        [JsonPropertyName("symptomList")]
        public List<string> symptomList { get; set; } = symptoms;
    }
}
