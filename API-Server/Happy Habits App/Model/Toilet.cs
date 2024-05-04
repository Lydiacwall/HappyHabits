namespace Happy_Habits_App.Model
{
    public class Toilet(DateOnly date, string userId, string type, string hour, string note) : Habit(date, userId)
    {
        public string Type { get; set; } = type;
        public string Note { get; set; } = note;
        public string Hour { get; set; } = hour;
    }
}
