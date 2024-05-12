using MongoDB.Bson.Serialization.Attributes;

namespace Happy_Habits_App.Model
{
    public class Mood(DateOnly date, string userId, string diary, string scale) : Habit(date, userId)
    {
        [BsonElement("Diary")]
        public string Diary { get; set; } = diary;
        [BsonElement("Scale")]
        public string Scale { get; set; } = scale;
    }
}
