namespace Happy_Habits_App.Model
{
    public class Mood(string date, string userId, string type, string note) : Habit(date, userId)
    {
        public string Type { get; set; } = type;
        public string Note { get; set; } = note;
    }
}
