using Happy_Habits_App.Model;
using static System.Runtime.InteropServices.JavaScript.JSType;
using System.Diagnostics.Metrics;
using System.Xml.Linq;
using System.Text.Json.Serialization;

namespace Happy_Habits_App.Forms
{
    public class FoodDto(string id, string name, string meal, float calories, float protein, float fats, float carbs, float fiber, float quantity, string measurement)
    {
        [JsonPropertyName("id")]
        public string? Id { get; set; } = id;
        [JsonPropertyName("name")]
        public string? Name { get; set; } = name;
        [JsonPropertyName("meal")]
        public string? Meal { get; set; } = meal;
        [JsonPropertyName("calories")]
        public float Calories { get; set; } = calories;
        [JsonPropertyName("protein")]
        public float Protein { get; set; } = protein;
        [JsonPropertyName("fats")]
        public float Fats { get; set; } = fats;
        [JsonPropertyName("carbs")]
        public float Carbs { get; set; } = carbs;
        [JsonPropertyName("fiber")]
        public float Fiber { get; set; } = fiber;
        [JsonPropertyName("quantity")]
        public float Quantity { get; set; } = quantity;
        [JsonPropertyName("measurement")]
        public string? Measurement { get; set; } = measurement;

    }
}
