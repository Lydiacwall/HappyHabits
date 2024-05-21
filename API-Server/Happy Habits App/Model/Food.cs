namespace Happy_Habits_App.Model
{
    public class Food(DateOnly date, string userId, string name, string meal, float calories, float protein, float fats, float carbs, float fiber, float quantity, string measurement) : Habit(date, userId)
    {
        public string Name { get; set; } = name;
        public string Meal { get; set; } = meal;
        public float Calories { get; set; } = calories;
        public float Protein { get; set; } = protein;
        public float Fats { get; set; } = fats;
        public float Carbs { get; set; } = carbs;
        public float Fiber { get; set; } = fiber;
        public float Quantity { get; set; } = quantity;
        public string Measurement { get; set; } = measurement;
    }
}
