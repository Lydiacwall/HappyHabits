namespace Happy_Habits_App.Model
{
    public class Food(DateOnly date, string userId, string meal, float quantity, string metric) : Habit(date, userId)
    {
        public string Meal { get; set; } = meal;
        public float Quantity { get; set; } = quantity;
        public string Metric { get; set; } = metric;
    }
}
