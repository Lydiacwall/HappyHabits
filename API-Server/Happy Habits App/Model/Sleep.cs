using MongoDB.Bson.Serialization.Attributes;

namespace Happy_Habits_App.Model
{
    public class Sleep(DateOnly date, string userId, string time, string quality) : Habit(date, userId)
    {
        [BsonElement("Time")]
        public string Time { get; set; } = time;
        [BsonElement("Quality")]
        public string Quality { get; set; } = quality;
    }
}
