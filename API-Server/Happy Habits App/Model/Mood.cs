namespace Happy_Habits_App.Model
{
    public class Mood(DateOnly date, string userId, string diary, string scale) : Habit(date, userId)
    {
        public string Diary { get; set; } = diary;
        public string Scale { get; set; } = scale;
    }
}
