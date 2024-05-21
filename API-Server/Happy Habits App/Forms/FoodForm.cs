using System.Diagnostics.Metrics;
using System.Text.Json.Serialization;

namespace Happy_Habits_App.Forms
{
    public class FoodForm : HabitForm
    {
        [JsonPropertyName("specificFoodsForms")]
        public List<FoodDto> Foods { get; set; } = new List<FoodDto>();
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
