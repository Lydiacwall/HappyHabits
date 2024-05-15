using MongoDB.Bson.Serialization.Attributes;
using System.Text.Json.Serialization;

namespace Happy_Habits_App.Forms
{
    public class MedicineForm
    {
        [JsonPropertyName("userId")]
        public string? UserId { get; set; }
        [JsonPropertyName("name")]
        public string? Name { get; set; }
        [JsonPropertyName("dosageQuantity")]
        public float? DosageQuantity { get; set; }
        [JsonPropertyName("dosageUnitMeasurement")]
        public string? DosageUnitMeasurement { get; set; }
        [JsonPropertyName("startDay")]
        public string? StartDay { get; set; }
        [JsonPropertyName("endDay")]
        public string? EndDay { get; set; }
        [JsonPropertyName("timesShouldBeTaken")]
        public int TimesShouldBeTaken { get; set; }
        [JsonPropertyName("notes")]
        public string? Notes = null;

        public bool IsValid
        {
            get
            {
                return !string.IsNullOrEmpty(UserId) &&
                       !string.IsNullOrEmpty(Name) &&
                       !string.IsNullOrEmpty(DosageUnitMeasurement) &&
                       !string.IsNullOrEmpty(StartDay) &&
                       !string.IsNullOrEmpty(EndDay) &&
                       TimesShouldBeTaken > 0;
            }
        }
    }
}
